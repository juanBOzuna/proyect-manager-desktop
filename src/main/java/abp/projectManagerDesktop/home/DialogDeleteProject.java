/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.providers.Models.UserModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan barraza
 */
public class DialogDeleteProject extends JDialog {

    int w, h, y, x;
    JPanel buttonDelete;
    JPanel buttonCancel;

    public DialogDeleteProject(JFrame padre, Boolean modo, UserModel user) {
        super(padre, modo);
//        val = promotors;
//        this.user = user;
//        this.edit = edit;

        w = 400;
        h = 200;
        y = 150;
        x = 150;

        setBounds(x, y, w, h);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Eliminar a " + user.getName() + user.getLastname());
        setLayout(null);
//        initComponents();
    }

//    void initComponents() {
//        JLabel title = new JLabel("Esta Seguro que quiere borrar el Usuario: juan? ");
//        title.setBounds(swfp(0.05), shfp(0.18), swfp(0.9), shfp(0.27));
//
//        add(title);
//    }
//
//    int swfp(double number) {
//        return (int) ((w - 20) * number);
//    }
//
//    int shfp(double number) {
//        return (int) (h * number);
//    }

}
