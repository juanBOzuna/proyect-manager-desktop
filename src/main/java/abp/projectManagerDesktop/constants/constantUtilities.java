/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.constants;

import abp.projectManagerDesktop.providers.Models.UserModel;
import java.awt.Color;
import java.util.prefs.Preferences;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author juan barraza
 */
public class constantUtilities {

    static public UserModel usuario;
    static public Long projectId;
    static public String nameProject;
    final static public String ROLE_EMPLEADO = "empleado";
    final static public String ROLE_PROMOTOR = "promotor";
    final static public ColorUIResource primaryColor = new ColorUIResource(31, 124, 197);
    final static public Color secundaryColor = Color.WHITE;
    final static public ColorUIResource colorItemEnteredOfDrawer = new ColorUIResource(30, 40, 44);
    final static public ColorUIResource primaryColorBlack = new ColorUIResource(34, 45, 50);
    final static public ColorUIResource secundaryColorBlack = new ColorUIResource(26, 34, 38);
    final static public ColorUIResource colorItemTask = new ColorUIResource(237, 237, 237);

}
