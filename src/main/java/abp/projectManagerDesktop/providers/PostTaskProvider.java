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
import okhttp3.*;

public class PostTaskProvider {

    public Boolean post(String name, Long employeeId, Boolean edit, Long oldIdUser, Long idTask) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = null;
        if (edit) {
            body = RequestBody.create(mediaType, "{\r\n    \"createTaskModel\": {\r\n        \"task\": {\r\n            \"id\": " + idTask + " ,\r\n            \"name\": \"" + name + "\",\r\n            \"projectId\": " + constantUtilities.projectId + "\r\n        },\r\n        \"employee_id\": " + employeeId + "\r\n    },\r\n    \"id_oldUserAsigned\": " + oldIdUser + "\r\n}");
        } else {
            body = RequestBody.create(mediaType, "{\r\n    \"task\": {\r\n        \"name\": \"" + name + "\",\r\n        \"projectId\": " + constantUtilities.projectId + "\r\n    },\r\n    \"employee_id\": " + employeeId + "\r\n}");
        }

        Request request = null;
        if (edit) {
            request = new Request.Builder()
                    .url("http://localhost:8080/tasks/editTask")
                    .method("POST", body)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build();
        } else {

            request = new Request.Builder()
                    .url("http://localhost:8080/tasks")
                    .method("POST", body)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .build();
        }

        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        return response.isSuccessful();
    }
}
