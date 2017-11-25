/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gina Bustamante
 */
public class InvoiceDisplay {
        
    private final IntegerProperty id_invoice;
    private final IntegerProperty id_dispatch_order;
    private final IntegerProperty id_type;
    private final DoubleProperty total;

    public InvoiceDisplay(Integer id, Integer id_do, Integer id_type, Double total){
        this.id_invoice =  new SimpleIntegerProperty(id);
        this.id_dispatch_order =  new SimpleIntegerProperty(id_do);
        this.id_type =  new SimpleIntegerProperty(id_type);
        this.total =  new SimpleDoubleProperty(total);
    }
    
    public IntegerProperty getId_invoice() {
        return this.id_invoice;
    }
    
    public IntegerProperty getId_dispatch_order() {
        return this.id_dispatch_order;
    }
    
    public IntegerProperty getId_type() {
        return this.id_type;
    }
    
    public DoubleProperty getTotal() {
        return this.total;
    }
}
