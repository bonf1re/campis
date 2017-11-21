/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gina Bustamante
 */
public class DeliveryDisplay {
    private final IntegerProperty id_delivery;
    private final StringProperty location;
    private final StringProperty vh_plate;
    private final IntegerProperty id_dispatch_order;
    
    public DeliveryDisplay(Integer id, String address, String vh_plate, Integer id_do){
        this.id_delivery  = new SimpleIntegerProperty(id);
        this.location = new SimpleStringProperty(address);
        this.vh_plate = new SimpleStringProperty(vh_plate);
        this.id_dispatch_order = new SimpleIntegerProperty(id_do);  
    }
    
    public IntegerProperty getId_delivery() {
        return this.id_delivery;
    }

    public StringProperty getLocation() {
        return this.location;
    }

    public StringProperty getVh_plate() {
        return this.vh_plate;
    }
    
    public IntegerProperty getId_dispatch_order() {
        return this.id_dispatch_order;
    }
}
