#FROM eclipse-temurin:25-jdk AS build
FROM eclipse-temurin:25-jdk-jammy AS build
WORKDIR /app
COPY . .
# If Maven:
RUN chmod +x mvnw
RUN ./mvnw -DskipTests package
# If Gradle:
#RUN ./gradlew clean bootJar -x test
FROM eclipse-temurin:25-jre
WORKDIR /app
# If Gradle bootJar output:
#COPY --from=build /app/build/libs/*.jar app.jar
# If Maven output, comment line above and use:
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]