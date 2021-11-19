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
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetUsersByProjectId {

    public ArrayList<UserModel> get(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();
        Request request = new Request.Builder()
                .url(constantUtilities.URL_API+"/user/ofProyects/projectId="+constantUtilities.projectId)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        return parse(response.body().string());
    }

    ArrayList<UserModel> parse(String response) {
        ArrayList<UserModel> employees = new ArrayList<UserModel>();
        JSONArray respJson = new JSONArray(response);

        try {
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
                    userModel.setHiring_date(user.getString("hiring_date"));
                } catch (Exception e) {
                }

                userModel.setId(user.getLong("id"));
                userModel.setPassword(user.getString("password"));

                employees.add(userModel);

            }
        } catch (Exception e) {
        }
        return employees;
    }
}
