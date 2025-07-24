🐾 PetControl
PetControl es un sistema de gestión para clínicas veterinarias que permite:

Registrar pacientes (mascotas) con su información.

Agendar consultas para diferentes servicios veterinarios.

Visualizar la lista de pacientes y sus datos.

Consultar las citas agendadas.

✨ Características principales
✅ Registro de pacientes con validaciones robustas (nombre, especie, edad, clave).
✅ Excepciones personalizadas para entradas inválidas.
✅ Agenda de consultas con servicio y comentario adicional.
✅ Interfaz gráfica amigable con Java Swing.
✅ Panel de bienvenida con logo y mensajes de guía.
✅ Menú de navegación intuitivo para registrar y consultar datos.
✅ Código organizado por paquetes (model, exceptions, app).
✅ Manejo de errores como NullPointerException y ArithmeticException sin detener el programa.

🖥️ Requisitos
Java JDK 17 o superior.

IDE recomendado: IntelliJ IDEA o NetBeans.

⚙️ Instalación y ejecución
Clona este repositorio:


git clone https://github.com/tuusuario/PetControl.git
cd PetControl
Abre el proyecto en tu IDE favorito.

Asegúrate de tener configurado el JDK 17.

Ejecuta la clase Main o VentanaPrincipal para iniciar el sistema.

🚀 Uso
Desde el menú Archivo, puedes:

Registrar un nuevo paciente.

Agendar una consulta para una mascota existente.

Desde el menú Vista, puedes:

Ver la lista de pacientes.

Ver la lista de consultas agendadas.

Explora los servicios en el panel izquierdo (JTree).

🔒 Validaciones y excepciones implementadas
✔️ NombreVacioException: nombre de mascota no puede estar vacío.
✔️ EdadInvalidaException: edad de mascota no puede ser negativa.
✔️ Nombre de mascota con al menos 3 caracteres.
✔️ Manejo de NullPointerException y ArithmeticException sin interrupciones.
✔️ Mensajes claros al usuario cuando ocurre un error.

📁 Estructura de carpetas
css
Copiar
Editar
src/
├── app/
│    └── MainTallerExcepciones.java
├── exceptions/
│    ├── EdadInvalidaException.java
│    └── NombreVacioException.java
├── imagenes/
│    └── logo.jpg
├── model/
│    ├── Mascota.java
│    └── EventoClinico.java
├── vistas/
│    ├── FormMascota.java
│    └── FormConsulta.java
└── VentanaPrincipal.java
👨‍💻 Autor
Miguel Angel Guarin

❤️ Créditos
Proyecto desarrollado como parte de prácticas de programación orientada a objetos y desarrollo de interfaces gráficas en Java.

📌 Notas
Persistencia: actualmente usa archivos locales, ideal para prácticas.

El logo está en src/imagenes/logo.jpg y puedes reemplazarlo por el de tu clínica.

Sistema diseñado para extensiones futuras como conexión a MySQL o exportación de datos.

