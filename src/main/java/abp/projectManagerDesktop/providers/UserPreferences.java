/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers;

import java.util.prefs.Preferences;

/**
 *
 * @author juan barraza
 */
public class UserPreferences {

    static Preferences prefs = Preferences.userRoot();

    public void setId(int id) {
        prefs.putInt("id", id);
    }

    public void setToken(String token) {
        prefs.put("token", token);
    }

    public void setName(String name) {
        prefs.put("name", name);
    }

    public void setPhoneNumber(int number) {
        prefs.putInt("phone", number);
    }

    public void setEmail(String email) {
        prefs.put("email", email);
    }

    //getters
    public int getId() {
        return prefs.getInt("id", 0);
    }

    public String getToken() {
        return prefs.get("token", "");
    }
}
