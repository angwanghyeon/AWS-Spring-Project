#!/bin/bash
set -e

echo "🐳 최신 Docker 이미지 가져오기"
docker pull pangja/aws-spring-project:latest

echo "🛑 기존 컨테이너 중지 및 삭제"
docker stop first-project-container || true
docker rm first-project-container || true

echo "🚀 새 컨테이너 실행"
docker run -d \
  --name first-project-container \
  -p 8081:8080 \
  pangja/aws-spring-project:latest

echo "✅ 이미지 기반 배포 완료"