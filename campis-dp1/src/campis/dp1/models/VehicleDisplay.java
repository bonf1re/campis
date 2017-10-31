/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author david
 */
public class VehicleDisplay {
    private final IntegerProperty id_vehicle;
    private final DoubleProperty max_weight;
    private final IntegerProperty speed;
    private final StringProperty active;
    private final StringProperty warehouse;
    private final StringProperty plate;
    
    
    public VehicleDisplay(int id_vehicle, double max_weight, int speed, boolean active, String warehouse, String plate) {
        this.id_vehicle = new SimpleIntegerProperty(id_vehicle);
        this.max_weight = new SimpleDoubleProperty(max_weight);
        this.speed = new SimpleIntegerProperty(speed);
        
        if (active) this.active = new SimpleStringProperty("Activo");
        else this.active = new SimpleStringProperty("No Activo");
     
        this.warehouse = new SimpleStringProperty(warehouse);
        this.plate = new SimpleStringProperty(plate);
        
    }
    /**
     * @return the id_vehicle
     */
    public IntegerProperty idProperty() {
        return id_vehicle;
    }

    /**
     * @return the max_weight
     */
    public DoubleProperty maxWeightProperty() {
        return max_weight;
    }

    /**
     * @return the speed
     */
    public IntegerProperty speedProperty() {
        return speed;
    }

    /**
     * @return the active
     */
    public StringProperty activeProperty() {
        return active;
    }

    /**
     * @return the warehouse
     */
    public StringProperty warehouseProperty() {
        return warehouse;
    }

    /**
     * @return the plate
     */
    public StringProperty plateProperty() {
        return plate;
    }
    
    
    
}
