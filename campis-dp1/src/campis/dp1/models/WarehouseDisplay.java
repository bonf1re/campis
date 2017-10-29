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
public class WarehouseDisplay {
    private final IntegerProperty id_warehouse;
    private final StringProperty name;
    private final IntegerProperty length;
    private final IntegerProperty width;
    private final IntegerProperty area;
    private final StringProperty status;
    
    public WarehouseDisplay(int id_warehouse, String name, int length, int width, boolean status){
        this.id_warehouse = new SimpleIntegerProperty(id_warehouse);
        this.name = new SimpleStringProperty(name);
        this.length = new SimpleIntegerProperty(length);
        this.width = new SimpleIntegerProperty(width);
        this.area = new SimpleIntegerProperty(length*width);
        if (status==true){
            this.status = new SimpleStringProperty("Habilitado");
        }else{
            this.status = new SimpleStringProperty("Deshabilitado");
        }
    }
    
    public IntegerProperty id_warehouseProperty(){
        return id_warehouse;
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public IntegerProperty lengthProperty(){
        return length;
    } 
    
    public IntegerProperty widthProperty(){
        return width;
    }
    
    public IntegerProperty areaProperty(){
        return area;
    }
    
    public StringProperty statusProperty(){
        return status;
    }
}
