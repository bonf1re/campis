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
 * @author sergio
 */
@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_batch;

    
    private int quantity;
    private float batch_cost;
    private Timestamp arrival_date;
    private Timestamp expiration_date;
    private int id_product;
    private int type_batch;
    private String location;
    private boolean state;
    private String heritage="--";
    private int id_supplier;
    
    
    public Batch(){
        super();
    }

    public Batch(int quantity, float batch_cost, Timestamp arrival_date, Timestamp expiration_date, int id_product, int type_batch, String location, boolean state) {
        this.quantity = quantity;
        this.batch_cost = batch_cost;
        this.arrival_date = arrival_date;
        this.expiration_date = expiration_date;
        this.id_product = id_product;
        this.type_batch = type_batch;
        this.location = location;
        this.state = state;
    }
    
    public Batch(BatchDisplay batch){
        super();
        this.quantity = batch.getQuantity().get();
        this.batch_cost = batch.getBatch_cost().get();
        this.arrival_date = Timestamp.valueOf(batch.getArrival_date().get());
        this.expiration_date = Timestamp.valueOf(batch.getExpiration_date().get());
        this.id_product = batch.getId_product().get();
        this.id_batch = batch.getId_batch().get();
        this.type_batch = batch.getType_batch().get();
        this.location = batch.getLocation().get();
        this.state = batch.getState().get() == "true";
    }

    public Batch(BatchWH_Move get) {
        this.quantity = get.getQuantity();
        this.batch_cost = get.getBatch_cost();
        this.arrival_date = get.getArrival_date();
        this.expiration_date = get.getExpiration_date();
        this.id_product = get.getId_product();
        this.type_batch = get.getType_batch();
        this.location = get.getLocation();
        this.state = get.isState();
    }

    public Batch(Batch batch) {
        this.arrival_date = batch.arrival_date;
        this.batch_cost = batch.batch_cost;
        this.expiration_date = batch.expiration_date;
        this.heritage = batch.heritage;
        this.id_product = batch.id_product;
        this.location = batch.location;
        this.quantity = batch.quantity;
        this.state = batch.state;
        this.type_batch = batch.type_batch;
        this.id_supplier = batch.getId_supplier();
    }
    
    public Batch(Batch batch, int r) {
        this.arrival_date = batch.arrival_date;
        this.batch_cost = batch.batch_cost;
        this.expiration_date = batch.expiration_date;
        this.heritage = batch.heritage;
        this.id_product = batch.id_product;
        this.location = batch.location;
        this.quantity = batch.quantity;
        this.state = batch.state;
        this.type_batch = batch.type_batch;
        this.id_batch=batch.id_batch;
    }
    
    
    public int getId_batch() {
        return id_batch;
    }

    public void setId_batch(int id_batch) {
        this.id_batch = id_batch;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getBatch_cost() {
        return batch_cost;
    }

    public void setBatch_cost(float batch_cost) {
        this.batch_cost = batch_cost;
    }

    public Timestamp getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Timestamp arrival_date) {
        this.arrival_date = arrival_date;
    }

    public Timestamp getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Timestamp expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getType_batch() {
        return type_batch;
    }

    public void setType_batch(int type_batch) {
        this.type_batch = type_batch;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getHeritage() {
        return heritage;
    }

    public void setHeritage(String heritage) {
        this.heritage = heritage;
    }
    
    public void printBatch(){
        System.out.println("Batch with id: "+this.id_batch);
        System.out.println("-Id Product: "+this.id_product);
        System.out.println("-State: "+this.state);
        System.out.println("-Heritage: "+this.heritage);
    }
    
    /**
     * @return the id_supplier
     */
    public int getId_supplier() {
        return id_supplier;
    }

    /**
     * @param id_supplier the id_supplier to set
     */
    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }
}
