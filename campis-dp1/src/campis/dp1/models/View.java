/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marco
 */
@Entity
@Table(name = "view")
public class View {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_view;
    String description;
    
    public View() {
        super();
    }

    public View(String description) {
        this.description = description;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id_role
     */
    public int getId_view() {
        return id_view;
    }

    /**
     * @param id_role the id_role to set
     */
    public void setId_view(int id_role) {
        this.id_view = id_role;
    }
}
