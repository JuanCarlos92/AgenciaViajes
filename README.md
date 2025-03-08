# Agencia de Viajes - Microservicios con Spring Boot

## Descripción
Este proyecto implementa una **Agencia de Viajes** basada en **microservicios con Spring Boot**, donde los usuarios pueden realizar y gestionar reservas de vuelos y hoteles. 

### Características Principales
- Gestión de **hoteles** y **vuelos** disponibles.
- Creación y administración de **reservas**, asociando un vuelo y un hotel a un usuario.
- **Eureka Server** para el descubrimiento de servicios.
- **API Gateway** para centralizar las peticiones y redirigirlas a los microservicios adecuados.
- **Spring Security** y **JWT** para autenticación y autorización.
- **MySQL y JPA/Hibernate** para la persistencia de datos.
- Comunicación entre microservicios con **WebClient**.
- Interfaz web en Angular con Bootstrap para mejorar la experiencia de usuario.

## Arquitectura de Microservicios
La aplicación está compuesta por los siguientes **microservicios**:

| Microservicio    | Descripción |
|-----------------|-------------|
| **Hoteles**     | Devuelve la lista de hoteles disponibles. |
| **Vuelos**      | Devuelve la lista de vuelos disponibles y actualiza las plazas al hacer una reserva. |
| **Reservas**    | Registra las reservas, vinculando vuelos y hoteles a un usuario. |
| **Eureka Server** | Actúa como servidor de descubrimiento para los microservicios. |
| **API Gateway**  | Centraliza y redirige las peticiones a los microservicios correspondientes. Implementa la seguridad mediante Token JWT y gestiona el filtrado de endpoints.|
| **Security**  | Se encarga exclusivamente de la generación y validación de tokens JWT. |

## Tecnologías Utilizadas
### Backend:
- **Spring Boot 3+**
- **Spring Cloud Netflix Eureka** (para descubrimiento de servicios)
- **Spring Security & JWT** (para autenticación y autorización)
- **Spring WebFlux - WebClient** (para la comunicación entre microservicios)
- **Spring Data JPA & Hibernate**
- **MySQL** (Base de datos relacional)

### Frontend:
- **Angular 19+**
- **Bootstrap 5** (para diseño responsivo y estilos)
- **Angular Services** para la comunicación con el backend
- **RxJS** para manejo de peticiones asíncronas

## Instalación y Configuración
### Requisitos Previos
- **JDK 17+**
- **Maven 3+**
- **MySQL 8+**
- **Node.js 18+** y npm (para ejecutar el frontend)

### Pasos para Ejecutar el Proyecto
1. **Clona el repositorio**
   ```bash
   git clone https://github.com/JuanCarlos92/AgenciaViajes.git
   cd AgenciaViajes
   ```

2. **Configura la base de datos**
   - Importa las base de datos que se encuentran en el la carpeta database
   - Modifica las credenciales en `application.properties` de cada microservicio para que coincidan con tu configuración.

3. **Inicia Eureka Server**
   ```bash
   eureka-server
   spring-boot:run
   ```
   
4. **Inicia el Security**
   ```bash
   security
   ```
   
5. **Inicia el API Gateway**
   ```bash
   api-gateway
   ```
   
6. **Inicia los microservicios**
   ```bash
   hoteles-service
   ```
   ```bash
   vuelos-service
   ```
   ```bash
   reservas-service
   ```
   
7. **Iniciar el frontend en Angular**
   ```bash
   cd frontend
   npm install
   npm start
   ```
   
## Endpoints de los Microservicios
   - **Hoteles**: `http://localhost:8081/hoteles`
   - **Vuelos**: `http://localhost:8082/vuelos`
   - **Reservas**: `http://localhost:8083/reservas`
   - **Security**: `http://localhost:8085`
   - **API Gateway**: `http://localhost:8080`

## Seguridad y Autenticación
- Se utiliza **Spring Security** con **JWT** para la autenticación de usuarios.
- Para probar, se puede generar un token con un endpoint de autenticación y usarlo en las peticiones a los servicios.

### Generar Token
Se puede generar mediante Swagger en `http://localhost:8085/swagger-ui.html`

## Contribuciones

## Contribuciones
Si deseas contribuir a este proyecto, ¡las pull requests son bienvenidas!

---
**Autor:** Juan Carlos Filter Martín  
📌 Proyecto final de PSP DAM2 - Desarrollo de Microservicios con Spring Boot
