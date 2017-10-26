/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.model;

/**
 *
 * @author Eddy
 */
public class ProductType {
    String id_prodType;
    String descripcion;

    public String getId_prodType() {
        return id_prodType;
    }

    public void setId_prodType(String id_prodType) {
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
    
    public ProductType(String id, String descrip){
        this.id_prodType = id;
        this.descripcion = descrip;
    }
}
