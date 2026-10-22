package herencia_ejercicio1_22611069;
import java.util.Calendar;
/**
 *
 * kendrick Joad Guteirrez Maldonado
 */
public class CuentaAhorro extends CuentaBancaria {
    
    private Calendar ultimaModificacion;
    private Calendar ultimoRetiro;
    private int retirosRealizados=0;
    private static final int MAX_RETIROS=3;
    
    
    public CuentaAhorro(int cuenta, String cliente, String moneda){
        super(cuenta, cliente, moneda, TipoCuenta.AHORRO);
        this.ultimaModificacion=Calendar.getInstance();
        this.ultimoRetiro=Calendar.getInstance();
    }
    
    public boolean status(){
        Calendar fechaActual= Calendar.getInstance();
        fechaActual.add(Calendar.MONTH,-6);
        return ultimaModificacion.after(fechaActual);
    }
    
    public void aplicarIntereses(){
        if(status()){
            double interes=saldo*tasa;
            depositar(interes);
            historial.add("Intereses aplicados: +L."+interes);
        }
        
    }
    
    
     public void depositar(double monto) {
        if (!status()) {
            throw new IllegalStateException("La cuenta de ahorro está inactiva");
        }

        super.depositar(monto);
        ultimaModificacion = Calendar.getInstance();
    }
    
    //hacer el retirar, tomar en cuenta la fecha del ultimo retiro y la cantidad maxima de retiros, 21/10/2026
    
    public boolean retirar(double monto){
        if(retirosRealizados<MAX_RETIROS){
            if(super.retirar(monto)){
               retirosRealizados++;
               ultimaModificacion=Calendar.getInstance();
               return true;
            } 
        }
        else{
            System.out.println("No se pudo realizar el retiro");
            return false;
        }
        return false;
    }
    public void reintegrar(double monto) {
        super.reintegrar(monto);
        ultimaModificacion=Calendar.getInstance();
    }
    public void quienSoy(){
        System.out.println("Soy una CuentaAHorro");
    }
    public String toString() {
        return super.toString() + "Activa:"+status();
    }
    
    
    
    
}
