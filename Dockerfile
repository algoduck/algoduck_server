FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

# 로그 저장 디렉토리 지정
WORKDIR /app

# 애플리케이션 실행 시 로그가 /app/algoduck.log 로 기록됨
ENTRYPOINT ["java", "-jar", "/app.jar"]
