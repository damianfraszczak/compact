FROM tomcat:8.0-jre8

RUN rm -rf /usr/local/tomcat/webapps/*

ENV SPRING_CONFIG_ADDITIONAL_LOCATION=file:/usr/local/tomcat/config/

RUN apt-get update \
 && apt-get install -y --no-install-recommends ca-certificates curl \
 && rm -rf /var/lib/apt/lists/* \
 && mkdir -p /usr/local/tomcat/config /root/h2 /root/dted

ARG WAR_URL="https://github.com/damianfraszczak/compact/releases/download/0.0.1/compact.war"

RUN curl -fL "$WAR_URL" -o /usr/local/tomcat/webapps/ROOT.war

COPY application.properties /usr/local/tomcat/config/application.properties

VOLUME ["/root/h2", "/root/dted"]
EXPOSE 8080
CMD ["catalina.sh", "run"]
