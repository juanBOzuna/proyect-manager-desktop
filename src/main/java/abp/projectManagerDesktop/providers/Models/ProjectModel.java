/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abp.projectManagerDesktop.providers.Models;

import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @author juan barraza
 */
public class ProjectModel {
       
    private Long id;
    private String name;
    private String key_name;
    private String comercial_designation;
    private String date_init;
    private String date_finish;
    private Long promotor_id;
    private Timestamp created_at;
    private double percentageCompleted;
    private Boolean isCompleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public String getComercial_designation() {
        return comercial_designation;
    }

    public void setComercial_designation(String comercial_designation) {
        this.comercial_designation = comercial_designation;
    }

    public String getDate_init() {
        return date_init;
    }

    public void setDate_init(String date_init) {
        this.date_init = date_init;
    }

    public String getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(String date_finish) {
        this.date_finish = date_finish;
    }

    public Long getPromotor_id() {
        return promotor_id;
    }

    public void setPromotor_id(Long promotor_id) {
        this.promotor_id = promotor_id;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public double getPercentageCompleted() {
        return percentageCompleted;
    }

    public void setPercentageCompleted(double percentageCompleted) {
        this.percentageCompleted = percentageCompleted;
    }

}
