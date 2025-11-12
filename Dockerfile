FROM eclipse-temurin:21-jre-jammy
ADD target/HospitalApp.jar myapp.jar
ENTRYPOINT ["java","-jar","myapp.jar"]
