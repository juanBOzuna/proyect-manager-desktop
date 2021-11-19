/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

import abp.projectManagerDesktop.constants.constantUtilities;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author juan barraza
 */
public class GetUsersProvider {

    public Map<String, ArrayList<UserModel>> getUsers() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(constantUtilities.URL_API+"/user")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return parse(response.body().string());

//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        Request request = new Request.Builder()
//                .url("http://localhost:8080/user")
//                .method("GET", null)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//        return parse(response.body().string());
//        System.out.println();
    }

    Map<String, ArrayList<UserModel>> parse(String response) {
        ArrayList<UserModel> promotors = new ArrayList<UserModel>();
        ArrayList<UserModel> employees = new ArrayList<UserModel>();
        JSONArray respJson = new JSONArray(response);

        for (int i = 0; i < respJson.length(); i++) {
            JSONObject user = respJson.getJSONObject(i);
            UserModel userModel = new UserModel();
            userModel.setAddress(user.getString("address"));
            userModel.setDni(user.getString("dni"));
            userModel.setEmail(user.getString("email"));
            userModel.setName(user.getString("name"));
            userModel.setLastname(user.getString("lastname"));
            userModel.setNumber_phone(user.getString("number_phone"));
            userModel.setRole(user.getString("role"));

            try {
                userModel.setProjectId(user.getLong("projectId"));
            } catch (Exception e) {
            }

            try {
                userModel.setTaskId(user.getLong("taskId"));
            } catch (Exception e) {
            }

            try {
                userModel.setHiring_date(user.getString("hiring_date"));
            } catch (Exception e) {
            }

            userModel.setId(user.getLong("id"));
            userModel.setPassword(user.getString("password"));

            if (user.getString("role").equals(constantUtilities.ROLE_EMPLEADO)) {
                employees.add(userModel);
            }

            if (user.getString("role").equals(constantUtilities.ROLE_PROMOTOR)) {
                promotors.add(userModel);
            }
        }

        Map<String, ArrayList<UserModel>> map = new HashMap<String, ArrayList<UserModel>>();

        map.put(constantUtilities.ROLE_EMPLEADO, employees);
        map.put(constantUtilities.ROLE_PROMOTOR, promotors);

        return map;
    }

}
