/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author sergio
 */
public class ProductWH_Move extends Product{
    private final IntegerProperty qtLt;
    private final IntegerProperty num;
    private final IntegerProperty cant;
    private final IntegerProperty stock;
    
    public ProductWH_Move(Product result, Integer num) {
        this.setId_product(result.getId_product());
        this.setId_product_type(result.getId_product_type());
        this.setId_unit_of_measure(result.getId_unit_of_measure());
        this.setBase_price(result.getBase_price());
        this.setC_stock(result.getC_stock());
        this.setDescription(result.getDescription());
        this.setName(result.getName());
        this.setP_stock(result.getP_stock());
        this.setTrademark(result.getTrademark());
        this.setWeight(result.getWeight());
        this.setMax_qt(result.getMax_qt());
        this.qtLt = new SimpleIntegerProperty(num);
        this.num = new SimpleIntegerProperty(1);
        this.cant = new SimpleIntegerProperty(-1);
        this.stock = new SimpleIntegerProperty(-1);
    }
    
    public ProductWH_Move(Product result, Integer cant, Integer stock_s) {
        this.setId_product(result.getId_product());
        this.setId_product_type(result.getId_product_type());
        this.setId_unit_of_measure(result.getId_unit_of_measure());
        this.setBase_price(result.getBase_price());
        this.setC_stock(result.getC_stock());
        this.setDescription(result.getDescription());
        this.setName(result.getName());
        this.setP_stock(result.getP_stock());
        this.setTrademark(result.getTrademark());
        this.setWeight(result.getWeight());
        this.setMax_qt(result.getMax_qt());
        this.qtLt = new SimpleIntegerProperty(-1);
        this.num = new SimpleIntegerProperty(-1);
        this.cant = new SimpleIntegerProperty(cant);
        this.stock = new SimpleIntegerProperty(stock_s);
    }
    

    public IntegerProperty getQtLt() {
        return this.qtLt;
    }

    public IntegerProperty getNum() {
        return this.num;
    }
    
    public IntegerProperty getCant() {
        return cant;
    }

    public IntegerProperty getStock() {
        return stock;
    }
}
