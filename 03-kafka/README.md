# Sesión 3 - DAW - 2

Selección múltiple: Kafka

Creamos capeta contenedora 03-kafka

Previamente se debe de descargar el programa `kafka_2.13-3.7.0.tgz` , se descomprime en c:\ y seguimos la siguiente configuración

```
Guía de Configuración Inicial (Sobre la Instalación Limpia)
Paso 1: Crear las Carpetas de Datos
Tal como hicimos antes, vamos a crear un lugar dedicado y seguro para que Kafka y Zookeeper guarden su información.

Dentro de C:\kafka_2.13-3.7.0, crea una nueva carpeta llamada data.

Dentro de C:\kafka_2.13-3.7.0\data, crea dos carpetas más:

zookeeper
kafka-logs
La estructura final será:

C:\kafka_2.13-3.7.0
├───data
│   ├───kafka-logs
│   └───zookeeper
└───... (resto de carpetas)
Paso 2: Configurar Zookeeper
Vamos a decirle a Zookeeper que use su nueva carpeta.

Abre el archivo C:\kafka_2.13-3.7.0\config\zookeeper.properties.
Busca la línea: dataDir=/tmp/zookeeper
Reemplázala con la ruta a tu nueva carpeta (recuerda usar barras /): dataDir=C:/kafka_2.13-3.7.0/data/zookeeper
Guarda y cierra el archivo.
Paso 3: Configurar Kafka
Ahora le toca a Kafka.

Abre el archivo C:\kafka_2.13-3.7.0\config\server.properties.
Busca la línea: log.dirs=/tmp/kafka-logs
Reemplázala con la ruta a tu nueva carpeta de logs (de nuevo, con barras /): log.dirs=C:/kafka_2.13-3.7.0/data/kafka-logs
Guarda y cierra el archivo.
Secuencia de Ejecución (Ahora sobre la Base Correcta)
Con tu instalación limpia y ahora correctamente configurada, el proceso de arranque será estable.

Iniciar Zookeeper:

Abre una terminal.
C:\kafka_2.13-3.7.0> bin\windows\zookeeper-server-start.bat config\zookeeper.properties
Verifica que se inicie y se mantenga corriendo.
Iniciar Kafka:

Abre una segunda terminal.
C:\kafka_2.13-3.7.0> bin\windows\kafka-server-start.bat config\server.properties
Verifica que se inicie y, lo más importante, que no se apague. Debería quedarse corriendo.
Crear el Tópico:

Abre una tercera terminal.
C:\kafka_2.13-3.7.0> bin\windows\kafka-topics.bat --create --topic orders --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

Proyecto: kafka-publicador

Dependencias

```java
Spring Web
Spring for Apache Kafka
Spring Boot Dev Tools
```

Estructura del proyecto

```java
D:\REPOSITORIOS\DAW-2\03-KAFKA\KAFKA-PUBLICADOR\SRC\MAIN
├───java
│   └───pe
│       └───edu
│           └───cibertec
│               └───kafkapublicador
│                   │   [KafkaPublicadorApplication.java](https://www.notion.so/Sesi-n-3-DAW-2-21d00d56b6a68037b874ebfb17041e1d?pvs=21)
│                   │
│                   ├───controller
│                   │       ApiController.java
│                   │
│                   └───service
│                           KafkaPublicadorService.java
│
└───resources
    │   [application.properties](https://www.notion.so/Sesi-n-3-DAW-2-21d00d56b6a68037b874ebfb17041e1d?pvs=21)
    │
    ├───static
    └───templates
```

[application.properties](http://application.properties.java)

```java
spring.application.name=kafka-publicador
server.port=8092

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```

Empezamos por KafkaPublicadorApplication.java

```java
package pe.edu.cibertec.kafkapublicador.service;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPublicadorService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private static final String TOPIC = "topic-app-web";

  public KafkaPublicadorService(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void enviarMensaje(String mensaje){
    this.kafkaTemplate.send(TOPIC, mensaje);
  }
}
```

Luego al controlador ApiController.java

```java
package pe.edu.cibertec.kafkapublicador.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.kafkapublicador.service.KafkaPublicadorService;

@Controller
@RequestMapping("/api")
public class ApiController {

  private final KafkaPublicadorService kafkaPublicadorService;

  public ApiController(KafkaPublicadorService kafkaPublicadorService) {
    this.kafkaPublicadorService = kafkaPublicadorService;
  }

  @RequestMapping("/enviar")
  public ResponseEntity<String> enviar(@RequestParam String mensaje) {
    this.kafkaPublicadorService.enviarMensaje(mensaje);
    return new ResponseEntity<>("Mensaje enviado correctamente a Kafka",
          HttpStatus.OK);
  }

}
```

No necesitamos agregar nada en el ejecutador main

---

Proyecto: kafka-suscriptor

Dependencias

```java
Spring for Apache Kafka
```

Estructura del proyecto

```java
D:\REPOSITORIOS\DAW-2\03-KAFKA\KAFKA-SUSCRIPTOR\SRC\MAIN
├───java
│   └───pe
│       └───edu
│           └───cibertec
│               └───suscriptor
│                   │   SuscriptorApplication.java
│                   │
│                   └───service
│                           KafkaConsumerService.java
│
└───resources
        application.properties

```

application.properties

```java
spring.application.name=suscriptor
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=group-app-web
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

Dentro de KafkaConsumerService.java

```java
package pe.edu.cibertec.suscriptor.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

  @KafkaListener(topics = "topic-app-web",groupId = "group-app-web")
  public void subscribe(String mensaje) {
    System.out.println("Mensaje recibido desde kafka: " + mensaje);
  }

}

```

Anotación de inicio ejecución para que funcione el proyecto:

```
1. Ejecutamos zookeeper
2. Ejecutamos kafka
3. Ejecuto suscriptor
4. Ejecuto publicador
```

Al ejecutar funciona correctamente, desde [localhost:8092/api/enviar?mensaje=Holakafka](http://localhost:8092/api/enviar?mensaje=Holakafka) y en la consola de kafka publicador se muestra el mensaje, “Mensaje recibido desde kafka: HolaKafka”

## Resumen del Proyecto Kafka

Este proyecto implementa un sistema de mensajería utilizando Apache Kafka en Spring Boot. A continuación se presenta un resumen paso a paso:

### 1. Configuración del Proyecto

- Creación de dos proyectos Spring Boot: kafka-publicador y kafka-suscriptor
- Dependencias: Spring Web, Spring for Apache Kafka, Spring Boot Dev Tools
- Requisitos previos: Instalación y configuración de Apache Kafka y Zookeeper
- Estructura básica: controller y service para el publicador; service para el suscriptor

### 2. Implementación de Componentes

- **Publicador (KafkaPublicadorService.java)**: Servicio encargado de enviar mensajes al tópico de Kafka
- **Controller (ApiController.java)**: Controlador REST que expone un endpoint para enviar mensajes al tópico
- **Suscriptor (KafkaConsumerService.java)**: Componente que escucha y procesa los mensajes recibidos del tópico

### 3. Configuración Principal

- Configuración en application.properties para la conexión con el servidor Kafka local
- Definición de un tópico llamado "topic-app-web"
- Configuración del consumer group "group-app-web" para el suscriptor
- Configuración de serializers/deserializers para mensajes de tipo String

### 4. Funcionamiento

- Secuencia de inicio: primero Zookeeper, luego Kafka, después el suscriptor y finalmente el publicador
- El usuario envía un mensaje a través del endpoint REST: `localhost:8092/api/enviar?mensaje=HolaKafka`
- El controlador recibe la petición y llama al servicio publicador para enviar el mensaje
- El servicio publicador envía el mensaje al tópico "topic-app-web"
- El suscriptor está constantemente escuchando el tópico y procesa los mensajes recibidos, mostrándolos en consola

## Aplicaciones de Kafka en Proyectos Reales

Apache Kafka es una plataforma de transmisión de eventos distribuida que se utiliza en numerosos contextos empresariales. A continuación se presentan algunos escenarios prácticos donde esta tecnología resulta especialmente útil:

### 1. Procesamiento de Eventos en Tiempo Real

- **Análisis en tiempo real:** Captura y procesamiento de flujos continuos de datos para obtener insights inmediatos.
- **Monitoreo de actividad:** Seguimiento en tiempo real de acciones de usuarios, operaciones de sistemas o transacciones.

### 2. Arquitecturas Orientadas a Eventos

- **Event sourcing:** Almacenamiento del historial completo de eventos como fuente de verdad del sistema.
- **CQRS (Command Query Responsibility Segregation):** Separación de operaciones de lectura y escritura para mejorar el rendimiento.

### 3. Integración de Sistemas

- **ETL en tiempo real:** Extracción, transformación y carga de datos entre sistemas heterogéneos.
- **CDC (Change Data Capture):** Captura y propagación de cambios en bases de datos.
- **Sincronización de datos:** Mantener consistentes múltiples sistemas o microservicios.

### 4. Casos de Uso Específicos

- **Sistemas IoT:** Procesamiento de datos de sensores y dispositivos conectados a gran escala.
- **Análisis de clickstream:** Seguimiento y análisis del comportamiento de usuarios en aplicaciones web.
- **Detección de fraudes:** Análisis en tiempo real de patrones sospechosos en transacciones financieras.

### 5. Ventajas sobre Sistemas de Mensajería Tradicionales

- **Alta durabilidad:** Persistencia configurable para garantizar que no se pierdan mensajes.
- **Escalabilidad horizontal:** Capacidad para manejar millones de mensajes por segundo distribuyendo la carga.
- **Retención configurable:** Posibilidad de almacenar mensajes durante períodos prolongados para reprocesamiento.
- **Ordenamiento garantizado:** Dentro de cada partición, los mensajes mantienen su orden de llegada.

En resumen, Apache Kafka proporciona una plataforma robusta para la transmisión y procesamiento de eventos a gran escala, siendo especialmente valioso en sistemas que requieren alto rendimiento, fiabilidad y capacidad para manejar grandes volúmenes de datos en tiempo real.