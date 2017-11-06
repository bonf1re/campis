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
 * @author david
 */
@Entity
@Table(name = "sale_condition")
public class SaleCondition {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_sale_condition;
    private Timestamp initial_date;
    private Timestamp final_date;
    private Float amount;
    private Integer id_sale_condition_type;
    private Integer limits;
    private Integer id_to_take;
    private Integer id_campaign;

    public SaleCondition(Timestamp initial_date, Timestamp final_date, Float amount, Integer id_sale_condition_type, Integer limits, Integer id_to_take, Integer id_campaign) {
        this.initial_date = initial_date;
        this.final_date = final_date;
        this.amount = amount;
        this.id_sale_condition_type = id_sale_condition_type;
        this.limits = limits;
        this.id_to_take = id_to_take;
        this.id_campaign = id_campaign;
    }
    

    /**
     * @return the id_sale_condition
     */
    public Integer getId_sale_condition() {
        return id_sale_condition;
    }

    /**
     * @param id_sale_condition the id_sale_condition to set
     */
    public void setId_sale_condition(Integer id_sale_condition) {
        this.id_sale_condition = id_sale_condition;
    }

    /**
     * @return the initial_date
     */
    public Timestamp getInitial_date() {
        return initial_date;
    }

    /**
     * @param initial_date the initial_date to set
     */
    public void setInitial_date(Timestamp initial_date) {
        this.initial_date = initial_date;
    }

    /**
     * @return the final_date
     */
    public Timestamp getFinal_date() {
        return final_date;
    }

    /**
     * @param final_date the final_date to set
     */
    public void setFinal_date(Timestamp final_date) {
        this.final_date = final_date;
    }

    /**
     * @return the amount
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * @return the id_sale_condition_type
     */
    public Integer getId_sale_condition_type() {
        return id_sale_condition_type;
    }

    /**
     * @param id_sale_condition_type the id_sale_condition_type to set
     */
    public void setId_sale_condition_type(Integer id_sale_condition_type) {
        this.id_sale_condition_type = id_sale_condition_type;
    }

    /**
     * @return the limits
     */
    public Integer getLimits() {
        return limits;
    }

    /**
     * @param limits the limits to set
     */
    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    /**
     * @return the id_to_take
     */
    public Integer getId_to_take() {
        return id_to_take;
    }

    /**
     * @param id_to_take the id_to_take to set
     */
    public void setId_to_take(Integer id_to_take) {
        this.id_to_take = id_to_take;
    }
    
     public Integer getId_campaign() {
        return id_campaign;
    }

    /**
     * @param id_campaign the id_campaign to set
     */
    public void setId_campaign(Integer id_campaign) {
        this.id_campaign = id_campaign;
    }
    
    public SaleCondition() {
        super();
    }
    
    
}
