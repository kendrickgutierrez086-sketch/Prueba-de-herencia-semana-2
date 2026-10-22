package herencia_ejercicio1_22611069;

import java.util.Calendar;

public class CuentaPlazoFijo extends CuentaBancaria {
    private Calendar findePlazo;
    private double intereses;
    public CuentaPlazoFijo(int numero, String cliente, String moneda) {
        super(numero, cliente, moneda, TipoCuenta.PLAZO);
        findePlazo=null;
        intereses=0;
    }
    public void setPlazo(int anio, int mes, int dia) {
        try {
            Calendar fecha = Calendar.getInstance();
            fecha.setLenient(false);
            fecha.clear();
            fecha.set(anio, mes - 1, dia);
            fecha.getTime();

            findePlazo = fecha;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La fecha de vencimiento no es válida"
            );
        }

        if (mes<1||mes>12) {
            throw new IllegalArgumentException(
                "El mes debe estar entre 1 y 12"
            );
        }
    }
    public boolean vencida() {
        return findePlazo != null
                && !Calendar.getInstance().before(findePlazo);
    }

    public boolean retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException(
                "El retiro debe ser mayor que cero"
            );
        }

        if (!vencida()) {
            if (monto>intereses){
                return false;
            }

            intereses=intereses-monto;

            registrarMovimiento(
                "RETIRO DE INTERESES -" + monto
                + " -> intereses: " + intereses
            );

            return true;
        }

        pasarInteresesASaldo();

        return super.retirar(monto);
    }

    public void registrarIntereses() {
        double nuevoInteres = saldo * tipo.tasa();

        if (!vencida()) {
            intereses = intereses + nuevoInteres;

            if (nuevoInteres != 0) {
                registrarMovimiento(
                    "INTERESES ACUMULADOS +" + nuevoInteres
                    + " -> intereses: " + intereses
                );
            }

        } else {
            pasarInteresesASaldo();
            super.registrarIntereses();
        }
    }

    private void pasarInteresesASaldo() {
        if (intereses > 0) {
            saldo = saldo + intereses;

            registrarMovimiento(
                "INTERESES LIBERADOS +" + intereses
                + " -> saldo: " + saldo
            );

            intereses = 0;
        }
    }

    public void reintegrar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException(
                "El reintegro debe ser mayor que cero"
            );
        }

        if (!vencida()) {
            intereses = intereses + monto;

            registrarMovimiento(
                "REVERSA A INTERESES +" + monto
                + " -> intereses: " + intereses
            );

        } else {
            super.reintegrar(monto);
        }
    }

    public void quienSoy() {
        System.out.println("Soy una CuentaPlazoFijo");
    }

    public String toString() {
        String fecha = "No definida";

        if (findePlazo != null) {
            fecha = findePlazo.getTime().toString();
        }

        return super.toString()+ " | Vencimiento: " +fecha+ " | Intereses acumulados: " + intereses;
    }
}