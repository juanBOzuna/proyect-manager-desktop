/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

/**
 *
 * @author juan barraza
 */
import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.TaskModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.io.*;
import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetTasksPromotorProvider {

    public ArrayList<TaskModel> getTasks() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/tasks/projectId="+constantUtilities.projectId)
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        return parse(response.body().string());
    }

    ArrayList<TaskModel> parse(String bodyResp) {
        ArrayList<TaskModel> tasks = new ArrayList<TaskModel>();
        JSONArray respJson = new JSONArray(bodyResp);

        for (int i = 0; i < respJson.length(); i++) {
            JSONObject taskJson = respJson.getJSONObject(i);

            TaskModel task = new TaskModel();
            task.setId(taskJson.getLong("id"));
            task.setName(taskJson.getString("name"));
            task.setProjectId(taskJson.getLong("projectId"));
            task.setIs_completed(taskJson.getBoolean("is_completed"));
            
            try {
                
            } catch (Exception e) {
            }
            tasks.add(task);
        }
        
        return tasks;
    }
}
