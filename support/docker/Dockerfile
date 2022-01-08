FROM openjdk:17.0.1-slim

USER root

EXPOSE 8080

ENV DIRPATH /qsa-storage/qsa-calculator
RUN mkdir -p $DIRPATH
RUN apt-get update && apt-get install -y curl

COPY ./qsa-calculator-server/build/libs/qsa-calculator-final.jar $DIRPATH/qsa-calculator.jar
COPY ./support/docker/init.sh $DIRPATH/init.sh
RUN chmod 755 $DIRPATH/init.sh
WORKDIR $DIRPATH

HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "/qsa-storage/qsa-calculator/init.sh"]