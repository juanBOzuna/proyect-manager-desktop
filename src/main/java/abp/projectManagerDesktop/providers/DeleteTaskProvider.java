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
import java.io.*;
import java.util.concurrent.TimeUnit;
import okhttp3.*;

public class DeleteTaskProvider {

    public Boolean delete(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(constantUtilities.URL_API+"/tasks/" + id)
                .method("DELETE", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json").build();
        Response response = client.newCall(request).execute();
        return response.isSuccessful();
    }

}
