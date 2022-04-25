This project is built with Maven

Dependencies:
spring-boot-starter-web
h2
lombok
spring-boot
spring-boot-starter-test
spring-boot-starter-data-jdbc


To build:
```aidl
./mvnw clean package
```

To run:
```aidl
java -jar ./target/path-to-SNAPSHOT.jar
```

To get points for last three months:
curl -X GET http://localhost:8080/customer-points/points-balance-last-three-months
