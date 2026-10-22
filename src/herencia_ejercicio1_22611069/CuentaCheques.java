import java.util.Scanner;
/**
 *
 * Ken
 */


public class CuentaCheques extends CuentaBancaria {
    private double limiteSobregiro;
    public CuentaCheques(int numero, String cliente, String moneda,double limiteSobregiro) {
        super(numero, cliente, moneda, TipoCuenta.CHEQUES);

        if (limiteSobregiro<0){
            throw new IllegalArgumentException("El límite de sobregiro no puede ser negativo");
        }

        this.limiteSobregiro=limiteSobregiro;
    }

    public void girarCheque(double monto) {
        if(monto<=0){
            throw new IllegalArgumentException( "El monto del cheque debe ser mayor que cero");
        }

        if (monto>saldo+limiteSobregiro) {
            throw new IllegalStateException("Fondos insuficientes para girar el cheque"
            );
        }

        saldo=saldo-monto;
        registrarMovimiento("Cheque-" +monto+" saldo: "+saldo);
    }

    public void quienSoy() {
        System.out.println("Soy una CuentaCheques");
    }

    public String toString() {
       
    }
    
}
