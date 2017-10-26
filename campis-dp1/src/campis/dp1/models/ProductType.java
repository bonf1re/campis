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
    Integer id_prodType;
    String descripcion;

    public int getId_prodType() {
        return id_prodType;
    }

    public void setId_prodType(int id_prodType) {
        this.id_prodType = id_prodType;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public ProductType() {
        this.id_prodType = null;
        this.descripcion = null;
    }
    
    public ProductType(int id, String descrip){
        this.id_prodType = id;
        this.descripcion = descrip;
    }
    
    public ProductType(String descrip){
        this.descripcion = descrip;
    }
}
