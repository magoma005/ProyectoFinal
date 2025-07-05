# 🐾 Clínica Veterinaria – Registro de Mascotas y Consultas

Aplicación de consola en **Java** que permite registrar propietarios, mascotas, consultas y veterinarios, mostrando el historial clínico completo de cada mascota.

---

## 📌 Descripción general

Este proyecto implementa un sistema para:

- Registrar propietarios con sus datos personales.
- Registrar una o más mascotas por propietario.
- Registrar consultas veterinarias para cada mascota.
- Asignar un veterinario a cada consulta.
- Imprimir el historial clínico completo en consola.

---

## 💻 Tecnologías utilizadas

- Java 17+
- Scanner (`java.util.Scanner`)
- ArrayList (`java.util.ArrayList`)

---

## 🗂️ Estructura de carpetas y archivos

ClinicaVeterinaria/
├── Main.java
├── Propietario.java
├── Mascota.java
├── Consulta.java
└── Veterinario.java


---

## 📝 Clases implementadas

| Clase         | Descripción |
|---------------|-------------|
| `Propietario` | Contiene nombre, documento, teléfono y lista de mascotas. |
| `Mascota`     | Contiene nombre, especie, edad y lista de consultas. |
| `Consulta`    | Contiene código, fecha y veterinario que la realizó. |
| `Veterinario` | Contiene nombre y especialidad. |

---

## 🔗 Relaciones entre clases

- **Propietario → Mascota:** 1 a N  
- **Mascota → Consulta:** 1 a N  
- **Consulta → Veterinario:** N a 1

---

## ⚙️ Cómo compilar y ejecutar

1. Abre la terminal en la carpeta del proyecto.  
2. Compila los archivos:

```bash
javac *.java

Ejecuta la aplicación:

java Main

📋 Ejemplo de salida esperada

===== FICHA CLÍNICA =====
👤 Propietario: Laura Pérez
🆔 Documento: 1234567890
📞 Teléfono: 3001234567

📋 Mascota: Luna | Especie: Gato | Edad: 3 años
Historial de consultas:
Consulta Código: 1001
Fecha: 20250704
Veterinario: Dra. Camila Soto | Especialidad: Medicina Felina
--------------------------

📋 Mascota: Max | Especie: Perro | Edad: 5 años
Historial de consultas:
Consulta Código: 1002
Fecha: 20250705
Veterinario: Dr. Esteban Mora | Especialidad: Cirugía General
--------------------------
Consulta Código: 1003
Fecha: 20250706
Veterinario: Dr. Esteban Mora | Especialidad: Cirugía General
--------------------------

✅ Funcionalidades completadas
Registro dinámico con Scanner.

Uso de listas (ArrayList) para almacenar múltiples mascotas y consultas.

Relaciones de clases implementadas correctamente.

Impresión ordenada y estructurada del historial clínico.

✨ Autor
Taller práctico – Programación Orientada a Objetos
💻 Miguel Angel Guarin Ospina
📧 mago123005@gmail.com

🙌 Notas finales
Este proyecto hace parte de la práctica académica de POO para fortalecer el manejo de clases, relaciones y estructuras de datos en Java.
