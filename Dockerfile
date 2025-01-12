FROM eclipse-temurin:17
WORKDIR /opt/accounts/
RUN mkdir api
COPY ./api ./api
CMD ["ls"]
CMD ["java", "-jar", "./api/tar/api-1.0-SNAPSHOT.jar"]