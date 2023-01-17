/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Clases.*;
import Controladores.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

/**
 *
 * @author USUARIO
 */
public class Diagrama {

    RR listado = new RR();

    Resultados R = new Resultados();

    public static void diagrama_grantt(ArrayList<Proceso> Procesos) {

        IntervalCategoryDataset dataset = createDataset(Procesos);

        JFreeChart chart = ChartFactory.createGanttChart(
                "Diagrama de Grantt",
                "Proceso",
                "tiempo",
                dataset,
                true,
                true,
                false);

        ChartPanel panel = new ChartPanel(chart);
        //creamos la ventana 
        JFrame ventana = new JFrame("Diagrma");

        ventana.setVisible(true);
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.add(panel);
    }

    public static IntervalCategoryDataset createDataset(ArrayList<Proceso> Procesos) {
        ArrayList<Integer> coordenadas = new ArrayList<>();
        TaskSeriesCollection collection = new TaskSeriesCollection();
        TaskSeries p;
        int suma = 0;
        for (int i = 0; i < Procesos.size(); i++) {
            suma += Procesos.get(i).getDuracion();
            coordenadas.add(suma);
        }
        System.out.println(">>><" + coordenadas);
        int aux = 0;
        for (int i = 0; i < Procesos.size(); i++) {
            p = new TaskSeries(Procesos.get(i).getNombre());
            p.add(new Task(Procesos.get(i).getNombre(), new SimpleTimePeriod(date(aux, 0, 0), date(coordenadas.get(i), 0, 0))));
            aux = coordenadas.get(i);
            collection.add(p);

        }

        return collection;

    }

//     * @param day
//     * @param month
//     * @param year
//     * @return
//     */
    public static Date date(int day, int mont, int year) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(day, 0, 0);
        final Date result = calendar.getTime();
        return result;
    }

    public static void diagrama_RR(ArrayList<RR> listado) {

        IntervalCategoryDataset dataset = createDataset1(listado);

        JFreeChart chart = ChartFactory.createGanttChart(
                "Diagrama de Grantt",
                "Proceso",
                "tiempo",
                dataset,
                true,
                true,
                false);

        ChartPanel panel = new ChartPanel(chart);
        //creamos la ventana 
        JFrame ventana = new JFrame("Diagrma");

        ventana.setVisible(true);
        ventana.setSize(600, 500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.add(panel);
    }

    public static IntervalCategoryDataset createDataset1(ArrayList<RR> listado) {

        TaskSeriesCollection collection = new TaskSeriesCollection();
        TaskSeries p;
        int aux = 0;
        for (int i = 0; i < listado.size(); i++) {
            p = new TaskSeries(listado.get(i).getNombre());
            p.add(new Task(listado.get(i).getNombre(), new SimpleTimePeriod(date(listado.get(i).getInicio(), 0, 0), date(listado.get(i).getEnd(), 0, 0))));

            collection.add(p);
        }

        return collection;

    }

}
