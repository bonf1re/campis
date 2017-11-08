/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class AreaDisplay {
    private IntegerProperty id_area;
    private StringProperty name;
    private IntegerProperty length;
    private IntegerProperty width;
    private IntegerProperty pos_x;
    private IntegerProperty pos_y;
    private IntegerProperty id_warehouse;

    public AreaDisplay(Area area) {
        this.id_area = new SimpleIntegerProperty(area.getId_area());
        this.name = new SimpleStringProperty(area.getName());
        this.length = new SimpleIntegerProperty(area.getLength());
        this.width = new SimpleIntegerProperty(area.getWidth());
        this.pos_x = new SimpleIntegerProperty(area.getPos_x());
        this.pos_y = new SimpleIntegerProperty(area.getPos_y());
        this.id_warehouse = new SimpleIntegerProperty(area.getId_warehouse());
    }
    
    public IntegerProperty getId_area() {
        return id_area;
    }

    public StringProperty getName() {
        return name;
    }

    public IntegerProperty getLength() {
        return length;
    }

    public IntegerProperty getWidth() {
        return width;
    }

    public IntegerProperty getPos_x() {
        return pos_x;
    }

    public IntegerProperty getPos_y() {
        return pos_y;
    }

    public IntegerProperty getId_warehouse() {
        return id_warehouse;
    }
}
