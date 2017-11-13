/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author david
 */
public class ReportStocksDisplay {
    private final StringProperty product;
    private final StringProperty unitM;
    private final StringProperty productType;
    private final IntegerProperty cstock;
    private final IntegerProperty pstock;
    private final StringProperty warehouse;
    
    public ReportStocksDisplay(String product, String unitM, String productType,
                               Integer cstock, Integer pstock, String warehouse) {
  
        this.product = new SimpleStringProperty(product);
        this.unitM = new SimpleStringProperty(unitM);
        this.productType = new SimpleStringProperty(productType);
        this.cstock = new SimpleIntegerProperty(cstock);
        this.pstock = new SimpleIntegerProperty(pstock);
        this.warehouse = new SimpleStringProperty(warehouse);

    }

    /**
     * @return the product
     */
    public StringProperty getProduct() {
        return product;
    }

    /**
     * @return the unitM
     */
    public StringProperty getUnitM() {
        return unitM;
    }

    /**
     * @return the productType
     */
    public StringProperty getProductType() {
        return productType;
    }

    /**
     * @return the cstock
     */
    public IntegerProperty getCstock() {
        return cstock;
    }

    /**
     * @return the pstock
     */
    public IntegerProperty getPstock() {
        return pstock;
    }

    /**
     * @return the warehouse
     */
    public StringProperty getWarehouse() {
        return warehouse;
    }
    
}
