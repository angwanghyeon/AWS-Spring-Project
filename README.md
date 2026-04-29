# AWS Spring Boot CI/CD 프로젝트
## 아키텍처 다이어그램
![alt text](<프로젝트 배포 흐름 구도.png>)
> GitHub Actions → Docker Hub → EC2 → Nginx → Blue-Green 기반 무중단 배포 CI/CD 파이프라인

---

## 1. 프로젝트 개요

Spring Boot 애플리케이션을 AWS EC2 환경에 배포하고,  
Docker와 GitHub Actions를 활용해 **이미지 기반 CI/CD 파이프라인**을 구축한 프로젝트입니다.

단순 배포를 넘어서 다음을 구현했습니다:

- Nginx Reverse Proxy 기반 트래픽 제어
- Blue-Green 배포를 통한 무중단 서비스 운영
- Health Check 기반 배포 검증
- 배포 실패 시 자동 롤백 구조

👉 실무에서 사용하는 **안정적인 배포 환경을 직접 구현**하는 것을 목표로 했습니다.

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
- SSH 기반 자동 배포

---

## 3. 아키텍처

```text
Developer
   ↓
Git Push
   ↓
GitHub Actions
   ↓
Docker Image Build & Push
   ↓
EC2 (SSH 접속)
   ↓
git pull + deploy.sh 실행
   ↓
Docker Container 실행 (Blue / Green)
   ↓
Health Check 검증
   ↓
Nginx 트래픽 전환
   ↓
User

```
4. 서비스 URL
   API: http://52.79.195.249.nip.io
   Health Check: http://52.79.195.249.nip.io/health
   Swagger: http://52.79.195.249.nip.io/swagger-ui/index.html

## 8. API 설계 개선

### 8.1 DTO 기반 요청 처리

- Map 대신 DTO를 사용하여 명확한 요청 구조 설계
- 유지보수성과 가독성 향상

### 8.2 Validation 적용

- `@NotBlank`를 사용하여 입력값 검증
- 잘못된 요청에 대해 400 응답 처리

### 8.3 예외 처리 (Exception Handling)

- GlobalExceptionHandler를 통해 예외를 일관된 JSON 형식으로 반환

#### Validation 실패

```json
{
  "status": 400,
  "message": "내용은 필수입니다."
}