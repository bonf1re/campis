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
@Table(name = "dispatch_order_line")
public class DispatchOrderLine {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_dispatch_order_line;
    Integer id_dispatch_order;
    Integer id_product;
    Integer quantity;
    
    public DispatchOrderLine() {
        super();
    }
    
    public DispatchOrderLine(int id_dispatch_order, int id_prod, int quantity) {
        this.id_dispatch_order = id_dispatch_order;
        this.id_product = id_prod;
        this.quantity = quantity;
    }
    
    public DispatchOrderLine(int id_dispatch_order_line,int id_dispatch_order, int id_prod, int quantity) {
        this.id_dispatch_order_line = id_dispatch_order_line;
        this.id_dispatch_order = id_dispatch_order;
        this.id_product = id_prod;
        this.quantity = quantity;
    }
    
    public Integer getId_dispatch_order_line() {
        return id_dispatch_order_line;
    }

    public void setId_dispatch_order_line(Integer id_dispatch_order_line) {
        this.id_dispatch_order_line = id_dispatch_order_line;
    }

    public Integer getId_dispatch_order() {
        return id_dispatch_order;
    }

    public void setId_dispatch_order(Integer id_dispatch_order) {
        this.id_dispatch_order = id_dispatch_order;
    }

    public Integer getId_prod() {
        return id_product;
    }

    public void setId_prod(Integer id_prod) {
        this.id_product = id_prod;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}
