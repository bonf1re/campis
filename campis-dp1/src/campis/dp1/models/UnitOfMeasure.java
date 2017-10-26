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
    Integer id_measure;
    String descrip;

    public Integer getId_measure() {
        return id_measure;
    }

    public void setId_measure(int id_measure) {
        this.id_measure = id_measure;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    
    public UnitOfMeasure() {
        this.id_measure = 0;
        this.descrip = null;
    }
    
    public UnitOfMeasure(int id, String descripcion) {
        this.id_measure = id;
        this.descrip = descripcion;
    }
    
    public UnitOfMeasure(String descripcion) {
        this.descrip = descripcion;
    }
}
