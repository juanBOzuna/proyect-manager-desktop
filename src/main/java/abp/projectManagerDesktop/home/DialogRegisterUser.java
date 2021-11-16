/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.GetPromotors;
import abp.projectManagerDesktop.providers.Models.UserModel;
import abp.projectManagerDesktop.providers.PostProjectProvider;
import abp.projectManagerDesktop.providers.PostUserProvider;
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
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author juan barraza
 */
public class DialogRegisterUser extends JDialog {
    
    int w, h, y, x, indexDay = 0;
    TfmfldUser name;
    TfmfldUser lastName;
    TfmfldUser dni;
    TfmfldUser address;
    TfmfldUser email;
    TfmfldUser number_phone;
    TfmfldUser pass;
    JButton send;
    JComboBox comboHiringYear;
    JComboBox comboHiringDay;
    JComboBox comboHiringMonth;
    JComboBox promotorsComb;
    JComboBox rol;
    String dayHiring = "1", monthHiring = "1", yearHiring = "0";
    boolean val;
    boolean edit;
    UserModel user;
    String role;
    
    public DialogRegisterUser(JFrame padre, Boolean modo, Boolean promotors, boolean edit, UserModel user) {
        super(padre, modo);
        val = promotors;
        this.user = user;
        this.edit = edit;
        
        w = 400;
        h = 400;
        y = 150;
        x = 150;
        
        setBounds(x, y, w, h);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Agregar nuevo proyecto");
        setLayout(null);
        initComponents();
    }
    
    void initComponents() {
        System.out.println("width -----: " + this.getWidth());
        
        int widthForm = swfp(0.4275);
        int heightForm = shfp(0.1025);
        
        name = new TfmfldUser(swfp(0.02725), shfp(0.06125), widthForm, heightForm, "NOmbre", false, false, false);
        add(name);
        
        lastName = new TfmfldUser(swfp(0.5425), shfp(0.06125), widthForm, heightForm, "Apellidos", false, false, false);
        add(lastName);
        
        dni = new TfmfldUser(swfp(0.02725), shfp(0.1975), widthForm, heightForm, "Dni", false, true, false);
        add(dni);
        
        number_phone = new TfmfldUser(swfp(0.5425), shfp(0.1975), widthForm, heightForm, "Numero telefono", false, true, false);
        add(number_phone);
        
        email = new TfmfldUser(swfp(0.02725), shfp(0.34), widthForm, heightForm, "Email", false, false, true);
        add(email);
        
        address = new TfmfldUser(swfp(0.5425), shfp(0.34), widthForm, heightForm, "Direccion", false, false, false);
        add(address);
        
        JLabel titlePass = new JLabel("Contraseña");
        titlePass.setBounds(swfp(0.02725), shfp(0.7), widthForm, shfp(0.05));
        
        pass = new TfmfldUser(swfp(0.02725), shfp(0.758), widthForm, shfp(0.1075), "", true, false, false);
        add(pass);
        add(titlePass);
        
        dateHiring();
        
        if (edit) {
            rol = new JComboBox();
            rol.addItem(constantUtilities.ROLE_EMPLEADO);
            rol.addItem(constantUtilities.ROLE_PROMOTOR);
            rol.setBounds(swfp(0.5425), shfp(0.54), widthForm, shfp(0.0875));
            rol.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent itemEvent) {
                    role = String.valueOf(rol.getSelectedItem());
                }
            });
            add(rol);
            
            name.setText(user.getName());
            System.out.println("\n\n\n" + user.getName());
            lastName.setText(user.getLastname());
            System.out.println(user.getLastname());
            dni.setText(user.getDni());
            System.out.println(user.getDni());
            number_phone.setText(user.getNumber_phone());
            email.setText(user.getEmail());
            address.setText(user.getAddress());
            pass.setText(user.getPassword());
            
            if (user.getRole().equals(constantUtilities.ROLE_EMPLEADO)) {
                rol.setSelectedIndex(0);
            } else {
                rol.setSelectedIndex(1);
            }
            
            try {
                
            } catch (Exception e) {
            }
        }
        
        send = new JButton("Enviar");
        send.setBounds(swfp(0.5825), shfp(0.758), swfp(0.3475), shfp(0.1075));
        
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendUserToProvider();
            }
        });
        add(send);
    }
    
    int swfp(double number) {
        return (int) ((w - 20) * number);
    }
    
    int shfp(double number) {
        return (int) (h * number);
    }
    
    void dateHiring() {
        JLabel titleHiring = new JLabel("Fecha de Contratacion");
        titleHiring.setBounds(swfp(0.02725), shfp(0.475), swfp(0.4275), shfp(0.05));
        
        comboHiringYear = new JComboBox();
        comboHiringYear.setBounds(swfp(0.03), shfp(0.54), swfp(0.150225), shfp(0.0875));
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        yearHiring = String.valueOf(year);
        for (int i = year; i < (year + 50); i++) {
            comboHiringYear.addItem("" + (i));
        }
        
        comboHiringMonth = new JComboBox();
        comboHiringMonth.setBounds(swfp(0.2125), shfp(0.54), swfp(0.1075), shfp(0.0875));
        for (int i = 0; i < 12; i++) {
            if (i < 9) {
                comboHiringMonth.addItem("0" + (i + 1));
            } else {
                comboHiringMonth.addItem("" + (i + 1));
            }
            
        }
        
        comboHiringDay = new JComboBox();
        comboHiringDay.setBounds(swfp(0.345), shfp(0.54), swfp(0.1075), shfp(0.0875));
        int msS = comboHiringMonth.getSelectedIndex();
        for (int i = 0; i < 31; i++) {
            if (i < 9) {
                comboHiringDay.addItem("0" + (i + 1));
            } else {
                comboHiringDay.addItem("" + (i + 1));
            }
        }
        
        comboHiringMonth.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                try {
                    System.out.println("cambio");
                    monthHiring = String.valueOf(Integer.parseInt(String.valueOf(comboHiringMonth.getSelectedItem())));
                    int msS = comboHiringMonth.getSelectedIndex();
                    int max = 0;
                    comboHiringDay.removeAllItems();
                    if (msS == 0 || msS == 2 || msS == 4 || msS == 6 || msS == 7 || msS == 9 || msS == 11) {
                        max = 31;
                    } else if (msS == 3 || msS == 5 || msS == 8 || msS == 10) {
                        max = 30;
                    } else {
                        max = 28;
                    }
                    for (int i = 0; i < max; i++) {
                        if (i < 9) {
                            comboHiringDay.addItem("0" + (i + 1));
                        } else {
                            comboHiringDay.addItem("" + (i + 1));
                        }
                    }
                    
                    comboHiringDay.setSelectedIndex(indexDay);
                } catch (Exception e) {
                }
            }
        });
        
        comboHiringDay.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                
                dayHiring = String.valueOf(comboHiringDay.getSelectedItem());
//               
            }
        });
        
        comboHiringYear.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                yearHiring = String.valueOf(Integer.parseInt(String.valueOf(comboHiringYear.getSelectedItem())));;
            }
        });
        
        add(titleHiring);
        add(comboHiringYear);
        add(comboHiringMonth);
        add(comboHiringDay);
    }
    
    void sendUserToProvider() {
        PostUserProvider p = new PostUserProvider();
        Boolean call = false;
        
        if (name.validateForm() && dni.validateForm() && address.validateForm() && email.validateForm() && number_phone.validateForm() && pass.validateForm()) {
            try {
                if (!edit) {
                    if (val) {
                        call = p.postUser(address.GetFormValue(),
                                dni.GetFormValue(),
                                email.GetFormValue(),
                                lastName.GetFormValue(),
                                name.GetFormValue(),
                                number_phone.GetFormValue(),
                                constantUtilities.ROLE_PROMOTOR,
                                yearHiring + "-" + monthHiring + "-" + dayHiring + " 00:00:00",
                                pass.GetFormValue(),
                                false, false, null,
                                null
                        );
                    } else {
                        call = p.postUser(address.GetFormValue(),
                                dni.GetFormValue(),
                                email.GetFormValue(),
                                lastName.GetFormValue(),
                                name.GetFormValue(),
                                number_phone.GetFormValue(),
                                constantUtilities.ROLE_EMPLEADO,
                                yearHiring + "-" + monthHiring + "-" + dayHiring + " 00:00:00",
                                pass.GetFormValue(),
                                false, false, null,
                                null
                        );
                        
                    }
                } else {
                    
                    call = p.postUser(address.GetFormValue(),
                            dni.GetFormValue(),
                            email.GetFormValue(),
                            lastName.GetFormValue(),
                            name.GetFormValue(),
                            number_phone.GetFormValue(),
                            String.valueOf(rol.getSelectedItem()),
                            yearHiring + "-" + monthHiring + "-" + dayHiring + " 00:00:00",
                            pass.GetFormValue(),
                            true, false, user.getId(),
                            null
                    );
                    
                }
                
                if (call) {
                    this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            } catch (IOException e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "rellene todos los campos correctamente");
        }
        
    }
}

class TfmfldUser extends JPanel {
    
    JTextField form = new JTextField();
    JPasswordField formPass = new JPasswordField(20);
    String titleGeneral = "";
    boolean pass;
    boolean isNumeric;
    boolean email;
    boolean date;
    
    public TfmfldUser(int boundX, int boundY, int width, int height, String title, boolean pass, boolean isNumeric, boolean email) {
        this.pass = pass;
        this.isNumeric = isNumeric;
        this.email = email;
        titleGeneral = title;
        this.setBounds(boundX, boundY, width, height);
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(1, 1));
        
        if (pass) {
            HintTextListener focusAdapterForm2 = new HintTextListener(formPass, titleGeneral);
            formPass.setText(titleGeneral);
            formPass.setToolTipText(titleGeneral);
            formPass.addFocusListener(focusAdapterForm2);
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
            add(form);
        } else {
            HintTextListenerUser focusAdapterForm2 = new HintTextListenerUser(form, titleGeneral);
            form.setText(titleGeneral);
            form.setToolTipText(titleGeneral);
            form.addFocusListener(focusAdapterForm2);
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
    
    void setText(String text) {
        form.setText(text);
        formPass.setText(text);
    }
    
    String getPass() {
        return String.valueOf(formPass.getPassword());
    }
    
    void cleanForm() {
        form.setText(titleGeneral);
    }
}

class HintTextListenerUser extends FocusAdapter {
    
    JTextField form = new JTextField();
    String titleGeneral;
    
    public HintTextListenerUser(JTextField f, String t) {
        form = f;
        titleGeneral = t;
    }
    
    @Override
    public void focusGained(FocusEvent e) {
        if (form.getText().equals(titleGeneral)) {
            form.setText("");
        } else {
            form.setText(form.getText());
        }
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        if (form.getText().equals(titleGeneral) || form.getText().length() == 0) {
            form.setText(titleGeneral);
        } else {
            form.setText(form.getText());
        }
    }
}
