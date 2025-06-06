# 베이스 이미지로 Java 17 사용
FROM openjdk:17

# JAR 파일 이름을 빌드 시 인자로 전달받기
ARG JAR_FILE=build/libs/algoduck-0.0.1-SNAPSHOT.jar

# JAR 파일을 이미지에 복사
COPY ${JAR_FILE} app.jar

COPY src/main/resources/application.yml /app/application.yml
COPY .env /app/.env

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]