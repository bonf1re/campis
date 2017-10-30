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
@Table(name = "group_batch")
public class DispatchMoves {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_group_batch;
    private Integer type_owner;
    private Integer id_owner;
    private Timestamp arrival_date;
    private Integer reason;
    
    public DispatchMoves(){
        super();
    }
    
    public DispatchMoves(Integer type_owner, Integer id_owner, Timestamp arrival_date, 
                 Integer reason){
        
        this.type_owner = type_owner;
        this.id_owner = id_owner;
        this.arrival_date = arrival_date;
        this.reason =  reason;
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
     * @return the tyoe_owner
     */
    public Integer getTyoe_owner() {
        return type_owner;
    }

    /**
     * @param tyoe_owner the tyoe_owner to set
     */
    public void setTyoe_owner(Integer tyoe_owner) {
        this.type_owner = tyoe_owner;
    }

    /**
     * @return the id_owner
     */
    public Integer getId_owner() {
        return id_owner;
    }

    /**
     * @param id_owner the id_owner to set
     */
    public void setId_owner(Integer id_owner) {
        this.id_owner = id_owner;
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
     * @return the reason
     */
    public Integer getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(Integer reason) {
        this.reason = reason;
    }
                   
}
