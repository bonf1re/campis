/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

import campis.dp1.models.ProductDisplay;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Eddy
 */
public class ContextFX {
    private final static ContextFX instance = new ContextFX();
    public static ContextFX getInstance(){
        return instance;
    }
    
    Integer id = null;
    Integer quantity = null;
    Float totAmount = 0.0f;
    ObservableList<ProductDisplay> tempList = FXCollections.observableArrayList();

    public Integer getId() {
        Integer returnable = new Integer(this.id);
        this.id=null;
        return returnable;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getQuantity() {
        Integer returnable = new Integer(this.quantity);
        this.quantity = null;
        return returnable;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public Float getTotAmount() {
        Float returnable = new Float(this.totAmount);
        return returnable;
    }
    
    public void setTotAmount(Float totAmount) {
        this.totAmount = totAmount;
    }
    
    public ObservableList<ProductDisplay> getTempList() {
        ObservableList<ProductDisplay> returnable = this.tempList;
        return returnable;
    }
    
    public void setTempList(ObservableList<ProductDisplay> tempList) {
        this.tempList = tempList;
    }
}
