package es.etg.dam.titanic.botes;

import java.util.Random;

public class Bote {

    private static final String PUNTO_COMA = ";";
    private static final int VALOR_ZERO = 0;

    public static void main(String[] args) {

        String idBote = args[VALOR_ZERO];
        Random random = new Random();

        try {

            int tiempo = 2000 + random.nextInt(4001);
            Thread.sleep(tiempo);

            int total = 1 + random.nextInt(100);
            int mujeres = random.nextInt(total);
            int hombres = random.nextInt(total - mujeres);
            int niños = total - mujeres - hombres;

            System.out.println(
                    idBote + PUNTO_COMA + total + PUNTO_COMA + mujeres + PUNTO_COMA + hombres + PUNTO_COMA + niños);

        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }
}
