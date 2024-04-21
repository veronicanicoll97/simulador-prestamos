package com.simulador.simuladorprestamos.prestamo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @Autowired
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping(path = "/plazos")
    public ArrayList<Integer> plazos() {
        return this.prestamoService.listarPlazos();
    }

    @GetMapping(path = "/tipos-prestamos")
        public Map<String, Integer> tiposPrestamos() {
        return this.prestamoService.listarTiposPrestamos();
    }

    @GetMapping(path = "/tipos-sistemas")
    public ArrayList<String> tiposSistemas() {
        return this.prestamoService.listarTiposSistemas();
    }

    @PostMapping(path = "/sistema-frances")
    public String calcularCuotaPorSistema(@RequestBody Prestamo prestamo) throws JsonProcessingException {
        try {
            return this.prestamoService.calcularCuota(prestamo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
