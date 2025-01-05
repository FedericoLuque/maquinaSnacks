package maquinaSnacks.presentacion;

import maquinaSnacks.dominio.Snack;
import maquinaSnacks.servicio.IServicioSnacks;
import maquinaSnacks.servicio.ServicioSnackArchivos;
import maquinaSnacks.servicio.ServicioSnacksLista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {

        maquinaSnacks();

    }

    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        // Creamos el objeto para obtener el servicio de snacks (lista)
        IServicioSnacks servicioSnacks = new ServicioSnackArchivos();
        // Creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de ServicioSnacksLista ***");
        servicioSnacks.mostrarSnacks();     // Mostrar el inventario de snacks disponibles

        while(!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
            }catch (Exception e){
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
            finally {
                System.out.println();   // Imprime un salto de linea con cada iteracion
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar Nuevo Snack
                4. Salir
                Elige una opcion:\s""");

        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> {
                System.out.println("Hasta la próxima!");
                salir = true;
            }
            default -> System.out.println("Opcion invalida: " + opcion);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        System.out.print("Que snack quieres comprar (id)? ");
        var idSnack = Integer.parseInt(consola.nextLine());
        // Validar que el snack exista en la lista de snacks
        var snackEncontrado = false;
        for(var snack: servicioSnacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                //Agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("Ok, snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;
        for(var producto: productos){
            ticket += "\n\t- " + producto.getNombre() + " - " + producto.getPrecio() + " €";
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal -> " + total + " €";
        System.out.println(ticket);
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks){
        System.out.print("Nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre,precio));
        System.out.print("Tu snack se ha agregado correctamente\n");
        servicioSnacks.mostrarSnacks();
    }

}
