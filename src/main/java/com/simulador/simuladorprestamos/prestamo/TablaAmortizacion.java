package com.simulador.simuladorprestamos.prestamo;

public class TablaAmortizacion {
    private Integer mes;
    private double montoDeCuota;
    private double intereses;
    private double capital;
    private double saldo;

    public TablaAmortizacion(int mes, double montoDeCuota, double intereses, double capital, double saldo) {
        this.mes = mes;
        this.montoDeCuota = montoDeCuota;
        this.intereses = intereses;
        this.capital = capital;
        this.saldo = saldo;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public double getMontoDeCuota() {
        return montoDeCuota;
    }

    public void setMontoDeCuota(double montoDeCuota) {
        this.montoDeCuota = montoDeCuota;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return mes + "\t" + montoDeCuota + "\t\t" + intereses + "\t\t" + capital + "\t\t" + saldo;
    }
}
