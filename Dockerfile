# Java + 유틸리티 포함된 slim 베이스 이미지
FROM openjdk:17-slim

# 필요한 유틸리티 설치: bash, vi, clear, ps, etc.
RUN apt-get update && apt-get install -y --no-install-recommends \
    bash \
    procps \
    curl \
    less \
    vim-tiny \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 이름을 빌드 시 인자로 전달받기
ARG JAR_FILE=build/libs/algoduck-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 설정 파일 복사
COPY src/main/resources/application.yml /app/application.yml
COPY .env /app/.env

# 보기 좋은 PS1 설정
RUN echo "PS1='[\\u@\\h \\w]# '" >> /root/.bashrc

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
