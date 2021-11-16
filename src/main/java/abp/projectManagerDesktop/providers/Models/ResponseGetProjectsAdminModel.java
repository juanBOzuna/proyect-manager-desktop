/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers.Models;

import java.util.ArrayList;

/**
 *
 * @author juan barraza
 */
public class ResponseGetProjectsAdminModel {

    private ProjectModel project;
    private UserModel promotor;
    private ArrayList<ResponseGetTaskModel> tasks = new ArrayList<ResponseGetTaskModel>();

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public UserModel getPromotor() {
        return promotor;
    }

    public void setPromotor(UserModel promotor) {
        this.promotor = promotor;
    }

    public ArrayList<ResponseGetTaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(ResponseGetTaskModel task) {
        this.tasks.add(task);
    }
}
