FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=build /app/target/HospitalApp.jar myapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapp.jar"]