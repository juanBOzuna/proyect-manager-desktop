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
import java.io.*;
import okhttp3.*;

public class PostProjectProvider {

    public void postProyect() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\":\"pllasd\",\r\n    \"key_name\":\"asdasd\",\r\n    \"comercial_designation\":\"asdds\",\r\n    \"date_init\":\"2021-11-1 00:00:00.000000\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/projects")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
