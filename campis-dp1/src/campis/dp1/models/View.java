/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Marco
 */
public class View {
    String nombre;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public View(String nombre) {
        this.nombre = nombre;
    }
}
