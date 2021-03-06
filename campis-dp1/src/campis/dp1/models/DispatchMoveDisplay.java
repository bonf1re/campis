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
    private final IntegerProperty id_dispatch_move;
    private final IntegerProperty tyoe_owner;
    private final StringProperty type_owner_text;
    private final IntegerProperty id_owner;
    private final StringProperty arrival_date;
    private final IntegerProperty reason;
    private final StringProperty reason_text;
    
    public DispatchMoveDisplay(Integer id_dispatch_move, Integer type_owner, Integer id_owner, 
                        String arrival_date, Integer reason){
        this.id_dispatch_move = new SimpleIntegerProperty(id_dispatch_move);
        this.tyoe_owner = new SimpleIntegerProperty(type_owner);
        this.type_owner_text = new SimpleStringProperty(" ");
        this.id_owner =  new SimpleIntegerProperty(id_owner);
        this.arrival_date = new SimpleStringProperty(arrival_date);
        this.reason = new SimpleIntegerProperty(reason);
        this.reason_text = new SimpleStringProperty(" ");
    }
    
    public DispatchMoveDisplay(Integer id_dispatch_move, Integer type_owner, 
                         String type_owner_text,Integer id_owner, String arrival_date,
                         Integer reason_id, String reason){
        this.id_dispatch_move = new SimpleIntegerProperty(id_dispatch_move);
        this.tyoe_owner = new SimpleIntegerProperty(type_owner);
        this.type_owner_text = new SimpleStringProperty(type_owner_text);
        this.id_owner =  new SimpleIntegerProperty(id_owner);
        this.arrival_date = new SimpleStringProperty(arrival_date);
        this.reason = new SimpleIntegerProperty(reason_id);
        this.reason_text = new SimpleStringProperty(reason);
    }
    
    public IntegerProperty id_dispatch_moveProperty() {
        return id_dispatch_move;
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
    
    public StringProperty reason_textProperty() {
        return reason_text;
    }
    
    public StringProperty type_owner_textProperty() {
        return type_owner_text;
    }
    
}
