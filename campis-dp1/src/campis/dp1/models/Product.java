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
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Eddy
 */
public class Product {
    private final StringProperty codProd;
    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final IntegerProperty phy_stock;
    private final IntegerProperty comm_stock;
    private final FloatProperty peso;
    private final StringProperty marca;
    private final FloatProperty precio_base;
    private final IntegerProperty id_medida;
    private final StringProperty id_type;
    
    public Product(String codProd, String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base, int medida, String type) {
        this.codProd = new SimpleStringProperty(codProd);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.phy_stock = new SimpleIntegerProperty(phy_stock);
        this.comm_stock = new SimpleIntegerProperty(comm_stock);
        this.peso = new SimpleFloatProperty(peso);
        this.marca = new SimpleStringProperty(marca);
        this.precio_base = new SimpleFloatProperty(precio_base);
        this.id_medida = new SimpleIntegerProperty(medida);
        this.id_type = new SimpleStringProperty(type);
    }
    
    public String getCodProd() {
        return codProd.get();
    }
    
    public String getName() {
        return nombre.get();
    }
    
    public String getDescrip() {
        return descripcion.get();
    }
    
    public int getPStock() {
        return phy_stock.get();
    }
    
    public int getCStock() {
        return comm_stock.get();
    }
    
    public float getPeso() {
        return peso.get();
    }
    
    public String getMarca() {
        return marca.get();
    }
    
    public float getPrecioB() {
        return precio_base.get();
    }
    
    public int getCodMed() {
        return id_medida.get();
    }
    
    public String getCodType() {
        return id_type.get();
    }
    
    public void setCodProd(String cod) {
        codProd.set(cod);
    }
    
    public void setName(String nomb) {
        nombre.set(nomb);
    }
    
    public void setDescrip(String descrip) {
        descripcion.set(descrip);
    }
    
    public void setPStock(int pstock) {
        phy_stock.set(pstock);
    }
    
    public void setCStock(int cstock) {
        comm_stock.set(cstock);
    }
    
    public void setPeso(float peso) {
        this.peso.set(peso);
    }
    
    public void setMarca(String marc) {
        marca.set(marc);
    }
    
    public void setPrecioB(float preciob) {
        precio_base.set(preciob);
    }
    
    public void setCodMed(int codMed) {
        id_medida.set(codMed);
    }
    
    public void setCodType(String codType) {
        id_type.set(codType);
    }
    
    public StringProperty codProdProperty() {
        return codProd;
    }
    
    public StringProperty nameProperty() {
        return nombre;
    }
    
    public StringProperty descripProperty() {
        return descripcion;
    }
    
    public IntegerProperty pStockProperty() {
        return phy_stock;
    }
    
    public IntegerProperty cStockProperty() {
        return comm_stock;
    }
    
    public FloatProperty pesoProperty() {
        return peso;
    }
    
    public StringProperty marcaProperty() {
        return marca;
    }
    
    public FloatProperty precioBProperty() {
        return precio_base;
    }
    
    public IntegerProperty medidaProperty() {
        return id_medida;
    }
    
    public StringProperty typeProperty() {
        return id_type;
    }
}
