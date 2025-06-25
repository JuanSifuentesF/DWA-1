# Sesión 1

Selección múltiple: FeignClient

# 01-feignclient-basic

JDK 21

```java
//Dependencias
Spring Web
Lombok
OpenFeign
Spring DevTools
```

```java
// Estructura del proyecto
D:\REPOSITORIOS\DAW-2\01-FEIGCLIENT-BASIC\SRC\MAIN
├───java
│   └───pe
│       └───edu
│           └───cibertec
│               └───feigclientbasic
│                   │   FeigclientBasicApplication.java
│                   │   
│                   ├───clientrest
│                   │   └───placeholder
│                   │       │   UserClient.java
│                   │       │   
│                   │       └───model
│                   │               User.java
│                   │
│                   ├───controller
│                   │       UserController.java
│                   │
│                   └───service
│                           UserService.java
│
└───resources
    │   application.properties
    │
    ├───static
    └───templates

```

Primero creamos los paquetes, clientrest, controller y service, dentro de clientrest va placeholder y el modal

No enfocamos primero en el User.java

```java
// User.java

package pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model;
import lombok.Data;

@Data
public class User {

  private Long id;
  private String name;
  private String username;
  private String email;

}
```

Luego seguimos con  [UserClient.java](http://UserClient.java) que va hacer una interface. 

```java
//UserClient.java

package pe.edu.cibertec.feigclientbasic.clientrest.placeholder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;

import java.util.List;

@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface UserClient {

  // [/users]
  @GetMapping("/users")
  List<User> listaUsuarios();

  // [/users/1]
  @GetMapping("/users/{id}")
  User obtenerUsuario(@PathVariable Long id);
  
}
```

Luego seguimos con el servicio [UserService.java](http://UserService.java) 

```java
// UserService.java

package pe.edu.cibertec.feigclientbasic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.UserClient;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserClient userClient;

  public List<User> listarUsuarios(){
    return userClient.listaUsuarios();
  }

  public User obtenerUsuario(Long id){
    return userClient.obtenerUsuario(id);
  }

}
```

Luego vamos con el controlador, [UserController.java](http://UserController.java) 

```java
// UserController.java

package pe.edu.cibertec.feigclientbasic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.feigclientbasic.clientrest.placeholder.model.User;
import pe.edu.cibertec.feigclientbasic.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  // [/users]
  @GetMapping("/usuarios")
  public ResponseEntity<List<User>> listarUsuarios(){
    return ResponseEntity.ok(userService.listarUsuarios());
  }

  // [/users/1]
  @GetMapping("/usuarios/{id}")
  public ResponseEntity<User> obtenerUsuario(@PathVariable Long id){
    return ResponseEntity.ok(userService.obtenerUsuario(id));
  }

}
```

Al final nos aseguramos que la clase ejecutora contenga, la anotación “`@EnableFeignClients`”

```java
//FeigclientBasicApplication.java

package pe.edu.cibertec.feigclientbasic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeigclientBasicApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeigclientBasicApplication.class, args);
  }

}
```

Al ejecutar el proyecto y luego ir a postman

```json
// realizamos un get a
localhost:8080/usuarios

[
    {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz"
    },
    {
        "id": 2,
        "name": "Ervin Howell",
        "username": "Antonette",
        "email": "Shanna@melissa.tv"
    },
    ...
    
 ]
 
 // Si queremos por id.
 localhost:8080/usuarios/1
 
 {
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz"
}
```

## Resumen del Proyecto FeignClient Básico

Este proyecto demuestra la implementación básica de FeignClient en Spring Boot para consumir APIs REST externas. A continuación se presenta un resumen paso a paso:

### 1. Configuración del Proyecto

- Creación del proyecto Spring Boot con JDK 21
- Dependencias: Spring Web, Lombok, OpenFeign, Spring DevTools
- Estructura básica de paquetes: clientrest, controller, service

### 2. Implementación de Componentes

- **Model (User.java)**: Clase que mapea los datos de usuario con atributos básicos (id, name, username, email)
- **Client (UserClient.java)**: Interfaz con anotación @FeignClient que define los endpoints a consumir de JSONPlaceholder
- **Service (UserService.java)**: Clase de servicio que actúa como intermediario entre el cliente y el controlador
- **Controller (UserController.java)**: Controlador REST que expone endpoints para listar usuarios y obtener un usuario por ID

### 3. Configuración Principal

- Activación de FeignClient mediante la anotación @EnableFeignClients en la clase principal

### 4. Funcionamiento

- El cliente FeignClient consume la API externa [https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com)
- El controlador expone dos endpoints: /usuarios (lista todos) y /usuarios/{id} (obtiene uno específico)
- Las respuestas se devuelven en formato JSON con los datos de usuario mapeados

Este proyecto ilustra cómo FeignClient simplifica el consumo de APIs REST externas en aplicaciones Spring Boot, eliminando la necesidad de escribir código boilerplate para hacer las solicitudes HTTP.

## Aplicaciones de FeignClient en Proyectos Reales

FeignClient ofrece numerosos beneficios en proyectos reales de desarrollo de software. A continuación se presentan algunos escenarios prácticos donde esta tecnología resulta especialmente útil:

### 1. Arquitecturas de Microservicios

- **Comunicación entre servicios:** Facilita la comunicación entre microservicios de manera declarativa, reduciendo el código boilerplate necesario para realizar llamadas HTTP.
- **Centralización de configuración:** Permite centralizar la configuración de clientes REST, simplificando el mantenimiento y actualización de endpoints.

### 2. Integración con APIs Externas

- **Pasarelas de pago:** Integración con servicios como PayPal, Stripe o pasarelas de pago locales.
- **Servicios de mapas:** Consumo de APIs como Google Maps, OpenStreetMap para funcionalidades geoespaciales.
- **Redes sociales:** Integración con APIs de redes sociales para funciones de autenticación o compartir contenido.

### 3. Ventajas Operativas

- **Pruebas unitarias simplificadas:** El diseño basado en interfaces facilita la creación de mocks para pruebas unitarias.
- **Manejo de errores centralizado:** Permite implementar estrategias de reintentos, circuit breakers y manejo de errores.
- **Balanceo de carga:** Integración con Ribbon para balanceo de carga entre instancias de servicios.

### 4. Casos de Uso Específicos

- **Servicios bancarios:** Integración con APIs de bancos para consultas de saldo, transferencias o verificación de identidad.
- **E-commerce:** Comunicación con servicios de inventario, precios, envíos y notificaciones.
- **Aplicaciones móviles:** Backend para aplicaciones móviles que necesitan consumir múltiples servicios externos.

### 5. Monitoreo y Observabilidad

- **Integración con herramientas APM:** Facilidad para agregar interceptores que permiten monitorear tiempos de respuesta y errores.
- **Trazabilidad:** Posibilidad de integrar con sistemas como Zipkin o Jaeger para seguimiento distribuido de transacciones.

En resumen, FeignClient es una herramienta poderosa para cualquier proyecto real que requiera comunicación entre servicios o integración con APIs externas, ofreciendo una forma declarativa, mantenible y robusta de implementar estas comunicaciones.