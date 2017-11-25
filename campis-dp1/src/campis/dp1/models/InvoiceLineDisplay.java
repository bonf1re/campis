/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Eddy
 */
public class InvoiceLineDisplay {
    private final IntegerProperty id_invoice_line; 
    private final IntegerProperty id_invoice;
    private final IntegerProperty id_product;
    private final IntegerProperty quantity;
    private final FloatProperty cost;
    private final FloatProperty discount;
    private final IntegerProperty quantity_ro;
    private final FloatProperty final_cost;
    private final StringProperty nameProd;
    private final StringProperty typeProd;
    private final IntegerProperty qtref;
    private final IntegerProperty qtrefmax;
    
    public InvoiceLineDisplay(Integer id_invoice_line, Integer id_invoice, Integer id_product, 
                              String name, String type, Float cost, Float discount,Integer quant_ro, 
                              Float finalcost, Integer quant, Integer qtref, Integer qtrefmax) {
        this.id_invoice_line = new SimpleIntegerProperty(id_invoice_line);
        this.id_invoice = new SimpleIntegerProperty(id_invoice);
        this.id_product = new SimpleIntegerProperty(id_product);
        this.cost = new SimpleFloatProperty(cost);
        this.discount = new SimpleFloatProperty(discount);
        this.quantity_ro = new SimpleIntegerProperty(quant_ro);
        this.final_cost = new SimpleFloatProperty(finalcost);
        this.nameProd = new SimpleStringProperty(name);
        this.typeProd = new SimpleStringProperty(type);
        this.quantity = new SimpleIntegerProperty(quant);
        this.qtref = new SimpleIntegerProperty(qtref);
        this.qtrefmax = new SimpleIntegerProperty(qtrefmax);
    }
    
    public IntegerProperty getId_product() {
        return this.id_product;
    }
    
    public IntegerProperty getId_invoice_line() {
        return this.id_invoice_line;
    }
    
    public IntegerProperty getId_invoice() {
        return this.id_invoice;
    }
    
    public IntegerProperty getQuantity() {
        return this.quantity;
    }
    
    public FloatProperty getCost() {
        return this.cost;
    }
    
    public FloatProperty getDiscount() {
        return this.discount;
    }
    
    public IntegerProperty getQuantityRo() {
        return this.quantity_ro;
    }
    
    public FloatProperty getFinalCost() {
        return this.final_cost;
    }
    
    public StringProperty getNameproduct() {
        return this.nameProd;
    }
    
    public StringProperty getTypeproduct() {
        return this.typeProd;
    }
    
    public IntegerProperty getQtRef() {
        return this.qtref;
    }
    
    public IntegerProperty getQtRefMax() {
        return this.qtrefmax;
    }
}
