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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_role;
    String description;
    
    public Role() {
        super();
    }

    public Role(String description) {
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
    public int getId_role() {
        return id_role;
    }

    /**
     * @param id_role the id_role to set
     */
    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
