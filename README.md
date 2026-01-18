# Country Vote App

Aplicación web para votar por países favoritos, desarrollada con arquitectura hexagonal y dividida en dos componentes principales: un servicio backend y una interfaz frontend.

## 📋 Descripción

Country Vote App es una plataforma donde los usuarios pueden registrar su voto por su país favorito. La aplicación permite visualizar un ranking de los 10 países más votados y filtrar países por nombre, capital, región o subregión. El sistema garantiza que cada dirección de correo electrónico solo pueda votar una vez.

## 🏗️ Arquitectura

El proyecto está estructurado en dos componentes principales que siguen los principios de arquitectura hexagonal:

- **country-vote-service**: Servicio backend desarrollado con Spring Boot
- **country-vote-interface**: Interfaz frontend desarrollada con Angular

Ambos componentes están diseñados siguiendo los principios de arquitectura hexagonal (puertos y adaptadores), lo que permite una separación clara entre la lógica de negocio y los detalles de implementación.

## 🛠️ Tecnologías

### Backend (country-vote-service)
- **Spring Boot**: Framework de aplicación Java
- **Gradle**: Sistema de construcción
- **PostgreSQL**: Base de datos relacional
- **Docker Compose**: Orquestación de contenedores
- **WebFlux**: Programación reactiva
- **Spring Data R2DBC**: Acceso reactivo a datos
- **Java 21**: Lenguaje de programación

### Frontend (country-vote-interface)
- **Angular 21**: Framework de aplicación web
- **Bootstrap 5**: Framework CSS
- **TypeScript**: Lenguaje de programación
- **RxJS**: Programación reactiva

## 📦 Componentes

### country-vote-service
Servicio backend que maneja toda la lógica de negocio, persistencia de datos e integración con APIs externas. Proporciona endpoints REST para:
- Registrar votos
- Obtener el ranking de países más votados
- Buscar países (integración con REST Countries API)

### country-vote-interface
Interfaz de usuario desarrollada en Angular que permite a los usuarios:
- Registrar su voto mediante un formulario
- Visualizar el ranking de los 10 países más votados
- Filtrar países por nombre, capital, región o subregión

## 🚀 Inicio Rápido

### Prerrequisitos

- **Java 21** o superior
- **Node.js** y **npm** (versión compatible con Angular 21)
- **Docker** y **Docker Compose**
- **Gradle** (se incluye wrapper en el proyecto)

### Instalación

1. Clonar el repositorio:
```bash
git clone <repository-url>
cd country-vote-app
```

2. Configurar y ejecutar el servicio backend:
```bash
cd country-vote-service
./gradlew build
# Ver README.md de country-vote-service para más detalles
```

3. Configurar y ejecutar la interfaz frontend:
```bash
cd country-vote-interface
npm install
# Ver README.md de country-vote-interface para más detalles
```

## 📝 Reglas de Negocio

- **Un voto por email**: El sistema solo permite un voto por dirección de correo electrónico
- **Campos obligatorios**: Todos los campos del formulario de votación son obligatorios
- **Validación de email**: La dirección de correo electrónico debe tener un formato válido
- **Ranking**: Se muestra el top 10 de países más votados

## 🔌 Integraciones Externas

La aplicación se integra con [REST Countries API](https://restcountries.com/) para obtener información sobre países, incluyendo:
- Nombre del país
- Capital
- Región
- Subregión
- Código alpha-3

## 📁 Estructura del Proyecto

```
country-vote-app/
├── country-vote-service/     # Servicio backend
│   ├── domain/               # Lógica de dominio
│   ├── application/          # Casos de uso
│   ├── adapters/            # Adaptadores (inbound/outbound)
│   └── bootstrap/           # Configuración y arranque
├── country-vote-interface/   # Interfaz frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── domain/      # Lógica de dominio
│   │   │   ├── adapters/    # Adaptadores
│   │   │   └── features/    # Características de la aplicación
│   │   └── environments/    # Configuraciones de entorno
└── README.md                # Este archivo
```

## 📚 Documentación Adicional

Para más información sobre cada componente, consulta:
- [README de country-vote-service](./country-vote-service/README.md)
- [README de country-vote-interface](./country-vote-interface/README.md)

## 🤝 Contribución

Las contribuciones son bienvenidas. Por favor, asegúrate de seguir las convenciones de código establecidas en cada componente.

## 📄 Licencia

[Especificar licencia si aplica]
