# 🐾 Sistema de Gestión Veterinaria

Proyecto desarrollado en **Java con Swing**, que permite gestionar información sobre mascotas, propietarios, veterinarios y consultas médicas. Utiliza patrones de diseño, persistencia binaria y una arquitectura modular para garantizar escalabilidad y mantenibilidad.

---

## 🛠️ Funcionalidades principales

✅ Registrar, listar, actualizar y eliminar **mascotas**  
✅ Registrar y consultar **consultas veterinarias**  
✅ Gestión de **personas** (propietarios y veterinarios)  
✅ Persistencia de datos en archivos binarios (`.dat`)  
✅ Validación de datos mediante **excepciones personalizadas**  
✅ Interfaz gráfica intuitiva con **Java Swing**  
✅ Estructura orientada a objetos con **herencia, abstracción y polimorfismo**

---

## 📁 Estructura del proyecto

📦 proyecto-raiz
┣ 📁 controller → Lógica entre vista y modelo
┃ ┗ 📄 MascotaControlador.java
┃ ┗ 📄 ConsultaControlador.java
┃ ┗ 📄 PersonaControlador.java
┃
┣ 📁 model → Entidades del dominio
┃ ┗ 📄 Persona.java (abstracta)
┃ ┗ 📄 Propietario.java
┃ ┗ 📄 Veterinario.java
┃ ┗ 📄 Mascota.java
┃ ┗ 📄 EventoClinico.java (abstracta)
┃ ┗ 📄 Consulta.java
┃
┣ 📁 dto → Transferencia de datos
┃ ┗ 📄 MascotaDTO.java
┃ ┗ 📄 ConsultaDTO.java
┃ ┗ 📄 VeterinarioDTO.java
┃ ┗ 📄 VacunaDTO.java
┃
┣ 📁 dao → Acceso a datos
┃ ┗ 📄 MascotaDAO.java
┃ ┗ 📄 PersonaDAO.java
┃ ┗ 📄 ConsultaDAO.java
┃
┣ 📁 persistence → Manejo de archivos
┃ ┗ 📄 GestorPersistencia.java (Singleton)
┃ ┗ 📄 ArchivoManager.java
┃ ┗ 📄 ArchivoUtil.java
┃
┣ 📁 exception → Validaciones y errores personalizados
┃ ┗ 📄 DatoInvalidoException.java
┃ ┗ 📄 ClaveVaciaException.java
┃ ┗ 📄 EspecieVaciaException.java
┃ ┗ 📄 EdadInvalidaException.java
┃ ┗ 📄 NombreVacioException.java
┃
┣ 📁 utils → Herramientas auxiliares
┃ ┗ 📄 IDGenerator.java
┃
┣ 📁 view → Interfaces gráficas
┃ ┗ 📄 FormMascota.java
┃ ┗ 📄 FormPersona.java
┃ ┗ 📄 FormConsulta.java
┃
┣ 📁 data → Archivos de datos binarios
┃ ┗ 📄 mascotas.dat
┃ ┗ 📄 personas.dat
┃ ┗ 📄 consultas.dat
┃
┗ 📄 Main.java → Punto de entrada de la aplicación

yaml
Copiar
Editar

---

## 🧠 Patrones y principios aplicados

- **DAO (Data Access Object)** para encapsular la lógica de acceso a datos
- **DTO (Data Transfer Object)** para comunicar la lógica con la interfaz
- **Singleton** en la clase `GestorPersistencia` para garantizar acceso único al almacenamiento
- **Abstracción y polimorfismo** con clases como `Persona` y `EventoClinico`
- **Validación robusta** con excepciones personalizadas

---

## ▶️ Cómo ejecutar

1. Abre el proyecto en **IntelliJ IDEA** o **NetBeans**
2. Asegúrate de tener Java 8 o superior
3. Ejecuta la clase `Main.java`
4. La interfaz gráfica se abrirá automáticamente

> La carpeta `data/` se crea automáticamente si no existe. Los archivos `.dat` se usan para guardar los datos de forma persistente.

---

## 📌 Notas adicionales

- Proyecto educativo para reforzar principios de POO, estructura de capas y manejo de archivos.
- Puede extenderse fácilmente para incluir funcionalidades como vacunación, historial médico o reportes PDF.

---

## 👨‍💻 Autor

- Miguel Ángel Guarín
- Proyecto académico – 2025
