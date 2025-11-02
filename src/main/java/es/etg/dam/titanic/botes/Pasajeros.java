package es.etg.dam.titanic.botes;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor

public class Pasajeros {
    private int total;
    private int mujeres;
    private int hombres;
    private int niños;
}
