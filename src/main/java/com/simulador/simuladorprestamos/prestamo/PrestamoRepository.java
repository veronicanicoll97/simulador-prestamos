package com.simulador.simuladorprestamos.prestamo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PrestamoRepository {

    public ArrayList<Integer> obtenerPlazos() {
        ArrayList<Integer> plazos = new ArrayList<>(Arrays.asList(6, 12, 15, 18, 24, 36, 48, 60));
        return plazos;
    }
    public Map<String, Integer> obtenerTipoPrestamo(){
        Map<String, Integer> prestamos = new HashMap<>();
        prestamos.put("CONSUMO", 10);
        prestamos.put("EDUCACION", 5);
        prestamos.put("VIVIENDA", 7);
        prestamos.put("VEHICULO 0 Km", 10);
        prestamos.put("VEHICULO USADO", 12);

        return prestamos;
    }

    public  ArrayList<String> obtenerTiposSistemas() {
        ArrayList<String> sistemas = new ArrayList<>(Arrays.asList("ALEMAN", "FRANCES"));
        return sistemas;
    }
}
