/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.Models.ResponseGetTaskModel;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author juan barraza
 */
public class ElementTaskAdmin extends JPanel {

    int widthPanel;
    int heightPanel;
    long id;
    JLabel picLabel = new JLabel();

    public ElementTaskAdmin(int x, int y, int w, int h, ResponseGetTaskModel task, JFrame marcoFather) {
        this.id = task.getTask().getId();

        widthPanel = w;
        heightPanel = h;

        int wImage = (heightPanel / 2) - 10;

        setBounds(x, y, w, h);
        setBackground(constantUtilities.colorItemTask);
        setLayout(null);

        int xEye = widthPanel - (w / 8);
        int yEye = (y / 2) - ((w / 5) / 2);

        JPanel panelEye = new JPanel();
        panelEye.setBackground(Color.black);
        panelEye.setBounds(xEye, (heightPanel / 2) - (wImage / 2), wImage, wImage);

        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/eye.png").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(panelEye.getWidth(), panelEye.getHeight(), Image.SCALE_SMOOTH));
        picLabel.setIcon(img2);
        picLabel.setCursor(new Cursor(HAND_CURSOR));
        picLabel.setBounds(xEye, (heightPanel / 2 - (wImage / 2)), panelEye.getWidth(), panelEye.getHeight());

        JPanel title = new JPanel();
        title.setLayout(new GridLayout(1, 1));
        title.setBounds(10, heightPanel / 2, picLabel.getX() - 20, (heightPanel / 2) - 5);

        JLabel titleText = new JLabel("nombre de la tarea");
        title.add(titleText);

        JPanel isCompleted = new JPanel();
        isCompleted.setLayout(new GridLayout(1, 1));
        isCompleted.setBounds(10, 5, picLabel.getX() - 20, (heightPanel / 2) - 5);

        String text = task.getTask().getIs_completed() ? "Completada" : "Incompleta";
        JLabel isCompletedText = new JLabel(text);
        isCompletedText.setForeground(task.getTask().getIs_completed() ? new ColorUIResource(115, 214, 115) : new ColorUIResource(225, 24, 32));
        isCompleted.add(isCompletedText);

        picLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent arg0) {
                String msj = "USUARIO ASIGNADO: \n";
                try {
                    msj += "Nombre :" + task.getUser().getName();
                    msj += "\nEmail :" + task.getUser().getEmail();
                    msj += "\nTelefono :" + task.getUser().getNumber_phone();

                } catch (Exception e) {
                    msj += "No tiene empleado esta tarea";
                }
                JOptionPane.showMessageDialog(null, msj);
            }

            public void mouseEntered(MouseEvent arg0) {
            }

            public void mouseExited(MouseEvent arg0) {
            }

            public void mousePressed(MouseEvent arg0) {
            }

            public void mouseReleased(MouseEvent arg0) {
            }
        });

        add(picLabel);
        add(title);
        add(isCompleted);
    }

}
