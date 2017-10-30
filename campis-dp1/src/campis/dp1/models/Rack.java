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
    private Integer id_rack;
    private Integer id_warehouse;
    private Integer pos_x;
    private Integer pos_y;
    private Integer n_columns;
    private Integer n_floors;
    private Integer orientation;
    
    public Rack(){
        super();
    }
    
    public Rack(Integer id_warehouse, Integer pos_x, Integer pos_y, Integer n_columns, Integer n_floors, Integer orientation) {
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
    public Integer getId_rack() {
        return id_rack;
    }

    /**
     * @param id_rack the id_rack to set
     */
    public void setId_rack(Integer id_rack) {
        this.id_rack = id_rack;
    }

    /**
     * @return the id_warehouse
     */
    public Integer getId_warehouse() {
        return id_warehouse;
    }

    /**
     * @param id_warehouse the id_warehouse to set
     */
    public void setId_warehouse(Integer id_warehouse) {
        this.id_warehouse = id_warehouse;
    }

    /**
     * @return the pos_x
     */
    public Integer getPos_x() {
        return pos_x;
    }

    /**
     * @param pos_x the pos_x to set
     */
    public void setPos_x(Integer pos_x) {
        this.pos_x = pos_x;
    }

    /**
     * @return the pos_y
     */
    public Integer getPos_y() {
        return pos_y;
    }

    /**
     * @param pos_y the pos_y to set
     */
    public void setPos_y(Integer pos_y) {
        this.pos_y = pos_y;
    }

    /**
     * @return the n_columns
     */
    public Integer getN_columns() {
        return n_columns;
    }

    /**
     * @param n_columns the n_columns to set
     */
    public void setN_columns(Integer n_columns) {
        this.n_columns = n_columns;
    }

    /**
     * @return the n_floors
     */
    public Integer getN_floors() {
        return n_floors;
    }

    /**
     * @param n_floors the n_floors to set
     */
    public void setN_floors(Integer n_floors) {
        this.n_floors = n_floors;
    }

    /**
     * @return the orientation
     */
    public Integer getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }
}
