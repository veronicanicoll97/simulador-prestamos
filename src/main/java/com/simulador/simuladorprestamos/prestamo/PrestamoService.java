package com.simulador.simuladorprestamos.prestamo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.text.DecimalFormat;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ObjectMapper objectMapper;
    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository, ObjectMapper objectMapper) {

        this.prestamoRepository = prestamoRepository;
        this.objectMapper = objectMapper;
    }

    public ArrayList<Integer> listarPlazos() {
        return this.prestamoRepository.obtenerPlazos();
    }
    public Map<String, Integer> listarTiposPrestamos() {
        return  this.prestamoRepository.obtenerTipoPrestamo();
    }
    public ArrayList<String> listarTiposSistemas() {
        return this.prestamoRepository.obtenerTiposSistemas();
    }

    public String calcularCuotaSistemaFrances(
            Double tasaInteresAnual, Float montoPrestamo, Integer plazoPrestamo
    ) throws JsonProcessingException {
        // Calculamos la tasa de interés periódica en formato decimal.
        Float tasaInteresPeriodica = (float) (tasaInteresAnual / 12 / 100);

        // Calculamos la cuota sin redondear.
        Float cuotaSinRedondear = (float) ((montoPrestamo * tasaInteresPeriodica) /
                (1 - Math.pow((1 + tasaInteresPeriodica), -plazoPrestamo)));

        // Redondeamos la cuota.
        Float montoDeCuota = Math.round(cuotaSinRedondear * 100) / 100f;

        List<TablaAmortizacion> tablaAmortizacion = new ArrayList<>();
        double saldo = montoPrestamo;
        // Bucle para recorrer y cargar el array de datos.
        for (int i = 0; i < plazoPrestamo; i++) {
            // Calculamos los intereses.
            Float intereses = (float) (saldo * tasaInteresPeriodica);
            // Calculamos el capital.
            Float capital = montoDeCuota - intereses;
            // Actualizamos el saldo restante.
            saldo -= capital;

            Integer mes = i + 1;
            tablaAmortizacion.add(new TablaAmortizacion(mes, montoDeCuota, intereses, capital, saldo));
        }

        // Convertimos la lista de objetos TablaAmortizacion a formato JSON.
        return objectMapper.writeValueAsString(tablaAmortizacion);
    }

    public String calcularCuotaSistemaAleman(
            Double tasaInteresAnual, Float montoPrestamo, Integer plazoPrestamo
    ) throws JsonProcessingException {
        // Calculamos la tasa de interés periódica en formato decimal.
        Float tasaInteresPeriodica = (float) (tasaInteresAnual / 12 / 100);

        List<TablaAmortizacion> tablaAmortizacion = new ArrayList<>();
        double saldo = montoPrestamo;
        // Calculamos el monto del capital pagado en cada período.
        Float capitalPagado = montoPrestamo / plazoPrestamo;
        // Bucle para recorrer y cargar el array de datos.
        for (int i = 0; i < plazoPrestamo; i++) {
            // Calculamos los intereses.
            Float intereses = (float) (saldo * tasaInteresPeriodica);
            // Actualizamos el saldo restando el capital pagado.
            saldo -= capitalPagado;

            Integer mes = i + 1;
            // El monto de la cuota es la suma de los intereses y el capital pagado.
            Float montoDeCuota = intereses + capitalPagado;
            tablaAmortizacion.add(new TablaAmortizacion(mes, montoDeCuota, intereses, capitalPagado, saldo));
        }

        // Convertimos la lista de objetos TablaAmortizacion a formato JSON.
        return objectMapper.writeValueAsString(tablaAmortizacion);
    }


    public String calcularCuota(Prestamo prestamo) throws JsonProcessingException {
        String tipoPrestamo = prestamo.getTipoPrestamo();
        String tipoSistema = prestamo.getTipoSistema();
        Integer plazoPrestamo = prestamo.getPlazoPrestamo();
        Float montoPrestamo = prestamo.getMontoPrestamo();

        Map<String, Integer> prestamos = this.listarTiposPrestamos();
        Integer tasaInteresAnualPorTipoPrestamo = Integer.valueOf(prestamos.get(tipoPrestamo.toUpperCase()));
        if(Objects.equals(tipoSistema, "ALEMAN")) {
            return this.calcularCuotaSistemaAleman(
                    (double)tasaInteresAnualPorTipoPrestamo,
                    montoPrestamo,
                    plazoPrestamo
            );
        }
        else {
            return this.calcularCuotaSistemaFrances(
                    (double)tasaInteresAnualPorTipoPrestamo,
                    montoPrestamo,
                    plazoPrestamo
            );
        }
    }
}
