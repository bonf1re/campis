/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gina Bustamante
 */
public class DispatchMoveDisplay {
    
    private final IntegerProperty id_group_batch;
    private final IntegerProperty tyoe_owner;
    private final IntegerProperty id_owner;
    private final StringProperty arrival_date;
    private final IntegerProperty reason;
    
    public DispatchMoveDisplay(Integer id_group_batch, Integer type_owner, Integer id_owner, 
                        String arrival_date, Integer reason){
        this.id_group_batch = new SimpleIntegerProperty(id_group_batch);
        this.tyoe_owner = new SimpleIntegerProperty(type_owner);
        this.id_owner =  new SimpleIntegerProperty(id_owner);
        this.arrival_date = new SimpleStringProperty(arrival_date);
        this.reason = new SimpleIntegerProperty(reason);
    }
    
    public IntegerProperty id_group_batchProperty() {
        return id_group_batch;
    }
    
    public IntegerProperty type_ownerProperty() {
        return tyoe_owner;
    }
    
    public IntegerProperty id_ownerProperty() {
        return id_owner;
    }
    
    public StringProperty arrival_dateProperty() {
        return arrival_date;
    }
    
    public IntegerProperty reasonProperty() {
        return reason;
    }
    
}
