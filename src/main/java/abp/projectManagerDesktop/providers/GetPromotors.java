/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.io.*;
import java.util.ArrayList;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author juan barraza
 */
public class GetPromotors {

    public ArrayList<UserModel> getPromotrs(Boolean edit, ProjectModel project) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = null;
        if (edit) {

            request = new Request.Builder()
                    .url("http://localhost:8080/user/rolEdit=promotor/projectId=" + project.getId())
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

        } else {
            request = new Request.Builder()
                    .url("http://localhost:8080/user/rol=promotor")
                    .method("GET", null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

        }
        Response response = client.newCall(request).execute();
        return parse(response.body().string());
    }

    ArrayList<UserModel> parse(String response) {
        ArrayList<UserModel> promotors = new ArrayList<UserModel>();
        JSONArray respJson = new JSONArray(response);

        for (int i = 0; i < respJson.length(); i++) {
            JSONObject user = respJson.getJSONObject(i);
            try {

                UserModel userModel = new UserModel();
                userModel.setName(user.getString("name"));
                userModel.setId(user.getLong("id"));

                promotors.add(userModel);
            } catch (Exception e) {
            }
        }

        return promotors;
    }
}
