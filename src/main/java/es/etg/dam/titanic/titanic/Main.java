package es.etg.dam.titanic.titanic;

import es.etg.dam.titanic.emergencia.ServicioEmergencias;

public class Main {
    
    public static void main(String[] args) {
        ServicioEmergencias servicio = new ServicioEmergencias();
        servicio.ejecutarSimulacion();
    }
}
