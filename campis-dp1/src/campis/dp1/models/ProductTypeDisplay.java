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
 * @author joseamz
 */
public class ProductTypeDisplay {
    private final IntegerProperty id_product_type;
    private final StringProperty description;
    
    public ProductTypeDisplay(int id, String description) {
        this.id_product_type = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
    }
    
    public IntegerProperty idProductTypeProperty() {
        return id_product_type;
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
}
