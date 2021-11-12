/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import static abp.projectManagerDesktop.home.Home.getProjectsAdminProvider;
import abp.projectManagerDesktop.providers.GetProjectsAdminProvider;
import abp.projectManagerDesktop.providers.Models.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author juan barraza
 */
public class Home {

    static GetProjectsAdminProvider getProjectsAdminProvider = new GetProjectsAdminProvider();

    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) throws IOException {
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
    JPanel headerStep;
    JPanel tasks;
    JScrollPane scrollStep;
    JLabel iconReload;
    JLabel iconAdd;

    JPanel menuItemAux;

    public VentanaHome(int widthScreenSize, int heightScreenSize) throws IOException {
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize((int) (widthScreenSize * .9), (int) (heightScreenSize * 0.9));
        setResizable(false);

        panelP = new JPanel();
        panelP.setBounds(0, 0, getWidth(), getHeight());
        panelP.setLayout(null);

        initComponents();

        panelP.updateUI();
        panelP.repaint();
    }

    void initComponents() throws IOException {
        widthWindow = getWidth();
        heightWindow = getHeight();
        constructHeader();
        constructDrawerStatic();
        constructPanelHome(true);
        add(panelP);

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
        menuItemHome.setCursor(new Cursor(HAND_CURSOR));
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
        menuItemTasks.setCursor(new Cursor(HAND_CURSOR));
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
        menuItemUsers.setCursor(new Cursor(HAND_CURSOR));
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
    }

    void constructPanelHome(boolean after) throws IOException {
        //sizes
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;
        //sizes

        //define scroll of home panel
        if (after) {
            scrollContentHome = new JScrollPane();
            scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
            scrollContentHome.getVerticalScrollBar().setUnitIncrement(20);
            scrollContentHome.getHorizontalScrollBar().setUnitIncrement(20);
        } else {
            scrollContentHome = scrollContentHome;
        }

        //define scroll of home panel
        //define panelContentHome
        contentHome = new JPanel();
        contentHome.setBackground(constantUtilities.secundaryColor);
        contentHome.setLayout(null);
        //define panelContentHome

        //define title desktop
        JLabel title = new JLabel("Mi escritorio");
        title.setFont(new Font("Segoe UI Semibold", 0, 38));
        title.setBounds(30, 40, (int) (widthPanel * 0.75), 38);
        int widthPanelStep = (int) (widthWindow * 0.30);
        //define title desktop

        //icon Add
        Image imgAdd = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/add.png").getImage();
        ImageIcon imgAdd2 = new ImageIcon(imgAdd.getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH));
        iconAdd = new JLabel();
        iconAdd.addMouseListener(this);
        iconAdd.setIcon(imgAdd2);
        iconAdd.setCursor(new Cursor(HAND_CURSOR));
        iconAdd.setBounds((widthPanel - 150) - title.getHeight(), title.getY(), title.getHeight(), title.getHeight());
        //icon Add

        //icon Reload
        Image imgReload = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/reload.png").getImage();
        ImageIcon imgReload2 = new ImageIcon(imgReload.getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH));
        iconReload = new JLabel();
        iconReload.setIcon(imgReload2);
        iconReload.setCursor(new Cursor(HAND_CURSOR));
        iconReload.addMouseListener(this);
        iconReload.setBounds((widthPanel - 100) - title.getHeight(), title.getY(), title.getHeight(), title.getHeight());
        //icon Reload

        int xStep = 0;
        int widthStep = (int) (widthWindow * 0.25);
        int yStep = (title.getY() * 2) + (int) (title.getHeight() * 0.8);
        int heightStep = (int) (heightPanel * 0.9) - yStep;
        JLabel labelTitle;
        JLabel labelSubtitle;

        ArrayList<ResponseGetProjectsAdminModel> projects = getProjectsAdmin();
        System.out.println(projects);

        //Add step
        for (int i = 0; i < projects.size(); i++) {

            if (i > 0) {
                xStep = widthStep + scrollStep.getX() + 83;
            } else {
                xStep = 45;
            }

            //add title
            labelTitle = new JLabel(projects.get(i).getProject().getName());
            Font font = labelTitle.getFont();
            Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//            labelTitle.setFont(font.deriveFont(attributes). );

            labelTitle.setFont(new Font("Segoe UI Semibold", 0, 23));
            labelTitle.setBounds(0, 0, widthStep, 23);
            //add title

            DecimalFormat formato1 = new DecimalFormat("#.00");

            // add subtitle
            labelSubtitle = new JLabel("Porcentaje del proyecto: " + formato1.format(projects.get(i).getProject().getPercentageCompleted()) + " %");
            labelSubtitle.setFont(new Font("Segoe UI", 0, 12));
            labelSubtitle.setBounds(0, (int) (labelTitle.getHeight() * 3.5), widthStep, 14);
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

            //Add title, subtitle and divider in header
            headerStep.add(labelTitle);
            headerStep.add(labelSubtitle);
            headerStep.add(divider);
            //Add title, subtitle and divider in header

            //define panel tasks
            tasks = new JPanel();
            //define panel tasks

            //add task in tasks panel
            int yTask = 0;
            int heightTask = heightPanel / 13;
            ElementTaskAdmin task;

            System.out.println("Largo de la");

            for (int j = 0; j < projects.get(i).getTasks().size(); j++) {
                if (j == 0) {
                    yTask = 0;
                } else {
                    yTask = heightTask + yTask + 8;
                }
                task = new ElementTaskAdmin(0, yTask, widthStep, heightTask, projects.get(i).getTasks().get(j), this);
                tasks.add(task);
            }
            //add tasks in tasks panel

            //define properties of tasks panel
            tasks.setLayout(null);
            tasks.setBackground(Color.white);
            tasks.setPreferredSize(new DimensionUIResource(widthStep, yTask + heightTask));
            tasks.setBackground(constantUtilities.secundaryColor);
            //define properties of tasks panel

            //define scroll of tasks panel
            scrollStep = new JScrollPane();
            scrollStep.setBounds(xStep, headerStep.getHeight() + headerStep.getY(), widthStep + 20, heightStep - headerStep.getHeight());
            scrollStep.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            scrollStep.setViewportView(tasks);
            //define scroll of tasks panel

            contentHome.add(headerStep);
            contentHome.add(scrollStep);
        }
        //Add step

        contentHome.setPreferredSize(new DimensionUIResource((widthPanelStep * projects.size()) + (35 * projects.size()), heightPanel - 20));
        contentHome.add(title);
        contentHome.add(iconAdd);
        contentHome.add(iconReload);
        scrollContentHome.setViewportView(contentHome);
        panelP.add(scrollContentHome);
    }

//    void constructModulProyects() {
//        int topPanelHeight = header.getHeight();
//        int widthPanelDrawer = drawerStatic.getWidth();
//        int heightPanel = heightWindow - topPanelHeight;
//        int widthPanel = widthWindow - widthPanelDrawer;
//        contentHome.removeAll();
//        contentHome.updateUI();
//        contentHome.repaint();
//
//        contentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel);
//        contentHome.setBackground(constantUtilities.secundaryColor);
//        textAuxForPanelContent = new JLabel();
//        textAuxForPanelContent.setText("Proyectos");
//        contentHome.add(textAuxForPanelContent);
//    }
//    void constructModulUsers() {
//        int topPanelHeight = header.getHeight();
//        int widthPanelDrawer = drawerStatic.getWidth();
//        int heightPanel = heightWindow - topPanelHeight;
//        int widthPanel = widthWindow - widthPanelDrawer;
//        contentHome.removeAll();
//        contentHome.updateUI();
//        contentHome.repaint();
//
//        contentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel);
//        contentHome.setBackground(constantUtilities.secundaryColor);
//        textAuxForPanelContent = new JLabel();
//        textAuxForPanelContent.setText("Usuarios");
//        contentHome.add(textAuxForPanelContent);
//    }
    ArrayList<ResponseGetProjectsAdminModel> getProjectsAdmin() {

        try {
            return getProjectsAdminProvider.getModules();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se  puece conectar reinicie la app");
            return null;
        }
    }

    public void mouseClicked(MouseEvent evento) {

        if (evento.getSource() == menuItemUsers) {
            int topPanelHeight = header.getHeight();
            int widthPanelDrawer = drawerStatic.getWidth();
            int heightPanel = heightWindow - topPanelHeight;
            int widthPanel = widthWindow - widthPanelDrawer;

            contentHome.removeAll();

            UsersModule createUserModule = new UsersModule(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);

            scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
            scrollContentHome.setViewportView(createUserModule);
            scrollContentHome.setBackground(Color.white);
            scrollContentHome.updateUI();
            scrollContentHome.repaint();

        }

        if (evento.getSource() == menuItemHome) {
            try {
                reloadHome();
            } catch (IOException e) {
            }
        }
        if (evento.getSource() == iconAdd) {
            DialogRegisterProject d = new DialogRegisterProject(this, false);
            d.setVisible(true);
        }

        if (evento.getSource() == iconReload) {
            try {
                reloadHome();
            } catch (IOException e) {
            }
        }

    }

    void reloadHome() throws IOException {
        contentHome.removeAll();
        contentHome.setBackground(Color.white);

        contentHome.updateUI();
        contentHome.repaint();
        contentHome = null;

        constructPanelHome(false);
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
