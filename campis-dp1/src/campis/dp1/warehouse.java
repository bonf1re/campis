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
    float area;
    boolean status;

    public String getName() {
        return name;
    }

    public float getArea() {
        return area;
    }

    public boolean isStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(float area) {
        if (area<0){
            // time being
            System.out.println("No puede disenhar un  almacen con area negativa");
            return;
        }
        this.area = area;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    public warehouse(String name, float area, boolean status) {
        this.name = name;
        this.area = area;
        this.status = status;
    }
    
    
}
