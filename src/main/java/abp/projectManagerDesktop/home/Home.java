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
import javax.swing.border.Border;
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
    JPanel panelTitleHome;
    UsersModule createUserModule;
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
        int topPanelHeight = header.getHeight();
        int widthPanelDrawer = drawerStatic.getWidth();
        int heightPanel = heightWindow - topPanelHeight;
        int widthPanel = widthWindow - widthPanelDrawer;

//         define title desktop
        JLabel title = new JLabel("Mi escritorio");
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds(30, topPanelHeight - 20, (int) (widthPanel * 0.75), 40);
        int widthPanelStep = (int) (widthWindow * 0.30);
        //define title desktop

//        icon Add
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

        //define panel of title
        panelTitleHome = new JPanel();
        panelTitleHome.setLayout(null);
        panelTitleHome.setBounds(widthPanelDrawer + 1, topPanelHeight, widthPanel, topPanelHeight * 2);

        // add elements of title
        panelTitleHome.add(title);
        panelTitleHome.setBackground(Color.WHITE);
        panelTitleHome.add(iconAdd);
        panelTitleHome.add(iconReload);
        // add elements of title

        //add panel title
        panelP.add(panelTitleHome);
        //add panel title

        //define scroll content
        scrollContentHome = new JScrollPane();
        scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight + panelTitleHome.getHeight(), widthPanel, heightPanel - panelTitleHome.getHeight() - 38);
        scrollContentHome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollContentHome.getVerticalScrollBar().setUnitIncrement(20);
        scrollContentHome.getHorizontalScrollBar().setUnitIncrement(20);
        //define scroll content

        //define content
        contentHome = new JPanel();
        contentHome.setBackground(constantUtilities.secundaryColor);
        contentHome.setLayout(null);
        //define content

        int xStep = 0;
        int widthStep = (int) (widthWindow * 0.25);
        int yStep = 20;
        int heightStep = (int) ((heightPanel - panelTitleHome.getHeight()) * 0.9) - yStep;
        JLabel labelTitle;
        JLabel labelSubtitle;

        ArrayList<ResponseGetProjectsAdminModel> projects = getProjectsAdmin();
        System.out.println(projects);
//        //Add step
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

            labelTitle.setFont(new Font("Segoe UI", 0, 25));
            labelTitle.setBounds(0, 0, widthStep, 35);
            //add title

            DecimalFormat formato1 = new DecimalFormat("#.00");

            // add subtitle
            labelSubtitle = new JLabel("Porcentaje del proyecto: " + formato1.format(projects.get(i).getProject().getPercentageCompleted()) + " %");
            labelSubtitle.setFont(new Font("Segoe UI", 0, 12));
            labelSubtitle.setBounds(0, (int) (labelTitle.getHeight() * 2.5), widthStep, 14);
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
            scrollStep.setBounds(xStep, headerStep.getHeight() + headerStep.getY() - 2, widthStep + 20, heightStep - headerStep.getHeight());
            scrollStep.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            scrollStep.setViewportView(tasks);
            //define scroll of tasks panel

            contentHome.add(headerStep);
            contentHome.add(scrollStep);
        }
//        //Add step

        contentHome.setPreferredSize(new DimensionUIResource((widthPanelStep * projects.size()) + (35 * projects.size()), (heightPanel - title.getHeight()) - 20));
        scrollContentHome.setViewportView(contentHome);
        panelP.add(scrollContentHome);
    }

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

            panelTitleHome.removeAll();
            panelTitleHome.updateUI();
            panelTitleHome.repaint();

            panelP.remove(panelTitleHome);
            panelP.updateUI();
            panelP.repaint();

            createUserModule = new UsersModule(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38, this);
            scrollContentHome.setBounds(widthPanelDrawer, topPanelHeight, widthPanel, heightPanel - 38);
            scrollContentHome.setViewportView(createUserModule);
            scrollContentHome.setBackground(Color.white);
            scrollContentHome.updateUI();
            scrollContentHome.repaint();

        }

        if (evento.getSource() == menuItemHome) {

            try {
                panelP.remove(scrollContentHome);
                scrollContentHome.removeAll();

                createUserModule = null;
                contentHome.removeAll();
                panelTitleHome.removeAll();
                panelP.remove(panelTitleHome);
                panelP.remove(contentHome);
                reloadHome(false);
                panelTitleHome.updateUI();
                panelTitleHome.repaint();
                scrollContentHome.setBackground(Color.white);
                scrollContentHome.updateUI();
                scrollContentHome.repaint();

            } catch (IOException e) {
            }
        }
        if (evento.getSource() == iconAdd) {
            DialogRegisterProject d = new DialogRegisterProject(this, false);
            d.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(WindowEvent winEvt) {
                    try {
                        panelP.remove(scrollContentHome);
                        scrollContentHome.removeAll();

                        createUserModule = null;
                        contentHome.removeAll();
                        panelTitleHome.removeAll();
                        panelP.remove(panelTitleHome);
                        panelP.remove(contentHome);
                        reloadHome(false);
                        panelTitleHome.updateUI();
                        panelTitleHome.repaint();
                        scrollContentHome.setBackground(Color.white);
                        scrollContentHome.updateUI();
                        scrollContentHome.repaint();
                    } catch (IOException e) {
                    }
                }
            });
            d.setVisible(true);
        }

        if (evento.getSource() == iconReload) {
            try {
                panelP.remove(scrollContentHome);
                scrollContentHome.removeAll();

                createUserModule = null;
                contentHome.removeAll();
                panelTitleHome.removeAll();
                panelP.remove(panelTitleHome);
                panelP.remove(contentHome);
                reloadHome(false);
                panelTitleHome.updateUI();
                panelTitleHome.repaint();
                scrollContentHome.setBackground(Color.white);
                scrollContentHome.updateUI();
                scrollContentHome.repaint();
            } catch (IOException e) {
            }
        }

    }

    void reloadHome(boolean fromUser) throws IOException {
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
