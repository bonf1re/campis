/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1;

/**
 *
 * @author Gina Bustamante
 */
public class rack {
    
    private int id;
    private int num_levels;
    private int num_columns;
    
    private int id_area;
    private int id_warehouse;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the num_levels
     */
    public int getNum_levels() {
        return num_levels;
    }

    /**
     * @param num_levels the num_levels to set
     */
    public void setNum_levels(int num_levels) {
        this.num_levels = num_levels;
    }

    /**
     * @return the num_columns
     */
    public int getNum_columns() {
        return num_columns;
    }

    /**
     * @param num_columns the num_columns to set
     */
    public void setNum_columns(int num_columns) {
        this.num_columns = num_columns;
    }

    /**
     * @return the id_area
     */
    public int getId_area() {
        return id_area;
    }

    /**
     * @param id_area the id_area to set
     */
    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    /**
     * @return the id_warehouse
     */
    public int getId_warehouse() {
        return id_warehouse;
    }

    /**
     * @param id_warehouse the id_warehouse to set
     */
    public void setId_warehouse(int id_warehouse) {
        this.id_warehouse = id_warehouse;
    }
    
}
