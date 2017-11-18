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
    private final StringProperty location;
    private final StringProperty state;
    private final StringProperty heritage;
    private final BooleanProperty selected;
    private final SimpleIntegerProperty numMove;
    private final DoubleProperty weight;
    
    private final IntegerProperty id_line_do;
    private final IntegerProperty prod_quantity;
    private final StringProperty prod_name;
    

    public BatchDisplay(Batch whMove,double weight) {
        this.id_batch = new SimpleIntegerProperty(whMove.getId_batch());
        this.quantity = new SimpleIntegerProperty(whMove.getQuantity());
        this.batch_cost = new SimpleFloatProperty(whMove.getBatch_cost());
        this.arrival_date = new SimpleStringProperty(whMove.getArrival_date().toString());
        this.expiration_date = new SimpleStringProperty(whMove.getExpiration_date().toString());
        this.id_product = new SimpleIntegerProperty(whMove.getId_product());
        this.type_batch = new SimpleIntegerProperty(whMove.getType_batch());
        this.location = new SimpleStringProperty(whMove.getLocation());
        this.state = new SimpleStringProperty(Boolean.toString(whMove.isState()));
        this.heritage = new SimpleStringProperty(whMove.getHeritage());
        this.selected = new SimpleBooleanProperty(false);
        this.numMove = new SimpleIntegerProperty(0);
        this.weight =  new  SimpleDoubleProperty(weight);
        
        this.id_line_do = new SimpleIntegerProperty(0);
        this.prod_quantity = new SimpleIntegerProperty(0);
        this.prod_name =  new SimpleStringProperty(" ");
    }
    
    public BatchDisplay(int id_batch, int quantity, float cost, String arrivalDate, String expirationDate,
                        int id_product, int type, String location, String state, String heritage) {
        
        this.id_batch = new SimpleIntegerProperty(id_batch);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.batch_cost = new SimpleFloatProperty(cost);
        this.arrival_date = new SimpleStringProperty(arrivalDate);
        this.expiration_date = new SimpleStringProperty(expirationDate);
        this.id_product = new SimpleIntegerProperty(id_product);
        this.type_batch = new SimpleIntegerProperty(type);
        this.location = new SimpleStringProperty(location);
        this.state = new SimpleStringProperty(state);
        this.heritage = new SimpleStringProperty(heritage);
        this.selected = new SimpleBooleanProperty(false);
        this.numMove = new SimpleIntegerProperty(0);
        this.weight =  new SimpleDoubleProperty(0);
        
        this.id_line_do = new SimpleIntegerProperty(0);
        this.prod_quantity = new SimpleIntegerProperty(0);
        this.prod_name =  new SimpleStringProperty(" ");
    }
    
    public BatchDisplay(int id_batch, int quantity, float cost, String arrivalDate, 
                        String expirationDate, int id_product, int type, 
                        String location, String state, String heritage, 
                        int id_line, int prod_q, String p_name) {
        
        this.id_batch = new SimpleIntegerProperty(id_batch);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.batch_cost = new SimpleFloatProperty(cost);
        this.arrival_date = new SimpleStringProperty(arrivalDate);
        this.expiration_date = new SimpleStringProperty(expirationDate);
        this.id_product = new SimpleIntegerProperty(id_product);
        this.type_batch = new SimpleIntegerProperty(type);
        this.location = new SimpleStringProperty(location);
        this.state = new SimpleStringProperty(state);
        this.heritage = new SimpleStringProperty(heritage);
        this.selected = new SimpleBooleanProperty(false);
        this.numMove = new SimpleIntegerProperty(0);
        this.weight =  new SimpleDoubleProperty(0);
        
        //Muestra a que linea del dispatch order esta relacionado este lote
        this.id_line_do = new SimpleIntegerProperty(id_line);
        //guarda la cantidad de producto requerida por la linea dle dispatch order
        this.prod_quantity = new SimpleIntegerProperty(prod_q);
        //guarda el nombre del producto
        this.prod_name =  new SimpleStringProperty(p_name);
    }

    public BooleanProperty getSelected() {
        return selected;
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

    public StringProperty getLocation() {
        return location;
    }

    public StringProperty getState() {
        return state;
    }

    public StringProperty getHeritage() {
        return heritage;
    }

    public IntegerProperty getNumMove() {
        return numMove;
    }

    public DoubleProperty getWeight() {
        return weight;
    }  
        
    public IntegerProperty getId_line_do() {
        return id_line_do;
    }
    
    public IntegerProperty getProd_quantity() {
        return prod_quantity;
    }
    
    public StringProperty getProd_name() {
        return prod_name;
    }
    
}
