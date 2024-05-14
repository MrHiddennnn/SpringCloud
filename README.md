# SpringCloud
Apuntes Gateway:
    Configuracion Circuit-Breaker;
        slidingWindowSize(100) : 100 peticiones para validar de que el microservicio este bien (estado cerrado)
        failureRateThreshold(50) : Taza de fallo que pueden tener las 100 peticiones enviadas (estado cerrado)
        waitDurationInOpenState(60000 ms): Tiempo que permanece el microservicio en abierto antes de pasar a semi-abierto (por defecto son 60 segundos)
        permittedNumberOfCallsInHalfOpenState(10) : Peticiones que se realizaran para validar si el microservicio que esta en estado semi-abierto pasara a estado cerrado o volvera al abierto
        slowCallRateThreshold(100) : Si de 100 peticiones las 100 tienen una llamada lenta entonces el microservicio pasa a estado abierto
        slowCallDurationThreshold(60000 ms): Este es el tiempo para saber si la llamada del microservicio es lenta, si sobre pasa los 60000 ms la llamada es lenta
    Estados microservicios:
        Cerrado: El microservicio esta corriendo
        Abierto: El microservicio tiene fallas
        Semi-abierto: El microservicio esta a la espera de envio de peticiones para validar si pasar el microservicio a abierto o cerrado

