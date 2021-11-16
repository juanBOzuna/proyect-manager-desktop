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

public class FinalizeProjectProvider {

    public Boolean finalize(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/projects/finalize/projectId=" + id)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.isSuccessful();
    }
}
