FROM openjdk:8
WORKDIR /app
ADD build/libs/CreditCardPaymentProjectLatest-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","CreditCardPaymentProjectLatest-0.0.1-SNAPSHOT.jar"]

