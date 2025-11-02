package es.etg.dam.titanic.emergencia;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.etg.dam.titanic.botes.Pasajeros;

public class GeneradorInforme {

    private static final String FORMATO_FECHA = "dd/MM/yyyy 'a las' HH:mm:ss";
    private static final String ARCHIVO_INFORME = "src/main/resources/Informe.md";
    private static final String TITULO_INFORME = "# SERVICIO DE EMERGENCIAS\n\n";
    private static final String TEXTO_FECHA = "Ejecución realizada el día ";
    private static final String PREFIJO_BOTE = "## ";
    private static final String TEXTO_TOTAL_SALVADOS = "- Total Salvados ";
    private static final String TEXTO_MUJERES = "  - Mujeres ";
    private static final String TEXTO_HOMBRES = "  - Hombres ";
    private static final String TEXTO_NINOS = "  - Niños ";
    private static final String TEXTO_ERROR_DATOS = "- Error: No han llegado los datos del bote\n\n";
    private static final String TEXTO_TOTAL = "## Total\n";
    private static final String SALTO_LINEA = "\n";
    private static final String SALTO_LINEA_2 = "\n\n";
    private static final String LETRA_BOTE = "B";
    private static final int VALOR_ZERO = 0;

    public void generar(Pasajeros[] registro) {
        try (FileWriter writer = new FileWriter(ARCHIVO_INFORME)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
            LocalDateTime ahora = LocalDateTime.now();

            writer.write(TITULO_INFORME);
            writer.write(TEXTO_FECHA + ahora.format(formatter) + SALTO_LINEA_2);

            int totalGlobal = VALOR_ZERO;
            int totalMujeres = VALOR_ZERO;
            int totalHombres = VALOR_ZERO;
            int totalNiños = VALOR_ZERO;

            for (int i = 0; i < registro.length; i++) {
                Pasajeros p = registro[i];
                String id;
                if (i < 10) {
                    id = LETRA_BOTE + "0" + i;
                } else {
                    id = LETRA_BOTE + i;
                }

                writer.write(PREFIJO_BOTE + id + SALTO_LINEA_2);

                if (p != null) {
                    writer.write(TEXTO_TOTAL_SALVADOS + p.getTotal() + SALTO_LINEA);
                    writer.write(TEXTO_MUJERES + p.getMujeres() + SALTO_LINEA);
                    writer.write(TEXTO_HOMBRES + p.getHombres() + SALTO_LINEA);
                    writer.write(TEXTO_NINOS + p.getNiños() + SALTO_LINEA_2);

                    totalGlobal += p.getTotal();
                    totalMujeres += p.getMujeres();
                    totalHombres += p.getHombres();
                    totalNiños += p.getNiños();
                } else {
                    writer.write(TEXTO_ERROR_DATOS);
                }
            }

            writer.write(TEXTO_TOTAL);
            writer.write(TEXTO_TOTAL_SALVADOS + totalGlobal + SALTO_LINEA);
            writer.write(TEXTO_MUJERES + totalMujeres + SALTO_LINEA);
            writer.write(TEXTO_HOMBRES + totalHombres + SALTO_LINEA);
            writer.write(TEXTO_NINOS + totalNiños + SALTO_LINEA);

        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
