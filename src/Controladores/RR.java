/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

/**
 *
 * @author USUARIO
 */
public class RR {
    
    private String nombre;
    private int inicio;
    private int End;

    public RR() {
    }
    
    

    public RR(String nombre, int inicio, int End) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.End = End;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the inicio
     */
    public int getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the End
     */
    public int getEnd() {
        return End;
    }

    /**
     * @param End the End to set
     */
    public void setEnd(int End) {
        this.End = End;
    }
    
    
    
    
}
