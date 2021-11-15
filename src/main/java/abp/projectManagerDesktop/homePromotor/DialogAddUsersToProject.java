/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.GetUsersWithoutProject;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import abp.projectManagerDesktop.providers.PostUserProvider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author juan barraza
 */
public class DialogAddUsersToProject extends JDialog {

    int w, h, y, x;
    JComboBox users = new JComboBox();
//    UserModel user;
    ArrayList<UserModel> usersList = null;

    public DialogAddUsersToProject(JFrame padre, Boolean modo) {

        super(padre, modo);
//        this.user = user;

        w = 400;
        h = 280;
        y = 150;
        x = 150;
        GetUsersWithoutProject getUsersWithoutProject = new GetUsersWithoutProject();
        try {
            usersList = getUsersWithoutProject.get();
        } catch (IOException e) {
        }

        setBounds(x, y, w, h);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Agregar un nuevo usuario al proyecto");
        setLayout(null);

        JLabel title = new JLabel("Escojer el usuario");
        title.setBounds(137, 20, 20 * 18, 20);
        add(title);

        users.setBounds(137, 50, 100, 40);
        users.addItem("ninguno");

        try {
            for (UserModel userModel : usersList) {
                users.addItem(userModel.getId() + "-" + userModel.getName());
            }
        } catch (Exception e) {
        }

        add(users);

        JButton send = new JButton();
        send.setText("Agregar");
        send.setBounds(137, 140, 100, 35);

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendUserToProvider();
            }
        });
        add(send);
    }

    void sendUserToProvider() {
        PostUserProvider post = new PostUserProvider();
        if (String.valueOf(users.getSelectedItem()).equals("ninguno")) {

        } else {
            UserModel userSelected = new UserModel();
            for (UserModel userModel : usersList) {
                String[] split = String.valueOf(users.getSelectedItem()).split("-");

                if (userModel.getId() == Long.parseLong(split[0])) {
                    userSelected = userModel;
                    userSelected.setProjectId(constantUtilities.projectId);
                }
            }

            try {
                if (post.postUser(userSelected.getAddress(), userSelected.getDni(), userSelected.getEmail(), userSelected.getLastname(), userSelected.getName(), userSelected.getNumber_phone(), userSelected.getRole(), userSelected.getHiring_date(), userSelected.getPassword(), true, true, userSelected.getId(), constantUtilities.projectId)) {
                    this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                } else {
                    this.dispose();
                }
            } catch (Exception e) {
            }

        }
    }
}
