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
 * @author Eddy
 */
@Entity
@Table(name="product_type")
public class ProductType {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    Integer id_product_type;
    String description;

    public int getId_prodType() {
        return id_product_type;
    }

    public void setId_prodType(int id_prodType) {
        this.id_product_type = id_prodType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public ProductType() {
        this.id_product_type = null;
        this.description = null;
    }
    
    public ProductType(int id, String descrip){
        this.id_product_type = id;
        this.description = descrip;
    }
    
    public ProductType(String descrip){
        this.id_product_type = null;
        this.description = descrip;
    }
}
