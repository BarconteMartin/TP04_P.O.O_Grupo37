package ar.edu.unju.escmi.tp4.main;

import java.time.LocalDate;
import java.util.Scanner;

import ar.edu.unju.escmi.tp4.collections.CollectionCliente;
import ar.edu.unju.escmi.tp4.collections.CollectionContrato;
import ar.edu.unju.escmi.tp4.collections.CollectionInmueble;
import ar.edu.unju.escmi.tp4.dominio.Cliente;
import ar.edu.unju.escmi.tp4.dominio.ContratoAlquiler;
import ar.edu.unju.escmi.tp4.dominio.ContratoCVT;
import ar.edu.unju.escmi.tp4.dominio.Inmobiliaria;
import ar.edu.unju.escmi.tp4.dominio.Terreno;
import ar.edu.unju.escmi.tp4.dominio.Vivienda;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opc;

        // Precargar los datos de la inmobiliaria
        Inmobiliaria inmobiliaria = new Inmobiliaria("Inmobiliaria Jujuy", "3889876543", "Calle Minas 78");

        do {
            System.out.println("\n******** MENU ******** ");
            System.out.println("1. Registrar terreno");
            System.out.println("2. Registrar vivienda");
            System.out.println("3. Registrar cliente");
            System.out.println("4. Alquiler de vivienda");
            System.out.println("5. Venta de terreno");
            System.out.println("6. Consultar inmuebles disponibles");
            System.out.println("7. Consultar viviendas alquiladas");
            System.out.println("8. Consultar terrenos vendidos");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    registrarTerreno(scanner);
                    break;
                case 2:
                    registrarVivienda(scanner);
                    break;
                case 3:
                    registrarCliente(scanner);
                    break;

                case 4:
                    // Mostrar viviendas disponibles
                    CollectionInmueble.inmuebles.stream()
                        .filter(inmueble -> inmueble instanceof Vivienda && inmueble.isEstado())
                        .forEach(inmueble -> ((Vivienda) inmueble).mostrarDatos());

                    System.out.print("\nSeleccione el código de la vivienda a alquilar: ");
                    String codVivienda = scanner.nextLine();

                    // Buscar la vivienda por código
                    Vivienda viviendaAlquilada = (Vivienda) CollectionInmueble.buscarInmueble(codVivienda);

                    if (viviendaAlquilada != null) {
                        // Mostrar los datos de la vivienda seleccionada
                        viviendaAlquilada.mostrarDatos();

                        // Mostrar clientes
                        System.out.println("\nDatos de clientes:");
                        CollectionCliente.clientes.forEach(Cliente::mostrarDatos);

                        System.out.print("\nSeleccione el DNI del inquilino: ");
                        String dniInquilino = scanner.nextLine();

                        Cliente inquilino = CollectionCliente.buscarClientePorDNI(dniInquilino);

                        if (inquilino != null) {
                            System.out.print("\nIngrese el código del contrato de alquiler: ");
                            String codContratoAlquiler = scanner.nextLine();

                            System.out.print("\nIngrese la duración del contrato: ");
                            double duracionContrato = scanner.nextDouble();

                            System.out.print("\nIngrese los gastos de la inmobiliaria: ");
                            double gastosInm = scanner.nextDouble();

                            ContratoAlquiler contrato = new ContratoAlquiler(codContratoAlquiler, LocalDate.now(), inquilino,
                                    inmobiliaria, duracionContrato, gastosInm, viviendaAlquilada);

                            CollectionContrato.agregarContrato(contrato);
                            CollectionInmueble.cambiarEstadoInmueble(codVivienda);

                            System.out.println("\nCONTRATO REALIZADO CORRECTAMENTE");
                            contrato.mostrarDatos();
                            System.out.println("\nDATOS DEL INQUILINO");
                            inquilino.mostrarDatos();
                        } else {
                            System.out.println("\nERROR: Inquilino no encontrado\n");
                        }
                    } else {
                        System.out.println("\nERROR: Vivienda no encontrada\n");
                    }
                    break;


                case 5:
                	// Mostrar terrenos disponibles
                    CollectionInmueble.inmuebles.stream()
                        .filter(inmueble -> inmueble instanceof Terreno && inmueble.isEstado())
                        .forEach(inmueble -> ((Terreno) inmueble).mostrarDatos());

                    System.out.print("\nSeleccione el código del terreno a vender: ");
                    String codTerreno = scanner.nextLine();
                    
                    // Buscar terreno por código
                    Terreno terrenoVendido = (Terreno) CollectionInmueble.buscarInmueble(codTerreno);
                   
                    if (terrenoVendido != null) {
                    	// Mostrar los datos del terreno seleccionado
                        terrenoVendido.mostrarDatos();
                        
                        // Mostrar clientes
                        System.out.println("\nDatos de clientes:");
                        CollectionCliente.clientes.forEach(Cliente::mostrarDatos);
                        
                        System.out.print("\nSeleccione el DNI del comprador: ");
                        String dniComprador = scanner.nextLine();

                        Cliente comprador = CollectionCliente.buscarClientePorDNI(dniComprador);

                        if (comprador != null) {
                            System.out.print("\nIngrese el código del contrato: ");
                            String codContrato = scanner.nextLine();

                            System.out.print("\nIngrese los impuestos: ");
                            double impuesto = scanner.nextDouble();
                            scanner.nextLine();

                            ContratoCVT contrato = new ContratoCVT(codContrato, LocalDate.now(), comprador, inmobiliaria,
                                    terrenoVendido, impuesto);

                            CollectionContrato.agregarContratoCVT(contrato);
                            CollectionInmueble.cambiarEstadoInmueble(codTerreno);

                            System.out.println("\nCONTRATO REALIZADO CORRECTAMENTE");
                            contrato.mostrarDatos();
                            System.out.println("\nDATOS DEL COMPRADOR");
                            comprador.mostrarDatos();
                        } else {
                            System.out.println("\nERROR: Comprador no encontrado\n");
                        }
                    } else {
                        System.out.println("\nERROR: Terreno no encontrado\n");
                    }
                    break;

                case 6:
                    System.out.println("\n1.Consultar Viviendas");
                    System.out.println("2.Consultar Terrenos");
                    int op2 = scanner.nextInt();
                    scanner.nextLine();
                    switch (op2) {
                        case 1:
                            System.out.println("\nViviendas disponibles:");
                            boolean hayViviendas = CollectionInmueble.inmuebles.stream()
                                .filter(inmueble -> inmueble instanceof Vivienda && inmueble.isEstado())
                                .peek(inmueble -> ((Vivienda) inmueble).mostrarDatos())
                                .count() > 0;

                            if (!hayViviendas) {
                                System.out.println("No hay viviendas disponibles.");
                            } else {
                                inmobiliaria.mostrarDatos();
                            }
                            break;
                        case 2:
                            System.out.println("\nTerrenos disponibles:");
                            boolean hayTerrenos = CollectionInmueble.inmuebles.stream()
                                .filter(inmueble -> inmueble instanceof Terreno && inmueble.isEstado())
                                .peek(inmueble -> ((Terreno) inmueble).mostrarDatos())
                                .count() > 0;

                            if (!hayTerrenos) {
                                System.out.println("No hay terrenos disponibles.");
                            } else {
                                inmobiliaria.mostrarDatos();
                            }
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                    break;

                case 7:
                    System.out.println("\nViviendas alquiladas:");
                    CollectionInmueble.inmuebles.stream()
                        .filter(inmueble -> inmueble instanceof Vivienda && !inmueble.isEstado())
                        .forEach(inmueble -> ((Vivienda) inmueble).mostrarDatos());
                    break;

                case 8:
                    System.out.println("\nTerrenos vendidos:");
                    CollectionInmueble.inmuebles.stream()
                        .filter(inmueble -> inmueble instanceof Terreno && !inmueble.isEstado())
                        .forEach(inmueble -> ((Terreno) inmueble).mostrarDatos());

                    double montoTotalVentas = CollectionInmueble.calcularMontoTotalVentas();
                    System.out.printf("Monto total de todas las ventas: %.2f\n", montoTotalVentas);
                    break;

                case 9:
                    System.out.println("\n*****SALIENDO DEL MENU*****\n");
                    break;
                default:
                    System.out.println("\nOPCIÓN INVÁLIDA. Intentelo nuevamente\n");
            }
        } while (opc != 9);

        scanner.close();
    }

    private static void registrarTerreno(Scanner scanner) {
        System.out.println("\nINGRESE LOS DATOS DEL TERRENO");

        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Precio de venta: ");
        double precio = scanner.nextDouble();

        System.out.print("Latitud: ");
        double latitud = scanner.nextDouble();

        System.out.print("Longitud: ");
        double longitud = scanner.nextDouble();

        System.out.print("Superficie (m²): ");
        double superficie = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine();

        Terreno terreno = new Terreno(codigo, precio, latitud, longitud, superficie, disponible);
        CollectionInmueble.agregarInmueble(terreno);

        System.out.println("\nTERRENO AGREGADO CORRECTAMENTE");
        terreno.mostrarDatos();
    }

    private static void registrarVivienda(Scanner scanner) {
        System.out.println("\nINGRESE LOS DATOS DE LA VIVIENDA");

        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();

        System.out.print("Precio de alquiler mensual: ");
        double precioAlquiler = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Cantidad de habitaciones: ");
        int cantidadHabitaciones = scanner.nextInt();

        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = scanner.nextBoolean();
        scanner.nextLine();

        Vivienda vivienda = new Vivienda(codigo, precioAlquiler, direccion, cantidadHabitaciones, disponible);
        CollectionInmueble.agregarInmueble(vivienda);

        System.out.println("\nVIVIENDA AGREGADA CORRECTAMENTE");
        vivienda.mostrarDatos();
    }

    private static void registrarCliente(Scanner scanner) {
        System.out.println("\nINGRESE LOS DATOS DEL CLIENTE");

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.nextLine();

        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese la dirección del cliente: ");
        String direccion = scanner.nextLine();

        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, apellido, dni, telefono, direccion, email);
        CollectionCliente.agregarCliente(cliente);

        System.out.println("\nCLIENTE AGREGADO CORRECTAMENTE");
        cliente.mostrarDatos();
    }
}
