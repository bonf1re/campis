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
// Intersection between a floor and a column number of a specific rack
// in a specific warehouse
// like the space where you would put something
// please dont cofuse this
// please

@Entity
@Table(name="zone")
public class WarehouseZone {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id_zone;
    int id_warehouse;
    int id_rack;
    int pos_x;
    int pos_y;
    int pos_z;
    boolean free;
    
    public WarehouseZone(){
        super();
    }
    
    public WarehouseZone(int id_warehouse, int id_rack, int pos_x, int pos_y, int pos_z, boolean free) {
        this.id_warehouse = id_warehouse;
        this.id_rack = id_rack;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.pos_z = pos_z;
        this.free = free;
    }

    public WarehouseZone(WarehouseZone zone, int r) {
        this.free = zone.free;
        this.id_rack = zone.id_rack;
        this.id_warehouse = zone.id_warehouse;
        this.id_zone = zone.id_zone;
        this.pos_x = zone.pos_x;
        this.pos_y = zone.pos_y;
        this.pos_z = zone.pos_z;
    }

    public int getId_zone() {
        return id_zone;
    }

    public void setId_zone(int id_zone) {
        this.id_zone = id_zone;
    }

    public int getId_warehouse() {
        return id_warehouse;
    }

    public void setId_warehouse(int id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    public int getId_rack() {
        return id_rack;
    }

    public void setId_rack(int id_rack) {
        this.id_rack = id_rack;
    }

    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getPos_z() {
        return pos_z;
    }

    public void setPos_z(int pos_z) {
        this.pos_z = pos_z;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
