/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class Home {

    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        VentanaHome v = new VentanaHome(width, height);
    }
}

class VentanaHome extends JFrame implements MouseListener {

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
    JPanel menuItemTasks;
    JPanel menuItemUsers;
    JLabel textAuxForPanelContent;
//    JPanel stepPanel;
    JPanel headerStep;
    JPanel tasks;
    JScrollPane scrollStep;

    public VentanaHome(int widthScreenSize, int heightScreenSize) {

        setLayout(null);
        setVisible(true);

        setSize((int) (widthScreenSize), (int) (heightScreenSize * 0.9));
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);

        panelP = new JPanel();
        panelP.setBounds(0, 0, getWidth(), getHeight());
        panelP.setLayout(null);
        initComponents();

        panelP.updateUI();
        panelP.repaint();
//           this.repaint();
//           SwingUtilities.updateComponentTreeUI(this );
//           this.updateUI();
    }

    void initComponents() {
        widthWindow = getWidth();
        heightWindow = getHeight();
        constructHeader();
        constructDrawerStatic();
        constructPanelHome();
//         System.out.print("tamano:  "+  );
        add(panelP);

    }

    void constructHeader() {
        int heightPanel = (int) (heightWindow * 0.08);
        header = new JPanel();
        header.setBounds(0, 0, widthWindow, heightPanel);
        header.setBackground(constantUtilities.primaryColor);
        header.setLayout(null);
        panelP.add(header);
        panelP.add(header);
    }

    void constructDrawerStatic() {
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
        fill = new JLabel("Super Admin");
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
        fill = new JLabel("Home");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemHome.add(fill);

        menuItemTasks = new JPanel();
        menuItemTasks.addMouseListener(this);
        menuItemTasks.setBackground(constantUtilities.primaryColorBlack);
        menuItemTasks.setLayout(new GridLayout(1, 1));
        fill = new JLabel("Proyectos");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemTasks.add(fill);

        menuItemUsers = new JPanel();
        menuItemUsers.addMouseListener(this);
        menuItemUsers.setBackground(constantUtilities.primaryColorBlack);
        menuItemUsers.setLayout(new GridLayout(1, 1));
        fill = new JLabel("Usuarios");
        fill.setBorder(new EmptyBorder(0, 20, 0, 0));
        fill.setHorizontalAlignment(SwingConstants.LEADING);
        fill.setVerticalAlignment(SwingConstants.CENTER);
        fill.setForeground(Color.white);
        menuItemUsers.add(fill);

        drawerStatic.add(itemRolUser);
        drawerStatic.add(itemNavigation);
        drawerStatic.add(menuItemHome);
        drawerStatic.add(menuItemTasks);
        drawerStatic.add(menuItemUsers);

        panelP.add(drawerStatic);
//          add(/drawerStatic);
    }

    void constructPanelHome() {
        //sizes
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;
        //sizes

        //define scroll of home panel
        scrollContentHome = new JScrollPane();
        scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
        //define scroll of home panel

        //define panelContentHome
        contentHome = new JPanel();
        contentHome.setBackground(constantUtilities.secundaryColor);
        contentHome.setLayout(null);
        //define panelContentHome

        //define title desktop
        JLabel title = new JLabel("Mi escritorio");
        title.setFont(new Font("Segoe UI Semibold", 0, 38));
        title.setBounds(30, 40, widthPanel, 38);
        int widthPanelStep = (int) (widthWindow * 0.30);
        //define title desktop

        int xStep = 0;
        int widthStep = (int) (widthWindow * 0.25);
        int yStep = (title.getY() * 2) + (int) (title.getHeight() * 0.8);
        int heightStep = (int) (heightPanel * 0.9) - yStep;
        JLabel labelTitle;
        JLabel labelSubtitle;

        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                xStep = widthStep + scrollStep.getX() + 83;
            } else {
                xStep = 45;
            }

            //add title
            labelTitle = new JLabel("Etapa " + (i + 1));
            labelTitle.setFont(new Font("Segoe UI Semibold", 0, 21));
            labelTitle.setBounds(0, 0, widthStep, 21);
            //add title

            // add subtitle
            int heightTitleStep = labelTitle.getHeight();
            labelSubtitle = new JLabel("Tienes 9 tareas pendientes el dia de hoy  ");
            labelSubtitle.setFont(new Font("Segoe UI", 0, 12));
            labelSubtitle.setBounds(0, (int) (heightTitleStep * 3.5), widthStep, 14);
            labelSubtitle.setForeground(Color.gray);
            // add subtitle

            //add divider
            JPanel divider = new JPanel();
            divider.setBounds(0, labelSubtitle.getY() + (labelSubtitle.getHeight() * 2), widthStep, 1);
            divider.setBackground(Color.black);
            //add divider

            //Define what the test is
            headerStep = new JPanel();
            headerStep.setLayout(null);
            headerStep.setBackground(constantUtilities.secundaryColor);
            headerStep.setBounds(xStep, yStep, widthStep, divider.getY() + 1);
            //Define what the test is

            //Add title, subtitle and divider 
            headerStep.add(labelTitle);
            headerStep.add(labelSubtitle);
            headerStep.add(divider);
            //Add title, subtitle and divider 

            tasks = new JPanel();
//            tasks.setLayout(null);
            //add tasks in step panel
            int yTask = 0;
            int heightTask = heightPanel / 13;
            ElementTask task;
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    yTask = 0;
                } else {
                    yTask = heightTask + yTask + 8;
                }
                task = new ElementTask(0, yTask, widthStep, heightTask);
                tasks.add(task);
            }
            //add tasks in step panel

            //define properties of panel step
            tasks.setLayout(null);
            tasks.setBackground(Color.white);
            tasks.setPreferredSize(new DimensionUIResource(widthStep, yTask + heightTask));
            tasks.setBackground(constantUtilities.secundaryColor);
            //define properties of panel step

            //define scroll of panel step
            scrollStep = new JScrollPane();
            scrollStep.setBounds(xStep, headerStep.getHeight() + headerStep.getY(), widthStep + 20, heightStep - headerStep.getHeight());
            scrollStep.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            scrollStep.setViewportView(tasks);
            //define scroll of panel step

//            step
            contentHome.add(headerStep);
            contentHome.add(scrollStep);
            contentHome.updateUI();
            contentHome.repaint();

        }
        contentHome.setPreferredSize(new DimensionUIResource((widthPanelStep * 5) + (35 * 5), heightPanel - 20));
        contentHome.add(title);

        scrollContentHome.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollContentHome.getVerticalScrollBar().setUnitIncrement(20);
        scrollContentHome.getHorizontalScrollBar().setUnitIncrement(20);
        scrollContentHome.setViewportView(contentHome);
        panelP.add(scrollContentHome);
    }

    void constructModulProyects() {
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;
        contentHome.removeAll();
        contentHome.updateUI();
        contentHome.repaint();

        contentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel);
        contentHome.setBackground(constantUtilities.secundaryColor);
        textAuxForPanelContent = new JLabel();
        textAuxForPanelContent.setText("Proyectos");
        contentHome.add(textAuxForPanelContent);
    }

    void constructModulUsers() {
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;
        contentHome.removeAll();
        contentHome.updateUI();
        contentHome.repaint();

        contentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel);
        contentHome.setBackground(constantUtilities.secundaryColor);
        textAuxForPanelContent = new JLabel();
        textAuxForPanelContent.setText("Usuarios");
        contentHome.add(textAuxForPanelContent);
    }

    void constructModulHomeAftherSelectOthersModuls() {
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;
        contentHome.removeAll();
        contentHome.updateUI();
        contentHome.repaint();

        contentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel);
        contentHome.setBackground(constantUtilities.secundaryColor);
        textAuxForPanelContent = new JLabel();
        textAuxForPanelContent.setText("Home");
        contentHome.add(textAuxForPanelContent);
    }

    public void mouseClicked(MouseEvent evento) {
        if (evento.getSource() == menuItemHome) {
            constructModulHomeAftherSelectOthersModuls();
//          panelP.remove(header);
//          panelP.updateUI();
//          panelP.repaint();

        }
        if (evento.getSource() == menuItemUsers) {
            constructModulUsers();
        }
        if (evento.getSource() == menuItemTasks) {
            constructModulProyects();
        }
    }

    public void mousePressed(MouseEvent evento) {
    }

    public void mouseReleased(MouseEvent evento) {

    }

    public void mouseExited(MouseEvent evento) {
        if (evento.getSource() == menuItemHome) {
            menuItemHome.setBackground(constantUtilities.primaryColorBlack);
        }
        if (evento.getSource() == menuItemTasks) {
            menuItemTasks.setBackground(constantUtilities.primaryColorBlack);
        }
        if (evento.getSource() == menuItemUsers) {
            menuItemUsers.setBackground(constantUtilities.primaryColorBlack);
        }
    }

    public void mouseEntered(MouseEvent evento) {

        if (evento.getSource() == menuItemHome) {
            menuItemHome.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }

        if (evento.getSource() == menuItemTasks) {
            menuItemTasks.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }

        if (evento.getSource() == menuItemUsers) {
            menuItemUsers.setBackground(constantUtilities.colorItemEnteredOfDrawer);
        }
    }
}

class ElementTask extends JPanel {

    int widthPanel;
    int heightPanel;
    int id;

    public ElementTask(int x, int y, int w, int h) {
        widthPanel = w;
        heightPanel = h;
        setBounds(x, y, w, h);
        setBackground(constantUtilities.colorItemTask);
    }

}
