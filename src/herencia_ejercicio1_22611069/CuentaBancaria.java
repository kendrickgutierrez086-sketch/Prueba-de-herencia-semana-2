/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia_ejercicio1_22611069;
import java.util.ArrayList;
import java.util.Calendar;
/**kendrick, programacion II
 *
 * 20/10/2026
 * 21/20/2026
 */
public class CuentaBancaria {
    //Atributos
    protected int numero;
    protected String cliente;
    protected double saldo;
    protected double tasa;
    protected Calendar creacion;
    protected String[]historial;
    
    protected String moneda;
    protected TipoCuenta tipo;
    protected int cantidadMovimientos;
 
    
    public CuentaBancaria(int numero, String cliente, String moneda){
         this(numero,cliente, moneda, TipoCuenta.CHEQUES);
    }
    
    public CuentaBancaria(int numero, String cliente, double tasa, String moneda){
        this.numero=numero;
        this.cliente=cliente;
        this.moneda=moneda;
        
        this.tipo=tipo;
        this.saldo=0;
        this.creacion=Calendar.getInstance();
        this.historial=new String[50];
        this.cantidadMovimientos=0;
    }
    
    public void depositar(double monto){
        if(monto<=0) {
            throw new IllegalArgumentException("El depósito debe ser mayor que cero");
        }
        saldo=saldo+monto;
        registrarMovimiento("Deposito+"+monto+"  saldo: "+saldo);
    }

    
    public boolean retirar(double monto){
        if (monto <= 0) {
            throw new IllegalArgumentException( "El retiro debe ser mayor que cero");
        }
        if(monto>saldo) {
            return false;
        }
        saldo=saldo-monto;
        registrarMovimiento("Retiro-"+monto+" saldo: "+saldo);
        return true;
    }
    
    public void reintegrar(double monto){
        if (monto<=0){
            throw new IllegalArgumentException( "El reiintegro debe ser mayor que cero");
        }

        saldo=saldo+monto;
        registrarMovimiento("Revertido+"+monto+" saldo: "+saldo);
    }
    
    public void registrarIntereses(){
        double interes=saldo*tipo.tasa();
        if(interes!=0){
           saldo=saldo+interes;
            registrarMovimiento("Inetereses +"+interes+" saldo: "+saldo);
        }
    }

    
    public void registrarMovimiento(String linea){
        try {
            if(cantidadMovimientos>=historial.length){
                throw new IllegalStateException("El historial está lleno, movimeinto no registradop");
            }
            historial[cantidadMovimientos]=linea;
            cantidadMovimientos++;

        } catch (IllegalStateException e) {
            System.out.println("Aviso: " + e.getMessage());
        }
    }

    
    public void mostrarHistorial(){
        System.out.println("**************HISTORIAL*****************");
        System.out.println("DE: "+cliente.toUpperCase());
        for (int i=0;i<cantidadMovimientos; i++) {
            System.out.println(historial[i]);
            
        }
        
    }
    public TipoCuenta getTipo(){
        return tipo;
    }
    
    public void quienSoy(){
        System.out.println("CuentaBancaria (clase base)");
    }

    public double getSaldo() {
        return saldo;
    }
    
    public String toString(){
        return String.format("Cuenta #%d | %s | $%.2f", numero,cliente,saldo, moneda, tipo);
    }
    
   }

