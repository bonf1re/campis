/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Eddy
 */
@Entity
@Table (name="request_order")

public class RequestOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_request_order;
    Timestamp creation_date;
    Timestamp delivery_date;
    String status;
    Float base_amount;
    Float total_amount;
    Integer id_client;
    
    public RequestOrder() {
        super();
    }
    
    public RequestOrder(Timestamp creation_date, Timestamp delivery_date,
                        float base_amount, float total_amount, String status, int id_client) {
        this.creation_date = creation_date;
        this.delivery_date = delivery_date;
        this.status = status;
        this.base_amount = base_amount;
        this.total_amount = total_amount;
        this.id_client = id_client;
    }
    
    public Integer getId_request_order() {
        return id_request_order;
    }

    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(Float base_amount) {
        this.base_amount = base_amount;
    }

    public Float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }
    
}
