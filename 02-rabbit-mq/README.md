# Sesión 2 - DAW - 2

Selección múltiple: RabbitMQ

Creamos el proyecto 02-rabbit-mq con JDK 21 y SpringBoot

Previamente se debe de descargar el programa `rabbitmq-server-4.1.0` y `ot_win64_28.0` 

Dependencias

```java
Spring Web
Lombok
Spring for RabbitMQ
Spring Boot Dev Tools
```

Antes de ejecutar el proyecto debemos de activar el plugin

```bash
c:\Program Files\RabbitMQ Server\rabbitmq_server-4.0.7\sbin>rabbitmq-plugins enable rabbitmq_managment
```

Estructura del proyecto

```java
D:\REPOSITORIOS\DAW-2\02-RABBIT-MQ\SRC\MAIN
├───java
│   └───pe
│       └───edu
│           └───cibertec
│               └───rabbitmq
│                   │   RabbitMqApplication.java
│                   │   
│                   ├───controller
│                   │       RabbitMQController.java
│                   │
│                   └───enqueue
│                       ├───config
│                       │       RabbitMQConfig.java
│                       │
│                       ├───consumer
│                       │       RabbitMQConsumer.java
│                       │
│                       └───producer
│                               RabbitMQProducer.java
│
└───resources
    │   application.properties
    │
    ├───static
    └───templates

```

[application.properties.java](http://application.properties.java) 

```java
spring.application.name=rabbit-mq
server.port=8070
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

1. Creamos una clase en el paquete `config` que esta dentro de `enqueue` llamado [RabbitMQConfig.java](http://RabbitMQConfig.java) 

```java
package pe.edu.cibertec.rabbitmq.enqueue.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public Queue queue() {
    return new Queue("prueba_rabbit", true);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange("prueba_exchange");
  }

  @Bean
  public Binding binding (Queue queue, TopicExchange exchange){
    return BindingBuilder.bind(queue)
          .to(exchange)
          .with("prueba_routing");
  }

}
```

1. Creamos una clase en el paquete producer llamada [RabbitMQProducer.java](http://RabbitMQProducer.java) 

```java
package pe.edu.cibertec.rabbitmq.enqueue.producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQProducer {

  private final RabbitTemplate rabbitTemplate;
  private static final String EXCHANGE_NAME = "prueba_exchange";
  private static final String ROUTING_KEY = "prueba_routing";

  public void enviarMensaje(String mensaje){
    rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mensaje);
    log.info("Mensaje enviado a RabbitMQ");
  }
}
```

1. Creamos una clase en el paquete consumer llamado [RabbitMQConsumer.java](http://RabbitMQConsumer.java) 

```java
package pe.edu.cibertec.rabbitmq.enqueue.producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitMQProducer {

  private final RabbitTemplate rabbitTemplate;
  private static final String EXCHANGE_NAME = "prueba_exchange";
  private static final String ROUTING_KEY = "prueba_routing";

  public void enviarMensaje(String mensaje){
    rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mensaje);
    log.info("Mensaje enviado a RabbitMQ");
  }

}
```

1. Creamos una clase en el paquete controller llamado [RabbitMQController.java](http://RabbitMQController.java) 

```java
package pe.edu.cibertec.rabbitmq.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.rabbitmq.enqueue.producer.RabbitMQProducer;

@RequiredArgsConstructor
@RestController
public class RabbitMQController {

  private final RabbitMQProducer producer;

  //localhost:8070/enviar?mensaje=Hola Mundo
  @GetMapping("/enviar")
  public String enviarMensaje(@RequestParam String mensaje){
    producer.enviarMensaje(mensaje);
    return "Mensaje enviado a RabbitMQ";
  }
}
```

1. Ejecutamos y visualizamos en consola que ha enviado el mensaje.

```java

2025-06-24T22:51:45.669-05:00  INFO 32152 --- [rabbit-mq] [  restartedMain] o.s.a.r.c.CachingConnectionFactory       : Attempting to connect to: [localhost:5672]
2025-06-24T22:51:45.675-05:00  INFO 32152 --- [rabbit-mq] [  restartedMain] o.s.a.r.c.CachingConnectionFactory       : Created new connection: rabbitConnectionFactory#2e54d9a4:0/SimpleConnection@68990965 [delegate=amqp://guest@127.0.0.1:5672/, localPort=61580]
2025-06-24T22:51:45.682-05:00  INFO 32152 --- [rabbit-mq] [  restartedMain] p.e.c.rabbitmq.RabbitMqApplication       : Started RabbitMqApplication in 0.258 seconds (process running for 14.635)
2025-06-24T22:51:45.683-05:00  INFO 32152 --- [rabbit-mq] [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

1. Al ingresar al navegador [http://localhost:15672/#/](http://localhost:15672/#/) se observa la conexión.

## Resumen del Proyecto RabbitMQ

Este proyecto implementa un sistema básico de mensajería utilizando RabbitMQ en Spring Boot. A continuación se presenta un resumen paso a paso:

### 1. Configuración del Proyecto

- Creación del proyecto Spring Boot con JDK 21
- Dependencias: Spring Web, Lombok, Spring for RabbitMQ, Spring Boot Dev Tools
- Requisitos previos: Instalación de RabbitMQ Server y activación del plugin de gestión
- Estructura básica de paquetes: controller, enqueue (config, consumer, producer)

### 2. Implementación de Componentes

- **Config (RabbitMQConfig.java)**: Configuración que define la cola, el exchange y el binding para la comunicación
- **Producer (RabbitMQProducer.java)**: Servicio encargado de enviar mensajes a la cola de RabbitMQ
- **Consumer (RabbitMQConsumer.java)**: Componente que escucha y procesa los mensajes recibidos de la cola
- **Controller (RabbitMQController.java)**: Controlador REST que expone un endpoint para enviar mensajes a la cola

### 3. Configuración Principal

- Configuración en application.properties para la conexión con el servidor RabbitMQ local
- Definición de una cola persistente (durable) llamada "prueba_rabbit"
- Configuración de un exchange de tipo topic llamado "prueba_exchange"
- Establecimiento del binding entre la cola y el exchange con la routing key "prueba_routing"

### 4. Funcionamiento

- El usuario envía un mensaje a través del endpoint REST: `localhost:8070/enviar?mensaje=Hola Mundo`
- El controlador recibe la petición y llama al productor para enviar el mensaje
- El productor envía el mensaje al exchange con la routing key correspondiente
- El consumidor está constantemente escuchando la cola y procesa los mensajes recibidos
- La interfaz web de RabbitMQ (localhost:15672) permite monitorear las colas, mensajes y conexiones

## Aplicaciones de RabbitMQ en Proyectos Reales

RabbitMQ es ampliamente utilizado en entornos empresariales por su robustez y flexibilidad. A continuación se presentan algunos escenarios prácticos donde esta tecnología resulta especialmente útil:

### 1. Arquitecturas de Microservicios

- **Comunicación asíncrona:** Permite que los microservicios se comuniquen sin necesidad de esperar respuestas inmediatas, mejorando el rendimiento general del sistema.
- **Desacoplamiento:** Reduce las dependencias directas entre servicios, permitiendo que evolucionen de manera independiente.

### 2. Procesamiento de Tareas en Segundo Plano

- **Envío de correos electrónicos:** Gestión de envíos masivos sin bloquear la aplicación principal.
- **Generación de informes:** Procesamiento de informes complejos en segundo plano para no afectar la experiencia del usuario.
- **Procesamiento de imágenes:** Redimensionamiento, compresión o aplicación de filtros a imágenes sin bloquear las solicitudes principales.

### 3. Equilibrio de Carga y Escalabilidad

- **Distribución de trabajo:** Reparto equitativo de tareas entre múltiples instancias de trabajadores.
- **Elasticidad:** Capacidad para escalar horizontalmente agregando más consumidores según la demanda.
- **Tolerancia a fallos:** Si un consumidor falla, los mensajes pueden ser procesados por otros o reintentados posteriormente.

### 4. Casos de Uso Específicos

- **Sistemas de notificaciones:** Entrega confiable de notificaciones a usuarios a través de diferentes canales (email, SMS, push).
- **Registros y auditoría:** Recopilación centralizada de eventos y actividades para análisis o cumplimiento normativo.
- **Sistemas IoT:** Gestión de mensajes provenientes de múltiples dispositivos conectados.

### 5. Patrones de Integración

- **Publicación/Suscripción:** Distribución de mensajes a múltiples consumidores interesados.
- **Enrutamiento:** Dirección de mensajes a destinatarios específicos basados en criterios.
- **RPC distribuido:** Implementación de llamadas a procedimientos remotos con colas de respuesta.

En resumen, RabbitMQ proporciona una solución robusta para la comunicación asíncrona entre componentes de software, siendo especialmente valioso en sistemas distribuidos donde la fiabilidad, escalabilidad y desacoplamiento son requisitos clave.