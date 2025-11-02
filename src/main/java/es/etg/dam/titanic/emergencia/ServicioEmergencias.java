package es.etg.dam.titanic.emergencia;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import es.etg.dam.titanic.botes.Pasajeros;
import es.etg.dam.titanic.emergencia.GeneradorInforme;

public class ServicioEmergencias {

    private static final int NUM_BOTES = 20;
    private static final String LETRA_BOTE = "B";

    private static final String COMANDO_JAVA = "java";
    private static final String CLASE_BOTE = "es.etg.dam.titanic.botes.Bote";
    private static final String RUTA_CLASES = "target/classes";
    private static final String SEPARADOR_DATOS = ";";
    private static final int NUM_DATOS = 5;
    private static final String MSG_ERROR_BOTE = "Error en el bote ";

    private static final int POSICION_TOTAL = 1;
    private static final int POSICION_MUJERES = 2;
    private static final int POSICION_HOMBRES = 3;
    private static final int POSICION_NINOS = 4;

    private Pasajeros[] registro = new Pasajeros[NUM_BOTES];

    public void ejecutarSimulacion() {
        for (int i = 0; i < NUM_BOTES; i++) {
            String id;
            if (i < 10) {
                id = LETRA_BOTE + "0" + i;
            } else {
                id = LETRA_BOTE + i;
            }
            try {
                String[] comandos = { COMANDO_JAVA, "-cp", RUTA_CLASES, CLASE_BOTE, id };

                Process proceso = Runtime.getRuntime().exec(comandos);

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream()));
                String linea = br.readLine();

                proceso.waitFor();

                if (linea != null && linea.contains(SEPARADOR_DATOS)) {
                    String[] datos = linea.split(SEPARADOR_DATOS);
                    if (datos.length == NUM_DATOS) {
                        int total = Integer.parseInt(datos[POSICION_TOTAL]);
                        int mujeres = Integer.parseInt(datos[POSICION_MUJERES]);
                        int hombres = Integer.parseInt(datos[POSICION_HOMBRES]);
                        int niños = Integer.parseInt(datos[POSICION_NINOS]);

                        registro[i] = new Pasajeros(total, mujeres, hombres, niños);
                    }
                }
            } catch (Exception error) {
                System.err.println(MSG_ERROR_BOTE + id);
                error.printStackTrace();
            }
        }

        new GeneradorInforme().generar(registro);
    }

}
