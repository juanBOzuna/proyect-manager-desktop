/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.providers.DeleteUserProvider;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author juan barraza
 */
public class DialogDeleteUser extends JDialog implements MouseListener {

    int w, h, y, x;
    JPanel buttonDelete;
    JPanel buttonCancel;
    JPanel panelP = new JPanel();
    UserModel user;

    public DialogDeleteUser(JFrame padre, Boolean modo, UserModel user) {

        super(padre, modo);
        this.user = user;
        w = 400;
        h = 200;
        y = 150;
        x = 150;

        setBounds(x, y, w, h);
        panelP.setSize(w, h);
        panelP.setBackground(Color.white);
        panelP.setLayout(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Eliminar a " + user.getName() + user.getLastname());
        setLayout(null);
        initComponents();
    }

    void initComponents() {
        setBackground(Color.WHITE);
        String titleText = "Borrar a \n juan?";
        JLabel title = new JLabel();
        title.setText(titleText);
        title.setFont(new Font("Segoe UI", 0, 20));
        title.setBounds(swfp(0.05), shfp(0.1), swfp(0.9), shfp(0.27));

        JLabel titleButonDelete = new JLabel("Borrar");
        titleButonDelete.setHorizontalAlignment(SwingConstants.CENTER);
        titleButonDelete.setVerticalAlignment(SwingConstants.CENTER);
        titleButonDelete.setForeground(Color.white);

        JLabel titleButonCancel = new JLabel("Cancelar");
        titleButonCancel.setHorizontalAlignment(SwingConstants.CENTER);
        titleButonCancel.setVerticalAlignment(SwingConstants.CENTER);
        titleButonCancel.setForeground(Color.white);

        buttonDelete = new JPanel();
        buttonDelete.setLayout(new GridLayout(1, 1));
        buttonDelete.setBounds(swfp(0.05), shfp(0.68), swfp(0.425), shfp(0.225));
        buttonDelete.setBackground(new ColorUIResource(244, 67, 54));
        buttonDelete.addMouseListener(this);
        buttonDelete.setCursor(new Cursor(HAND_CURSOR));
        buttonDelete.add(titleButonDelete);

        buttonCancel = new JPanel();
        buttonCancel.setLayout(new GridLayout(1, 1));
        buttonCancel.setBounds(swfp(0.52625), shfp(0.68), swfp(0.425), shfp(0.225));
        buttonCancel.setBackground(new ColorUIResource(76, 175, 80));
        buttonCancel.addMouseListener(this);
        buttonCancel.setCursor(new Cursor(HAND_CURSOR));
        buttonCancel.add(titleButonCancel);

        panelP.add(title);
        panelP.add(buttonDelete);
        panelP.add(buttonCancel);

        add(panelP);
    }

    int swfp(double number) {
        return (int) ((w - 20) * number);
    }

    int shfp(double number) {
        return (int) ((h - 40) * number);
    }

    void deleteUser() {
        DeleteUserProvider deleteUserProvider = new DeleteUserProvider();

        try {
            if (deleteUserProvider.delete(user.getId())) {
                this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } else {
                this.dispose();
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == buttonDelete) {
            deleteUser();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
