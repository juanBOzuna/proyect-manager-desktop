/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

import abp.projectManagerDesktop.providers.models.ModulesResponse.Converter;
import abp.projectManagerDesktop.providers.models.ModulesResponse.ModelResponse;
import abp.projectManagerDesktop.providers.models.ModulesResponse.ModulesObject;
import okhttp3.*;

/**
 *
 * @author juan barraza
 */
public class GetModulesProvider {

    static ModulesObject[] resp;

    public static ModulesObject[] getModules() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://proyect-manager-backend.test/api/v1/moduls?rolId=2")
                .get()
                .build();

        try ( Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                resp = parse(response.body().string());
                return resp;
            } else {
                resp = parse(response.body().string());
                return resp;
            }
        } catch (Exception e) {
            return resp;
        }

    }

    public static ModulesObject[] parse(String respBody) {
        ModelResponse data = null;
        try {
            data = Converter.fromJsonString(respBody);
        } catch (Exception e) {
        }
        return data.getData();
    }
}
