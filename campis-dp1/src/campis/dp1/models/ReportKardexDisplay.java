/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author david
 */
public class ReportKardexDisplay {

    /**
     * @return the mov_date
     */
    public StringProperty getMov_date() {
        return mov_date;
    }

    /**
     * @return the product
     */
    public StringProperty getProduct() {
        return product;
    }

    /**
     * @return the unitM
     */
    public StringProperty getUnitM() {
        return unitM;
    }

    /**
     * @return the quantity
     */
    public IntegerProperty getQuantity() {
        return quantity;
    }

    /**
     * @return the user
     */
    public StringProperty getUser() {
        return user;
    }

    /**
     * @return the warehouse
     */
    public StringProperty getWarehouse() {
        return warehouse;
    }

    /**
     * @return the location
     */
    public StringProperty getLocation() {
        return location;
    }

    /**
     * @return the plate
     */
    public StringProperty getPlate() {
        return plate;
    }

    /**
     * @return the mov_type
     */
    public StringProperty getMov_type() {
        return mov_type;
    }
    
    private final StringProperty mov_date;
    private final StringProperty product;
    private final StringProperty unitM;
    private final IntegerProperty quantity;
    private final StringProperty user;
    private final StringProperty warehouse;
    private final StringProperty location;
    private final StringProperty plate;
    private final StringProperty mov_type;
    
    public ReportKardexDisplay(String mov_date, String product, 
            String unitM, Integer quantity, String user, 
            String warehouse, String location, String plate, String mov_type) {
        this.mov_date = new SimpleStringProperty(mov_date);
        this.product = new SimpleStringProperty(product);
        this.unitM = new SimpleStringProperty(unitM);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.user = new SimpleStringProperty(user);
        this.warehouse = new SimpleStringProperty(warehouse);
        this.location = new SimpleStringProperty(location);
        this.plate = new SimpleStringProperty(plate);
        this.mov_type = new SimpleStringProperty(mov_type);
        
    }
    
}
