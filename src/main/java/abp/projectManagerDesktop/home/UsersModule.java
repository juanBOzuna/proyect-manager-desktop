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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class UsersModule extends JPanel {

    JLabel title;
    int Width;
    int height;
    static int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;

    Map<String, ArrayList<UserModel>> mapUsers;
    ArrayList<UserModel> promotors = new ArrayList<UserModel>();
    ArrayList<UserModel> employees = new ArrayList<UserModel>();

//    ArrayList<UserModel> employees
    public UsersModule(int x, int y, int w, int h) {
        Width = w;
        height = h - 38;
        setLayout(null);
        setPreferredSize(new DimensionUIResource(Width - 38, 10000));
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

        JPanel panelEmployees = new JPanel();
        panelEmployees.setBackground(Color.white);
        panelEmployees.setLayout(null);

        JLabel titlePanel = new JLabel("Empleados");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelEmployees.add(titlePanel);

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(widthImage, widthImage, Image.SCALE_SMOOTH));
        JLabel iconAdd = new JLabel();
//        iconAdd.addMouseListener(this);
        iconAdd.setIcon(imgAdd2);
        iconAdd.setCursor(new Cursor(HAND_CURSOR));
        iconAdd.setBounds(widthPanel - widthImage, titlePanel.getY(), widthImage, widthImage);
        panelEmployees.add(iconAdd);
        //icon Add

        ElementUserAdmin element;
        int yElement = 0;
        UserModel employe;
        int contador=0;
        for (UserModel employee : employees) {
            contador++;
            if (contador== 1) {
                yElement = iconAdd.getY() + iconAdd.getHeight() + 10;
            } else {
                yElement += 50;
            }
            element = new ElementUserAdmin(0, yElement, widthPanel, 40);
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
        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * employees.size() ) + 10;
        panelEmployees.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));

        JScrollPane scrollPanel = new JScrollPane();
        scrollPanel.setBounds((int) (Width * 0.055), (int) (height * 0.1629), widthPanel + 20, heightPanel);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPanel.setViewportView(panelEmployees);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(20);

        add(scrollPanel);
    }

    void createPanelPromtors() {

        int widthPanel = (int) (Width * 0.312);
        int heightPanel = (int) (height * 0.8);
        int widthImage = (int) (heightPanel * 0.0657);

        JPanel panelPromotors = new JPanel();
        panelPromotors.setLayout(null);
        panelPromotors.setBackground(Color.white);

        JLabel titlePanel = new JLabel("Promotores");
        titlePanel.setBounds(0, (int) (heightPanel * 0.0398), widthPanel - widthImage - 10, (int) (heightPanel * 0.0657));
        titlePanel.setFont(new Font("Segoe UI", 0, 25));
        panelPromotors.add(titlePanel);

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(widthImage, widthImage, Image.SCALE_SMOOTH));
        JLabel iconAdd = new JLabel();
//        iconAdd.addMouseListener(this);
        iconAdd.setIcon(imgAdd2);
        iconAdd.setCursor(new Cursor(HAND_CURSOR));
        iconAdd.setBounds(widthPanel - widthImage, titlePanel.getY(), widthImage, widthImage);
        panelPromotors.add(iconAdd);
        //icon Add

        ElementUserAdmin element;
        int yElement = 0;
        UserModel employe;
        int contador = 0;
        for (UserModel promotor : promotors) {
            contador++;
            if (contador == 1) {
                yElement = iconAdd.getY() + iconAdd.getHeight() + 10;
            } else {
                yElement += 50;
            }
            element = new ElementUserAdmin(0, yElement, widthPanel, 40);
            panelPromotors.add(element);
        }

        int heightPreferred = titlePanel.getY() + titlePanel.getHeight() + (50 * promotors.size() ) + 10;
        panelPromotors.setPreferredSize(new DimensionUIResource(widthPanel, heightPreferred));

        JScrollPane scrollPanel = new JScrollPane();
        scrollPanel.setBounds((int) (Width * 0.6282) - 10, (int) (height * 0.1629), (int) (Width * 0.312) + 20, (int) (height * 0.8));
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPanel.setViewportView(panelPromotors);
        scrollPanel.getVerticalScrollBar().setUnitIncrement(20);
        scrollPanel.getHorizontalScrollBar().setUnitIncrement(20);
        add(scrollPanel);
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

}
