FROM eclipse-temurin:21-jdk as builder

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENV CACHE_TIME_INTERVAL_CONTROLLER=15 \
    CACHE_TIME_INTERVAL_SERVICE=5 \
    CACHE_TIME_MEASURE_CONTROLLER=MINUTE \
    CACHE_TIME_MEASURE_SERVICE=HOUR

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
