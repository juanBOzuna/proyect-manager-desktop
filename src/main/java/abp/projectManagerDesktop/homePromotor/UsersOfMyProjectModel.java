/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.GetTasksPromotorProvider;
import abp.projectManagerDesktop.providers.GetUsersByProjectId;
import abp.projectManagerDesktop.providers.Models.UserModel;
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
public class UsersOfMyProjectModel extends JPanel implements MouseListener {

    JFrame padre;
    int Width, height;
    JLabel iconAdd;
    ArrayList<UserModel> employees;
    DialogAddUsersToProject add;

    public UsersOfMyProjectModel(int x, int y, int w, int h, JFrame padre) {
        this.padre = padre;
        //        dialogRegister = new DialogRegisterTask(padre, false, false,null);
        Width = w;
        height = h - 38;

        add = new DialogAddUsersToProject(padre, false);
        setLayout(null);
        setPreferredSize(new DimensionUIResource(Width - 38, height));
        setBackground(Color.WHITE);
        initComponents();
    }

    void initComponents() {
        GetUsersByProjectId get = new GetUsersByProjectId();
        try {
            employees = get.get(constantUtilities.projectId);
        } catch (Exception e) {
        }

        JLabel title = new JLabel("Usuarios de mi proyecto");
        title.setFont(new Font("Segoe UI Semibold", 0, 42));
        title.setBounds((int) (Width * 0.051), 40, Width / 2, 38);
        add(title);

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

//            GetTasksPromotorProvider getTasks = new GetTasksPromotorProvider();
//        try {
//            tasks = getTasks.getTasks();
//        } catch (IOException e) {
//        }
//
        ElementUsersPromotor userElement;
//
        int y = title.getY() + title.getHeight() + 50;
        try {
            for (int i = 0; i < employees.size(); i++) {

                if (i >= 1) {
                    y += 60;
                }

                userElement = new ElementUsersPromotor((int) (Width * 0.05), y, (int) (Width * 0.9), 50, padre, employees.get(i));
                add(userElement);
            }
        } catch (Exception e) {
        }

        try {
            int heightPreferred = title.getY() + title.getHeight() + (60 * employees.size()) + 60;
            setPreferredSize(new DimensionUIResource(Width, heightPreferred));
        } catch (Exception e) {
            int heightPreferred = title.getY() + title.getHeight() + (60 * 2) + 60;
            setPreferredSize(new DimensionUIResource(Width, heightPreferred));
        }

    }

    public DialogAddUsersToProject getAdd() {
        return add;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == iconAdd) {
//            d = new DialogAddUsersToProject(padre, false);
            add.setVisible(true);
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
