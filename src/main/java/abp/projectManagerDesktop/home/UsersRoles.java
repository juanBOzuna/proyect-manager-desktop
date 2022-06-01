package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.GetUsersProvider;
import abp.projectManagerDesktop.providers.Models.UserModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GradientPaint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UsersRoles extends JFrame {

    JFreeChart chart;
    Map<String, ArrayList<UserModel>> mapUsers;
    ArrayList<UserModel> promotors = new ArrayList<UserModel>();
    ArrayList<UserModel> employees = new ArrayList<UserModel>();

    public UsersRoles() {

        super("Demo de BarChart2");
        GetUsersProvider users = new GetUsersProvider();
        try {
            mapUsers = users.getUsers();
            promotors = mapUsers.get(constantUtilities.ROLE_PROMOTOR);
            employees = mapUsers.get(constantUtilities.ROLE_EMPLEADO);
        } catch (IOException e) {
        }

        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        crearGrafico();

        ChartPanel panel = new ChartPanel(chart, false);
        panel.setBounds(10, 20, 760, 520);
        add(panel);

        setVisible(true);
    }

    public void crearGrafico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(employees.size(), "Usuarios", "Empleados");
        dataset.setValue(promotors.size(), "Usuarios", "Promotores");
        dataset.setValue(promotors.size() + employees.size(), "Usuarios", "Total");
//        dataset.setValue(7, "Usuarios", "Ana");

        chart = ChartFactory.createBarChart3D(
                "Diferenciar usuarios", //Nombre de la grafica
                "Roles de usuarios", //Nombre del eje Horizontal
                "Cantidad", //Nombre del eje vertical
                dataset, //Data
                PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informacion al pasar el mouse
                true);                      //URls

        chart.setBackgroundPaint(Color.GRAY);//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

    }

    public static void main(String args[]) {
        UsersRoles demo = new UsersRoles();
    }
}
