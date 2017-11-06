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
@Table(name = "dispatch_move")
public class DispatchMove {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_dispatch_move;
    private Integer type_owner;
    private Integer id_owner;
    private Timestamp mov_date;
    private Integer reason;
    private Integer id_batch;
    private Timestamp arrival_date;

    public DispatchMove(){
        super();
    }
    
    public DispatchMove(Integer type_owner, Integer id_owner, Timestamp mov_date, 
                 Integer reason, Integer id_batch, Timestamp arrival_date){
        
        this.type_owner = type_owner;
        this.id_owner = id_owner;
        this.mov_date = mov_date;
        this.reason =  reason;
        this.id_batch = id_batch;
        this.arrival_date = arrival_date;
    }
    
    public DispatchMove(Integer type_owner, Integer id_owner, 
                 Integer reason, Integer id_batch){
        
        this.type_owner = type_owner;
        this.id_owner = id_owner;
        this.reason =  reason;
        this.id_batch = id_batch;
    }
    
    /**
     * @return the id_group_batch
     */
    public Integer getId_dispatch_move() {
        return id_dispatch_move;
    }

    /**
     * @param id_group_batch the id_group_batch to set
     */
    public void setId_dispatch_move(Integer id_dispatch_move) {
        this.id_dispatch_move = id_dispatch_move;
    }

    /**
     * @return the tyoe_owner
     */
    public Integer getType_owner() {
        return type_owner;
    }

    /**
     * @param tyoe_owner the tyoe_owner to set
     */
    public void setType_owner(Integer tyoe_owner) {
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
    public Timestamp getMov_date() {
        return mov_date;
    }

    /**
     * @param arrival_date the arrival_date to set
     */
    public void setMov_date(Timestamp mov_date) {
        this.mov_date = mov_date;
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
    
    public Integer getId_batch() {
        return id_batch;
    }

    public void setId_batch(Integer id_batch) {
        this.id_batch = id_batch;
    }
   
    public Timestamp getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Timestamp arrival_date) {
        this.arrival_date = arrival_date;
    }
}
