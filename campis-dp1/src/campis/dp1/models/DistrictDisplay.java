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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author joseamz
 */
public class DistrictDisplay {
    private final IntegerProperty id_district;
    private final StringProperty name;
    private final DoubleProperty freight;
    
    public DistrictDisplay(int id, String name, Double freight) {
        this.id_district = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.freight = new SimpleDoubleProperty(freight);
    }

    public IntegerProperty idDistrictProperty() {
        return id_district;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty freightProperty() {
        return freight;
    }
}
