/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gina Bustamante
 */
public class BatchEntryDisplay {
    
    private final IntegerProperty id_batch;
    private final IntegerProperty quantity;
    private final FloatProperty batch_cost;
    private final StringProperty arrival_date;
    private final StringProperty expirantion_date;
    private final IntegerProperty id_product;
    private final IntegerProperty type_batch;
    private final IntegerProperty id_group_batch;
    private final StringProperty location;
    private final BooleanProperty state;
    private final StringProperty heritage;
    private final IntegerProperty id_unit;
    
    public BatchEntryDisplay(Integer id_batch, Integer quantity, Float batch_cost, 
                        String arrival_date, String expiration_date, 
                        Integer id_product, Integer type_batch, Integer id_group_batch,
                        String location, Boolean state, String heritage, Integer id_unit){
        
        this.id_batch = new SimpleIntegerProperty(id_batch);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.batch_cost = new SimpleFloatProperty(batch_cost);
        this.arrival_date = new SimpleStringProperty(arrival_date);
        this.expirantion_date = new SimpleStringProperty(expiration_date);
        this.id_product = new SimpleIntegerProperty(id_product);
        this.type_batch = new SimpleIntegerProperty(type_batch);
        this.id_group_batch = new SimpleIntegerProperty(id_group_batch);
        this.location = new SimpleStringProperty(location);
        this.state = new SimpleBooleanProperty(state);
        this.heritage = new SimpleStringProperty(heritage);    
        this.id_unit = new SimpleIntegerProperty(id_unit);
    }
    
    public IntegerProperty id_batchProperty() {
        return id_batch;
    }
    
    public IntegerProperty quantityProperty() {
        return quantity;
    }
    
    public FloatProperty batch_costProperty() {
        return batch_cost;
    }
    
    public StringProperty arrival_dateProperty() {
        return arrival_date;
    }
    
    public StringProperty expirantion_dateProperty() {
        return expirantion_date;
    }
    
    public IntegerProperty id_productProperty() {
        return id_product;
    }
    
    public IntegerProperty type_batchProperty() {
        return type_batch;
    }
    
    public IntegerProperty id_group_batchProperty() {
        return id_group_batch;
    }
    
    public StringProperty locationProperty() {
        return location;
    }
    
    public BooleanProperty stateProperty() {
        return state;
    }
    
    public StringProperty heritageProperty() {
        return heritage;
    }
    
    public IntegerProperty id_unitProperty() {
        return id_unit;
    }
}
