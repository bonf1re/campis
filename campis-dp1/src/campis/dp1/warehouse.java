/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

/**
 *
 * @author sergio
 */
public class warehouse {
    String name;
    float length;
    float width;
    boolean status;
    
    public warehouse(String name, float length, float width, boolean status) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.status = status;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
    

    public String getName() {
        return name;
    }

 
    public boolean isStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }
}
