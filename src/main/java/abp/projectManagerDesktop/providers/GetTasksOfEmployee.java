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
import abp.projectManagerDesktop.providers.Models.TaskModel;
import java.io.*;
import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetTasksOfEmployee {

    public TaskModel get(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/user/myTask/userId=" + id)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return parse(response.body().string());
    }

    TaskModel parse(String bodyResp) {
        JSONObject respJson = new JSONObject(bodyResp);

        TaskModel task = new TaskModel();
        task.setId(respJson.getLong("id"));
        task.setName(respJson.getString("name"));
        try {
            task.setProjectId(respJson.getLong("projectId"));

        } catch (Exception e) {
        }
        task.setIs_completed(respJson.getBoolean("is_completed"));

        try {

        } catch (Exception e) {
        }

        return task;
    }
}
