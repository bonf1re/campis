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
 * @author david
 */
public class CampaignDisplay {
    private final IntegerProperty id_campaign;
    private final StringProperty name;
    private final StringProperty description;

    public IntegerProperty getId_campaign() {
        return id_campaign;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getDescription() {
        return description;
    }

    public StringProperty getInitial_date() {
        return initial_date;
    }

    public StringProperty getEnd_date() {
        return end_date;
    }
    private final StringProperty initial_date;
    private final StringProperty end_date;
    
    
    public CampaignDisplay(IntegerProperty id_campaign, StringProperty name, StringProperty description, StringProperty initial_date, StringProperty end_date) {
        this.id_campaign = id_campaign;
        this.name = name;
        this.description = description;
        this.initial_date = initial_date;
        this.end_date = end_date;
    }
    
    
    public CampaignDisplay(int id_campaign, String name, String description, 
                         String initial_date, String end_date) {
        this.id_campaign = new SimpleIntegerProperty(id_campaign);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.initial_date = new SimpleStringProperty(initial_date);
        this.end_date = new SimpleStringProperty(end_date);
    }
}
