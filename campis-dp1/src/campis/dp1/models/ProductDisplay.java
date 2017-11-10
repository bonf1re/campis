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
public class ProductDisplay {
    
    private final IntegerProperty id_product;
    private final StringProperty name;
    private final StringProperty description;
    private final IntegerProperty p_stock;
    private final IntegerProperty c_stock;
    private final FloatProperty weight;
    private final StringProperty trademark;
    private final FloatProperty base_price;
    private final IntegerProperty id_unit_of_measure;
    private final IntegerProperty id_product_type;
    private final IntegerProperty max_qt;
    
    public ProductDisplay(Integer codProd, String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base, int medida, int type, int max_qt) {
        this.id_product = new SimpleIntegerProperty(codProd);
        this.name = new SimpleStringProperty(nombre);
        this.description = new SimpleStringProperty(descripcion);
        this.p_stock = new SimpleIntegerProperty(phy_stock);
        this.c_stock = new SimpleIntegerProperty(comm_stock);
        this.weight = new SimpleFloatProperty(peso);
        this.trademark = new SimpleStringProperty(marca);
        this.base_price = new SimpleFloatProperty(precio_base);
        this.id_unit_of_measure = new SimpleIntegerProperty(medida);
        this.id_product_type = new SimpleIntegerProperty(type);
        this.max_qt = new SimpleIntegerProperty(max_qt);
    }
    
    public IntegerProperty codProdProperty() {
        return id_product;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public StringProperty descripProperty() {
        return description;
    }
    
    public IntegerProperty pStockProperty() {
        return p_stock;
    }
    
    public IntegerProperty cStockProperty() {
        return c_stock;
    }
    
    public FloatProperty pesoProperty() {
        return weight;
    }
    
    public StringProperty marcaProperty() {
        return trademark;
    }
    
    public FloatProperty precioBProperty() {
        return base_price;
    }
    
    public IntegerProperty medidaProperty() {
        return id_unit_of_measure;
    }
    
    public IntegerProperty typeProperty() {
        return id_product_type;
    }
    
    public IntegerProperty max_qtProperty() {
        return max_qt;
    }
}
