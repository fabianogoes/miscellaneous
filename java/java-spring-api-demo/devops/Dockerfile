FROM openjdk:8

RUN echo "America/Sao_Paulo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

COPY api-demo*.jar /app/app.jar
WORKDIR /app

ARG PORT=8080
ARG PROFILE=default
ENV ENVPORT=$PORT
ENV ENVPROFILE=$PROFILE

ENTRYPOINT echo "PORT = ${ENVPORT} - PROFILE = ${ENVPROFILE}" && \
           java -jar -Dserver.port=${ENVPORT} -Dspring.profiles.active=${ENVPROFILE} app.jar
EXPOSE $ENVPORT
