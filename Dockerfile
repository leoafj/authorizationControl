FROM maven:3-eclipse-temurin-11 AS BUILDER

WORKDIR /opt/procedureControl

COPY . .

RUN mvn clean package


FROM alpine AS DEPENDENCY_FETCHER

WORKDIR /tmp

RUN apk update && \
    apk add curl unzip

RUN curl -LO https://downloads.mysql.com/archives/get/p/3/file/mysql-connector-j-8.2.0.zip && \
    unzip mysql-connector-j-8.2.0.zip


FROM bitnami/wildfly:29.0.1 AS RUNNER

COPY --from=BUILDER /opt/procedureControl/target/procedureControl-*.war  /app/procedureControl.war
COPY --from=DEPENDENCY_FETCHER /tmp/mysql-connector-j-8.2.0/mysql-connector-j-8.2.0.jar /app/mysql-connector-j-8.2.0.jar

ENV WILDFLY_MANAGEMENT_LISTEN_ADDRESS 0.0.0.0
ENV WILDFLY_USERNAME user
ENV WILDFLY_PASSWORD Jpne@#3358

EXPOSE 8080
EXPOSE 9990

CMD ["/opt/bitnami/scripts/wildfly/run.sh"]
