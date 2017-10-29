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
 * @author Gina Bustamante
 */
@Entity
@Table(name = "rack")
public class Rack {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_rack;
    private int id_warehouse;
    private int pos_x;
    private int pos_y;
    private int n_columns;
    private int n_floors;
    private int orientation;
    
    public Rack(){
        super();
    }
    
    public Rack(int id_warehouse, int pos_x, int pos_y, int n_columns, int n_floors, int orientation) {
        this.id_warehouse = id_warehouse;
        this.pos_x =  pos_x;
        this.pos_y = pos_y;
        this.n_columns = n_columns;
        this.n_floors = n_floors;
        this.orientation = orientation;
    }

    /**
     * @return the id_rack
     */
    public int getId_rack() {
        return id_rack;
    }

    /**
     * @param id_rack the id_rack to set
     */
    public void setId_rack(int id_rack) {
        this.id_rack = id_rack;
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

    /**
     * @return the pos_x
     */
    public int getPos_x() {
        return pos_x;
    }

    /**
     * @param pos_x the pos_x to set
     */
    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    /**
     * @return the pos_y
     */
    public int getPos_y() {
        return pos_y;
    }

    /**
     * @param pos_y the pos_y to set
     */
    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    /**
     * @return the n_columns
     */
    public int getN_columns() {
        return n_columns;
    }

    /**
     * @param n_columns the n_columns to set
     */
    public void setN_columns(int n_columns) {
        this.n_columns = n_columns;
    }

    /**
     * @return the n_floors
     */
    public int getN_floors() {
        return n_floors;
    }

    /**
     * @param n_floors the n_floors to set
     */
    public void setN_floors(int n_floors) {
        this.n_floors = n_floors;
    }

    /**
     * @return the orientation
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
