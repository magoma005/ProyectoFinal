package DAO;

import DTO.ConsultaDTO;
import persistencia.ArchivoManager;
import java.util.ArrayList;
import java.util.List;

// Esta clase gestiona los datos en memoria y también los guarda de forma persistente en un archivo de texto plano.
public class ConsultaDAO {

    private final List<ConsultaDTO> consultas; // Lista en memoria que simula una base de datos
    private final ArchivoManager archivo;      // Manejador de archivos para persistencia en archivo .dat

    // Constructor que inicializa el archivo y carga las consultas desde él
    public ConsultaDAO() {
        archivo = new ArchivoManager("data/consultas.dat"); // Archivo donde se almacenan las consultas
        consultas = new ArrayList<>();                      // Inicializa la lista en memoria
        cargarDesdeArchivo();                               // Carga las consultas almacenadas previamente
    }

    // Método para guardar una nueva consulta
    public void guardar(ConsultaDTO dto) {
        consultas.add(dto);                            // Agrega la consulta en la lista en memoria
        archivo.escribirLinea(toLineaArchivo(dto));    // Agrega la consulta al final del archivo .dat
    }

    // Retorna una copia de todas las consultas almacenadas
    public List<ConsultaDTO> listar() {
        return new ArrayList<>(consultas); // Retorna una copia para evitar modificaciones externas
    }

    // Actualiza una consulta en la posición indicada
    public void actualizar(int indice, ConsultaDTO dto) {
        if (indice >= 0 && indice < consultas.size()) {
            consultas.set(indice, dto);   // Reemplaza la consulta en la lista
            sobrescribirArchivo();        // Reescribe todo el archivo con los nuevos datos
        }
    }

    // Elimina una consulta según el índice indicado
    public void eliminar(int indice) {
        if (indice >= 0 && indice < consultas.size()) {
            consultas.remove(indice);     // Elimina la consulta de la lista
            sobrescribirArchivo();        // Reescribe todo el archivo sin la consulta eliminada
        }
    }

    // Carga todas las consultas desde el archivo al iniciar el programa
    private void cargarDesdeArchivo() {
        consultas.clear();                       // Limpia la lista actual
        for (String linea : archivo.leerLineas()) {
            ConsultaDTO dto = desdeLineaArchivo(linea); // Convierte cada línea del archivo a un objeto ConsultaDTO
            if (dto != null) consultas.add(dto);        // Agrega a la lista si la conversión fue válida
        }
    }

    // Convierte una consulta a una línea de texto para guardar en el archivo
    private String toLineaArchivo(ConsultaDTO dto) {
        return dto.getFecha() + "," +
                dto.getMascota() + "," +
                dto.getServicio() + "," +
                dto.getDiagnostico(); // Los datos se separan por comas
    }

    // Convierte una línea de texto del archivo a un objeto ConsultaDTO
    private ConsultaDTO desdeLineaArchivo(String linea) {
        String[] partes = linea.split(","); // Separa la línea por comas
        if (partes.length != 4) return null; // Si no hay exactamente 4 partes, es inválida

        return new ConsultaDTO(partes[0], partes[1], partes[2], partes[3]); // Crea el objeto
    }

    // Reescribe el archivo completo con todas las consultas actuales
    private void sobrescribirArchivo() {
        List<String> lineas = new ArrayList<>();
        for (ConsultaDTO dto : consultas) {
            lineas.add(toLineaArchivo(dto)); // Convierte cada consulta a línea de archivo
        }
        archivo.sobrescribirArchivo(lineas); // Escribe todas las líneas en el archivo
    }
}
