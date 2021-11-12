/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.Models.UserModel;
import abp.projectManagerDesktop.providers.GetPromotors;
import abp.projectManagerDesktop.providers.PostProjectProvider;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author juan barraza
 */
public class DialogRegisterProject extends JDialog {

    JComboBox comboInitYear;
    JComboBox comboInitDay;
    JComboBox comboInitMonth;
    JComboBox comboFinishYear;
    JComboBox comboFinishDay;
    JComboBox comboFinishMonth;
    JLabel titleInit;
    JLabel titleFinish;

    public DialogRegisterProject(JFrame padre, Boolean modo) {
        super(padre, modo);
        setBounds(150, 150, 400, 400);
        setTitle("Agregar nuevo proyecto");
        setLayout(null);
        initComponents();

//        add(panelP);
    }

    private void initComponents() {
        GetPromotors getPromotors = new GetPromotors();
        ArrayList<UserModel> promotors = new ArrayList<UserModel>();

        try {
            promotors = getPromotors.getPromotrs();
        } catch (IOException e) {
        }

        Tfmfld name = new Tfmfld(20, 30, (getWidth() / 2) - 30, getWidth() / 10, "Name", false, false, true);
        Tfmfld key_name = new Tfmfld((getWidth() / 2) + 10, 30, (getWidth() / 2) - 40, getWidth() / 10, "Apellidos", false, false, true);

        Tfmfld comercial_designation = new Tfmfld(20, key_name.getY() + key_name.getHeight() + 10, (getWidth() / 2) - 30, getWidth() / 10, "dni", false, true, false);
        dateInit(comercial_designation.getY() + comercial_designation.getHeight() + 10);
        dateFinish(comboInitDay.getY() + comboInitDay.getHeight());

        JComboBox promotorsComb = new JComboBox();
        promotorsComb.setBounds((getWidth() / 2) + 10, key_name.getY() + key_name.getHeight() + 10, (getWidth() / 2) - 40, 30);

        try {
            for (UserModel promotor : promotors) {
                promotorsComb.addItem(promotor.getName());
            }
        } catch (Exception e) {
        }

        add(promotorsComb);

        JLabel titlePromotorsComb = new JLabel("Promotor");
        titlePromotorsComb.setBounds((getWidth() / 2) + 10, key_name.getY() + key_name.getHeight() + 30, promotorsComb.getWidth(), 50);
        add(titlePromotorsComb);

        add(key_name);
        add(comercial_designation);
        add(name);

        JButton send = new JButton();

        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    void sendProjectToProvider() {
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        Instant instant = timestamp.toInstant();
        System.out.println("Current Time Stamp: " + instant);
        PostProjectProvider p = new PostProjectProvider();
        try {
            p.postProyect();

        } catch (IOException e) {
        }

    }

    void dateFinish(int yYear) {
        titleFinish = new JLabel("Fecha de fin");
        titleFinish.setBounds(20, yYear, getWidth(), 50);

        comboFinishYear = new JComboBox();
        comboFinishYear.setBounds(20, titleFinish.getY() + titleFinish.getHeight(), (getWidth() / 4) - 40, getWidth() / 14);
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        for (int i = year; i < (year + 50); i++) {
            comboFinishYear.addItem("" + (i));
        }

        comboFinishMonth = new JComboBox();
        comboFinishMonth.setBounds(comboFinishYear.getWidth() + comboFinishYear.getX() + 10, titleFinish.getY() + titleFinish.getHeight(), (getWidth() / 4) - 60, getWidth() / 14);
        for (int i = 0; i < 12; i++) {
            comboFinishMonth.addItem("" + (i + 1));
        }

        comboFinishDay = new JComboBox();
        comboFinishDay.setBounds(comboFinishMonth.getWidth() + comboFinishMonth.getX() + 10, titleFinish.getY() + titleFinish.getHeight(), (getWidth() / 4) - 60, getWidth() / 14);
        int msS = comboFinishMonth.getSelectedIndex();
        for (int i = 0; i < 31; i++) {
            comboFinishDay.addItem("" + (i + 1));
        }

        comboFinishMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                try {

                    int msS = comboFinishMonth.getSelectedIndex();
                    int max = 0;
                    comboFinishDay.removeAllItems();
                    if (msS == 0 || msS == 2 || msS == 4 || msS == 6 || msS == 7 || msS == 9 || msS == 11) {
                        max = 31;
                    } else if (msS == 3 || msS == 5 || msS == 8 || msS == 10) {
                        max = 30;
                    } else {
                        max = 28;
                    }
                    for (int i = 0; i < max; i++) {
                        comboFinishDay.addItem("" + (i + 1));
                    }
                } catch (Exception e) {
                }
            }
        });

        add(titleFinish);
        add(comboFinishYear);
        add(comboFinishMonth);
        add(comboFinishDay);
//        dateFinish(comboInitDay.getY() + comboInitDay.getHeight() + 20);
    }

    void dateInit(int yYear) {
        titleInit = new JLabel("Fecha de inicio");
        titleInit.setBounds(20, yYear, getWidth(), 50);

        comboInitYear = new JComboBox();
        comboInitYear.setBounds(20, titleInit.getY() + titleInit.getHeight(), (getWidth() / 4) - 40, getWidth() / 14);
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        for (int i = year; i < (year + 50); i++) {
            comboInitYear.addItem("" + (i));
        }

        comboInitMonth = new JComboBox();
        comboInitMonth.setBounds(comboInitYear.getWidth() + comboInitYear.getX() + 10, titleInit.getY() + titleInit.getHeight(), (getWidth() / 4) - 60, getWidth() / 14);
        for (int i = 0; i < 12; i++) {
            comboInitMonth.addItem("" + (i + 1));
        }

        comboInitDay = new JComboBox();
        comboInitDay.setBounds(comboInitMonth.getWidth() + comboInitMonth.getX() + 10, titleInit.getY() + titleInit.getHeight(), (getWidth() / 4) - 60, getWidth() / 14);
        int msS = comboInitMonth.getSelectedIndex();
        for (int i = 0; i < 31; i++) {
            comboInitDay.addItem("" + (i + 1));
        }

        comboInitMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                try {
                    int msS = comboInitMonth.getSelectedIndex();
                    int max = 0;
                    comboInitDay.removeAllItems();
                    if (msS == 0 || msS == 2 || msS == 4 || msS == 6 || msS == 7 || msS == 9 || msS == 11) {
                        max = 31;
                    } else if (msS == 3 || msS == 5 || msS == 8 || msS == 10) {
                        max = 30;
                    } else {
                        max = 28;
                    }
                    for (int i = 0; i < max; i++) {
                        comboInitDay.addItem("" + (i + 1));
                    }
                } catch (Exception e) {
                }
            }
        });

        add(titleInit);
        add(comboInitYear);
        add(comboInitMonth);
        add(comboInitDay);

    }

    void comboPromotors(int x, int y, int w, int h) {
//        JComboBox promotorsComb = new JComboBox();
//        promotorsComb.setBounds(key_name.getX(), key_name.getY(), key_name.getWidth(), 30);
//        add(promotorsComb);

    }
}

class Tfmfld extends JPanel {

    JTextField form = new JTextField();
    JPasswordField formPass = new JPasswordField(20);
    String titleGeneral = "";
    boolean pass;
    boolean isNumeric;
    boolean email;
    boolean date;

    public Tfmfld(int boundX, int boundY, int width, int height, String title, boolean pass, boolean isNumeric, boolean email) {
        this.pass = pass;
        this.isNumeric = isNumeric;
        this.email = email;
//        ColorUIResource colorForm = new ColorUIResource(255, 255, 255);
        titleGeneral = title;
        this.setBounds(boundX, boundY, width, height);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 1));

        if (pass) {
            HintTextListener focusAdapterForm2 = new HintTextListener(formPass, titleGeneral);
            formPass.setText(titleGeneral);
            formPass.setToolTipText(titleGeneral);
            formPass.addFocusListener(focusAdapterForm2);
//            formPass.setForeground(constantUtilities.primaryColor);
//            formPass.setBackground(Colors);
//            formPass.setBorder(BorderFactory.createLineBorder(colorForm));
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
//            form.setForeground(constantUtilities.primaryColor);
//            form.setBackground(colorForm);
//            form.setBorder(BorderFactory.createLineBorder(colorForm));
            add(form);
        } else {
            HintTextListener focusAdapterForm2 = new HintTextListener(form, titleGeneral);
            form.setText(titleGeneral);
            form.setToolTipText(titleGeneral);
            form.addFocusListener(focusAdapterForm2);
//            form.setForeground(constantUtilities.primaryColor);
//            form.setBackground(colorForm);
//            form.setBorder(BorderFactory.createLineBorder(colorForm));
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
        if (email) {
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
        } else if (pass) {
            if (String.valueOf(formPass.getPassword()).equals(titleGeneral) || String.valueOf(formPass.getPassword()).length() < 8) {
                return false;
            } else {
                return true;
            }
        } else {
            if (form.getText().equals(titleGeneral)) {
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
//            form.setForeground(constantUtilities.secundaryColorBlack);
            form.setText("");
        } else {
            form.setText(form.getText());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (form.getText().equals(titleGeneral) || form.getText().length() == 0) {
//            form.setForeground(constantUtilities.primaryColor);
            form.setText(titleGeneral);
        } else {
//            form.setForeground(constantUtilities.secundaryColorBlack);
            form.setText(form.getText());
        }
    }
}
