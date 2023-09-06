FROM gradle:jdk11

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradle/wrapper ./gradle/wrapper
COPY src ./src

RUN gradle build

EXPOSE 8081

CMD ["java", "-jar", "build/libs/sparklemotion-0.0.1-SNAPSHOT.jar"]
