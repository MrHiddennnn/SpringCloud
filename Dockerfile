FROM openjdk:18-jdk-alpine
WORKDIR /app/proyectos/
COPY springboot-servicio-apigateway/target/springboot-servicio-gateway-server-0.0.1-SNAPSHOT.jar .
COPY spring-servicios-usuarios/target/spring-servicios-usuarios-0.0.1-SNAPSHOT.jar .
COPY springboot-servicio-eureka/target/springboot-servicio-eureka-server-0.0.1-SNAPSHOT.jar .
COPY springboot-servicios-productos/target/springboot-servicios-productos-0.0.1-SNAPSHOT.jar .
COPY springboot-servicio-item/target/springboot-servicio-item-0.0.1-SNAPSHOT.jar .
COPY config/ .