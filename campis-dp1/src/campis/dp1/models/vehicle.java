/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author david
 */
public class vehicle {

    private float max_weight;
    private int max_speed;
    private boolean active;
    private String warehouse;
    
    /**
     * @return the max_weight
     */
    public float getMax_weight() {
        return max_weight;
    }

    /**
     * @param max_weight the max_weight to set
     */
    public void setMax_weight(float max_weight) {
        this.max_weight = max_weight;
    }

    /**
     * @return the max_speed
     */
    public int getMax_speed() {
        return max_speed;
    }

    /**
     * @param max_speed the max_speed to set
     */
    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
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
    
    public vehicle(float max_weight, int max_speed, boolean active, String warehouse) {
        this.max_weight = max_weight;
        this.max_speed = max_speed;
        this.active = active;
        this.warehouse = warehouse;
    }
}
