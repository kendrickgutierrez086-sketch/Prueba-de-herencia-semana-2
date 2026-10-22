/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herencia_ejercicio1_22611069;
import java.util.Calendar;
/**
 *
 * @author kendr
 */
public class testCalendar {
    
    
    static void main(){
        Calendar fecha=Calendar.getInstance();
        System.out.println(fecha);
        
        int year=fecha.get(Calendar.YEAR);
        System.out.println(year);
    }
}
