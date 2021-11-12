package abp.projectManagerDesktop.providers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import abp.projectManagerDesktop.providers.Models.DocumentModel;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetProjectsAdminModel;
import abp.projectManagerDesktop.providers.Models.ResponseGetTaskModel;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.*;
import org.json.JSONArray;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
import okhttp3.*;

/**
 *
 * @author juan barraza
 */
public class GetProjectsAdminProvider {

    private static String bodyResp = "";

    public ArrayList<ResponseGetProjectsAdminModel> getModules() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/projects")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return parse(response.body().string());
    }

    ArrayList<ResponseGetProjectsAdminModel> parse(String resp) {
        //Jsons
        JSONArray response = new JSONArray(resp);
        JSONObject project;
        JSONArray tasks;
        JSONObject task;
        JSONArray documents;
        JSONObject document;
        JSONObject employeeAssign;

        //respuestaModel
        ArrayList<ResponseGetProjectsAdminModel> listProjectsResponse = new ArrayList<ResponseGetProjectsAdminModel>();

        ResponseGetProjectsAdminModel responseGetProjectsModel;

        //Models
        ProjectModel projectModel;
        TaskModel taskModel;
        DocumentModel documentModel;
        ArrayList<DocumentModel> documentsOfTask = new ArrayList<DocumentModel>();
        ResponseGetTaskModel taskResponseModel = null;
        UserModel userModel = new UserModel();

        if (!response.isEmpty()) {
            for (int i = 0; i < response.length(); i++) {
                responseGetProjectsModel = new ResponseGetProjectsAdminModel();
                //get project in response
                project = response.getJSONObject(i).getJSONObject("project");
                //get task in response
                tasks = response.getJSONObject(i).getJSONArray("tasks");

                //construct project model
                projectModel = new ProjectModel();
                projectModel.setId(project.getLong("id"));
                try {
                    projectModel.setPromotor_id(project.getLong("promotorId"));
                } catch (Exception e) {
                }
                projectModel.setName(project.getString("name"));
                projectModel.setPercentageCompleted(response.getJSONObject(i).getFloat("percentageCompleted"));

                for (int j = 0; j < tasks.length(); j++) {
                    //construct task model
                    task = tasks.getJSONObject(j).getJSONObject("task");
                    taskModel = new TaskModel();
                    taskModel.setId(task.getLong("id"));
                    taskModel.setIs_completed(task.getBoolean("isCompleted"));
                    taskModel.setName(task.getString("name"));
                    taskModel.setProjectId(task.getLong("projectId"));

                    try {
                        employeeAssign = tasks.getJSONObject(j).getJSONObject("employeeAssign");
                        userModel = new UserModel();
                        userModel.setName(employeeAssign.getString("name"));
                        userModel.setEmail(employeeAssign.getString("email"));
                        userModel.setNumber_phone(employeeAssign.getString("number_phone"));
                        taskResponseModel.setUser(userModel);
                    } catch (Exception e) {
                    }

                    documents = tasks.getJSONObject(j).getJSONArray("documents");

                    if (!documents.isEmpty()) {
                        for (int k = 0; k < documents.length(); k++) {
                            documentModel = new DocumentModel();
                            document = documents.getJSONObject(k);
                            documentModel.setId(document.getLong("id"));
                            documentModel.setUrl(document.getString("url"));
                            documentsOfTask.add(documentModel);
                        }
                    }

                    //add task on task response model
                    taskResponseModel = new ResponseGetTaskModel();
                    taskResponseModel.setTask(taskModel);

                    taskResponseModel.setDocuments(documentsOfTask);

                    //add tasksResponse on response http get model
                    responseGetProjectsModel.setTasks(taskResponseModel);
                }

                //add project on response http get model
                responseGetProjectsModel.setProject(projectModel);

                //add responseModel to listResponseModel
                listProjectsResponse.add(responseGetProjectsModel);
            }
        }
        return listProjectsResponse;
    }

    public static void setBodyResp(String respBody) {
        bodyResp = respBody;
    }

}
