package ar.edu.unju.escmi.tp4.collections;

import ar.edu.unju.escmi.tp4.dominio.Inmueble;
import ar.edu.unju.escmi.tp4.dominio.Terreno;
import java.util.ArrayList;
import java.util.List;

public class CollectionInmueble { 
    public static List<Inmueble> inmuebles = new ArrayList<>();

    // Agregar un inmueble (puede ser vivienda o terreno)
    public static void agregarInmueble(Inmueble inmueble) {
        inmuebles.add(inmueble);
    }

    // Buscar inmueble por código
    public static Inmueble buscarInmueble(String codigo) {
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getCodigo().equals(codigo)) {
                return inmueble;
            }
        }
        return null;
    }

    // Cambiar estado de un inmueble
    public static void cambiarEstadoInmueble(String codigo) {
        for (Inmueble inmueble : inmuebles) {
            if (inmueble.getCodigo().equals(codigo)) {
                inmueble.setEstado(false);
            }
        }
    }

    // Calcular el monto total de las ventas de terrenos
    public static double calcularMontoTotalVentas() {
        return inmuebles.stream()
                        .filter(inmueble -> inmueble instanceof Terreno && !inmueble.isEstado())
                        .mapToDouble(inmueble -> ((Terreno) inmueble).getPrecio())
                        .sum();
    }
}
