# 🐾 PetControl

**PetControl** es un sistema de gestión para clínicas veterinarias que permite:

- Registrar pacientes (mascotas) con su información.
- Agendar consultas para diferentes servicios veterinarios.
- Visualizar la lista de pacientes y sus datos.
- Consultar las citas agendadas.

## ✨ Características

✅ Registro de pacientes con nombre, especie, edad y clave.  
✅ Agenda de consultas con servicio y comentario adicional.  
✅ Interfaz gráfica amigable con Java Swing.  
✅ Panel de bienvenida con logo y mensajes de guía.  
✅ Menú de navegación intuitivo para registrar y consultar datos.

## 🖥️ Requisitos

- Java JDK 17 o superior.
- IDE recomendado: **IntelliJ IDEA** o **NetBeans**.

## ⚙️ Instalación y ejecución

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/PetControl.git
   cd PetControl
2. Abre el proyecto en tu IDE favorito.

3. Asegúrate de tener configurado el JDK 17.

4. Ejecuta la clase Main o VentanaPrincipal para iniciar el sistema.

🚀 Uso
Desde el menú Archivo, puedes:

Registrar un nuevo paciente.

Agendar una consulta para una mascota existente.

Desde el menú Vista, puedes:

Ver la lista de pacientes.

Ver la lista de consultas agendadas.

Explora los servicios en el panel izquierdo (JTree).

📁 Estructura de carpetas
css
Copiar
Editar
src/
 ├── imagenes/
 │    └── logo.jpg
 ├── Consulta.java
 ├── CrudMascotas.java
 ├── Historial.java
 ├── IDGenerator.java
 ├── Main.java
 ├── Mascota.java
 ├── Propietario.java
 ├── VentanaPrincipal.java
 └── Veterinario.java
👨‍💻 Autor
Miguel Angel Guarin

❤️ Créditos
Este proyecto fue creado como parte de prácticas de programación orientada a objetos y desarrollo de interfaces gráficas en Java.

📌 Notas
No requiere persistencia aún (no implementa DAO o bases de datos).

El logo se encuentra en src/imagenes/logo.jpg y puede reemplazarse por el de tu clínica.

Este sistema es una versión inicial, ideal para prácticas y ampliaciones futuras (como exportar datos o usar MySQL).
