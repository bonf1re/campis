/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author david
 */

@Entity
@Table (name="campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_campaign ;
    String name;
    String description;
    Timestamp initial_date;
    Timestamp final_date;

    public Integer getId_campaign() {
        return id_campaign;
    }

    public void setId_campaign(Integer id_campaign) {
        this.id_campaign = id_campaign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getInitial_date() {
        return initial_date;
    }

    public void setInitial_date(Timestamp initial_date) {
        this.initial_date = initial_date;
    }

    public Timestamp getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Timestamp final_date) {
        this.final_date = final_date;
    }

    public Campaign(Integer id_campaign, String name, String description, Timestamp initial_date, Timestamp final_date) {
        this.id_campaign = id_campaign;
        this.name = name;
        this.description = description;
        this.initial_date = initial_date;
        this.final_date = final_date;
    }
    
    public Campaign(String name, String description, Timestamp initial_date, Timestamp final_date) {
        this.name = name;
        this.description = description;
        this.initial_date = initial_date;
        this.final_date = final_date;
    }
    
    public Campaign() {
        super();
    }
}
