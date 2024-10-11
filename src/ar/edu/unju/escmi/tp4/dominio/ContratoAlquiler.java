package ar.edu.unju.escmi.tp4.dominio;

import java.time.LocalDate;

public class ContratoAlquiler extends Contrato{
	private double duracion;
	private double gastosInm;
	private Vivienda vivienda;
	
    public ContratoAlquiler(String codigo, LocalDate fechaContrato, Cliente cliente, Inmobiliaria inmobiliaria,
			double duracion, double gastosInm, Vivienda vivienda) {
		super(codigo, fechaContrato, cliente, inmobiliaria);
		this.duracion = duracion;
		this.gastosInm = gastosInm;
		this.vivienda = vivienda;
	} 

	public double calcularMontoTotal() {
        return vivienda.getPrecio() + gastosInm;
    }
    
    public void mostrarDatos() {
    	System.out.println("----------------------");
    	System.out.println("Codigo del contrato: "+codigo);
    	System.out.println("Fecha del contrato: "+fechaContrato);
    	System.out.println("Duracion: " + duracion);
    	System.out.println("Dirección: " + vivienda.getDireccion());
    	System.out.println("Precio: " + vivienda.getPrecio());
    	System.out.println("Gastos de inmobiliaria: " + gastosInm);
    	System.out.println("Monto Total Alquiler: " + calcularMontoTotal());
    	System.out.println("DATOS DE INMOBILIARIA");
		System.out.println("- Nombre: "+inmobiliaria.getNombre());
		System.out.println("- Dirección: "+inmobiliaria.getDireccion());
		System.out.println("- Teléfono: "+inmobiliaria.getTelefono());
    }
}