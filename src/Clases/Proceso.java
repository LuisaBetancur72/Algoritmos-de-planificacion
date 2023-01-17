/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author valen
 */
public class Proceso implements Comparable<Proceso> {

    private String nombre;
    private int duracion;
    private int prioridad;
    private int inicio;
    private int end;

    public Proceso() {
    }

    public Proceso(String nombre, int duracion, int prioridad) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.prioridad = prioridad;
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
     * @return the duracion
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * @return the prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the Q
     */
    @Override
    public int compareTo(Proceso o) {
        if (this.getPrioridad() != o.getPrioridad()) {
            return this.getPrioridad() - o.getPrioridad();
        }
        return compareTo(o);
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
     * @return the end
     */
    public int getEnd() {
        return end;
    }

    /**
     * @param end the end to set
     */
    public void setEnd(int end) {
        this.end = end;
    }

}
