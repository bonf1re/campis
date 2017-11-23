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
 * @author sergio
 */
public class RequestOrderLineDisplay extends RequestOrderLine{

    private String prodName;
    private final IntegerProperty dspQt;
    private Integer missQt;
    private String typeName;
    
    public RequestOrderLineDisplay(RequestOrderLine rqOrdLine, String name, String type_name, int dspQt, int missQt){
        this.setCost(rqOrdLine.getCost());
        this.setId_product(rqOrdLine.getId_product());
        this.setId_request_order(rqOrdLine.getId_request_order());
        this.setId_request_order_line(rqOrdLine.getId_request_order_line());
        this.setQuantity(rqOrdLine.getQuantity());
        this.prodName = new String(name);
        this.typeName = type_name;
       this.dspQt =  new SimpleIntegerProperty(dspQt);
       this.missQt = missQt;
    }

    public IntegerProperty getDspQt() {
        return dspQt;
    }
    
    public String getProdName(){
        return new String(prodName);
    }

    public Integer getMissQt() {
        return missQt;
    }
    
    public String getTypeName(){
        return new String(this.typeName);
    }
}
