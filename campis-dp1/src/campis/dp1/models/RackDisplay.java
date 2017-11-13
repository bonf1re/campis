/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 *
 * @author Gina Bustamante
 */
public class RackDisplay {
    
    private final IntegerProperty id_rack;
    private final IntegerProperty pos_x;
    private final IntegerProperty pos_y;
    private final IntegerProperty n_columns;
    private final IntegerProperty n_floors;
    private final IntegerProperty orientation;
    
    public RackDisplay(int id_rack, int pos_x, int pos_y, int n_columns, int n_floors, int orientation) {
        this.id_rack = new SimpleIntegerProperty(id_rack);
        this.pos_x =  new SimpleIntegerProperty(pos_x);
        this.pos_y = new SimpleIntegerProperty(pos_y);
        this.n_columns = new SimpleIntegerProperty(n_columns);
        this.n_floors = new SimpleIntegerProperty(n_floors);
        this.orientation = new SimpleIntegerProperty(orientation);
    }
    
    public IntegerProperty id_rackProperty() {
        return id_rack;
    }
    
    public IntegerProperty pos_xProperty() {
        return pos_x;
    }
    
    public IntegerProperty pos_yProperty() {
        return pos_y;
    }
    
    public IntegerProperty n_columnsProperty() {
        return n_columns;
    }
    
    public IntegerProperty n_floorsProperty() {
        return n_floors;
    }
    
    public IntegerProperty orientationProperty() {
        return orientation;
    }
}
