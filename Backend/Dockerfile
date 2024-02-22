FROM maven:3.9.5
WORKDIR /usr/src/sweng
COPY . /usr/src/sweng
RUN mvn clean install -DskipTests
ENTRYPOINT ["mvn", "spring-boot:run"]