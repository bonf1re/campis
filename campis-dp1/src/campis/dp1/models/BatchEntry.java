/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Gina Bustamante
 */

@Entity
@Table(name = "batch")
public class BatchEntry {

    /**
     * @return the id_unit
     */
    public Integer getId_unit() {
        return id_unit;
    }

    /**
     * @param id_unit the id_unit to set
     */
    public void setId_unit(Integer id_unit) {
        this.id_unit = id_unit;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_batch;
    private Integer quantity;
    private Float batch_cost;
    private Timestamp arrival_date;
    private Timestamp expirantion_date;
    private Integer id_product;
    private Integer type_batch;
    private Integer id_group_batch;
    private String location;
    private Boolean state;
    private String heritage;
    private Integer id_unit;
    
    public BatchEntry(Integer id_batch, Integer quantity, Float batch_cost, 
                        Timestamp arrival_date, Timestamp expiration_date, 
                        Integer id_product, Integer type_batch, Integer id_group_batch,
                        String location, Boolean state, String heritage, Integer id_unit){
        
        this.id_batch = id_batch;
        this.quantity = quantity;
        this.batch_cost = batch_cost;
        this.arrival_date = arrival_date;
        this.expirantion_date = expiration_date;
        this.id_product = id_product;
        this.type_batch = type_batch;
        this.id_group_batch = id_group_batch;
        this.location = location;
        this.state = state;
        this.heritage = heritage; 
        this.id_unit = id_unit;
    }
    
    /**
     * @return the id_batch
     */
    public Integer getId_batch() {
        return id_batch;
    }

    /**
     * @param id_batch the id_batch to set
     */
    public void setId_batch(Integer id_batch) {
        this.id_batch = id_batch;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the batch_cost
     */
    public Float getBatch_cost() {
        return batch_cost;
    }

    /**
     * @param batch_cost the batch_cost to set
     */
    public void setBatch_cost(Float batch_cost) {
        this.batch_cost = batch_cost;
    }

    /**
     * @return the arrival_date
     */
    public Timestamp getArrival_date() {
        return arrival_date;
    }

    /**
     * @param arrival_date the arrival_date to set
     */
    public void setArrival_date(Timestamp arrival_date) {
        this.arrival_date = arrival_date;
    }

    /**
     * @return the expirantion_date
     */
    public Timestamp getExpirantion_date() {
        return expirantion_date;
    }

    /**
     * @param expirantion_date the expirantion_date to set
     */
    public void setExpirantion_date(Timestamp expirantion_date) {
        this.expirantion_date = expirantion_date;
    }

    /**
     * @return the id_product
     */
    public Integer getId_product() {
        return id_product;
    }

    /**
     * @param id_product the id_product to set
     */
    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    /**
     * @return the type_batch
     */
    public Integer getType_batch() {
        return type_batch;
    }

    /**
     * @param type_batch the type_batch to set
     */
    public void setType_batch(Integer type_batch) {
        this.type_batch = type_batch;
    }

    /**
     * @return the id_group_batch
     */
    public Integer getId_group_batch() {
        return id_group_batch;
    }

    /**
     * @param id_group_batch the id_group_batch to set
     */
    public void setId_group_batch(Integer id_group_batch) {
        this.id_group_batch = id_group_batch;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * @return the heritage
     */
    public String getHeritage() {
        return heritage;
    }

    /**
     * @param heritage the heritage to set
     */
    public void setHeritage(String heritage) {
        this.heritage = heritage;
    }
  
}
