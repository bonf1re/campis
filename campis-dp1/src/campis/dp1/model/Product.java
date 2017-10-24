/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.model;

import javafx.beans.property.StringProperty;

/**
 *
 * @author Eddy
 */
public class Product {
    String codProd;
    String nombre;
    String descripcion;
    int phy_stock;
    int comm_stock;
    float peso;
    String marca;
    float precio_base;
    int id_medida;
    String id_type;

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPhy_stock() {
        return phy_stock;
    }

    public void setPhy_stock(int phy_stock) {
        this.phy_stock = phy_stock;
    }

    public int getComm_stock() {
        return comm_stock;
    }

    public void setComm_stock(int comm_stock) {
        this.comm_stock = comm_stock;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(float precio_base) {
        this.precio_base = precio_base;
    }

    public int getId_medida() {
        return id_medida;
    }

    public void setId_medida(int id_medida) {
        this.id_medida = id_medida;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }
    
    public Product(){
        this.codProd = null;
        this.nombre = null;
        this.descripcion = null;
        this.phy_stock = 0;
        this.comm_stock = 0;
        this.peso = 0;
        this.marca = null;
        this.precio_base = 0;
    }
    
    public Product(String codProd, String nombre, String descripcion, int phy_stock, int comm_stock,
                    float peso, String marca, float precio_base) {
        this.codProd = codProd;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.phy_stock = phy_stock;
        this.comm_stock = comm_stock;
        this.peso = peso;
        this.marca = marca;
        this.precio_base = precio_base;
    }
}
