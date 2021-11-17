/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author juan barraza
 */
public class ElementUsersPromotor extends JPanel implements MouseListener {

//    DialogDeleteTask delete;
//    JFrame padre;
    int w, h;
    JLabel picLabel;
    JLabel picLabelDelete;
    JLabel picLabelEdit;
    UserModel user;
//    TaskModel task;

    public ElementUsersPromotor(int x, int y, int w, int h, JFrame marcoFather, UserModel user) {
        this.w = w;
        this.h = h;

        this.user = user;

        setBounds(x, y, w, h);
        setBackground(constantUtilities.colorItemTask);
        setLayout(null);

        int xEye = w - (w / 13);
        int yEye = (y / 2) - ((w / 5) / 2);
        int wImage = (h / 2) - 5;
        JPanel panelEye = new JPanel();
        panelEye.setBackground(Color.black);
        panelEye.setBounds(xEye, (h / 2) - (wImage / 2), wImage, wImage);

//        Image imgDelete = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/remove.png").getImage();
//        ImageIcon imgDelete2 = new ImageIcon(imgDelete.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
//        picLabelDelete = new JLabel();
//        picLabelDelete.setIcon(imgDelete2);
//        picLabelDelete.addMouseListener(this);
//        picLabelDelete.setCursor(new Cursor(HAND_CURSOR));
//        picLabelDelete.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);
//        xEye -= (wImage + 5);
        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/eye.png").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
        picLabel = new JLabel();
        picLabel.setIcon(img2);
        picLabel.addMouseListener(this);
        picLabel.setCursor(new Cursor(HAND_CURSOR));
        picLabel.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

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
        title.setBounds(10, (h / 2) - (((h / 2) - 5) / 2), xEye - 20, (h / 2) - 5);

        JLabel titleText = new JLabel(user.getName() + " " + user.getLastname());
        title.add(titleText);

        add(title);
        add(picLabel);
//        add(picLabelEdit);
//        add(picLabelDelete);
    }

    String getDataUSer() {
        String content = "PROJECTO #" + user.getId() + " - " + user.getName();
        try {
            content += "\nDireccion: " + user.getAddress();
        } catch (Exception e) {
        }

        try {
//            String dateInit = projectModel.getProject().getDate_init().substring(0, 10);
            content += "\nNumero de telefono: " + user.getNumber_phone();
        } catch (Exception e) {
        }

        try {
//            String dateFinish = projectModel.getProject().getDate_finish().substring(0, 10);
            content += "\nCorreo : " + user.getEmail();
        } catch (Exception e) {
        }

        try {
            String dateHiring = user.getHiring_date().substring(0, 10);
            content += "\nFecha de contratacion : " + dateHiring;
        } catch (Exception e) {
        }

        return content;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == picLabel) {
  JOptionPane.showMessageDialog(null, getDataUSer());
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
