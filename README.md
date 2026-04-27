# AWS Spring Boot CI/CD 프로젝트

## 1. 프로젝트 개요

Spring Boot 애플리케이션을 AWS EC2 환경에 배포하고, Docker와 GitHub Actions를 활용해 이미지 기반 CI/CD 파이프라인을 구축한 프로젝트입니다.

단순 배포를 넘어서 Nginx Reverse Proxy, HTTPS, Docker Hub, Blue-Green 배포 전략을 적용하여 실무형 무중단 배포 구조를 구현했습니다.

---

## 2. 사용 기술

### Backend
- Java 17
- Spring Boot
- Gradle

### Infrastructure
- AWS EC2
- Nginx
- HTTPS / SSL
- Docker
- Docker Hub

### CI/CD
- GitHub Actions
- Docker Buildx
- Blue-Green Deployment

---

## 3. 아키텍처

```text
Developer
   ↓
GitHub Push
   ↓
GitHub Actions
   ↓
Docker Image Build
   ↓
Docker Hub Push
   ↓
EC2 Pull Image
   ↓
Docker Container Run
   ↓
Nginx Reverse Proxy
   ↓
User