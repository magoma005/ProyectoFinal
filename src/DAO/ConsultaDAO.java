package DAO;

import DTO.ConsultaDTO;
import persistencia.ArchivoManager;

import java.util.ArrayList;
import java.util.List;

//DAO para la entidad Consulta. Gestiona acceso en memoria simulando BD y persistencia en archivo.

public class ConsultaDAO {

    private final List<ConsultaDTO> consultas;   // Simulación en memoria
    private final ArchivoManager archivo;        // Persistencia en archivo plano

    public ConsultaDAO() {
        archivo = new ArchivoManager("data/consultas.dat");
        consultas = new ArrayList<>();
        cargarDesdeArchivo();
    }


    public void guardar(ConsultaDTO dto) {
        consultas.add(dto);
        archivo.escribirLinea(toLineaArchivo(dto));
    }

    public List<ConsultaDTO> listar() {
        return new ArrayList<>(consultas);
    }

    public void actualizar(int indice, ConsultaDTO dto) {
        if (indice >= 0 && indice < consultas.size()) {
            consultas.set(indice, dto);
            sobrescribirArchivo();
        }
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < consultas.size()) {
            consultas.remove(indice);
            sobrescribirArchivo();
        }
    }

    private void cargarDesdeArchivo() {
        consultas.clear();
        for (String linea : archivo.leerLineas()) {
            ConsultaDTO dto = desdeLineaArchivo(linea);
            if (dto != null) consultas.add(dto);
        }
    }

    private String toLineaArchivo(ConsultaDTO dto) {
        return dto.getFecha() + "," +
                dto.getMascota() + "," +
                dto.getServicio() + "," +
                dto.getDiagnostico();
    }

    private ConsultaDTO desdeLineaArchivo(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 4) return null;

        return new ConsultaDTO(partes[0], partes[1], partes[2], partes[3]);
    }

    private void sobrescribirArchivo() {
        List<String> lineas = new ArrayList<>();
        for (ConsultaDTO dto : consultas) {
            lineas.add(toLineaArchivo(dto));
        }
        archivo.sobrescribirArchivo(lineas);
    }
}
