/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author Gina Bustamante
 */
public class Delivery {
    
    private Integer id_delivery;
    private String location;
    private String vh_plate;
    private Integer id_dispatch_order;
    private Boolean invoiced;
    
    /**
     * @return the id_delivery
     */
    public Integer getId_delivery() {
        return id_delivery;
    }

    /**
     * @param id_delivery the id_delivery to set
     */
    public void setId_delivery(Integer id_delivery) {
        this.id_delivery = id_delivery;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the vh_plate
     */
    public String getVh_plate() {
        return vh_plate;
    }

    /**
     * @param vh_plate the vh_plate to set
     */
    public void setVh_plate(String vh_plate) {
        this.vh_plate = vh_plate;
    }

    /**
     * @return the id_dispatch_order
     */
    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    /**
     * @param id_dispatch_order the id_dispatch_order to set
     */
    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }
    
    /**
     * @return the invoiced
     */
    public Boolean getInvoiced() {
        return invoiced;
    }

    /**
     * @param invoiced the invoiced to set
     */
    public void setInvoiced(Boolean invoiced) {
        this.invoiced = invoiced;
    }
    
    public Delivery(Integer id, String address, String vh_plate, Integer id_do){
        this.id_delivery  = id;
        this.location = address;
        this.vh_plate = vh_plate;
        this.id_dispatch_order = id_do;  
    }
    
    public Delivery(String address, String vh_plate, Integer id_do){
        this.location = address;
        this.vh_plate = vh_plate;
        this.id_dispatch_order = id_do;  
        this.invoiced = false;
    }
    
    public Delivery(){
        super();
    }
   
            
    
}
