/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eddy
 */
@Entity
@Table(name = "dispatch_order")
public class DispatchOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_dispatch_order;
    Integer id_request_order;
    Integer priority;
    String status;
    
    public DispatchOrder() {
        super();
    }
    
    public DispatchOrder(int id_dispatch_order, int id_request_order, int priority, String status) {
        this.id_dispatch_order = id_dispatch_order;
        this.id_request_order = id_request_order;
        this.priority = priority;
        this.status = status;
    }
    
    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }

    public Integer getId_request_order() {
        return id_request_order;
    }

    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
