/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.GetUsersProvider;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class UsersModule extends JPanel implements MouseListener {

    JFrame padre;
    JLabel title;
    JLabel iconAddUser;
    JLabel iconAddPromotors;
    JPanel panelPromotors;
    JPanel panelEmployees;

    JScrollPane scrollPanelUsers;
    JScrollPane scrollPanelPromotors;
    int Width;
    int height;
    static int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;

    Map<String, ArrayList<UserModel>> mapUsers;
    ArrayList<UserModel> promotors = new ArrayList<UserModel>();
    ArrayList<UserModel> employees = new ArrayList<UserModel>();

//    ArrayList<UserModel> employees
    public UsersModule(int x, int y, int w, int h, JFrame padre) {
        this.padre = padre;
        Width = w;
        height = h - 38;
        setLayout(null);
        setPreferredSize(new DimensionUIResource(Width - 38, height));
        setBackground(Color.WHITE);
        initComponentsUser();
    }

    void initComponentsUser() {
        GetUsersProvider users = new GetUsersProvider();
        try {
            mapUsers = users.getUsers();
            promotors = mapUsers.get(constantUtilities.ROLE_PROMOTOR);
            employees = mapUsers.get(constantUtilities.ROLE_EMPLEADO);
        } catch (IOException e) {
        }

        addTitleModul();
        addDividers();
        createPanelEmployees();
        createPanelPromtors();
    }

    void createPanelEmployees() {
        int widthPanel = (int) (Width * 0.312);
        int heightPanel = (int) (height * 0.8);
        int widthImage = (int) (heightPanel * 0.0657);

        panelEmployees = new JPanel();
        panelEmployees.setBackground(Color.white);
        panelEmployees.setLayout(null);

        JLabel titlePanel = new JLabel("Empleados");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelEmployees.add(titlePanel);

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(widthImage, widthImage, Image.SCALE_SMOOTH));
        iconAddUser = new JLabel();
        iconAddUser.addMouseListener(this);
        iconAddUser.setIcon(imgAdd2);
        iconAddUser.setCursor(new Cursor(HAND_CURSOR));
        iconAddUser.setBounds(widthPanel - widthImage, titlePanel.getY(), widthImage, widthImage);
        panelEmployees.add(iconAddUser);
        //icon Add

        ElementUserAdmin element;
        int yElement = 0;
        UserModel employe;
        int contador = 0;
        for (UserModel employee : employees) {
            contador++;
            if (contador == 1) {
                yElement = iconAddUser.getY() + iconAddUser.getHeight() + 10;
            } else {
                yElement += 50;
            }
            element = new ElementUserAdmin(0, yElement, widthPanel, 40, employee,padre);
            panelEmployees.add(element);
        }

//        //icon Reload
//        Image imgReload = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/reload.png").getImage();
//        ImageIcon imgReload2 = new ImageIcon(imgReload.getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH));
//        JLabel iconReload = new JLabel();
//        iconReload.setIcon(imgReload2);
//        iconReload.setCursor(new Cursor(HAND_CURSOR));
////        iconReload.addMouseListener(this);
//        iconReload.setBounds((widthPanel - 100) - title.getHeight(), title.getY(), title.getHeight(), title.getHeight());
//        //icon Reload
        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * employees.size()) + 10;
        panelEmployees.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));

        scrollPanelUsers = new JScrollPane();
        scrollPanelUsers.setBounds((int) (Width * 0.055), (int) (height * 0.1629), widthPanel + 20, heightPanel);
        scrollPanelUsers.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPanelUsers.setViewportView(panelEmployees);
        scrollPanelUsers.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanelUsers.getHorizontalScrollBar().setUnitIncrement(20);

        add(scrollPanelUsers);
    }

    void createPanelPromtors() {
        panelPromotors = null;
        scrollPanelPromotors = null;
        JLabel titlePanel = null;

        int widthPanel = (int) (Width * 0.312);
        int heightPanel = (int) (height * 0.8);
        int widthImage = (int) (heightPanel * 0.0657);

        panelPromotors = new JPanel();
        panelPromotors.setLayout(null);
        panelPromotors.setBackground(Color.white);

        titlePanel = new JLabel("Promotores");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelPromotors.add(titlePanel);

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(widthImage, widthImage, Image.SCALE_SMOOTH));
        iconAddPromotors = new JLabel();
        iconAddPromotors.addMouseListener(this);
        iconAddPromotors.setIcon(imgAdd2);
        iconAddPromotors.setCursor(new Cursor(HAND_CURSOR));
        iconAddPromotors.setBounds(widthPanel - widthImage, titlePanel.getY(), widthImage, widthImage);
        panelPromotors.add(iconAddPromotors);
        //icon Add

        ElementUserAdmin element;
        int yElement = 0;
        UserModel employe;
        int contador = 0;
        for (UserModel promotor : promotors) {
            contador++;
            if (contador == 1) {
                yElement = iconAddPromotors.getY() + iconAddPromotors.getHeight() + 10;
            } else {
                yElement += 50;
            }
            element = new ElementUserAdmin(0, yElement, widthPanel, 40, promotor,padre);
            panelPromotors.add(element);
        }

        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * promotors.size()) + 10;
        panelPromotors.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));

        scrollPanelPromotors = new JScrollPane();
        scrollPanelPromotors.setBounds((int) (Width * 0.6282) - 10, (int) (height * 0.1629), (int) (Width * 0.312) + 20, (int) (height * 0.8));
        scrollPanelPromotors.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPanelPromotors.setViewportView(panelPromotors);
        scrollPanelPromotors.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanelPromotors.getHorizontalScrollBar().setUnitIncrement(20);

        panelPromotors.updateUI();
        panelPromotors.repaint();

        scrollPanelPromotors.updateUI();
        scrollPanelPromotors.repaint();
        add(scrollPanelPromotors);
        this.updateUI();
        this.repaint();
    }

    void addTitleModul() {
        title = new JLabel("Usuarios");
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds((int) (Width * 0.051), 40, widthScreen, 38);
        add(title);
    }

    void addDividers() {
        int x = (int) (Width / 2);

        int y = title.getY() + title.getHeight();
        int heightD = (int) (height / 4.4);
        add(createDivider(x, y, 1, heightD));

        y += heightD + 10;
        heightD = (int) (height * 0.085);
        add(createDivider(x, y, 1, heightD));

        y += heightD + 10;
        heightD = (int) (height * 0.085);
        add(createDivider(x, y, 1, heightD));

        y += heightD + 10;
        heightD = (int) (height / 4.4);
        add(createDivider(x, y, 1, heightD));
    }

    JPanel createDivider(int x, int y, int w, int h) {
        JPanel panelDivider = new JPanel();
        panelDivider.setBounds(x, y + 40, w, h);
        panelDivider.setBackground(Color.gray);
        return panelDivider;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iconAddUser) {
            System.out.println("undio");
            DialogRegisterUser registerUser = new DialogRegisterUser(padre, false, false, false, null);
            registerUser.setVisible(true);

            registerUser.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    JOptionPane.showMessageDialog(null, " haga click en el menu usuarios nuevamente para refrescar");
                }
            });
        }

        if (e.getSource() == iconAddPromotors) {
            System.out.println("undio");
            DialogRegisterUser registerUser = new DialogRegisterUser(padre, false, true, false, null);
            registerUser.setVisible(true);

            registerUser.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    //                    refresh();
                    JOptionPane.showMessageDialog(null, " haga click en el menu usuarios nuevamente para refrescar");
                }
            });
        }
    }

    void refresh(Boolean after) {
        if (after) {
            this.remove(scrollPanelPromotors);
            this.remove(scrollPanelUsers);
        }

        this.updateUI();
        this.repaint();
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
