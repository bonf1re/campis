/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 *
 * @author Gina Bustamante
 */
@Entity
@Table (name = "invoice_line")
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_invoice_line; 
    private Integer id_invoice;
    private Integer id_product;
    private Integer quantity;
    private Float cost;
    private Float discount;
    private Integer quantity_ro;
    private Float final_cost;

    public InvoiceLine() {
        super();
    }
    
    public InvoiceLine (Integer id_invoice_line, Integer id_invoice, Integer id_product,
                        Integer quantity, Float cost, Float discount, Integer quantity_ro, 
                        Float final_cost){
        
        this.id_invoice_line = id_invoice_line;
        this.id_invoice = id_invoice;
        this.id_product = id_product;
        this.quantity = quantity;
        this.cost = cost;
        this.discount = discount;
        this.quantity_ro = quantity_ro;
        this.final_cost = final_cost;    
    }
    
    public InvoiceLine (Integer id_invoice, Integer id_product,
                        Integer quantity, Float cost, Float discount, Integer quantity_ro, 
                        Float final_cost){
        
        this.id_invoice_line = -1;
        this.id_invoice = id_invoice;
        this.id_product = id_product;
        this.quantity = quantity;
        this.cost = cost;
        this.discount = discount;
        this.quantity_ro = quantity_ro;
        this.final_cost = final_cost;    
    }
    
    
    /**
     * @return the id_invoice_line
     */
    public Integer getId_invoice_line() {
        return id_invoice_line;
    }

    /**
     * @param id_invoice_line the id_invoice_line to set
     */
    public void setId_invoice_line(Integer id_invoice_line) {
        this.id_invoice_line = id_invoice_line;
    }

    /**
     * @return the id_invoice
     */
    public Integer getId_invoice() {
        return id_invoice;
    }

    /**
     * @param id_invoice the id_invoice to set
     */
    public void setId_invoice(Integer id_invoice) {
        this.id_invoice = id_invoice;
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
     * @return the cost
     */
    public Float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Float cost) {
        this.cost = cost;
    }

    /**
     * @return the discount
     */
    public Float getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    /**
     * @return the quantity_ro
     */
    public Integer getQuantity_ro() {
        return quantity_ro;
    }

    /**
     * @param quantity_ro the quantity_ro to set
     */
    public void setQuantity_ro(Integer quantity_ro) {
        this.quantity_ro = quantity_ro;
    }

    /**
     * @return the final_cost
     */
    public Float getFinal_cost() {
        return final_cost;
    }

    /**
     * @param final_cost the final_cost to set
     */
    public void setFinal_cost(Float final_cost) {
        this.final_cost = final_cost;
    }
    
}
