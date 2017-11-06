/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sergio
 */
public class BatchWH_Move extends Batch {
    private WarehouseZone zone;
    
    public BatchWH_Move(Batch batch,WarehouseZone zone){
        super(batch.getQuantity(), 
                batch.getBatch_cost(),
                batch.getArrival_date(),
                batch.getExpiration_date(),
                batch.getId_product(),
                batch.getType_batch(),
                batch.getLocation(),
                batch.isState());
        this.zone =  zone;
    }

    public WarehouseZone getZone() {
        return zone;
    }

    public void setZone(WarehouseZone zone) {
        this.zone = zone;
    }
    
    public SimpleStringProperty getZoneStr(){
        String str = "R"+zone.id_rack+"X"+zone.getPos_x()+"Y"+zone.getPos_y()+"Z"+zone.getPos_z();
        return new SimpleStringProperty(str);
    }
    
    

    public BatchWH_Move(){
        super();
    }    
    
}
