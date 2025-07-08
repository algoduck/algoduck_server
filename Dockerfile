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

# Scouter Agent 복사
COPY scouter-agent.java /scouter-agent

# 설정 파일 복사
COPY src/main/resources/application.yml /app/application.yml
COPY .env /app/.env

# 보기 좋은 PS1 설정
RUN echo "PS1='[\\u@\\h \\w]# '" >> /root/.bashrc

# Scouter 환경변수 기대값
ENV SCOUTER_SERVER_ADDR=127.0.0.1
ENV SCOUTER_OBJ_NAME=algoduck_was

# 애플리케이션 실행
ENTRYPOINT ["/bin/bash", "-c", "\
  set -a && source /app/.env && set +a && \
  printf 'scouter.server.addr=%s\n' \"$SCOUTER_SERVER_ADDR\" > /scouter-agent/conf/scouter.conf && \
  printf 'obj_name=%s\n' \"$SCOUTER_OBJ_NAME\" >> /scouter-agent/conf/scouter.conf && \
  java -javaagent:/scouter-agent/scouter.agent.jar \
  -Dscouter.config=/scouter-agent/conf/scouter.conf \
  -jar /app/app.jar"]
