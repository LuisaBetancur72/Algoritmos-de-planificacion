/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.Proceso;
import Vista.Diagrama;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author valen
 */
public class ControladorProceso implements Comparator {

    Resultados R = new Resultados();

    public ControladorProceso() {
    }

    public void seleccionProceso(ArrayList<Proceso> Procesos, ArrayList<RR> listados) {

        String[] Inicio = {"Continuar", "Salir",};
        Object opcionI = JOptionPane.showInputDialog(null, "Bienvenido", "Elegir",
                JOptionPane.QUESTION_MESSAGE, null, Inicio, Inicio[0]);

        while (opcionI != "Salir") {

            String[] procesos = {"Orden de llegada", "Primero el mas Corto", "Prioridad", "Round Robin", "Salir"};
            Object opcion = JOptionPane.showInputDialog(null, "Selecciona un algoritmo", "Elegir",
                    JOptionPane.QUESTION_MESSAGE, null, procesos, procesos[0]);

            if (opcion == "Orden de llegada") {
                AlgoritmoOrdenDeLlegada(Procesos);
            }
            if (opcion == "Primero el mas Corto") {
                AlgoritmoPrimeroElMasCorto(Procesos);
            }
            if (opcion == "Prioridad") {
                AlgoritmoPrioridad(Procesos);
            }
            if (opcion == "Round Robin") {
                int Q = Integer.parseInt(JOptionPane.showInputDialog("ingrese el Q"));
                Round_robin(Procesos, Q, listados);
            }
            if (opcion == "Salir") {
                opcionI = "Salir";
            }

        }
    }

    public void AlgoritmoOrdenDeLlegada(ArrayList<Proceso> Procesos) {

        double Retorno = TiempoDeRetorno(Procesos);
        double espera = TiempoDeEspera(Procesos);
        R = new Resultados("Orden de Llegada", Retorno, espera);
        tabla(R);

        System.out.println(">>>>" + Retorno + "<<<<" + espera);
        Diagrama.diagrama_grantt(Procesos);

    }

    public void AlgoritmoPrimeroElMasCorto(ArrayList<Proceso> Procesos) {
        ArrayList<Proceso> aux = (ArrayList) Procesos.clone();
        Proceso procesoCorto = new Proceso();

        int b = 1;
        for (int i = 0; i < aux.size(); i++) {
            if (aux.get(i).getDuracion() < aux.get(b).getDuracion()) {
                procesoCorto = aux.get(i);
                b++;
            } else {
                procesoCorto = aux.get(b);
            }

        }
        aux.remove(procesoCorto);
        aux.add(0, procesoCorto);
        for (int i = 0; i < aux.size(); i++) {
            System.out.println("<<<<<" + aux.get(i).getDuracion());

        }
        double Retorno = TiempoDeRetorno(aux);
        double espera = TiempoDeEspera(aux);
        Diagrama.diagrama_grantt(aux);

        R = new Resultados("Primero el mas corto", Retorno, espera);
        tabla(R);

    }

    public void AlgoritmoPrioridad(ArrayList<Proceso> Procesos) {
        ArrayList<Proceso> Aux = Procesos;
        Collections.sort(Aux, (p1, p2) -> p1.getDuracion() - p2.getDuracion());
        Collections.sort(Procesos, new Comparator<Proceso>() {
            @Override
            public int compare(Proceso p1, Proceso p2) {
                return p1.getPrioridad() - p2.getPrioridad();
            }
        });
        System.out.println("0" + Procesos.get(0).getNombre());
        for (int k = 0; k < Aux.size(); k++) {
            System.out.println("Prioridad" + Aux.get(k).getDuracion());
        }
        double Retorno = TiempoDeRetorno(Aux);
        double espera = TiempoDeEspera(Aux);

        R = new Resultados("Prioridad", Retorno, espera);
        System.out.println(">>>>" + Retorno + "<<<<" + espera);
        tabla(R);

        Diagrama.diagrama_grantt(Aux);
    }

    public void Round_robin(ArrayList<Proceso> Procesos, int Q, ArrayList<RR> listados) {
        RR lista = new RR();
        Proceso subproceso = new Proceso();
        int tiempoUltimo = 0;
        ArrayList<Proceso> subprocesos = (ArrayList) Procesos.clone();
        ArrayList<Proceso> listaProcesos = new ArrayList<>();
        int i = 0;
        //Q = subprocesos.get(i).getQ();
        while (!subprocesos.isEmpty()) {
            subprocesos.get(i).setInicio(tiempoUltimo);
            subprocesos.get(i).setEnd(subprocesos.get(i).getInicio() + Q);
            tiempoUltimo = subprocesos.get(i).getEnd();
            subprocesos.get(i).setDuracion(subprocesos.get(i).getDuracion() - Q);

            if (subprocesos.get(i).getDuracion() > 0) {
                subproceso = subprocesos.get(i);
                agregaralalista(listaProcesos, subprocesos.get(i));
                subprocesos.remove(i);
                subprocesos.add(subproceso);

            } else if (subprocesos.get(i).getDuracion() == 0) {
                subproceso = subprocesos.get(i);
                agregaralalista(listaProcesos, subproceso);
                subprocesos.remove(i);

            } else {
                subproceso = subprocesos.get(i);
                subproceso.setEnd((Q + subproceso.getDuracion()) + subproceso.getInicio());
                subproceso.setDuracion(0);
                tiempoUltimo = subproceso.getEnd();
                agregaralalista(listaProcesos, subproceso);
                subprocesos.remove(i);

            }

        }
//        imprimir(listaProcesos);
//        TiempoRetornoRoundRObin(Procesos, listaProcesos);
//        TiempoEsperaRoundRobin(Procesos, listaProcesos);
        System.out.println("TE: " + TiempoEsperaRoundRobin(Procesos, listaProcesos) + " ---- " + "T.R: " + TiempoRetornoRoundRObin(Procesos, listaProcesos));
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("p1");
        nombres.add("p2");
        nombres.add("p3.");
        nombres.add("p1.1");
        nombres.add("p2.1");
        nombres.add("p1.2");
        nombres.add("p1.3");
        nombres.add("p1.4");

        for (int j = 0; j < listaProcesos.size(); j++) {

            lista = new RR(nombres.get(j), listaProcesos.get(j).getInicio(), listaProcesos.get(j).getEnd());
            listados.add(lista);

        }

        double Retorno = TiempoRetornoRoundRObin(Procesos, listaProcesos);
        double espera = TiempoEsperaRoundRobin(Procesos, listaProcesos);

        Diagrama.diagrama_RR(listados);
//           R = new Resultados("RoundRobin", Retorno, espera);
//                tabla(R);

       
    }

    public double TiempoRetornoRoundRObin(ArrayList<Proceso> Procesos, ArrayList<Proceso> SubProcesos) {
        int Mayor = Integer.MIN_VALUE;
        double TiempoRetorno = 0.0;
        for (Proceso procesoActual : Procesos) {
            for (Proceso subActual : SubProcesos) {
                if (procesoActual.getNombre().equals(subActual.getNombre()) && subActual.getEnd() > Mayor) {
                    Mayor = subActual.getEnd();
                }
            }
            TiempoRetorno = TiempoRetorno + Mayor;
            Mayor = Integer.MIN_VALUE;
        }
        TiempoRetorno = TiempoRetorno / Procesos.size();
        BigDecimal bd = new BigDecimal(TiempoRetorno).setScale(2, RoundingMode.HALF_UP);
        double tiempo = bd.doubleValue();

        return tiempo;
    }

    public double TiempoEsperaRoundRobin(ArrayList<Proceso> Procesos, ArrayList<Proceso> SubProcesos) {
        double TiempoRetorno = 0.0;
        double Tiempo = 0.0;
        Proceso procesoAnterior = new Proceso();
        for (Proceso procesoActual : Procesos) {
            for (int i = 0; i < SubProcesos.size(); i++) {
                if (procesoActual.getNombre().equals(SubProcesos.get(i).getNombre())) {
                    if (i == 0) {
                        Tiempo = Tiempo + SubProcesos.get(i).getInicio();
                        procesoAnterior = SubProcesos.get(i);
                    } else {
                        if (procesoAnterior.getNombre().equals(SubProcesos.get(i).getNombre())) {
                            Tiempo = Tiempo + (SubProcesos.get(i).getInicio() - procesoAnterior.getEnd());
                            procesoAnterior = SubProcesos.get(i);
                        } else {
                            Tiempo = Tiempo + (SubProcesos.get(i).getInicio());
                            procesoAnterior = SubProcesos.get(i);
                        }

                    }
                }
            }
            TiempoRetorno = TiempoRetorno + Tiempo;
            Tiempo = 0;
        }
        TiempoRetorno = TiempoRetorno / Procesos.size();
        BigDecimal bd = new BigDecimal(TiempoRetorno).setScale(2, RoundingMode.HALF_UP);
        double tiempo = bd.doubleValue();

        return tiempo;
    }

    public void imprimir(ArrayList<Proceso> Procesos) {
        for (int i = 0; i < Procesos.size(); i++) {
            System.out.println("Inicio " + Procesos.get(i).getInicio() + " - Fin " + Procesos.get(i).getEnd() + " duracion" + Procesos.get(i).getDuracion() + " nombre" + Procesos.get(i).getNombre() + "\n");
        }

    }

    public void agregaralalista(ArrayList<Proceso> Procesos, Proceso proceso) {
        Proceso p = new Proceso(proceso.getNombre(), proceso.getDuracion(), proceso.getPrioridad());
        p.setInicio(proceso.getInicio());
        p.setEnd(proceso.getEnd());
        Procesos.add(p);
    }

    public double TiempoDeRetorno(ArrayList<Proceso> Procesos) {
        double TiempoRetorno = 0;
        double Tiempo = 0;
        for (int i = 0; i < Procesos.size(); i++) {
            if (i == 0) {
                TiempoRetorno = Procesos.get(i).getDuracion();
                System.out.println("+" + TiempoRetorno);
            } else {
                TiempoRetorno = TiempoRetorno + Procesos.get(i).getDuracion();
            }
            Tiempo = Tiempo + TiempoRetorno;
            System.out.println(Tiempo + "/");
        }
        Tiempo = Tiempo / Procesos.size();
        BigDecimal bd = new BigDecimal(Tiempo).setScale(2, RoundingMode.HALF_UP);
        double tiempo = bd.doubleValue();
        System.out.println("size" + Procesos.size());
        return tiempo;
    }

    public double TiempoDeEspera(ArrayList<Proceso> Procesos) {
        double TiempoRetorno = 0;
        double Tiempo = 0;
        for (int i = 0; i < Procesos.size() - 1; i++) {
            if (i == 0) {
                TiempoRetorno = Procesos.get(i).getDuracion();
                System.out.println("+" + TiempoRetorno);
            } else {
                TiempoRetorno = TiempoRetorno + Procesos.get(i).getDuracion();
            }
            Tiempo = Tiempo + TiempoRetorno;
            System.out.println(Tiempo + "/");
        }
        Tiempo = Tiempo / Procesos.size();
        BigDecimal bd = new BigDecimal(Tiempo).setScale(2, RoundingMode.HALF_UP);
        double tiempo = bd.doubleValue();
        System.out.println("size" + Procesos.size());
        return tiempo;
    }

    @Override
    public int compare(Object o1, Object o2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void tabla(Resultados R) {

        JFrame marco = new MarcoTabla(R);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setLocation(600, 300);
        marco.setVisible(true);

    }

    class MarcoTabla extends JFrame {

        public MarcoTabla(Resultados R) {
            setTitle("Resultados Tiempos ");

            setBounds(300, 300, 400, 200);
            JTable tabla = new JTable(datos, columName);
            add(new JScrollPane(tabla), BorderLayout.CENTER);

        }
        String[] columName = {"Proceso", "Tiempo de Retorno", "Tiempo de Espera"};
        Object[][] datos = {
            {R.getNombre(), R.getTR(), R.getTE()}

        };

    }

}
