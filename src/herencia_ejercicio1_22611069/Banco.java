package herencia_ejercicio1_22611069;


//ken
public class Banco {
    private CuentaBancaria[] cuentas;
    private int cantidadCuentas;

    public Banco() {
        cuentas = new CuentaBancaria[20];
        cantidadCuentas = 0;
    }

    public void agregarCuenta(CuentaBancaria c) {
        if (c == null) {
            throw new IllegalArgumentException(
                "La cuenta no puede ser nula"
            );
        }

        int i;

        for (i = 0; i < cantidadCuentas; i++) {
            if (cuentas[i].getNumero() == c.getNumero()) {
                throw new IllegalArgumentException(
                    "Ya existe una cuenta con ese número"
                );
            }
        }

        if (cantidadCuentas >= cuentas.length) {
            throw new IllegalStateException(
                "El banco no puede registrar más cuentas"
            );
        }

        cuentas[cantidadCuentas] = c;
        cantidadCuentas++;
    }

    public CuentaBancaria buscarPorNumero(int numero) {
        return buscarAuxiliar(numero, 0);
    }

    private CuentaBancaria buscarAuxiliar(int numero, int indice) {
        if (indice >= cantidadCuentas) {
            throw new IllegalArgumentException(
                "No existe una cuenta con el número " + numero
            );
        }

        if (cuentas[indice].getNumero() == numero) {
            return cuentas[indice];
        }

        return buscarAuxiliar(numero, indice + 1);
    }

    public void ordenarPorSaldo() {
        ordenarPasada(0, cantidadCuentas);
    }

    private void ordenarPasada(int indice, int limite) {
        if (limite <= 1) {
            return;
        }

        if (indice >= limite - 1) {
            ordenarPasada(0, limite - 1);
            return;
        }

        if (cuentas[indice].getSaldo()
                > cuentas[indice + 1].getSaldo()) {

            CuentaBancaria temporal = cuentas[indice];
            cuentas[indice] = cuentas[indice + 1];
            cuentas[indice + 1] = temporal;
        }

        ordenarPasada(indice + 1, limite);
    }

    public void transferir(int numeroOrigen, int numeroDestino,
                           double monto) {
        if (numeroOrigen == numeroDestino) {
            throw new IllegalArgumentException(
                "No se puede transferir a la misma cuenta"
            );
        }

        if (monto <= 0) {
            throw new IllegalArgumentException(
                "El monto debe ser mayor que cero"
            );
        }

        CuentaBancaria origen = buscarPorNumero(numeroOrigen);
        CuentaBancaria destino = buscarPorNumero(numeroDestino);

        if (!origen.retirar(monto)) {
            throw new IllegalStateException(
                "La cuenta origen no tiene fondos suficientes"
            );
        }

        try {
            destino.depositar(monto);

        } catch (RuntimeException e) {
            origen.reintegrar(monto);
            throw e;
        }
    }

    public void aplicarInteresesGlobal() {
        int i;

        for (i = 0; i < cantidadCuentas; i++) {
            try {
                cuentas[i].registrarIntereses();

            } catch (RuntimeException e) {
                System.out.println(
                    "No se pudieron aplicar intereses a la cuenta "
                    + cuentas[i].getNumero() + ": " + e.getMessage()
                );
            }
        }
    }

    public double saldoTotalDelBanco() {
        return saldoTotalAuxiliar(0);
    }

    private double saldoTotalAuxiliar(int indice) {
        if (indice >= cantidadCuentas) {
            return 0;
        }

        return cuentas[indice].getSaldo()
                + saldoTotalAuxiliar(indice + 1);
    }

    public void generarReporte() {
        ordenarPorSaldo();

        int i;

        System.out.println("========== REPORTE DEL BANCO ==========");

        for (i = 0; i < cantidadCuentas; i++) {
            System.out.println(cuentas[i].toString());
        }

        System.out.println(
            "Saldo total del banco: " + saldoTotalDelBanco()
        );
    }

    public int getCantidadCuentas() {
        return cantidadCuentas;
    }
}