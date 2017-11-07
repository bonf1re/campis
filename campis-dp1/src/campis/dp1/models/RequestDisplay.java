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
 * @author Eddy
 */
public class RequestDisplay {
    private final IntegerProperty id_request;
    private final StringProperty status;
    private final FloatProperty total_amount;
    private final StringProperty nom_client;
    private final IntegerProperty priority;
    
    public RequestDisplay(Integer codReq, String nomCli, float totAmount,
                            String status, Integer priority) {
        this.id_request = new SimpleIntegerProperty(codReq);
        this.status = new SimpleStringProperty(status);
        this.total_amount = new SimpleFloatProperty(totAmount);
        this.nom_client = new SimpleStringProperty(nomCli);
        this.priority = new SimpleIntegerProperty(priority);
    }
    
    public IntegerProperty idRequestProperty() {
        return id_request;
    }
    
    public StringProperty statusProperty () {
        return status;
    }
    
    public FloatProperty totalAmountProperty() {
        return total_amount;
    }
    
    public StringProperty nomClient() {
        return nom_client;
    }  
    
    public IntegerProperty priority() {
        return priority;
    }
}
