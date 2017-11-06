/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

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
@Table(name="sale_condition_type")
public class SaleConditionType {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private Integer id_sale_condition_type;
    private String description;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public SaleConditionType() {
        super();
    }

    
    public SaleConditionType(int id, String d){
        this.id_sale_condition_type = id;
        this.description = d;
    }
    

}
