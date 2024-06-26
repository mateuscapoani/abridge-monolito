FROM maven:3.8.3-openjdk-17 AS build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn clean package -DskipTests

FROM openjdk:17-alpine 
ARG JAR_FILE=/usr/app/target/*.jar
COPY --from=build $JAR_FILE /app/app.jar
EXPOSE 8080 9999
ENTRYPOINT ["java", \
"-Xms1024m", \
"-Xmx1024m", \
"-Dcom.sun.management.jmxremote=true", \
"-Djava.rmi.server.hostname=127.0.0.1", \
"-Dcom.sun.management.jmxremote.host=0.0.0.0", \
"-Dcom.sun.management.jmxremote.port=9999", \
"-Dcom.sun.management.jmxremote.rmi.port=9999", \
"-Dcom.sun.management.jmxremote.ssl=false", \
"-Dcom.sun.management.jmxremote.registry.ssl=false", \
"-Dcom.sun.management.jmxremote.authenticate=false", \
"-Djava.net.preferIPv4Stack=true", \
"-jar", \
"/app/app.jar"]
