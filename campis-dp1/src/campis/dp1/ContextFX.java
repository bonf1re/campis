/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

import campis.dp1.models.ProductDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

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
    List aux_list = null;   // Abstract so it can take any kind of arraylist
    Integer quantity = null;
    Float totAmount = 0.0f;
    ObservableList<ProductDisplay> tempList = FXCollections.observableArrayList();
    
    public List getList(){
        List returnable = new ArrayList<>(this.aux_list);
        this.aux_list = null;
        return returnable;
    }
    
    public void setList(List aux_list){
        this.aux_list = new ArrayList<>(aux_list);
    }

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
