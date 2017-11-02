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
public class RequestStatusDisplay {
    private final IntegerProperty id_request_status;
    private final StringProperty description;
    
    public RequestStatusDisplay(int id, String description) {
        this.id_request_status = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
    }
    
    public IntegerProperty idRequestStatusProperty() {
        return id_request_status;
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }
}
