FROM openjdk:11

USER root

EXPOSE 8080

RUN mkdir /qsa-storage

ENV DIRPATH /qsa-storage

COPY ./qsa-calculator-server/build/libs/qsa-final.jar $DIRPATH/qsa-backend.jar
COPY ./support/docker/backend/init.sh $DIRPATH/init.sh
RUN chmod 755 $DIRPATH/init.sh
WORKDIR $DIRPATH

RUN mkdir $DIRPATH/logs

HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "/qsa-storage/init.sh"]