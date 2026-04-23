#!/bin/bash
set -e
cd /home/ec2-user/AWS-Spring-Project

echo "🔄 최신 코드 가져오기"
git pull origin main

echo "🐳 도커 이미지 빌드"
docker build -t first-project .

echo "🛑 기존 컨테이너 중지 및 삭제"
docker stop first-project-container || true
docker rm first-project-container || true

echo "🚀 새 컨테이너 실행"
docker run -d --name first-project-container -p 8081:8080 first-project

echo "✅ 도커 배포 완료"