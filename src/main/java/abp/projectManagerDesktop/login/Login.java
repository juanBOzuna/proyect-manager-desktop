/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.login;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;


/**
 *
 * @author juan barraza
 */
public class Login {
    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static void main(String[] args) {
            VentanaLogin v = new VentanaLogin(width,height);
    }   
}

class VentanaLogin extends JFrame {
    JPanel description;
    JPanel form;
    JPanel panelP;

    public VentanaLogin(int widthScreenSize , int heightScreenSize){
        panelP = new JPanel();
        setVisible(true);
        setSize(widthScreenSize/2  ,heightScreenSize/2 );
        setLayout(null);
        panelP.setBounds(0,0,getWidth(),getHeight());
        panelP.setLayout(null);
        initComponents();
        add(panelP);
        panelP.updateUI();
        panelP.updateUI();
        panelP.repaint();
    }

    void initComponents() {
        constructDescription();
        constructForm();
    }

    void constructDescription(){
        ColorUIResource colorPannel = new ColorUIResource(31, 124, 197);
        description = new JPanel();
        description.setBackground(colorPannel);
        description.setBounds(0,0,getWidth()/2,getHeight() );
        panelP.add(description);
    }

    void constructForm(){
     form = new JPanel();
     form.setBackground(Color.white);
     form.setBounds(description.getWidth(),0,description.getWidth(),description.getHeight());
     form.setLayout(null);
     JPanel contentImage = new JPanel();
      Image img = new ImageIcon("C:\\Users\\juan barraza\\IdeaProjects\\step_gui_abp\\src\\icons\\Group 3.png").getImage();
      ImageIcon img2 = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
      JLabel picLabel = new JLabel(img2);
      picLabel.setBounds((form.getWidth()/2)-50  ,20,100,100);

    form.add(picLabel);
        panelP.add(form);
    }
}
