/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.login;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.home.HomeAdmin;
import abp.projectManagerDesktop.homeEmployee.HomeEMployee;
import abp.projectManagerDesktop.homePromotor.HomePromotor;
import abp.projectManagerDesktop.providers.LoginProvider;
import abp.projectManagerDesktop.providers.Models.LoginResponseModel;
import abp.projectManagerDesktop.providers.UserPreferences;
//import abp.projectManagerDesktop.register.Register;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
//import abp.projectManagerDesktop.home.Home;

/**
 *
 * @author juan barraza
 */
public class Login {

    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
//    static UserPreferences prefs = new UserPreferences();

    public static void main(String[] args) {
        VentanaLogin v = new VentanaLogin(width, height);
//System.out.println("juan");
    }

}

class VentanaLogin extends JFrame implements MouseListener {

    JPanel description;
    JPanel form;
    JPanel panelP;
    JPanel button;
    JLabel picLabelLoading;
    Tfmfld inputEmail;
    Tfmfld inputPassword;
//    JLabel register;
//    ConectionPreferences prefs = new ConectionPreferences();

    public VentanaLogin(int widthScreenSize, int heightScreenSize) {
        panelP = new JPanel();

        //properties of window
        setSize(widthScreenSize / 2, heightScreenSize / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        //properties of panelP
        panelP.setBounds(0, 0, getWidth(), getHeight());
        panelP.setLayout(null);

        //init components and add to window
        initComponents();
        add(panelP);

        //update Design
        panelP.updateUI();
        panelP.repaint();
    }

    void initComponents() {
        //construct all components (panelDescription and panelForm)
        constructDescription();
        constructForm();
    }

    void constructDescription() {
        description = new JPanel();
        description.setBackground(constantUtilities.primaryColor);
        description.setBounds(0, 0, getWidth() / 2, getHeight());
        description.setLayout(null);

        int widthImage = (int) (description.getWidth() * 0.75);
        int heightImage = (int) (description.getHeight() / 3.5);

        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/LogoProjectManager.png")
                .getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(widthImage,
                heightImage,
                Image.SCALE_SMOOTH));
        JLabel picLabel = new JLabel(img2);
        picLabel.setBounds((description.getWidth() - widthImage) / 2,
                10,
                widthImage,
                heightImage);
        description.add(picLabel);

        panelP.add(description);
    }

    void constructForm() {
        form = new JPanel();
        form.setBackground(Color.white);
        form.setBounds(description.getWidth(), 0, description.getWidth(), description.getHeight());
        form.setLayout(null);

        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/UserLogin.png").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel picLabel = new JLabel(img2);
        picLabel.setBounds((form.getWidth() / 2) - 50, 20, 100, 100);
        form.add(picLabel);

        Image imgIconUser = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/User.jpg").getImage();
        ImageIcon imgIconUser2 = new ImageIcon(imgIconUser.getScaledInstance(34, 32, Image.SCALE_SMOOTH));
        JLabel picLabelIconUser = new JLabel(imgIconUser2);
        picLabelIconUser.setBounds(30, picLabel.getHeight() + 50, 34, 32);
        form.add(picLabelIconUser);

        Image imgIconLock = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/Lock.jpg").getImage();
        ImageIcon imgIconLock2 = new ImageIcon(imgIconLock.getScaledInstance(34, 32, Image.SCALE_SMOOTH));
        JLabel picLabelIconLock = new JLabel(imgIconLock2);
        picLabelIconLock.setBounds(30, picLabel.getHeight() + 50 + picLabelIconUser.getWidth() + 20, 34, 32);
        form.add(picLabelIconLock);

        inputEmail = new Tfmfld(picLabelIconUser.getX() + picLabelIconUser.getWidth(), picLabelIconUser.getY(), form.getWidth() - picLabelIconUser.getWidth() - 60, picLabelIconUser.getHeight(), " Correo", false);
        form.add(inputEmail);

        inputPassword = new Tfmfld(picLabelIconLock.getX() + picLabelIconLock.getWidth(), picLabelIconLock.getY(), form.getWidth() - picLabelIconLock.getWidth() - 60, picLabelIconLock.getHeight(), " *******", true);
        form.add(inputPassword);

        button = new JPanel();
        button.setBounds(inputPassword.getX() - picLabelIconLock.getWidth(), inputPassword.getY() + 50, inputPassword.getWidth() + picLabelIconLock.getWidth(), 40);
        button.setBackground(constantUtilities.primaryColor);
        button.setCursor(new Cursor(HAND_CURSOR));
        button.setLayout(new GridLayout(1, 1));
        button.addMouseListener(this);
        JLabel text = new JLabel("Iniciar sesion");
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        button.add(text);
        button.setVisible(true);
        form.add(button);

//        register = new JLabel("Registrarse");
//        register.setBounds(button.getX(), button.getY() + button.getHeight() + 5, button.getWidth(), button.getHeight());
//        register.setHorizontalAlignment(SwingConstants.CENTER);
//        register.setCursor(new Cursor(HAND_CURSOR));
//        register.setForeground(constantUtilities.primaryColor);
//        register.addMouseListener(this);
//        form.add(register);
        Icon imgLoading = new ImageIcon("C:\\Users\\juan barraza\\IdeaProjects\\step_gui_abp\\src\\icons\\loading1.gif");
        picLabelLoading = new JLabel(imgLoading);
        picLabelLoading.setBounds((form.getWidth() / 2) - 25, inputPassword.getY() + 50, 50, 50);
        picLabelLoading.setVisible(false);
        picLabelLoading.addMouseListener(this);
        form.add(picLabelLoading);

        panelP.add(form);
    }

    public void mouseClicked(MouseEvent evento) {

        if (evento.getSource() == button) {
            if (inputEmail.GetFormValue().equals(inputEmail.getTitleGeneral())) {
                JOptionPane.showMessageDialog(null, "El correo es obligatorio");
            } else if (inputPassword.getPass().equals(inputPassword.getTitleGeneral())) {
                JOptionPane.showMessageDialog(null, "La contraseña es obligatorio");
            } else {
                if (inputEmail.validateForm() && inputPassword.validateForm()) {

                    login();

                } else {
                    String msg = "El correo debe tener un arroba y hasta 2 puntos \nla contraseña debe tener 8 caracteres";
                    JOptionPane.showMessageDialog(null, msg);
                }
            }
        }

//        if (evento.getSource() == register) {
//            Register.main(null);
//            this.dispose();
//        }
    }

    void login() {
        try {

            LoginProvider loginU = new LoginProvider();
            LoginResponseModel loginResponse = loginU.login(inputEmail.GetFormValue(), inputPassword.GetFormValue());
            if (loginResponse.getError().equals("null")) {
//                JOptionPane.showMessageDialog(null, "USuario: " + loginResponse.getUser().getName());

                if (!constantUtilities.ROLE_EMPLEADO.equals(loginResponse.getUser().getRole()) && !constantUtilities.ROLE_PROMOTOR.equals(loginResponse.getUser().getRole())) {
                    try {
                        constantUtilities.usuario = loginResponse.getUser();
                        this.dispose();
                        HomeAdmin.main(null);
                    } catch (IOException e) {
                    }
                }

                if (loginResponse.getUser().getRole().equals(constantUtilities.ROLE_PROMOTOR)) {
                    try {
                        constantUtilities.usuario = loginResponse.getUser();
                        try {
                            constantUtilities.projectId = loginResponse.getUser().getProjectId();
                        } catch (Exception e) {
                            constantUtilities.projectId = 0L;
                        }
                        try {
                            constantUtilities.nameProject = loginResponse.getProject().getName();
                        } catch (Exception e) {
                        }
                        this.dispose();

                        HomePromotor.main(null);

                    } catch (IOException e) {
                    }
                }

                if (loginResponse.getUser().getRole().equals(constantUtilities.ROLE_EMPLEADO)) {

                    constantUtilities.usuario = loginResponse.getUser();
                    try {
                        constantUtilities.projectId = loginResponse.getUser().getProjectId();
                    } catch (Exception e) {
                        constantUtilities.projectId = 0L;
                    }
                    try {
                        constantUtilities.nameProject = loginResponse.getProject().getName();
                    } catch (Exception e) {
                    }
                    this.dispose();

                    HomeEMployee.main(null);

                }
            } else {
                button.setVisible(true);
                picLabelLoading.setVisible(false);
                panelP.updateUI();
                panelP.repaint();
                String error = "ERROR\n";
                error += loginResponse.getError();
                JOptionPane.showMessageDialog(null, error);
            }
        } catch (Exception e) {
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

class Tfmfld extends JPanel {

    JTextField form = new JTextField();
    JPasswordField formPass = new JPasswordField(20);
    String titleGeneral = "";
    boolean pass;

    public Tfmfld(int boundX, int boundY, int width, int height, String title, Boolean pass) {
        this.pass = pass;
        ColorUIResource colorForm = new ColorUIResource(240, 240, 240);
        titleGeneral = title;
        this.setBounds(boundX, boundY, width, height);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 1));

        if (pass) {
            HintTextListener focusAdapterForm2 = new HintTextListener(formPass, titleGeneral);
            formPass.setText(titleGeneral);
            formPass.setToolTipText(titleGeneral);
            formPass.addFocusListener(focusAdapterForm2);
            formPass.setForeground(constantUtilities.primaryColor);
            formPass.setBackground(colorForm);
            formPass.setBorder(BorderFactory.createLineBorder(colorForm));
            add(formPass);
        } else {
            HintTextListener focusAdapterForm2 = new HintTextListener(form, titleGeneral);
            form.setText(titleGeneral);
            form.setToolTipText(titleGeneral);
            form.addFocusListener(focusAdapterForm2);
            form.setForeground(constantUtilities.primaryColor);
            form.setBackground(colorForm);
            form.setBorder(BorderFactory.createLineBorder(colorForm));
            add(form);
        }

    }

    public String getTitleGeneral() {
        return titleGeneral;
    }

    String GetFormValue() {
        if (!pass) {
            System.out.println("correo: " + form.getText());
            return form.getText();
        } else {
            System.out.println("contraseña: " + String.valueOf(formPass.getPassword()));
            return String.valueOf(formPass.getPassword());
        }
    }

    boolean validateForm() {
        if (!pass) {
            char[] arrayChar = form.getText().toCharArray();
            String arrayCharsAfterArroba = "";
            char caracterValidation;
            int countCaracterArroba = 0;
            int countCaracterPoints = 0;

            for (int i = 0; i < arrayChar.length; i++) {
                caracterValidation = arrayChar[i];
                if (caracterValidation == '@') {
                    countCaracterArroba++;
                    System.out.println("Un arroba");
                    if (countCaracterArroba == 1) {
                        for (int it = i + 1; it < arrayChar.length; it++) {
                            System.out.println("Añadi el caracter: " + arrayChar[it]);
                            arrayCharsAfterArroba += arrayChar[it];
                        }
                    }
                }
            }

            arrayChar = arrayCharsAfterArroba.toCharArray();
            for (int i = 0; i < arrayChar.length; i++) {
                caracterValidation = arrayChar[i];
                if (caracterValidation == '.') {
                    System.out.println("Un punto");
                    countCaracterPoints++;
                }
            }
            if (!(countCaracterArroba == 1) || countCaracterPoints > 2 || countCaracterPoints == 0) {
                return false;
            } else {
                return true;
            }
        } else {
            if (String.valueOf(formPass.getPassword()).equals(titleGeneral) || formPass.getPassword().length < 8) {
                return false;
            } else {
                return true;
            }
        }
    }

    String getPass() {
        return String.valueOf(formPass.getPassword());
    }

    void cleanForm() {
        form.setText(titleGeneral);
    }
}

class HintTextListener extends FocusAdapter {

    JTextField form = new JTextField();
    String titleGeneral;

    public HintTextListener(JTextField f, String t) {
        form = f;
        titleGeneral = t;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (form.getText().equals(titleGeneral)) {
            form.setForeground(constantUtilities.secundaryColorBlack);
            form.setText("");
        } else {
            form.setText(form.getText());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (form.getText().equals(titleGeneral) || form.getText().length() == 0) {
            form.setForeground(constantUtilities.primaryColor);
            form.setText(titleGeneral);
        } else {
            form.setForeground(constantUtilities.secundaryColorBlack);
            form.setText(form.getText());
        }
    }
}
