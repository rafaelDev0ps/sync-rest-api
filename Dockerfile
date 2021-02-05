FROM maven:3.6.3-jdk-8 as builder
WORKDIR /pet

COPY ./domain/pom.xml ./domain/
COPY ./application/pom.xml ./application/
COPY ./infra/pom.xml ./infra/
COPY ./presentation/pom.xml ./presentation/
COPY ./pom.xml ./

RUN mvn install test clean

COPY ./domain/ ./domain/
COPY ./application/ ./application/
COPY ./infra/ ./infra/
COPY ./presentation/ ./presentation/

RUN mvn clean package

FROM adoptopenjdk/openjdk13-openj9:alpine-slim
WORKDIR /pet

COPY ./docker-entrypoint.sh /usr/local/bin/
RUN ln -s /usr/local/bin/docker-entrypoint.sh / && chmod +x /usr/local/bin/docker-entrypoint.sh

COPY --from=builder /pet/presentation/target/lib ./lib
COPY --from=builder /pet/presentation/target/*-runner.jar ./application.jar
RUN chmod 775 /pet

ENV MONGO_CONNECTION_STRING=mongodb+srv://rafael:<password>@petcluster.s75gp.mongodb.net/rest?retryWrites=true&w=majority

EXPOSE 8080

RUN /bin/sh -c 'docker-entrypoint.sh --run_type=short &' ; sleep 15 ; pkill -9 -f 'docker-entrypoint.sh'
ENTRYPOINT ["sh", "/usr/local/bin/docker-entrypoint.sh"]