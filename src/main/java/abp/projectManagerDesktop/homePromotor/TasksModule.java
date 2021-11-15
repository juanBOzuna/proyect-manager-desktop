/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.home.ElementProjectAdmin;
import abp.projectManagerDesktop.home.ElementTaskAdmin;
import abp.projectManagerDesktop.providers.GetTasksPromotorProvider;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class TasksModule extends JPanel implements MouseListener {

    DialogRegisterTask dialogRegister;
    JFrame padre;
    int Width, height;
    JLabel iconAdd;
    ArrayList<TaskModel> tasks;
    ArrayList<DialogDeleteTask> deletes = new ArrayList<DialogDeleteTask>();
    ArrayList<DialogRegisterTask> edits = new ArrayList<DialogRegisterTask>();

    public TasksModule(int x, int y, int w, int h, JFrame padre) {
        this.padre = padre;
        dialogRegister = new DialogRegisterTask(padre, false, false, null);
        Width = w;
        height = h - 38;
        setLayout(null);
//        setPreferredSize(new DimensionUIResource(Width - 38, height));
        setBackground(Color.WHITE);
        initComponents();

    }

    void initComponents() {
        JLabel title = new JLabel("Tareas de mi proyecto");
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds((int) (Width * 0.051), 40, Width / 2, 38);
        add(title);

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH));
        iconAdd = new JLabel();
        iconAdd.addMouseListener(this);
        iconAdd.setIcon(imgAdd2);
        iconAdd.setCursor(new Cursor(HAND_CURSOR));
//        iconAdd.setBounds((Width - title.getHeight() + 20), title.getY(), title.getHeight(), title.getHeight());
        iconAdd.setBounds((Width - title.getX() - title.getHeight()), title.getY(), title.getHeight(), title.getHeight());
        //icon Add
        add(iconAdd);

        GetTasksPromotorProvider getTasks = new GetTasksPromotorProvider();
        try {
            tasks = getTasks.getTasks();
        } catch (IOException e) {
        }

        ElementTasksPromotor taskElement;

        try {
            int y = title.getY() + title.getHeight() + 50;
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("Projecto encontrado");
                if (i >= 1) {
                    y += 60;
                }
                System.out.println("bound y = " + y);
                taskElement = new ElementTasksPromotor((int) (Width * 0.05), y, (int) (Width * 0.9), 50, padre, tasks.get(i));
                add(taskElement);

                deletes.add(taskElement.getDelete());
                edits.add(taskElement.getEdit());
//                deletes.add(projectElement.getDialogDelete());
            }
        } catch (Exception e) {
        }

        int heightPreferred = title.getY() + title.getHeight() + (60 * tasks.size()) + 60;
        setPreferredSize(new DimensionUIResource(Width, heightPreferred));
    }

    public ArrayList<DialogDeleteTask> getDeletes() {
        return deletes;
    }

    public DialogRegisterTask getDialogRegister() {
        return dialogRegister;
    }

    public ArrayList<DialogRegisterTask> getEdits() {
        return edits;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iconAdd) {

            dialogRegister.setVisible(true);
//            this.dialogRegister = dialogRegister;
        }

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
