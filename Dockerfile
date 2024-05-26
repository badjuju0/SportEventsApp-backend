# Stage 1: Build the applicationFROM gradle:7.6.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/srcWORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon -Dorg.gradle.jvmargs="-Xmx256m"
# Stage 2: Run the applicationFROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /app/diploma-back.jarENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-jar", "/app/diploma-back.jar"]
