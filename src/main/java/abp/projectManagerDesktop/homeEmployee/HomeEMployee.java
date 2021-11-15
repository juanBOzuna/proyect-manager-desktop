/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homeEmployee;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.home.ElementUserAdmin;
import abp.projectManagerDesktop.providers.GetTasksOfEmployee;
import abp.projectManagerDesktop.providers.GetUsersProvider;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
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

    public WindowEmployee(int Width, int height) {
        this.Width = Width;
        this.height = height;
        this.setBackground(Color.white);
//        panelP = new JPanel();

//        panelP.setBackground(Color.white);
//        panelP.setLayout(null);
//        panelP.setBounds(0, 0, Width, height);
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
        createPanelPromtors();
    }

    void addTitleModul() {
        title = new JLabel("Usuarios");
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

    void createPanelPromtors() {
        GetTasksOfEmployee get = new GetTasksOfEmployee();
        TaskModel task = null;
        try {
            task = get.get(constantUtilities.usuario.getId());
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

        titlePanel = new JLabel("Promotores");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelTasks.add(titlePanel);

        try {
            ElementTaskEmployee taskE;
            int yElement = 0;

            yElement = titlePanel.getY() + titlePanel.getHeight() + 10;

            taskE = new ElementTaskEmployee(0, yElement, widthPanel, 40, this, task);
            panelTasks.add(taskE);
        } catch (Exception e) {
        }
//   
        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * 20) + 10;
        panelTasks.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));

        scrollPanelTaks = new JScrollPane();
        scrollPanelTaks.setBounds(swfp(0.059), title.getY() + title.getHeight() + 20, widthPanel + 20, shfp(0.730));
        scrollPanelTaks.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPanelTaks.setViewportView(panelTasks);
        scrollPanelTaks.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanelTaks.getHorizontalScrollBar().setUnitIncrement(20);

        panelTasks.updateUI();
        panelTasks.repaint();

        scrollPanelTaks.updateUI();
        scrollPanelTaks.repaint();
        add(scrollPanelTaks);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
