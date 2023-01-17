/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Controladores;
package Vista;

import Clases.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author valen
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int cantidad;
        String nombre;
        int duracion;
        int prioridad;
       
        ArrayList<Proceso>Procesos = new ArrayList<>();
        ArrayList<RR> listado= new ArrayList<>();
            
        cantidad=Integer.parseInt(JOptionPane.showInputDialog("La cantidad de prosesos"));
        
        for (int i = 0; i < cantidad; i++) {
            Proceso Pr=new   Proceso(
                    nombre=JOptionPane.showInputDialog("Ingrese el nombre del proceso"),
                    duracion=Integer.parseInt(JOptionPane.showInputDialog("Cuanto dura el proceso")), 
                    prioridad=Integer.parseInt(JOptionPane.showInputDialog("Prioridad")));
                    
                    Procesos.add(Pr);
            
        }

        
        ControladorProceso miControladorProceso = new ControladorProceso();
        
        miControladorProceso.seleccionProceso(Procesos, listado);

    }
    
}
