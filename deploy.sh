#!/bin/bash
set -e

IMAGE_NAME="pangja/aws-spring-project:latest"
BLUE_PORT=8081
GREEN_PORT=8082
CONTAINER_BLUE="first-project-blue"
CONTAINER_GREEN="first-project-green"
NGINX_CONF="/etc/nginx/nginx.conf"

echo "🐳 최신 Docker 이미지 가져오기"
docker pull $IMAGE_NAME

echo "📌 현재 운영 포트 확인"
CURRENT_PORT=$(grep "proxy_pass" $NGINX_CONF | grep -oE '[0-9]+' | tail -1)

if [ "$CURRENT_PORT" == "$BLUE_PORT" ]; then
  NEW_PORT=$GREEN_PORT
  NEW_CONTAINER=$CONTAINER_GREEN
  OLD_CONTAINER=$CONTAINER_BLUE
else
  NEW_PORT=$BLUE_PORT
  NEW_CONTAINER=$CONTAINER_BLUE
  OLD_CONTAINER=$CONTAINER_GREEN
fi

echo "현재 운영 포트: $CURRENT_PORT"
echo "새 배포 포트: $NEW_PORT"

echo "🧹 새 포트에 남아있는 기존 컨테이너 제거"
docker stop $NEW_CONTAINER || true
docker rm $NEW_CONTAINER || true

echo "🚀 새 컨테이너 실행"
docker run -d \
  --name $NEW_CONTAINER \
  -p $NEW_PORT:8080 \
  --env-file /etc/first-project.env \
  -e SPRING_PROFILES_ACTIVE=prod \
  --restart always \
  $IMAGE_NAME

echo "🩺 헬스 체크 시작"
for i in {1..10}
do
  if curl -f http://localhost:$NEW_PORT/health > /dev/null 2>&1; then
    echo "✅ 헬스 체크 성공"
    break
  fi

  echo "⏳ 서버 준비 대기 중... ($i/10)"
  sleep 3

  if [ $i -eq 10 ]; then
    echo "❌ 헬스 체크 실패"
    docker logs $NEW_CONTAINER

    echo "🧹 실패한 새 컨테이너 제거"
    docker stop $NEW_CONTAINER || true
    docker rm $NEW_CONTAINER || true

    exit 1
  fi
done

echo "🔁 Nginx 포트 전환"
sudo sed -i "s|proxy_pass http://127.0.0.1:[0-9]*;|proxy_pass http://127.0.0.1:$NEW_PORT;|" $NGINX_CONF

echo "🔄 Nginx reload"
sudo nginx -t
sudo systemctl reload nginx

echo "🛑 이전 컨테이너 종료"
docker stop $OLD_CONTAINER || true
docker rm $OLD_CONTAINER || true

echo "✅ Blue-Green 이미지 기반 무중단 배포 완료"