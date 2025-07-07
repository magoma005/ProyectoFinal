# 🐾 Proyecto Veterinaria – Ficha Clínica

## 📌 Descripción

Este proyecto es una aplicación de consola desarrollada en **Java** que gestiona la información de propietarios, mascotas, consultas y veterinarios en un sistema veterinario básico.

Permite:
- Registrar propietarios con sus datos personales.
- Registrar múltiples mascotas por propietario.
- Registrar múltiples consultas por mascota, asignadas a veterinarios.
- Generar códigos automáticos únicos para consultas y mascotas.
- Visualizar la ficha clínica completa en consola.

---

## 🚀 Tecnologías utilizadas

- **Java SE 17**
- IDE: NetBeans / IntelliJ IDEA

---

## 🗃️ Estructura de clases

| Clase | Descripción |
|---|---|
| `Propietario` | Gestiona la información del propietario y su lista de mascotas. |
| `Mascota` | Contiene datos de la mascota y delega su historial. |
| `Consulta` | Registra la información de cada consulta médica. |
| `Veterinario` | Guarda los datos del veterinario asignado a la consulta. |
| `Historial` | Maneja la lista de consultas de una mascota. |
| `IDGenerator` | Genera códigos automáticos únicos para consultas y mascotas. |
| `Main` | Contiene el flujo principal de registro y muestra de información. |

---


## 💡 Cambios implementados (Refactorización)

✔️ Encapsulamiento completo de atributos.  
✔️ Setters con validación (no se aceptan campos vacíos o inválidos).  
✔️ **Uso de `LocalDate`** para el manejo correcto de fechas.  
✔️ Implementación de la clase `Historial` para delegar la gestión de consultas.  
✔️ Generación de IDs automáticos con `IDGenerator`.  
✔️ Comentarios explicativos en el código para evidenciar las mejoras.  
✔️ Protegida la colección interna de mascotas (`ArrayList`).  
✔️ Código limpio, estructurado y listo para ejecución desde consola.

---

## 📝 Ejecución

1. Compila el proyecto:
    ```bash
    javac *.java
    ```

2. Ejecuta la clase Main:
    ```bash
    java Main
    ```

3. Ingresa los datos según lo solicite el programa en consola.

---

## ✅ Resultado esperado

Al finalizar, se mostrará en consola la **ficha clínica completa**, incluyendo:

- Datos del propietario
- Información de cada mascota
- Consultas médicas con fecha y veterinario asignado

---
✨ Autor
Taller práctico – Programación Orientada a Objetos
💻 Miguel Angel Guarin Ospina
📧 mago123005@gmail.com
