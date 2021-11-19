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
import abp.projectManagerDesktop.providers.Models.TaskModel;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetTasksOfEmployee {

    public TaskModel get(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(constantUtilities.URL_API+"/user/myTask/userId=" + id)
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
