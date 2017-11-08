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
public class ReportExpirationDisplay {

    /**
     * @return the date
     */
    public StringProperty getDate() {
        return date;
    }

    /**
     * @return the product
     */
    public StringProperty getProduct() {
        return product;
    }

    /**
     * @return the quantity
     */
    public IntegerProperty getQuantity() {
        return quantity;
    }

    /**
     * @return the location
     */
    public StringProperty getLocation() {
        return location;
    }
    
    private final StringProperty unit;
    private final StringProperty date;
    private final StringProperty product;
    private final IntegerProperty quantity;
    private final StringProperty location;
    
    public ReportExpirationDisplay(String date, int product, 
            Integer quantity, String location) {
        System.out.println("=================");
        System.out.println(product);
        this.date = new SimpleStringProperty(date);
        Product prod = Product.getProduct(product);
        System.out.println("=================");
        System.out.println(prod.getName());
        UnitOfMeasure un = UnitOfMeasure.getUnit(prod.getId_unit_of_measure());
        this.product = new SimpleStringProperty(prod.name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.location = new SimpleStringProperty(location);
        this.unit = new SimpleStringProperty(un.getDescription());
    }

    /**
     * @return the unit
     */
    public StringProperty getUnit() {
        return unit;
    }
}
