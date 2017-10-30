/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class BatchDisplay {
    private final IntegerProperty id_batch;
    private final IntegerProperty quantity;
    private final FloatProperty batch_cost;
    private final StringProperty arrival_date;
    private final StringProperty expiration_date;
    private final IntegerProperty id_product;
    private final IntegerProperty type_batch;
    private final IntegerProperty id_group_batch;
    private final StringProperty location;
    private final StringProperty state;
    private final StringProperty heritage;
    

    public BatchDisplay(Batch whMove) {
        this.id_batch = new SimpleIntegerProperty(whMove.getId_batch());
        this.quantity = new SimpleIntegerProperty(whMove.getQuantity());
        this.batch_cost = new SimpleFloatProperty(whMove.getBatch_cost());
        this.arrival_date = new SimpleStringProperty(whMove.getArrival_date().toString());
        this.expiration_date = new SimpleStringProperty(whMove.getExpiration_date().toString());
        this.id_product = new SimpleIntegerProperty(whMove.getId_product());
        this.type_batch = new SimpleIntegerProperty(whMove.getType_batch());
        this.id_group_batch = new SimpleIntegerProperty(whMove.getId_group_batch());
        this.location = new SimpleStringProperty(whMove.getLocation());
        this.state = new SimpleStringProperty(Boolean.toString(whMove.isState()));
        this.heritage = new SimpleStringProperty(whMove.getHeritage());
    }
    

    public IntegerProperty getId_batch() {
        return id_batch;
    }

    public IntegerProperty getQuantity() {
        return quantity;
    }

    public FloatProperty getBatch_cost() {
        return batch_cost;
    }

    public StringProperty getArrival_date() {
        return arrival_date;
    }

    public StringProperty getExpiration_date() {
        return expiration_date;
    }

    public IntegerProperty getId_product() {
        return id_product;
    }

    public IntegerProperty getType_batch() {
        return type_batch;
    }

    public IntegerProperty getId_group_batch() {
        return id_group_batch;
    }

    public StringProperty getLocation() {
        return location;
    }

    public StringProperty getState() {
        return state;
    }

    public StringProperty getHeritage() {
        return heritage;
    }
    
    
}
