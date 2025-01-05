package maquinaSnacks.servicio;

import maquinaSnacks.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks {

    private static final List<Snack> snacks;

    // Bloque static inicializador
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 3));
        snacks.add(new Snack("Refresco", 2));
        snacks.add((new Snack("Sandwich", 5)));
    }

    public void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public void mostrarSnacks(){
        var inventarioSnack = "";
        for(var snack: snacks){
            inventarioSnack += snack.toString() +"\n";
        }
        System.out.println("--- Snacks en el Inventario ---");
        System.out.println(inventarioSnack);
    }

    public List<Snack> getSnacks(){
        return snacks;
    }
}
