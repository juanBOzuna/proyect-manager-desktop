/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.homePromotor;

import abp.projectManagerDesktop.providers.GetUsersOfEditTask;
import abp.projectManagerDesktop.providers.GetUsersWithoutTask;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import abp.projectManagerDesktop.providers.PostTaskProvider;
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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class DialogRegisterTask extends JDialog {

    int w, h, y, x;
    ArrayList<UserModel> employees = null;
    JTextField name;
    JComboBox comboEmplpoyees;
    JButton send;
    Long employee_id = 0L;
    TaskModel task;
    Boolean edit;
    Long userOld = 0L;
    boolean notEmploye = false;

    public DialogRegisterTask(JFrame padre, Boolean modo, boolean edit, TaskModel task) {
        super(padre, modo);
        this.task = task;
        this.edit = edit;
        w = 400;
        h = 350;
        y = 150;
        x = 150;

        setBounds(x, y, w, h);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Agregar nuevo proyecto");
        setLayout(null);

        initComponents();
    }

    void initComponents() {
        GetUsersWithoutTask getUsersWithoutTask = new GetUsersWithoutTask();
        GetUsersOfEditTask getUsersOfEditTask = new GetUsersOfEditTask();
        JLabel titleName = new JLabel("Nombre de la tarea");
        titleName.setBounds(swfp(0.1), 20, swfp(0.8), 30);
        add(titleName);

        if (edit) {
            try {
                employees = getUsersOfEditTask.get(task.getId());
                try {
                    userOld = employees.get(0).getId();
                } catch (Exception e) {
                    notEmploye = true;
                    employees = getUsersWithoutTask.get();
                }
            } catch (Exception e) {
            }
        } else {
            try {
                comboEmplpoyees.addItem("Ninguno");
                employees = getUsersWithoutTask.get();
            } catch (Exception e) {
            }
        }

        JPanel panelInput = new JPanel();

        panelInput.setBounds(swfp(0.1), 50, swfp(0.8), 50);
        panelInput.setBackground(Color.white);
        panelInput.setLayout(new GridLayout(1, 1));

        name = new JTextField();
        panelInput.add(name);
        add(panelInput);

        JLabel titleEmployees = new JLabel("Asginar empleado");
        titleEmployees.setBounds(swfp(0.1), 110, w / 2, 20);
        add(titleEmployees);

        comboEmplpoyees = new JComboBox();
        comboEmplpoyees.setBounds(swfp(0.1), 130, w / 2, 30);
        if (!edit) {

            comboEmplpoyees.addItem("Ninguno");
        }
        try {
            for (UserModel employee : employees) {
                comboEmplpoyees.addItem(employee.getId() + "-" + employee.getName());
            }
            comboEmplpoyees.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent itemEvent) {
                    setEmployee_id(employees);
                    System.out.println("Promotor: =--------" + employee_id
                    );
                }
            });
        } catch (Exception e) {
        }

        add(comboEmplpoyees);

        send = new JButton();
        send.setBounds((w / 2) - 60, 200, 120, 30);
        send.setText("Crear");
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendTaskToProvider();
            }
        });

        add(send);

        if (edit) {
            name.setText(task.getName());
        }

    }

    public ArrayList<UserModel> getEmployees() {
        return employees;
    }

    void sendTaskToProvider() {
        if (!name.getText().equals("")) {
            try {
                PostTaskProvider postTaskProvider = new PostTaskProvider();
                if (edit) {
//                    UserModel userOld = 
                    if (postTaskProvider.post(name.getText(), employee_id, true, userOld, task.getId())) {
                        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    }
                } else {
                    if (postTaskProvider.post(name.getText(), employee_id, false, null, null)) {
                        this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    }
                }

            } catch (IOException e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escriba un nombre");
        }
    }

    public void setEmployee_id(ArrayList<UserModel> employes) {

        try {
            UserModel userSelected = null;

            for (UserModel employee : employees) {
                String[] split = String.valueOf(comboEmplpoyees.getSelectedItem()).split("-");
                if (employee.getId() == Long.parseLong(split[0])) {
                    userSelected = employee;
                }
            }
            if (userSelected == null) {
                this.employee_id = 0L;
            } else {
                this.employee_id = userSelected.getId();
            }
        } catch (Exception e) {
        }

    }

    int swfp(double number) {
        return (int) (w * number);
    }

    void postTask() {
        String nameTask = name.getText();
        Long id_employee;

        if (comboEmplpoyees.getSelectedIndex() == 0) {
            id_employee = 0L;
        }

        PostTaskProvider postTaskProvider = new PostTaskProvider();
    }
}
