/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author sergio
 */
public class BatchWH_Move extends Batch {
    private IntegerProperty pos_x;
    private IntegerProperty pos_y;

    public IntegerProperty getPos_x() {
        return pos_x;
    }

    public IntegerProperty getPos_y() {
        return pos_y;
    }
    
    public BatchWH_Move(Batch batch){
        super(batch.getQuantity(), 
                batch.getBatch_cost(),
                batch.getArrival_date(),
                batch.getExpiration_date(),
                batch.getId_product(),
                batch.getType_batch(),
                batch.getLocation(),
                batch.isState());
        pos_x=new SimpleIntegerProperty(-1);
        pos_y=new SimpleIntegerProperty(-1);
    }

    public BatchWH_Move(){
        super();
    }    
    
}
