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
 * @author sergio
 */
@Entity
@Table(name="warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_warehouse;
    String name;
    int length;
    int width;
    boolean status;

    public Warehouse(String name, int length, int width, boolean status) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.status = status;
    }
    
    public int getId() {
        return id_warehouse;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public Warehouse(){
        super();
    }

    public Warehouse(int id, String name, int length, int width, boolean status) {
        this.id_warehouse = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.status = status;
    }
    
    public String toString(){
        return new String(this.name);
    }
    
    
}
