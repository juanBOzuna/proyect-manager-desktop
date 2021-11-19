package abp.projectManagerDesktop.providers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author juan barraza
 */
import abp.projectManagerDesktop.constants.constantUtilities;
import java.io.*;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import okhttp3.*;

public class PostProjectProvider {

    public Boolean postProyect(String name, String key_name, String comercial_designation, String date_init, String date_finish, long promotor_id, Boolean update, Long id) throws IOException {
        System.out.println("Usuario promotor: " + promotor_id);

        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
//        System.out.println("");

//        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"project\": {\r\n        \"name\": \"" + name + "\",\r\n        \"key_name\": \"" + key_name + "\",\r\n        \"comercial_designation\": \"" + comercial_designation + "\"\r\n    },\r\n    \"date_init\": \"" + date_init + "\",\r\n    \"date_finish\": \"" + date_finish + "\"\r\n}");
        RequestBody body;
        if (!update) {
            body = RequestBody.create(mediaType, "{\r\n    \"project\": {\r\n        \"name\": \"" + name + "\",\r\n        \"key_name\": \"" + key_name + "\",\r\n        \"comercial_designation\": \"" + comercial_designation + "\"\r\n    },\r\n    \"date_init\": \"" + date_init + "\",\r\n    \"date_finish\": \"" + date_finish + "\",\r\n    \"promotor_id\": " + promotor_id + "\r\n}");
        } else {
            body = RequestBody.create(mediaType, "{\r\n    \"project\": {\r\n     \"id\":" + id + ",\r\n    \"name\": \"" + name + "\",\r\n        \"key_name\": \"" + key_name + "\",\r\n        \"comercial_designation\": \"" + comercial_designation + "\"\r\n    },\r\n    \"date_init\": \"" + date_init + "\",\r\n    \"date_finish\": \"" + date_finish + "\",\r\n    \"promotor_id\": " + promotor_id + "\r\n}");
        }
//        RequestBody 

        Request request = new Request.Builder()
                .url(constantUtilities.URL_API+":8080/projects")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());

        return response.isSuccessful();
    }
}
