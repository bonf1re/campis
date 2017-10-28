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
 * @author Eddy
 */
@Entity
@Table (name = "unit_of_measure")
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_unit_of_measure;
    String description;

    public Integer getId_unit_of_measure() {
        return id_unit_of_measure;
    }

    public void setId_measure(int id_measure) {
        this.id_unit_of_measure = id_measure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public UnitOfMeasure() {
        this.id_unit_of_measure = 0;
        this.description = null;
    }
    
    public UnitOfMeasure(int id, String descripcion) {
        this.id_unit_of_measure = id;
        this.description = descripcion;
    }
    
    public UnitOfMeasure(String descripcion) {
        this.id_unit_of_measure = null;
        this.description = descripcion;
    }
}
