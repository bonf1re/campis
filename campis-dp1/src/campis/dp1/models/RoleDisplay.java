/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marco
 */
public class RoleDisplay {
    private final StringProperty description;
    
    public RoleDisplay(String description) {
        this.description = new SimpleStringProperty(description);
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
}
