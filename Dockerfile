FROM maven:3.6.3-adoptopenjdk-11 AS mavenbuilder
# INIZIO gestione certificato ca
COPY trusted_ca.crt /usr/local/share/ca-certificates
RUN update-ca-certificates
# importo in keystore java
RUN keytool -import -alias CorpProxy -keystore $JAVA_HOME/lib/security/cacerts -file /usr/local/share/ca-certificates/trusted_ca.crt -storepass changeit -noprompt
# FINE gestione certificato ca
ARG api_version=0.0.3-SNAPSHOT
ENV APIVERSION=api_version
COPY pom.xml pom.xml
RUN mvn dependency:go-offline
COPY src src 
RUN mvn clean package -DskipTests


FROM adoptopenjdk:11-jre-hotspot as layerbuilder
COPY --from=mavenbuilder /target/mercer*.jar /application/application.jar
WORKDIR /application
# creo questa struttura dal jar: dependencies snapshot-dependencies spring-boot-loader application
RUN java -Djarmode=layertools -jar application.jar extract


FROM adoptopenjdk:11-jre-hotspot
ARG mercer_version=0.0.3-SNAPSHOT
ENV MERCER_VERSION=$mercer_version
WORKDIR /application
COPY --from=layerbuilder application/dependencies/ .
COPY --from=layerbuilder application/snapshot-dependencies/ .
COPY --from=layerbuilder application/spring-boot-loader/ .
COPY --from=layerbuilder application/application/ .
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]