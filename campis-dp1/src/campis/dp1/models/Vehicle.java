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
 * @author david
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_vehicle;
    Double max_weight;
    Integer speed;
    boolean active;
    Integer id_warehouse;
    String plate;
    Integer type_vh;
    
    public Vehicle(){
        super();
    }
    
    
    public Vehicle(Integer id_vehicle, Double max_weight, Integer speed, boolean active, Integer id_warehouse, String plate) {
        this.id_vehicle = id_vehicle;
        this.max_weight = max_weight;
        this.speed = speed;
        this.active = active;
        this.id_warehouse = id_warehouse;
        this.plate = plate;
    }
    
    public Vehicle(Double max_weight, Integer speed, boolean active, Integer id_warehouse, String plate) {
        this.max_weight = max_weight;
        this.speed = speed;
        this.active = active;
        this.id_warehouse = id_warehouse;
        this.plate = plate;
    }
    
    public Vehicle(Double max_weight, Integer speed, boolean active, Integer id_warehouse, String plate, int type_vh) {
        this.max_weight = max_weight;
        this.speed = speed;
        this.active = active;
        this.id_warehouse = id_warehouse;
        this.plate = plate;
        this.type_vh = type_vh;
    }

    public Vehicle(Vehicle vh, int i) {
        this.active = vh.active;
        this.id_vehicle = vh.id_vehicle;
        this.id_warehouse = vh.id_warehouse;
        this.max_weight =  vh.max_weight;
        this.plate = vh.plate;
        this.speed = vh.speed;
    }
    
    
    public Integer getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(Integer id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public Double getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(Double max_weight) {
        this.max_weight = max_weight;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getId_warehouse() {
        return id_warehouse;
    }

    public void setId_warehouse(Integer id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    
}


