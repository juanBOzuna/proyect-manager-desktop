/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import static abp.projectManagerDesktop.home.UsersModule.widthScreen;
import abp.projectManagerDesktop.providers.GetProjectsAdminProvider;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class ProjectsModule extends JPanel implements MouseListener {

    int Width;
    int height;
    JFrame padre;
    ArrayList<DialogRegisterProject> edits = new ArrayList<DialogRegisterProject>();
    ArrayList<DialogDeleteProject> deletes = new ArrayList<DialogDeleteProject>();

    public ProjectsModule(int x, int y, int w, int h, JFrame padre) {
// this.padre = padre;
        this.padre = padre;
        Width = w;
        height = h - 38;
        setLayout(null);
//        setPreferredSize(new DimensionUIResource(Width - 38, height));
        setBackground(Color.WHITE);
        try {

            initComponentsProjects();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void initComponentsProjects() {
        GetProjectsAdminProvider getProjects = new GetProjectsAdminProvider();

        ArrayList<ResponseGetProjectsAdminModel> projects = null;

        try {
            projects = getProjects.getProjects();
        } catch (IOException e) {
        }

        JLabel title = new JLabel("Proyectos");
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds((int) (Width * 0.051), 40, Width, 38);
        add(title);

        ElementProjectAdmin projectElement;

        try {
            int y = title.getY() + title.getHeight() + 50;
            for (int i = 0; i < projects.size(); i++) {
                System.out.println("Projecto encontrado");
                if (i >= 1) {
                    y += 60;
                }
                System.out.println("bound y = " + y);
                projectElement = new ElementProjectAdmin(swfp(0.05), y, swfp(0.9), 50, padre, projects.get(i));
                add(projectElement);
                edits.add(projectElement.getEdit());
                deletes.add(projectElement.getDialogDelete());
            }
        } catch (Exception e) {
        }

        int heightPreferred = title.getY() + title.getHeight() + (60 * projects.size()) + 10;
        setPreferredSize(new DimensionUIResource(Width, heightPreferred));
    }

    public ArrayList<DialogRegisterProject> getEdits() {
        return edits;
    }

    public ArrayList<DialogDeleteProject> getDeletes() {
        return deletes;
    }

    int swfp(double number) {
        return (int) ((Width - 20) * number);
    }

    int shfp(double number) {
        return (int) (height * number);
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
