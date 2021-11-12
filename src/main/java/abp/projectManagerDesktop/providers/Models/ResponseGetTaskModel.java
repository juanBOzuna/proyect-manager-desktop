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
public class ResponseGetTaskModel {

    private TaskModel task;
    private UserModel user;
    private ArrayList<DocumentModel> documents;

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public ArrayList<DocumentModel> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<DocumentModel> documents) {
        this.documents = documents;
    }
}
