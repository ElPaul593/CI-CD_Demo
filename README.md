# 🧩 CI/CD: ToDo API Project

## 📌 Descripción del Proyecto
Este proyecto es una API REST para la gestión de tareas (“To-Do List”), desarrollada con Spring Boot como parte de una práctica de arquitectura backend y configuración de pipelines CI/CD.
El objetivo principal del proyecto es demostrar:
- Construcción de una API modular con buenas prácticas (MVC + Service + Repository).
- Implementación de un CRUD completo con persistencia en base de datos.
- Ejecución de pruebas unitarias.
- Automatización de Build/Test/Deploy mediante CI/CD.
- Despliegue automático en una instancia AWS EC2 usando SSH.

---

## 🏗️ Arquitectura del Proyecto
La estructura sigue un patrón Clean Architecture + Spring MVC:
```bash
src/main/java/com/cicdexample/demo/
 ├── controller      → Controladores REST (endpoints)
 ├── service         → Lógica del negocio
 ├── repository      → Acceso a datos con JPA
 ├── model           → Entidades JPA
 ├── exception       → Excepciones personalizadas
 └── DemoAplication.java
```
Además incluye:
- TaskNotFoundException para manejar errores.
- TaskService con lógica CRUD completa.
- TaskController con endpoints REST.

---

## 🛠️ Tecnologías utilizadas
- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2
- Maven 3.x
- JUnit 5

Para CI/CD:
- GitLab CI
- AWS EC2
- SSH Deployment

---

## 🚀 Cómo ejecutar el proyecto localmente
### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/ElPaul593/CI-CD_Demo.git
cd CI-CD_Demo
```

### 2️⃣ Compilar
```bash
mvn clean package
```

### 3️⃣ Ejecutar
```bash
mvn spring-boot:run
```

### 4️⃣ Acceder a la API
```bash
http://localhost:8080/api/tasks
```

---

## API Documentación
Base URL:
```bash
/api/tasks
```

---

### 1️⃣ Crear tarea

**POST** ```/api/tasks```

**REQUEST BODY**
```json
{
  "title": "Hacer tareas",
  "description": "Terminar el proyecto de Spring",
  "completed": false
}
```

**RESPONSES**

| Código | Descripción |
|-------------|-------------|
| **201** CREATED | Tarea creada exitosamente |
| **400** BAD REQUEST | Error en el cuerpo enviado |

**EJEMPLO DE RESPUESTA**
```json
{
  "id": 1,
  "title": "Hacer tareas",
  "description": "Terminar el proyecto de Spring",
  "completed": false
}
```

---

### 2️⃣ Listar todas las tareas

**GET** ```/api/tasks```

**RESPONSE**
```json
[
  {
    "id": 1,
    "title": "Hacer tareas",
    "description": "Terminar Spring",
    "completed": false
  }
]
```

---

### 3️⃣ Obtener tarea por Id

**GET** ```/api/tasks/{id}```

**RESPONSES**

| Código | Descripción |
|-------------|-------------|
| **200** OK | TAREA OBTENIDA EXITOSAMENTE |
| **404** NOT FOUND | TAREA NO ENCONTRADA |

**EJEMPLO DE RESPUESTA**
```json
{
  "id": 1,
  "title": "Hacer tareas",
  "description": "Terminar Spring",
  "completed": false
}
```

---

### 4️⃣ Actualizar tarea

**PUT** ```/api/tasks/{id}```

**REQUEST BODY**
```json
{
  "title": "Nuevo título",
  "description": "Descripción actualizada",
  "completed": true
}   
```

**RESPONSES**

| Código | Descripción |
|-------------|-------------|
| **200** OK | TAREA OBTENIDA EXITOSAMENTE |
| **404** NOT FOUND | TAREA NO ENCONTRADA |

---

### 5️⃣ Eliminar tarea

**DELETE** ```/api/tasks/{id}```

**RESPONSES**

| Código | Descripción |
|-------------|-------------|
| **204** NO CONTENT | ELIMINADA |
| **404** NOT FOUND | NO EXISTE |

--- 

## 🧪 Pruebas Unitarias
Ejecutar las pruebas:
```bash
mvn test
```
Incluye pruebas a:
- Lógica de negocio en TaskService.
- Validación de excepciones.
- (Opcional) Tests de controller con MockMvc.

---

## ⚙️ CI/CD — Pipeline
Este proyecto implementa un pipeline CI/CD completo en GitLab, con el objetivo de automatizar el ciclo de vida de desarrollo:
- Compilación del proyecto (Build)
- Ejecución de pruebas (Test)
- Despliegue automático en AWS EC2 (Deploy)

El pipeline está disponible públicamente en el repositorio GitLab:

🔗 Repositorio:
https://gitlab.com/jjjosueva/CI-CD_Demo

🔗 Pipelines ejecutados:
https://gitlab.com/jjjosueva/CI-CD_Demo/-/pipelines

--- 

### ⚙️ Estructura del Pipeline

En este pipeline se definen las siguientes etapas principales:

### 1️⃣ Build
Compila el proyecto usando Maven:
```bash
mvn clean install
```

### 2️⃣ Test
Ejecutar pruebas unitarias
```bash
mvn test
```
Si alguna prueba falla → el pipeline se detiene.

### 3️⃣ Packing
```bash
mvn clean package
```

### 4️⃣ Deploy (despliegue en EC2)
El pipeline se conecta mediante SSH a una instancia EC2 y:
- Transfiere el .jar generado
- Detiene el servicio en ejecución (si aplica)
- Inicia la nueva versión:

Ejemplo de configuración:
```bash
deploy:
  stage: deploy
  before_script:
    - apk update && apk add --no-cache openssh-client
  script:
    - chmod 600 /builds/202510/to-do-list.tmp/AWS_KEY
    - nohup ssh -o StrictHostKeyChecking=no -i $AWS_KEY ubuntu@3.145.138.105 "nohup pkill -f 'java -jar'  > /dev/null 2>&1 &"
    - nohup ssh -o StrictHostKeyChecking=no -i $AWS_KEY ubuntu@3.145.138.105 "nohup java -jar /home/ubuntu/to-do-list-api/api.jar > /dev/null 2>&1 &"
  environment: production
```

---

### ✔️ Beneficios del CI/CD Implementado
- 🔄 Eliminación de despliegues manuales
- 👨‍💻 Código siempre probado antes de ir a producción
- ⚡ Despliegues rápidos y confiables
- 🔒 Uso de variables seguras en GitLab
- ☁️ Integración total con AWS EC2
- 📦 Artefactos de build almacenados automáticamente
- 🚨 Notificación instantánea de errores

--- 

## ✨ Autores
Proyecto desarrollado por estudiantes de la materia
Metodología de Desarrollo de Software (UDLA). Integrantes:
- Edwin Josue Valencia
- Paul Alejandro Larrea
- Matea Gabriel Puga
- Pablo Alexander Criollo
- Eric Rodrigo Mullo
- Víctor Andrés Suquilanda

---

## 📚 Licencia
MIT — puede usarse libremente para estudios y prácticas.
