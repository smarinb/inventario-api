# 🧾 Inventario API

API REST desarrollada en Java con Spring Boot para gestionar productos de inventario. Permite operaciones CRUD (crear, leer, actualizar, eliminar) sobre productos como "Pan", "Leche", etc.

Ideal como ejemplo para entrevistas de programador backend y proyectos de portfolio.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- Base de datos en memoria H2
- Validaciones con Jakarta Validation
- JUnit 5 + MockMvc para testing
- Maven

---

## ⚙️ Instalación y ejecución local

1. Clona este repositorio:

```bash
git clone https://github.com/smarinb/inventario-api.git
cd inventario-api
```

2. Ejecuta la aplicación:

```bash
./mvnw spring-boot:run
```

3. Accede a la API en:  
[http://localhost:8080/productos](http://localhost:8080/productos)

---

## 📚 Endpoints disponibles

| Método | Endpoint           | Descripción                  |
|--------|--------------------|------------------------------|
| GET    | `/productos`       | Listar todos los productos   |
| GET    | `/productos/{id}`  | Buscar producto por ID       |
| POST   | `/productos`       | Crear un nuevo producto      |
| DELETE | `/productos/{id}`  | Eliminar producto por ID     |

---

## 🛡️ Validaciones

- `nombre`: no puede estar vacío
- `precio`: debe ser mayor que 0
- `stock`: no puede ser negativo

---

## 🧪 Pruebas automatizadas

El proyecto incluye cobertura de pruebas:

- Unitarias (`InventarioServiceTest`)
- De integración (`ProductoControllerTest`) usando MockMvc y base H2

Para ejecutar los tests:

```bash
./mvnw test
```

---

## 📥 Ejemplo de JSON para POST

```json
{
  "nombre": "Café",
  "precio": 2.5,
  "stock": 20
}
```

---

## 🧑‍💻 Autor

Proyecto desarrollado por **Sergio Marín**  
📧 Contacto: smarinb@gmail.com  
🔗 Linkedin: [https://www.linkedin.com/in/sergio-mar%C3%ADn-b10364249/](https://www.linkedin.com/in/sergio-mar%C3%ADn-b10364249/)
💼 Repositorio: [https://github.com/smarinb/inventario-api](https://github.com/smarinb/inventario-api)

---

## ⭐ Licencia

Este proyecto es de código abierto y puede usarse con fines educativos o de portfolio.
