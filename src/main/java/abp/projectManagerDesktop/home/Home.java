/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.home;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.*;

/**
 *
 * @author juan barraza
 */
public class Home {
   public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply( HttpResponse::body )
                .thenAccept(Home::parse )
                .join();
    }
   
    public static void parse( String respBody ){
        JSONArray users = new JSONArray(respBody);

        JSONObject user;

        for (int i = 0; i < users.length() ; i++) {

            user = users.getJSONObject(i);

            System.out.println("\nID: "+ user.getInt("userId")) ;
            System.out.println("TITLE: "+ user.getString("title")) ;
            System.out.println("BODY: "+ user.getString("body")) ;
        }
    }
}
