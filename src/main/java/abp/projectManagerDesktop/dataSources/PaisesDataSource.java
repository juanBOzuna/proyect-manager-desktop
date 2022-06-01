package abp.projectManagerDesktop.dataSources;

//import sf.jasperreports.engine.JRDataSource;
import abp.projectManagerDesktop.providers.GetProjectsAdminProvider;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author JorgeLPR
 */
public class PaisesDataSource implements JRDataSource {

    private final Object[][] listadoPaises;
    private int index;

    ArrayList<ResponseGetProjectsAdminModel> projects;

    public PaisesDataSource() {
        index = -1;
//        Long s = 1L/;
        projects = null;
        GetProjectsAdminProvider getProjects = new GetProjectsAdminProvider();

        try {
            projects = getProjects.getProjects();

        } catch (IOException e) {
        }

        listadoPaises = new Object[][]{
            {1L, "Afganistán", "Kabul", "asdasd", "Asdas", "asdasd", "Asd"}};

        int i = 0;
        if (projects != null) {
            for (ResponseGetProjectsAdminModel project : projects) {
                String estado;
                if (project.getProject().getIsCompleted()) {
                    estado = "Completado";
                } else {
                    estado = "Incompleto";
                }
              
                listadoPaises[i][0] = project.getProject().getId();
                listadoPaises[i][1] = project.getProject().getName();
                listadoPaises[i][2] = project.getProject().getKey_name();
                listadoPaises[i][3] = project.getProject().getComercial_designation();
                listadoPaises[i][4] = String.valueOf(project.getProject().getDate_init());
                listadoPaises[i][5] = String.valueOf(project.getProject().getDate_finish());
                listadoPaises[i][6] = estado;
                  i++;
            }
        }
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return (index < listadoPaises.length);
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {

        Object value = null;

        String fieldName = jrf.getName();

        if (listadoPaises.length != 0) {
            switch (fieldName) {

                case "id":
                    value = listadoPaises[index][0];
                    break;

                case "name":
                    value = listadoPaises[index][1];
                    break;

                case "key_name":
                    value = listadoPaises[index][2];
                    break;

                case "comercial_designation":
                    value = listadoPaises[index][3];
                    break;
                case "f_inicio":
                    value = listadoPaises[index][4];
                    break;
                case "f_fin":
                    value = listadoPaises[index][5];
                    break;
                case "estado":
                    value = listadoPaises[index][6];
                    break;

            }
        }

        return value;

    }

    public static JRDataSource getDataSource() {
        return new PaisesDataSource();
    }

}
