# 🧩 CI/CD: ToDo API Project

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

### 3️⃣ Listar todas las tareas

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