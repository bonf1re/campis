/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import java.sql.Timestamp;
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
@Table(name = "movement")
public class WarehouseMove {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_movement;
    private Timestamp mov_date;
    private int id_user;
    private int quantity;
    private  int id_zone;
    private int id_vehicle;
    private int mov_type;
    private int id_warehouse;
    
    public WarehouseMove(){
        super();
    }

    public WarehouseMove(Timestamp mov_date, int id_user, int quantity, int id_zone, int id_vehicle, int mov_type, int id_warehouse) {
        this.mov_date = mov_date;
        this.id_user = id_user;
        this.quantity = quantity;
        this.id_zone = id_zone;
        this.id_vehicle = id_vehicle;
        this.mov_type = mov_type;
        this.id_warehouse = id_warehouse;
    }

    public int getId_warehouse() {
        return id_warehouse;
    }

    public void setId_warehouse(int id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    
    public int getId_movement() {
        return id_movement;
    }

    public void setId_movement(int id_movement) {
        this.id_movement = id_movement;
    }

    public Timestamp getMov_date() {
        return mov_date;
    }

    public void setMov_date(Timestamp mov_date) {
        this.mov_date = mov_date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_zone() {
        return id_zone;
    }

    public void setId_zone(int id_zone) {
        this.id_zone = id_zone;
    }

    public int getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public int getMov_type() {
        return mov_type;
    }

    public void setMov_type(int mov_type) {
        this.mov_type = mov_type;
    }
    
    
    
}
