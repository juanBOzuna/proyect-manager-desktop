/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homeEmployee;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.home.ElementUserAdmin;
import abp.projectManagerDesktop.providers.FinalizeTaskProvider;
import abp.projectManagerDesktop.providers.GetTasksOfEmployee;
import abp.projectManagerDesktop.providers.GetUsersProvider;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class HomeEMployee {

    static int Width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.9);
    static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.9);

    public static void main(String[] args) {
        int width = Width - (int) (Width * 0.2);
        int Height = height - (int) (height * 0.08);

        WindowEmployee v = new WindowEmployee(width, Height);
        v.setVisible(true);

    }

}

class WindowEmployee extends JFrame implements MouseListener {

    JScrollPane scrollPanelTaks;
    JLabel iconAddUser;
    JPanel panelTasks;
    JLabel title;
//    JPanel panelP;
    int Width;
    int height;
    JPanel panelTask;

    JLabel picLabelFinish;
    TaskModel task;

    public WindowEmployee(int Width, int height) {
        this.Width = Width;
        this.height = height;
        this.setBackground(Color.white);
        initComponents();
    }

    void initComponents() {

        setLayout(null);
        setVisible(true);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Width, height);
        setResizable(false);
//        GetUsersProvider users = new GetUsersProvider();
//        try {
//            mapUsers = users.getUsers();
//            promotors = mapUsers.get(constantUtilities.ROLE_PROMOTOR);
//            employees = mapUsers.get(constantUtilities.ROLE_EMPLEADO);
//        } catch (IOException e) {
//        }

        addTitleModul();
        createPanelTask();
    }

    void addTitleModul() {
        title = new JLabel(constantUtilities.usuario.getName() + " " + constantUtilities.usuario.getLastname());
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds((int) (Width * 0.051), 40, Width, 38);
        add(title);
    }

    int swfp(double number) {
        return (int) ((Width - 20) * number);
    }

    int shfp(double number) {
        return (int) ((height - 40) * number);
    }

    void createPanelTask() {
        GetTasksOfEmployee get = new GetTasksOfEmployee();
        TaskModel task = null;
        try {
            task = get.get(constantUtilities.usuario.getId());
            this.task = task;
        } catch (IOException e) {
        }

        panelTasks = null;
        scrollPanelTaks = null;
        JLabel titlePanel = null;

        int widthPanel = swfp(0.8);
        int heightPanel = shfp(0.730);
        int widthImage = (int) (heightPanel * 0.0657);

        panelTasks = new JPanel();
        panelTasks.setLayout(null);
        panelTasks.setBackground(new ColorUIResource(238, 238, 238));

        titlePanel = new JLabel("Mis tareas para completar");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelTasks.add(titlePanel);

        try {
            int yElement = 0;

            yElement = titlePanel.getY() + titlePanel.getHeight() + 10;

            panelTasks.add(AddTask(0, yElement, widthPanel, 70, task));
        } catch (Exception e) {
        }
//        try {
//        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * 2) + 10;
//        panelTasks.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));
        panelTasks.setBounds(swfp(0.059), title.getY() + title.getHeight() + 20, widthPanel + 20, shfp(0.730));
//        } catch (Exception e) {
//            int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * 20) + 10;
//            panelTasks.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));
//        }

//   
//        scrollPanelTaks = new JScrollPane();
//        scrollPanelTaks.setBounds(swfp(0.059), title.getY() + title.getHeight() + 20, widthPanel + 20, shfp(0.730));
//        scrollPanelTaks.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//        scrollPanelTaks.setViewportView(panelTasks);
//        scrollPanelTaks.getVerticalScrollBar().setUnitIncrement(20);
//        scrollPanelTaks.getHorizontalScrollBar().setUnitIncrement(20);
        panelTasks.updateUI();
        panelTasks.repaint();

//        scrollPanelTaks.updateUI();
//        scrollPanelTaks.repaint();
        add(panelTasks);

    }

    JPanel AddTask(int x, int y, int w, int h, TaskModel task) {
        panelTask = new JPanel();
        //    UserModel user;
        //    DialogDeleteUser delete;
        //    DialogRegisterUser edit;
//        this.padre = padre;
        int wImage = (h / 2);
        panelTask.setBounds(x, y, w, h);
        panelTask.setBackground(Color.white);
        panelTask.setLayout(null);

        int xEye = w - (w / 8);
        int yEye = (y / 2) - ((w / 5) / 2);

        JPanel panelEye = new JPanel();
        panelEye.setBackground(Color.black);
        panelEye.setBounds(xEye, (h / 2) - (wImage / 2), wImage, wImage);

        Image imgDelete = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/checked.png").getImage();
        ImageIcon imgDelete2 = new ImageIcon(imgDelete.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
        picLabelFinish = new JLabel();
        picLabelFinish.setIcon(imgDelete2);
        picLabelFinish.addMouseListener(this);
        picLabelFinish.setCursor(new Cursor(HAND_CURSOR));
        picLabelFinish.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

        xEye -= (wImage + 5);
//        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/eye.png").getImage();
//        ImageIcon img2 = new ImageIcon(img.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
//        picLabel = new JLabel();
//        picLabel.setIcon(img2);
//        picLabel.addMouseListener(this);
//        picLabel.setCursor(new Cursor(HAND_CURSOR));
//        picLabel.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);
//
//        xEye -= (wImage + 5);
//        Image imgEdit = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/edit.png").getImage();
//        ImageIcon imgEdit2 = new ImageIcon(imgEdit.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
//        picLabelEdit = new JLabel();
//        picLabelEdit.setIcon(imgEdit2);
//        picLabelEdit.addMouseListener(this);
//        picLabelEdit.setCursor(new Cursor(HAND_CURSOR));
//        picLabelEdit.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

//        int yEye = (y / 2) - ((w / 5) / 2);
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(1, 1));
        title.setBackground(Color.white);
        title.setBounds(10, (h / 2) - (((h / 2) - 5) / 2), xEye - 20, (h / 2) - 5);

        JLabel titleText = new JLabel(task.getName());
        titleText.setBackground(Color.white);
        title.add(titleText);

        panelTask.add(title);
//        add(picLabel);
//        add(picLabelEdit);
        panelTask.add(picLabelFinish);

        return panelTask;
    }

    void finalizeTask() {

        try {
            FinalizeTaskProvider finalize = new FinalizeTaskProvider();

            if (finalize.finalize(task.getId())) {
                panelTasks.removeAll();
                panelTasks.updateUI();
                panelTasks.repaint();
                panelTask.removeAll();
                panelTask.updateUI();
                panelTask.repaint();

                createPanelTask();

                panelTasks.updateUI();
                panelTasks.repaint();
            }
        } catch (IOException e) {
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == picLabelFinish) {
            finalizeTask();
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
