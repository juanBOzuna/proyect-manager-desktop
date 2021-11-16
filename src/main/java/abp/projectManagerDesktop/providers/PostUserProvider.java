/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

import java.io.*;
import okhttp3.*;

/**
 *
 * @author juan barraza
 */
public class PostUserProvider {

    public Boolean postUser(String address, String dni, String email, String lastName, String name, String number_phone, String role, String date, String passsword, Boolean update, Boolean changeProject, Long id, Long projectId) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body;
        if (update) {
            if (changeProject) {
                body = RequestBody.create(mediaType, "{\r\n    \"user\": {\r\n        \"id\":" + id + ",\r\n     \"projectId\":" + projectId + ",\r\n      \"address\": \"" + address + "\",\r\n        \"dni\": \"" + dni + "\",\r\n        \"email\": \"" + email + "\",\r\n        \"password\": \"" + passsword + "\",\r\n        \"lastname\":\"" + lastName + "\",\r\n        \"name\":\"" + name + "\",\r\n        \"number_phone\":\"" + number_phone + "\",\r\n        \"role\":\"" + role + "\"\r\n    },\r\n    \"date\": \"" + date + "\"\r\n}");
            } else {
                body = RequestBody.create(mediaType, "{\r\n    \"user\": {\r\n        \"id\":" + id + ",\r\n        \"address\": \"" + address + "\",\r\n        \"dni\": \"" + dni + "\",\r\n        \"email\": \"" + email + "\",\r\n        \"password\": \"" + passsword + "\",\r\n        \"lastname\":\"" + lastName + "\",\r\n        \"name\":\"" + name + "\",\r\n        \"number_phone\":\"" + number_phone + "\",\r\n        \"role\":\"" + role + "\"\r\n    },\r\n    \"date\": \"" + date + "\"\r\n}");
            }
        } else {
            body = RequestBody.create(mediaType, "{\r\n    \"user\": {\r\n        \"address\": \"" + address + "\",\r\n        \"dni\": \"" + dni + "\",\r\n        \"email\": \"" + email + "\",\r\n        \"password\": \"" + passsword + "\",\r\n        \"lastname\": \"" + lastName + "\",\r\n        \"name\": \"" + name + "\",\r\n        \"number_phone\": \"" + number_phone + "\",\r\n        \"role\": \"" + role + "\"\r\n    },\r\n    \"date\": \"" + date + "\"\r\n}");
        }

        Request request = new Request.Builder()
                .url("http://localhost:8080/user")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.isSuccessful();

    }

}
