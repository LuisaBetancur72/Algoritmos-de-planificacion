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
public class Resultados {
    private String nombre;
    private double TR;
    private double TE;

    public Resultados() {
    }

    public Resultados(String nombre, double TR, double TE) {
        this.nombre = nombre;
        this.TR = TR;
        this.TE = TE;
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
     * @return the TR
     */
    public double getTR() {
        return TR;
    }

    /**
     * @param TR the TR to set
     */
    public void setTR(double TR) {
        this.TR = TR;
    }

    /**
     * @return the TE
     */
    public double getTE() {
        return TE;
    }

    /**
     * @param TE the TE to set
     */
    public void setTE(double TE) {
        this.TE = TE;
    }
    
    
    
}
