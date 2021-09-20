FROM adoptopenjdk/openjdk8:ubi
COPY ./target/bankservices-0.0.1-SNAPSHOT.jar bankservices-0.0.1-SNAPSHOT.jar
EXPOSE 8099
ENTRYPOINT ["java","-jar","bankservices-0.0.1-SNAPSHOT.jar"]