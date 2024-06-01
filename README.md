# API de Tienda de Videojuegos
Este repositorio contiene una API completa para una tienda de videojuegos en línea, que incluye un backend construido con Spring Boot y un frontend desarrollado con React, Redux, React Router, Axios y Bootstrap.
## Descripción
La API de Tienda de Videojuegos proporciona una solución integral para administrar las operaciones de una tienda de videojuegos en línea. El backend, construido con Spring Boot, ofrece endpoints para administrar productos, clientes y ventas. Por otro lado, el frontend, desarrollado con React y otras tecnologías web modernas, proporciona una interfaz de usuario fácil de usar para interactuar con la API del backend.

## Características
### Backend

- Autenticación y autorización de usuarios
- Gestión de videojuegos (CRUD)
- Gestión de clientes (CRUD)
- Gestión de ventas (CRUD)
 - Generación de informes y análisis de ventas

### Frontend

- Autenticación y autorización de usuarios
- Visualización y búsqueda de productos
- Gestión del carrito de compras
- Proceso de pago y compra
- Historial de pedidos del cliente
- Informes y análisis de ventas

## Tecnologías utilizadas
### Backend

- Java
- Spring Boot
- Spring Data JPA
- Base de datos MySQL
- Hybernate
- Maven

### Frontend

- React
- Redux
- React Router
- Axios
- Bootstrap

## Configuración e instalación
### Backend

- Clona el repositorio: git clone https://github.com/julianch07/Final_Apividejuegos.git
- Navega al directorio del backend: cd backend
- Configura la conexión a la base de datos en el archivo application.properties
- Compila el proyecto: mvn clean install
- Inicia el servidor: mvn spring-boot:run

### Frontend

- Navega al directorio del frontend: cd frontend
- Instala las dependencias: npm install
- Inicia el servidor de desarrollo: npm run dev 
- Abre http://localhost:5173 en tu navegador web

### Uso
Una vez que hayas configurado e instalado tanto el backend como el frontend, podrás acceder a la aplicación de la tienda de videojuegos a través de http://localhost:3000. Desde allí, podrás explorar los diferentes productos, agregar artículos al carrito de compras, realizar pedidos y gestionar tu perfil de cliente. Además, si tienes los permisos adecuados, podrás administrar los productos, clientes y ventas desde el panel de administración.

## Contribución
Si deseas contribuir a este proyecto, puedes seguir los siguientes pasos:

1. Realiza un fork del repositorio
2. Crea una nueva rama para tu funcionalidad: git checkout -b mi-nueva-funcionalidad
3. Realiza los cambios necesarios y realiza commits descriptivos
4. Envía tus cambios a tu repositorio fork: git push origin mi-nueva-funcionalidad
5. Crea un nuevo Pull Request en este repositorio
