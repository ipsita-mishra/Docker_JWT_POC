FROM ubuntu
WORKDIR /home/app
RUN apt-get update -y
RUN apt install openjdk-8-jdk maven -yy
COPY . /home/app 
RUN mvn install -Dmaven.test.skip=true
EXPOSE 8099
# RUN cd target
CMD ["/bin/sh", "-c", "java -jar target/bankservices*.jar"]
