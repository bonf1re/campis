/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;


import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author david
 */
public class RequestLineDisplay {

    /**
     * @return the id_product
     */
    public IntegerProperty getId_product() {
        return id_product;
    }

    /**
     * @return the name
     */
    public StringProperty getName() {
        return name;
    }


    /**
     * @return the quantity
     */
    public IntegerProperty getQuantity() {
        return quantity;
    }

    /**
     * @return the base_price
     */
    public FloatProperty getBase_price() {
        return base_price;
    }

    /**
     * @return the final_price
     */
    public FloatProperty getFinal_price() {
        return final_price;
    }

    /**
     * @return the discount
     */
    public FloatProperty getDiscount() {
        return discount;
    }

    /**
     * @return the status
     */
    public StringProperty getStatus() {
        return status;
    }
    private final IntegerProperty id_product;
    private final StringProperty name;
    private final IntegerProperty quantity;
    private final FloatProperty base_price;
    private final FloatProperty final_price;
    private final FloatProperty discount;
    private final StringProperty status;
    
    public RequestLineDisplay(Integer codProd, String nombre, int quantity, 
                    float base_price, float final_price, float discount, String status) {
        this.id_product = new SimpleIntegerProperty(codProd);
        this.name = new SimpleStringProperty(nombre);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.base_price = new SimpleFloatProperty(base_price);
        this.final_price = new SimpleFloatProperty(final_price);
        this.discount = new SimpleFloatProperty(discount);
        this.status = new SimpleStringProperty(status);
        
    }
}
