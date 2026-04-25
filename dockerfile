FROM eclipse-temurin:17-jdk-jammy
COPY . .
# Esta línea es la que soluciona el error 126 (Permission denied)
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]