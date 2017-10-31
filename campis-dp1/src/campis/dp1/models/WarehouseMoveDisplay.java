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
 * @author sergio
 */
public class WarehouseMoveDisplay {
    private IntegerProperty id_movement;
    private StringProperty mov_date;
    private IntegerProperty id_user;
    private IntegerProperty quantity;
    private IntegerProperty id_zone;
    private IntegerProperty id_vehicle;
    private IntegerProperty mov_type;

    public WarehouseMoveDisplay(WarehouseMove whMove) {
        this.id_movement = new SimpleIntegerProperty(whMove.getId_movement());
        this.mov_date = new SimpleStringProperty(whMove.getMov_date().toString());
        this.id_user = new SimpleIntegerProperty(whMove.getId_user());
        this.quantity = new SimpleIntegerProperty(whMove.getQuantity());
        this.id_zone = new SimpleIntegerProperty(whMove.getId_zone());
        this.id_vehicle = new SimpleIntegerProperty(whMove.getId_movement());
        this.mov_type = new SimpleIntegerProperty(whMove.getMov_type());
    }
    
    
    
    
    public IntegerProperty getId_movement() {
        return id_movement;
    }

    public StringProperty getMov_date() {
        return mov_date;
    }

    public IntegerProperty getId_user() {
        return id_user;
    }

    public IntegerProperty getQuantity() {
        return quantity;
    }

    public IntegerProperty getId_zone() {
        return id_zone;
    }

    public IntegerProperty getId_vehicle() {
        return id_vehicle;
    }

    public IntegerProperty getMov_type() {
        return mov_type;
    }
    
    
}
