/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.DeleteProjectProvider;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Frame.HAND_CURSOR;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author juan barraza
 */
public class ElementProjectAdmin extends JPanel implements MouseListener {

    JLabel picLabel;
    JLabel picLabelDelete;
    JLabel picLabelEdit;
    int w, h;
    JFrame padre;
    ResponseGetProjectsAdminModel projectModel;
    DialogDeleteProject dialogDelete;
    DialogRegisterProject edit;

    public ElementProjectAdmin(int x, int y, int w, int h, JFrame padre, ResponseGetProjectsAdminModel projectModel) {

        this.padre = padre;
        this.w = w;
        this.h = h;
        this.projectModel = projectModel;
        edit = new DialogRegisterProject(padre, false, true, projectModel);

        dialogDelete = new DialogDeleteProject(padre, false, projectModel.getProject());
        setBounds(x, y, w, h);
        setBackground(constantUtilities.colorItemTask);
        setLayout(null);

        int xEye = w - (w / 13);
        int yEye = (y / 2) - ((w / 5) / 2);
        int wImage = (h / 2) - 5;
        JPanel panelEye = new JPanel();
        panelEye.setBackground(Color.black);
        panelEye.setBounds(xEye, (h / 2) - (wImage / 2), wImage, wImage);

        Image imgDelete = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/remove.png").getImage();
        ImageIcon imgDelete2 = new ImageIcon(imgDelete.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
        picLabelDelete = new JLabel();
        picLabelDelete.setIcon(imgDelete2);
        picLabelDelete.addMouseListener(this);
        picLabelDelete.setCursor(new Cursor(HAND_CURSOR));
        picLabelDelete.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

        xEye -= (wImage + 5);
        Image img = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/eye.png").getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
        picLabel = new JLabel();
        picLabel.setIcon(img2);
        picLabel.addMouseListener(this);
        picLabel.setCursor(new Cursor(HAND_CURSOR));
        picLabel.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

        xEye -= (wImage + 5);
        Image imgEdit = new ImageIcon("src/main/java/abp/projectManagerDesktop/assets/edit.png").getImage();
        ImageIcon imgEdit2 = new ImageIcon(imgEdit.getScaledInstance(wImage, wImage, Image.SCALE_SMOOTH));
        picLabelEdit = new JLabel();
        picLabelEdit.setIcon(imgEdit2);
        picLabelEdit.addMouseListener(this);
        picLabelEdit.setCursor(new Cursor(HAND_CURSOR));
        picLabelEdit.setBounds(xEye, (h / 2 - (wImage / 2)), wImage, wImage);

        JPanel title = new JPanel();
        title.setLayout(new GridLayout(1, 1));
        title.setBounds(10, (h / 2) - (((h / 2) - 5) / 2), xEye - 20, (h / 2) - 5);

        JLabel titleText = new JLabel(projectModel.getProject().getKey_name() + " | " + projectModel.getProject().getName());
        title.add(titleText);

        add(title);
        add(picLabel);
        add(picLabelEdit);
        add(picLabelDelete);
    }

    int swfp(double number) {
        return (int) ((w - 20) * number);
    }

    int shfp(double number) {
        return (int) (h * number);
    }

    public DialogDeleteProject getDialogDelete() {
        return dialogDelete;
    }
//    

    public DialogRegisterProject getEdit() {
        return edit;
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == picLabelEdit) {
            edit.setVisible(true);
        }
//
        if (e.getSource() == picLabelDelete) {
            dialogDelete.setVisible(true);
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
