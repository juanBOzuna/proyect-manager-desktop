/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.objects;

/**
 *
 * @author juan barraza
 */
public class Moduls {

    int id;
    String name;
    String created_at;
    String updated_at;
    String deleted_at;
    
    boolean is_visible;
    boolean is_create;
    boolean is_read;
    boolean is_edit;
    

    public Moduls(int id,
            String name,
            String created_at,
            String updated_at,
            String deleted_at,
            int visible,
            int create,
            int read,
            int edit
            ) {
        this.id = id;
        this.name = name;
        
        if (created_at == null) {
            this.created_at = "";
        } else {
            this.created_at = created_at;
        }
        
        if (updated_at == null) {
            this.updated_at = "";
        } else {
            this.updated_at = updated_at;
        }
        
        if (deleted_at == null) {
            this.deleted_at = "";
        } else {
            this.deleted_at = deleted_at;
        }
        
        this.is_visible = boolToInt(visible);
        this.is_create = boolToInt(create);
        this.is_read = boolToInt(read );
        this.is_edit = boolToInt(edit);
    }

public boolean boolToInt(int b) { 
    return b==1;
}

}



