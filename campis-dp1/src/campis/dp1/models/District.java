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
 * @author joseamz
 */
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_district;
    String name;
    Double freight;
    
    public District() {
        super();
    }

    public District(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the description to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id_district
     */
    public int getId_district() {
        return id_district;
    }

    /**
     * @param id_district the id_district to set
     */
    public void setId_district(int id_district) {
        this.id_district = id_district;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }
}
