package com.simulador.simuladorprestamos.prestamo;

public class Prestamo {
    private String tipoPrestamo;
    private String tipoSistema;
    private Integer plazoPrestamo;
    private Float montoPrestamo;

    public Prestamo(String tipoPrestamo, String tipoSistema, Integer plazoPrestamo, Float montoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
        this.tipoSistema = tipoSistema;
        this.plazoPrestamo = plazoPrestamo;
        this.montoPrestamo = montoPrestamo;
    }

    public String getTipoPrestamo() {
        return tipoPrestamo;
    }

    public void setTipoPrestamo(String tipoPrestamo) {
        this.tipoPrestamo = tipoPrestamo;
    }

    public String getTipoSistema() {
        return tipoSistema;
    }

    public void setTipoSistema(String tipoSistema) {
        this.tipoSistema = tipoSistema;
    }

    public Integer getPlazoPrestamo() {
        return plazoPrestamo;
    }

    public void setPlazoPrestamo(Integer plazoPrestamo) {
        this.plazoPrestamo = plazoPrestamo;
    }

    public Float getMontoPrestamo() {
        return montoPrestamo;
    }

    public void setMontoPrestamo(Float montoPrestamo) {
        this.montoPrestamo = montoPrestamo;
    }
}
