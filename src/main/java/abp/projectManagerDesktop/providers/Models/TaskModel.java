/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers.Models;

/**
 *
 * @author juan barraza
 */
public class TaskModel {

    private Long id;
    private String name;
    private long projectId;
    private boolean is_completed = false;
    private boolean hasEmployee = false;
//    private Boolean

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getHasEmployee() {
        return hasEmployee;
    }

    public void setHasEmployee(boolean hasEmployee) {
        this.hasEmployee = hasEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public Boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }
}
