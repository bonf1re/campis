/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author Eddy
 */
public class UnitOfMeasure {
    int id_measure;
    String descrip;

    public int getId_measure() {
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
}
