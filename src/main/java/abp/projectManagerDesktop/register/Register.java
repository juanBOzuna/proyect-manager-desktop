/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.register;

import abp.projectManagerDesktop.constants.constantUtilities;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author juan barraza
 */
public class Register {

    static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        VentanaRegister v = new VentanaRegister(width, height);
        v.setVisible(true);
    }

}

class VentanaRegister extends JFrame implements MouseListener {

    JPanel panelP;
    Tfmfld name;
    Tfmfld password;
    Tfmfld repeatPasssword;
    Tfmfld email;
    Tfmfld cellPhone;
    JPanel button;
    
   
    public VentanaRegister(int width, int height) {
        this.setSize(width / 2, height / 3);
        initComponents(width / 2, height / 2);
        setLayout(null);
    }

    private void initComponents(int width, int height) {
        panelP = new JPanel();
        panelP.setSize(width, height);
        panelP.setBackground(Color.white);
        panelP.setLayout(null);

        name = new Tfmfld(20, 20, (width / 2) - 40, (int) (height / 6.5) - 20, " Nombre", false, false);
        panelP.add(name);

        email = new Tfmfld(name.getX() + name.getWidth() + 10, 20, name.getWidth(), name.getHeight(), " Correo", false, false);
        panelP.add(email);

        password = new Tfmfld(name.getX(), email.getY() + email.getHeight() + 20, name.getWidth(), name.getHeight(), "su clave", true, false);
        panelP.add(password);

        repeatPasssword = new Tfmfld(email.getX(), password.getY(), name.getWidth(), name.getHeight(), "su clave", true, false);
        panelP.add(repeatPasssword);

        cellPhone = new Tfmfld(name.getX(), password.getY() + password.getHeight() + 20, name.getWidth(), name.getHeight(), " Telefono", false, true);
        panelP.add(cellPhone);

        button = new JPanel();
        button.setBounds(email.getX(), cellPhone.getY(), email.getWidth(), email.getHeight());
        button.setBackground(constantUtilities.primaryColor);
        button.setCursor(new Cursor(HAND_CURSOR));
        button.setLayout(new GridLayout(1, 1));
        button.addMouseListener(this);
        JLabel text = new JLabel("Registrarse");
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        button.add(text);
        button.setVisible(true);
        panelP.add(button);

        this.add(panelP);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == button) {
            if (password.validateForm() && email.validateForm() && name.validateSimpleForm()) {
                if (password.getPass().equals(repeatPasssword.getPass())) {

                } else {
                    JOptionPane.showMessageDialog(null, "Las Contraseñas deben ser las mismas");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos correctamente \n correo solo puede tener hasta un @\n contraseña de minimo 8 digitos");
            }
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
    boolean isNumeric;

    public Tfmfld(int boundX, int boundY, int width, int height, String title, boolean pass, boolean isNumeric) {
        this.pass = pass;
        this.isNumeric = isNumeric;
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
        } else if (this.isNumeric) {
            HintTextListener focusAdapterForm2 = new HintTextListener(form, titleGeneral);
            form.setText(titleGeneral);
            form.setToolTipText(titleGeneral);
            form.addFocusListener(focusAdapterForm2);
            form.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    char caracter = evt.getKeyChar();
                    if (caracter < '0' || caracter > '9') {
                        evt.consume();
                    }
                }
            });
            form.setForeground(constantUtilities.primaryColor);
            form.setBackground(colorForm);
            form.setBorder(BorderFactory.createLineBorder(colorForm));
            add(form);
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
            return form.getText();
        } else {
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
            if (String.valueOf(formPass.getPassword()).equals(titleGeneral) || String.valueOf(formPass.getPassword()).length() < 8) {
                return false;
            } else {
                return true;
            }
        }
    }

    boolean validateSimpleForm() {
        return !form.getText().equals(titleGeneral);

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
