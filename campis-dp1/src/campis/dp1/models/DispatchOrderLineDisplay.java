/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gina Bustamante
 */
public class DispatchOrderLineDisplay {
    
    private final IntegerProperty id_dispatch_order_line;
    private final IntegerProperty id_dispatch_order;
    private final IntegerProperty id_product;
    private final StringProperty product_name; //
    private final IntegerProperty quantity;
    private final IntegerProperty id_unit_of_measure; //
    private final StringProperty unit_of_measure_name; //
    private final BooleanProperty delivered;
    private final FloatProperty weigth; // prod*cant
    
    
    public DispatchOrderLineDisplay(Integer id_dispatch_order_line, Integer id_dispatch_order,
                                    Integer id_product, String product_name, Integer quantity, 
                                    Integer id_unit_of_measure, String unit_of_measure_name,
                                    Float weigth, Boolean delievred){
        
        this.id_dispatch_order_line = new SimpleIntegerProperty(id_dispatch_order_line);
        this.id_dispatch_order = new SimpleIntegerProperty(id_dispatch_order);
        this.id_product =  new SimpleIntegerProperty(id_product);
        this.product_name = new SimpleStringProperty(product_name); //
        this.quantity = new SimpleIntegerProperty(quantity);
        this.id_unit_of_measure = new SimpleIntegerProperty(id_unit_of_measure);//
        this.unit_of_measure_name = new SimpleStringProperty(unit_of_measure_name); //
        this.delivered = new SimpleBooleanProperty(delievred);
        this.weigth = new SimpleFloatProperty(weigth); //
    }

    
    public IntegerProperty id_dispatch_order_lineProperty() {
        return id_dispatch_order_line;
    }
    
    public IntegerProperty id_dispatch_orderProperty() {
        return id_dispatch_order;
    }
    
    public IntegerProperty id_productProperty() {
        return id_product;
    }
    
    //
    public StringProperty product_nameProperty() {
        return product_name;
    }
    
    
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    
    //
    public IntegerProperty id_unit_of_measureProperty() {
        return id_unit_of_measure;
    }
    
    //
    public StringProperty unit_of_measure_nameProperty() {
        return unit_of_measure_name;
    }
    
    public BooleanProperty deliveredProperty() {
        return delivered;
    }
    
     //
    public FloatProperty weigthProperty() {
        return weigth;
    }
}
