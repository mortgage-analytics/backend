FROM maven:3.9.5 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn -Dmaven.test.skip package

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /usr/app/target/Backend-0.0.1-SNAPSHOT.jar /app/runner.jar
COPY ./src/main/resources/combined_report-1707267132094.xlsx /Backend/src/main/resources/combined_report-1707267132094.xlsx
ENTRYPOINT java -jar /app/runner.jar