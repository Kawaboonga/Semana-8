FROM openjdk:21-ea-24-oracle

WORKDIR /app
COPY target/veterinaria-0.0.1-SNAPSHOT.jar app.jar
#COPY Wallet_BD5RB8RQ9NJ2ZWWC /app/oracle_wallet
EXPOSE 8080
CMD [ "java", "-jar","app.jar" ]


