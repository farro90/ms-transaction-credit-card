FROM openjdk:11
VOLUME /tmp
EXPOSE 8114
ADD ./target/ms-transaction-credit-card-0.0.1-SNAPSHOT.jar ms-transaction-credit-card.jar
ENTRYPOINT ["java","-jar","ms-transaction-credit-card.jar"]