package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.providers.GetProjectsAdminProvider;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
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

public class ProjectsStatus extends JFrame {

    JFreeChart chart;
    ArrayList<ResponseGetProjectsAdminModel> projects;
    ArrayList<ProjectModel> projectsFinish;
    ArrayList<ProjectModel> projectsNotFinish;

    public ProjectsStatus() {
        super("Demo de BarChart2");

        GetProjectsAdminProvider getProjects = new GetProjectsAdminProvider();

        projects = null;
        projectsFinish = new ArrayList<ProjectModel>();
        projectsNotFinish = new ArrayList<ProjectModel>();
        try {
            projects = getProjects.getProjects();
            for (ResponseGetProjectsAdminModel project : projects) {
                if (project.getProject().getIsCompleted()) {
                    projectsFinish.add(project.getProject());
                } else {
                    projectsNotFinish.add(project.getProject());
                }
            }
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

        dataset.setValue(projectsFinish.size(), "Proyectos", "Finalizados");
        dataset.setValue(projectsNotFinish.size(), "Proyectos", "Sin completar");
        dataset.setValue(projects.size(), "Proyectos", "Total");

        chart = ChartFactory.createBarChart3D(
                "Demo de BarChart3D", //Nombre de la grafica
                "Pojectos", //Nombre del eje Horizontal
                "Cantidad", //Nombre del eje vertical
                dataset, //Data
                PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informacion al pasar el mouse
                true);                      //URls

        chart.setBackgroundPaint(Color.GRAY);//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr?fico

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
        ProjectsStatus demo = new ProjectsStatus();
    }
}
