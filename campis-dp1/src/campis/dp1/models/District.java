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
 * @author david
 */

@Entity
@Table(name="district")
public class District {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
    private Integer id_district;
    private String name;
    private Float freight;

    /**
     * @return the id_district
     */
    public Integer getId_district() {
        return id_district;
    }

    /**
     * @param id_district the id_district to set
     */
    public void setId_district(Integer id_district) {
        this.id_district = id_district;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the freight
     */
    public Float getFreight() {
        return freight;
    }

    /**
     * @param freight the freight to set
     */
    public void setFreight(Float freight) {
        this.freight = freight;
    }
    
    
    public District() {
        super();
    }

    public District(Integer id_district, String name, Float freight) {
        this.id_district = id_district;
        this.name = name;
        this.freight = freight;
    }
    
}
