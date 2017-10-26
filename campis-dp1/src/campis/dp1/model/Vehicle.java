/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.model;

/**
 *
 * @author david
 */
public class Vehicle {
    private int idVehicle;
    private float maxWeight;
    private int maxSpeed;
    private boolean active;
    private String warehouse;
    
    /**
     * @return the max_weight
     */
    public float getMaxWeight() {
        return maxWeight;
    }

    /**
     * @param maxWeight the max_weight to set
     */
    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * @return the max_speed
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the warehouse
     */
    public String getWarehouse() {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    
    /**
     * @return the id_vehicle
     */
    public int getIdVehicle() {
        return idVehicle;
    }

    /**
     * @param idVehicle the id_vehicle to set
     */
    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }
    
    public Vehicle (){
        
    }
    
    public Vehicle(int idVehicle, float maxWeight, int maxSpeed, boolean active, String warehouse) {
        this.idVehicle = idVehicle;
        this.maxWeight = maxWeight;
        this.maxSpeed = maxSpeed;
        this.active = active;
        this.warehouse = warehouse;
    }

    
}
