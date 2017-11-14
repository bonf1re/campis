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

    /**
     * @return the n_discount
     */
    public Integer getN_discount() {
        return n_discount;
    }

    /**
     * @param n_discount the n_discount to set
     */
    public void setN_discount(Integer n_discount) {
        this.n_discount = n_discount;
    }

    /**
     * @return the n_tocount
     */
    public Integer getN_tocount() {
        return n_tocount;
    }

    /**
     * @param n_tocount the n_tocount to set
     */
    public void setN_tocount(Integer n_tocount) {
        this.n_tocount = n_tocount;
    }
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
    private Integer n_discount;
    private Integer n_tocount;
    

    public SaleCondition(Timestamp initial_date, Timestamp final_date, Float amount, 
            Integer id_sale_condition_type, Integer limits, 
            Integer id_to_take, Integer id_campaign,
            Integer n_discount, Integer n_tocount) {
        this.initial_date = initial_date;
        this.final_date = final_date;
        this.amount = amount;
        this.id_sale_condition_type = id_sale_condition_type;
        this.limits = limits;
        this.id_to_take = id_to_take;
        this.id_campaign = id_campaign;
        this.n_discount = n_discount;
        this.n_tocount = n_tocount;
        
    }
    
    public SaleCondition(Integer id_sale_condition, Timestamp initial_date, Timestamp final_date, Float amount, 
            Integer id_sale_condition_type, Integer limits, 
            Integer id_to_take, Integer id_campaign,
            Integer n_discount, Integer n_tocount) {
        this.id_sale_condition = id_sale_condition;
        this.initial_date = initial_date;
        this.final_date = final_date;
        this.amount = amount;
        this.id_sale_condition_type = id_sale_condition_type;
        this.limits = limits;
        this.id_to_take = id_to_take;
        this.id_campaign = id_campaign;
        this.n_discount = n_discount;
        this.n_tocount = n_tocount;
        
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
