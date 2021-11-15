/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.home.DialogDeleteProject;
import abp.projectManagerDesktop.home.DialogDeleteUser;
import abp.projectManagerDesktop.home.DialogRegisterProject;
import abp.projectManagerDesktop.home.DialogRegisterUser;
import abp.projectManagerDesktop.home.ElementTaskAdmin;
import abp.projectManagerDesktop.home.ProjectsModule;
import abp.projectManagerDesktop.home.UsersModule;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
//import abp.projectManagerDesktop.home.VentanaHome;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import abp.projectManagerDesktop.providers.FinalizeProjectProvider;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class HomePromotor {

//    public static void main(String[] args) {
//        VentanaHomePromotor v = new VentanaHomePromotor();
//        v.setVisible(true);
//    }
    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
//    static ProjectModel project;

    public static void main(String[] args) throws IOException {

        VentanaHomePromotor v = new VentanaHomePromotor(width, height);
    }

}

class VentanaHomePromotor extends JFrame implements MouseListener {

    int widthWindow;
    int heightWindow;
    JPanel panelP;
    JPanel header;
    JPanel drawerStatic;
    JPanel contentHome;
    JScrollPane scrollContentHome;
    JPanel itemRolUser;
    JPanel itemNavigation;
    JPanel menuItemHome;
    JPanel menuItemUsers;
    JPanel menuItemTasks;
    JLabel textAuxForPanelContent;
    JPanel headerStep;
    JPanel tasks;
    JScrollPane scrollStep;
    JLabel iconReload;
    JLabel iconAdd;
    JPanel panelTitleHome;
    TasksModule createTasksModule;
    UsersOfMyProjectModel createUsersModule;
    ProjectsModule projectsMpdule;
    JPanel menuItemAux;
    JPanel finishProjectButton;

    public VentanaHomePromotor(int widthScreenSize, int heightScreenSize) throws IOException {
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize((int) (widthScreenSize * .9), (int) (heightScreenSize * 0.9));
        setResizable(false);

        panelP = new JPanel();
        panelP.setBounds(0, 0, (int) (widthScreenSize * .9), (int) (heightScreenSize * 0.9));
        panelP.setLayout(null);
        panelP.setBackground(Color.white);

        initComponents();

        panelP.updateUI();
        panelP.repaint();
    }

    void initComponents() throws IOException {
        widthWindow = getWidth();
        heightWindow = getHeight();
        constructHeader();
        constructDrawerStatic();
        constructPanelHome();
        add(panelP);

    }

    void constructPanelHome() {
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;

        scrollContentHome = new JScrollPane();
        scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel - 16, heightPanel - 38);
        scrollContentHome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollContentHome.getVerticalScrollBar().setUnitIncrement(20);
        scrollContentHome.getHorizontalScrollBar().setUnitIncrement(20);

        contentHome = new JPanel();
        contentHome.setPreferredSize(new DimensionUIResource(widthPanel - 38, heightPanel - 38));

        contentHome.setBackground(Color.WHITE);
        contentHome.setLayout(null);

        JLabel titlePanel = new JLabel("Mi Escritorio");
        titlePanel.setFont(new Font("Segoe UI Semibold", 0, 42));
        titlePanel.setBounds((int) (widthPanel * 0.051), 40, widthPanel, 48);
        contentHome.add(titlePanel);

        JPanel panelProject = new JPanel();
        panelProject.setBounds((int) (widthPanel * 0.051), (titlePanel.getHeight() + titlePanel.getY()) * 2, widthPanel - (int) ((widthPanel * 0.051) * 2), 60);
        panelProject.setLayout(null);
        panelProject.setBackground(Color.white);
        panelProject.setBorder(BorderFactory.createLineBorder(Color.gray));

        JLabel titleProjectAssign = new JLabel();

        titleProjectAssign.setFont(new Font("Segoe UI Semibold", 0, 26));
        titleProjectAssign.setBounds(20, 30 - 18, widthPanel, 36);
        panelProject.add(titleProjectAssign);
//        panelHome.add(titlePanel);
        contentHome.add(panelProject);

        JLabel titleButonDelete = new JLabel("Finalzar Proyecto");
        titleButonDelete.setHorizontalAlignment(SwingConstants.CENTER);
        titleButonDelete.setVerticalAlignment(SwingConstants.CENTER);
        titleButonDelete.setForeground(Color.white);

        finishProjectButton = new JPanel();
        finishProjectButton.setBounds((int) (widthPanel * 0.051), (titlePanel.getHeight() + titlePanel.getY()) * 2 + 60 + 20, (int) (widthPanel * 0.195), 50);
        finishProjectButton.setBackground(new ColorUIResource(244, 67, 54));
        finishProjectButton.setCursor(new Cursor(HAND_CURSOR));
        finishProjectButton.addMouseListener(this);
        finishProjectButton.setLayout(new GridLayout(1, 1));
        finishProjectButton.add(titleButonDelete);

        try {
            if (constantUtilities.nameProject != null) {
                titleProjectAssign.setText("Projecto Asignado: " + constantUtilities.nameProject);
                contentHome.add(finishProjectButton);
            } else {
                titleProjectAssign.setText("Projecto Asignado: Ninguno");
            }

        } catch (Exception e) {
            titleProjectAssign.setText("Projecto Asignado: Ninguno");

        }
        scrollContentHome.setViewportView(contentHome);
//        scrollContentHome.add(panelHome);

        panelP.add(scrollContentHome);
    }

    void constructHeader() {
        int heightPanel = (int) (heightWindow * 0.08);
        header = new JPanel();
        header.setBounds(0, 0, widthWindow, heightPanel);
        header.setBackground(constantUtilities.primaryColor);
        header.setLayout(null);

        Image imgLogo = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/logo.png").getImage();
        ImageIcon imgLogo2 = new ImageIcon(imgLogo.getScaledInstance((int) (header.getWidth() / 5.5), (int) (header.getHeight() * 0.7), Image.SCALE_SMOOTH
        ));
        JLabel iconLogo = new JLabel();
        iconLogo.addMouseListener(this);
        iconLogo.setIcon(imgLogo2);
        iconLogo.setCursor(new Cursor(HAND_CURSOR));
        iconLogo.setBounds(10, (header.getHeight() / 2) - ((int) (header.getHeight() * 0.7) / 2), (int) (header.getWidth() / 5.5), (int) (header.getHeight() * 0.7));

        header.add(iconLogo);
        panelP.add(header);
        panelP.add(header);
    }

    void constructDrawerStatic() {
        ArrayList<JPanel> panelsMenu = new ArrayList<JPanel>();

        int topPanelHeight = header.getHeight();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = (int) (widthWindow * 0.2);

        JLabel fill;
        drawerStatic = new JPanel();
        drawerStatic.setBounds(0, topPanelHeight, widthPanel, heightPanel);
        drawerStatic.setBackground(constantUtilities.primaryColorBlack);
        drawerStatic.setLayout(new GridLayout(15, 1));

        itemRolUser = new JPanel();
        itemRolUser.setBackground(constantUtilities.primaryColorBlack);
        itemRolUser.setLayout(new GridLayout(1, 1));
//        fill = new JLabel(constantUtilities.usuario.getName() + constantUtilities.usuario.getLastname());
        fill = new JLabel("Promotor");
        fill.setHorizontalAlignment(SwingConstants.CENTER);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(constantUtilities.secundaryColor);
        itemRolUser.add(fill);

        itemNavigation = new JPanel();
        itemNavigation.addMouseListener(this);
        itemNavigation.setBackground(constantUtilities.secundaryColorBlack);
        itemNavigation.setLayout(new GridLayout(1, 1));
        ColorUIResource colorFontitem2 = new ColorUIResource(45, 89, 99);
        fill = new JLabel("Navigation");
        fill.setBorder(new EmptyBorder(0, 15, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEFT);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(colorFontitem2);
        itemNavigation.add(fill);

        menuItemHome = new JPanel();
        menuItemHome.addMouseListener(this);
        menuItemHome.setBackground(constantUtilities.primaryColorBlack);
        menuItemHome.setLayout(new GridLayout(1, 1));
        menuItemHome.setCursor(new Cursor(HAND_CURSOR));
        fill = new JLabel("Home");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemHome.add(fill);
        //
        menuItemUsers = new JPanel();
        menuItemUsers.addMouseListener(this);
        menuItemUsers.setBackground(constantUtilities.primaryColorBlack);
        menuItemUsers.setLayout(new GridLayout(1, 1));
        menuItemUsers.setCursor(new Cursor(HAND_CURSOR));
        fill = new JLabel("Usuarios");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemUsers.add(fill);

        menuItemTasks = new JPanel();
        menuItemTasks.addMouseListener(this);
        menuItemTasks.setBackground(constantUtilities.primaryColorBlack);
        menuItemTasks.setLayout(new GridLayout(1, 1));
        menuItemTasks.setCursor(new Cursor(HAND_CURSOR));
        fill = new JLabel("Tareas");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemTasks.add(fill);

        drawerStatic.add(itemRolUser);
        drawerStatic.add(itemNavigation);
        drawerStatic.add(menuItemHome);

        try {
            if (constantUtilities.projectId != 0L) {
                drawerStatic.add(menuItemUsers);
                drawerStatic.add(menuItemTasks);

            }
        } catch (Exception e) {
        }

        panelP.add(drawerStatic);
    }

    void createTasksModule() {
        try {
            int topPanelHeight = header.getHeight();
            int widthPanelDrawer = drawerStatic.getWidth();
            int heightPanel = heightWindow - topPanelHeight;
            int widthPanel = widthWindow - widthPanelDrawer;

            contentHome.removeAll();

            panelP.updateUI();
            panelP.repaint();

            createTasksModule = new TasksModule(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38, this);
            scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
            scrollContentHome.setViewportView(createTasksModule);
            scrollContentHome.setBackground(Color.white);
            scrollContentHome.updateUI();
            scrollContentHome.repaint();

            createTasksModule.getDialogRegister().addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    createTasksModule.removeAll();
                    createTasksModule = null;
                    createTasksModule();
//                    createUserModule.removeAll();
//                    createUserModule = null;
//                    createUserModule();
                }
            });

            for (DialogDeleteTask object : createTasksModule.getDeletes()) {
                object.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(WindowEvent winEvt) {
                        createTasksModule.removeAll();
                        createTasksModule = null;
                        createTasksModule();
//                    createUserModule.removeAll();
//                    createUserModule = null;
//                    createUserModule();
                    }
                });
            }
            for (DialogDeleteTask object : createTasksModule.getDeletes()) {
                object.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(WindowEvent winEvt) {
                        createTasksModule.removeAll();
                        createTasksModule = null;
                        createTasksModule();
//                    createUserModule.removeAll();
//                    createUserModule = null;
//                    createUserModule();
                    }
                });
            }
            for (DialogRegisterTask object : createTasksModule.getEdits()) {
                object.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(WindowEvent winEvt) {
                        createTasksModule.removeAll();
                        createTasksModule = null;
                        createTasksModule();
//                    createUserModule.removeAll();
//                    createUserModule = null;
//                    createUserModule();
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    void createUsersModule() {
        try {
            int topPanelHeight = header.getHeight();
            int widthPanelDrawer = drawerStatic.getWidth();
            int heightPanel = heightWindow - topPanelHeight;
            int widthPanel = widthWindow - widthPanelDrawer;

            contentHome.removeAll();

            panelP.updateUI();
            panelP.repaint();

//        try {
//            createTasksModule.removeAll();
//            createTasksModule =null;
//        } catch (Exception e) {
//        }
            createUsersModule = new UsersOfMyProjectModel(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38, this);
            scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
            scrollContentHome.setViewportView(createUsersModule);
            scrollContentHome.setBackground(Color.white);
            scrollContentHome.updateUI();
            scrollContentHome.repaint();

//        DialogAddUsersToProject s = createUsersModule.getAdd();
//         for (DialogDeleteTask object : createUsersModule.getAdd()()) {
            createUsersModule.getAdd().addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    createUsersModule.removeAll();
                    createUsersModule = null;
                    createUsersModule();
//                    createUserModule.removeAll();
//                    createUserModule = null;
//                    createUserModule();
                }
            });
//        }
        } catch (Exception e) {
        }
    }

    public void mouseClicked(MouseEvent evento) {

        if (evento.getSource() == menuItemHome) {
            panelP.remove(scrollContentHome);
            scrollContentHome.removeAll();

            createTasksModule = null;
            contentHome.removeAll();
//            panelTitleHome.removeAll();
//            panelP.remove(panelTitleHome);
            panelP.remove(contentHome);
            reloadHome();
//            panelTitleHome.updateUI();
//            panelTitleHome.repaint();
            scrollContentHome.setBackground(Color.white);
            scrollContentHome.updateUI();
            scrollContentHome.repaint();

        }
        try {
            if (constantUtilities.projectId != 0L) {
                if (evento.getSource() == menuItemTasks) {
                    createTasksModule();
                }
            }

            if (evento.getSource() == finishProjectButton) {
                FinalizeProjectProvider finalize = new FinalizeProjectProvider();
                if (finalize.finalize(constantUtilities.projectId)) {
                    this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            }

            if (evento.getSource() == menuItemUsers) {
                createUsersModule();
            }
        } catch (Exception e) {

        }

    }

    void reloadHome() {
        constructPanelHome();
        contentHome.updateUI();
        contentHome.repaint();
        scrollContentHome.updateUI();
        scrollContentHome.repaint();
    }

    public void mousePressed(MouseEvent evento) {
    }

    public void mouseReleased(MouseEvent evento) {
    }

    public void mouseExited(MouseEvent evento) {
        if (evento.getSource() == menuItemTasks) {
            menuItemTasks.setBackground(constantUtilities.primaryColorBlack);
        }
        if (evento.getSource() == menuItemHome) {
            menuItemHome.setBackground(constantUtilities.primaryColorBlack);
        }
        if (evento.getSource() == menuItemUsers) {
            menuItemUsers.setBackground(constantUtilities.primaryColorBlack);
        }
    }

    public void mouseEntered(MouseEvent evento) {
        if (evento.getSource() == menuItemTasks) {
            menuItemTasks.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }
        if (evento.getSource() == menuItemHome) {
            menuItemHome.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }
        if (evento.getSource() == menuItemUsers) {
            menuItemUsers.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }
    }
}
