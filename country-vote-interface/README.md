# Country Vote Interface

Interfaz de usuario desarrollada con Angular 21 que permite a los usuarios votar por su país favorito y visualizar el ranking de países más votados. Implementada siguiendo arquitectura hexagonal.

## 📋 Descripción

Country Vote Interface es una aplicación web frontend que proporciona una interfaz intuitiva para que los usuarios registren su voto por su país favorito. La aplicación muestra un formulario de votación, un ranking de los 10 países más votados y funcionalidades de búsqueda y filtrado de países.

## 🏗️ Arquitectura

El proyecto sigue una arquitectura hexagonal (puertos y adaptadores) organizada en las siguientes capas:

- **domain**: Lógica de dominio, modelos y puertos (interfaces)
- **adapters**: Implementaciones de adaptadores (HTTP, servicios)
- **features**: Características de la aplicación (componentes, vistas)
- **core**: Servicios core, interceptores, layout
- **shared**: Componentes y utilidades compartidas

## 🛠️ Tecnologías

- **Angular 21**: Framework de aplicación web
- **Bootstrap 5**: Framework CSS para diseño responsive
- **TypeScript**: Lenguaje de programación
- **RxJS**: Programación reactiva
- **Angular Forms**: Manejo de formularios reactivos
- **Angular Router**: Navegación y routing

## 📦 Dependencias Principales

- `@angular/core`: ^21.1.0
- `@angular/common`: ^21.1.0
- `@angular/forms`: ^21.1.0
- `@angular/router`: ^21.1.0
- `bootstrap`: ^5.3.8
- `rxjs`: ~7.8.0
- `@ng-bootstrap/ng-bootstrap`: ^20.0.0

## 🚀 Prerrequisitos

- **Node.js 24** o superior
- **npm** (versión 11.6.2 o superior recomendada)
- **country-vote-service** ejecutándose en `http://localhost:8080`

## 📥 Instalación

1. Navegar al directorio del proyecto:
```bash
cd country-vote-interface
```

2. Instalar dependencias:
```bash
npm install
```

## ⚙️ Configuración

### Proxy Configuration

La aplicación utiliza un proxy para redirigir las peticiones al backend. La configuración se encuentra en `proxy.conf.json`:

```json
{
  "/api/**": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "logLevel": "debug"
  }
}
```

### Environments

Los archivos de configuración de entorno se encuentran en `src/environments/`:
- `environment.ts`: Configuración de producción
- `environment.development.ts`: Configuración de desarrollo

## 🏃 Ejecución

### Servidor de Desarrollo

Ejecutar el servidor de desarrollo:
```bash
npm start
# o
ng serve
```

La aplicación estará disponible en `http://localhost:4200/`. El servidor recarga automáticamente cuando se modifican los archivos.

### Build de Producción

Generar build de producción:
```bash
npm run build
# o
ng build
```

Los archivos compilados se generan en el directorio `dist/`.

### Build con Watch Mode

Compilar en modo watch para desarrollo:
```bash
npm run watch
# o
ng build --watch --configuration development
```

## 📱 Funcionalidades

### Formulario de Votación

El formulario permite a los usuarios:
- Ingresar su dirección de correo electrónico (validación de formato)
- Ingresar su nombre
- Seleccionar un país desde un selector con búsqueda

**Validaciones:**
- Email: formato válido, obligatorio
- Nombre: obligatorio
- País: obligatorio

**Restricciones:**
- Solo se permite un voto por dirección de correo electrónico
- Todos los campos son obligatorios

### Ranking de Países

Muestra una tabla con los 10 países más votados, incluyendo:
- Posición en el ranking
- Código del país (alpha-3)
- Nombre del país
- Cantidad de votos

### Búsqueda y Filtrado

Permite filtrar países por:
- Nombre del país
- Capital
- Región
- Subregión

La búsqueda se realiza en tiempo real mientras el usuario escribe.

## 📁 Estructura del Proyecto

```
country-vote-interface/
├── src/
│   ├── app/
│   │   ├── domain/                 # Lógica de dominio
│   │   │   ├── vote/
│   │   │   │   ├── models/         # Modelos de dominio
│   │   │   │   └── ports/          # Puertos (interfaces)
│   │   │   └── ...
│   │   │
│   │   ├── adapters/                # Adaptadores
│   │   │   ├── vote/               # Adaptadores de votación
│   │   │   └── adapters.providers.ts
│   │   │
│   │   ├── features/                # Características
│   │   │   └── vote/               # Feature de votación
│   │   │       ├── components/     # Componentes
│   │   │       └── ...
│   │   │
│   │   ├── core/                    # Servicios core
│   │   │   ├── services/           # Servicios
│   │   │   ├── interceptors/       # Interceptores HTTP
│   │   │   ├── layout/             # Layout principal
│   │   │   ├── models/             # Modelos compartidos
│   │   │   └── enums/              # Enumeraciones
│   │   │
│   │   ├── shared/                  # Componentes compartidos
│   │   │
│   │   ├── app.config.ts           # Configuración de la app
│   │   ├── app.routes.ts           # Rutas
│   │   └── app.ts                  # Componente raíz
│   │
│   ├── environments/                # Configuraciones de entorno
│   ├── index.html                  # HTML principal
│   ├── main.ts                     # Punto de entrada
│   └── styles.scss                 # Estilos globales
│
├── public/                          # Archivos estáticos
│   ├── favicon.ico
│   ├── icons/
│   └── images/
│
├── angular.json                     # Configuración de Angular
├── package.json                     # Dependencias
├── proxy.conf.json                  # Configuración de proxy
└── tsconfig.json                    # Configuración de TypeScript
```

## 🎨 Estilos y Diseño

La aplicación utiliza:
- **Bootstrap 5** para el sistema de diseño y componentes
- **SCSS** para estilos personalizados
- Diseño responsive que se adapta a diferentes tamaños de pantalla

## 🔌 Integración con Backend

La aplicación se comunica con `country-vote-service` mediante peticiones HTTP:

- **POST** `/api/voting`: Registrar un voto
- **GET** `/api/voting/ranking`: Obtener ranking de países
- **GET** `/api/countries?searchTerm=...`: Buscar países

### Adaptadores HTTP

Los adaptadores HTTP implementan los puertos definidos en el dominio:
- `VoteHttpAdapter`: Implementa `VotePort` para operaciones de votación
- `CountryHttpAdapter`: Implementa `CountryPort` para búsqueda de países

## 📝 Convenciones de Código

- **Prettier**: Configurado con `printWidth: 100` y `singleQuote: true`
- **TypeScript**: Estricto, con configuración en `tsconfig.json`
- **Arquitectura Hexagonal**: Separación clara entre dominio, aplicación y adaptadores

## 🐛 Troubleshooting

### Problemas Comunes

1. **Error de conexión con el backend**:
   - Verificar que `country-vote-service` esté ejecutándose en `http://localhost:8080`
   - Verificar la configuración del proxy en `proxy.conf.json`

2. **Error al instalar dependencias**:
   - Asegurarse de usar la versión correcta de npm (`npm@11.6.2`)
   - Limpiar cache: `npm cache clean --force`
   - Eliminar `node_modules` y `package-lock.json`, luego reinstalar

3. **Problemas de compilación**:
   - Verificar que Node.js versión 24 o superior esté instalado
   - Ejecutar `npm install` nuevamente

## 🤝 Contribución

Al contribuir, asegúrate de:
1. Seguir las convenciones de código establecidas
2. Mantener la arquitectura hexagonal
3. Documentar componentes y servicios nuevos
