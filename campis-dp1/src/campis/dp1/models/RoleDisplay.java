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
 * @author Marco
 */
public class RoleDisplay {
    private final IntegerProperty id_role;
    private final StringProperty description;
    
    public RoleDisplay(int id, String description) {
        this.id_role = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
    }

    public IntegerProperty idRoleProperty() {
        return id_role;
    }

    public StringProperty descriptionProperty() {
        return description;
    }
}
