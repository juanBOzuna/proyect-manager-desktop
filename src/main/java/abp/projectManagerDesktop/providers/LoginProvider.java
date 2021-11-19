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
import abp.projectManagerDesktop.providers.Models.LoginResponseModel;
import abp.projectManagerDesktop.providers.Models.ProjectModel;
import abp.projectManagerDesktop.providers.Models.UserModel;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoginProvider {

    public LoginResponseModel login(String email, String pass) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();

//        client.se; // connect timeout
//        client.setReadTimeout(15, TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(constantUtilities.URL_API + "/user/login/email=" + email + "/pass=" + pass + "")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            System.err.println(e);
        }
//        System.out.println(response.body().string());

        return parse(response.body().string());
    }

    LoginResponseModel parse(String resp) {

        LoginResponseModel response = new LoginResponseModel();
        JSONObject respJson = new JSONObject(resp);
        JSONObject user;
        JSONObject project;
        UserModel userModel = new UserModel();

        System.out.println(respJson);

        try {
            ProjectModel proyectAssign;
            project = respJson.getJSONObject("project");
            proyectAssign = new ProjectModel();

            proyectAssign.setId(project.getLong("id"));
            try {
                proyectAssign.setPromotor_id(project.getLong("promotorId"));
            } catch (Exception e) {
            }
            proyectAssign.setName(project.getString("name"));
            try {

                proyectAssign.setPercentageCompleted(project.getFloat("percentageCompleted"));
            } catch (Exception e) {
            }
            proyectAssign.setKey_name(project.getString("key_name"));

            proyectAssign.setComercial_designation(project.getString("comercial_designation"));
            try {
                proyectAssign.setDate_finish(project.getString("date_finish"));
            } catch (Exception e) {
            }
            try {
                proyectAssign.setDate_init(project.getString("date_init"));
            } catch (Exception e) {
            }

            response.setProject(proyectAssign);

        } catch (Exception e) {
        }

        String error = respJson.getString("errors");
        if (error.equals("null")) {
            user = respJson.getJSONObject("userEntity");
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
                userModel.setHiring_date(user.getString("hiring_date"));
            } catch (Exception e) {
            }
            userModel.setId(user.getLong("id"));
            userModel.setPassword(user.getString("password"));
            response.setUser(userModel);
        } else {
            response.setUser(userModel);
        }

        response.setError(error);

        return response;
    }
}
