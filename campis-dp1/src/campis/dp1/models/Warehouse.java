/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

/**
 *
 * @author sergio
 */
public class Warehouse {

    
    int id;
    String name;
    int length;
    int width;
    boolean status;

    public Warehouse(int id, String name, int length, int width, boolean status) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.status = status;
    }
    
    public Warehouse(){
        super();
    }

    
    
    public int getId() {
        return id;
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
