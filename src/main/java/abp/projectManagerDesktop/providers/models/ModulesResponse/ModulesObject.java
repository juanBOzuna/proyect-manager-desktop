/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers.models.ModulesResponse;

/**
 *
 * @author juan barraza
 */
// Datum.java

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ModulesObject {
     private long id;
    private String name;
    private Object createdAt;
    private Object updatedAt;
    private Object deletedAt;
    private String namePermiso;
    private long isVisible;
    private long isCreate;
    private long isRead;
    private long isEdit;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("created_at")
    public Object getCreatedAt() { return createdAt; }
    @JsonProperty("created_at")
    public void setCreatedAt(Object value) { this.createdAt = value; }

    @JsonProperty("updated_at")
    public Object getUpdatedAt() { return updatedAt; }
    @JsonProperty("updated_at")
    public void setUpdatedAt(Object value) { this.updatedAt = value; }

    @JsonProperty("deleted_at")
    public Object getDeletedAt() { return deletedAt; }
    @JsonProperty("deleted_at")
    public void setDeletedAt(Object value) { this.deletedAt = value; }

    @JsonProperty("namePermiso")
    public String getNamePermiso() { return namePermiso; }
    @JsonProperty("namePermiso")
    public void setNamePermiso(String value) { this.namePermiso = value; }

    @JsonProperty("is_visible")
    public long getIsVisible() { return isVisible; }
    @JsonProperty("is_visible")
    public void setIsVisible(long value) { this.isVisible = value; }

    @JsonProperty("is_create")
    public long getIsCreate() { return isCreate; }
    @JsonProperty("is_create")
    public void setIsCreate(long value) { this.isCreate = value; }

    @JsonProperty("is_read")
    public long getIsRead() { return isRead; }
    @JsonProperty("is_read")
    public void setIsRead(long value) { this.isRead = value; }

    @JsonProperty("is_edit")
    public long getIsEdit() { return isEdit; }
    @JsonProperty("is_edit")
    public void setIsEdit(long value) { this.isEdit = value; }
}

