package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.Product;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;

public class productsController {
    private Main main;
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    
    private List<Product> prodList;
    
    public List<Product> getListaProd() {
        return prodList;
    }

    public void setListaProd(List<Product> prodList) {
        this.prodList = prodList;
    }
    
    public void addProd(Product objProd) {
        prodList.add(objProd);
    }
    
    public Product getProductByCod(String cod) {
        Product product = null;
        for(int i=0; i< this.prodList.size(); i++){
            if(this.prodList.get(i).getCodProd().compareTo(cod)==0){
                product = this.prodList.get(i);
                break;
            }
        }
        return product;
    }
    
    public Product getProductByName(String nombre) {
        Product product = null;
        for(int i=0; i< this.prodList.size(); i++){
            if(this.prodList.get(i).getNombre().compareTo(nombre)==0){
                product = this.prodList.get(i);
                break;
            }
        }
        return product;
    }
    
    public void UpdateProduct(Product product) {
        for(int i=0; i<this.prodList.size(); i++){
            if(this.prodList.get(i).getCodProd().compareTo(product.getCodProd())==0){
                this.prodList.add(i, product);
                break;
            }
        }
    }
    
    public void DeleteProduct(String cod) {
        for(int i=0; i< this.prodList.size(); i++){
            if(this.prodList.get(i).getCodProd().compareTo(cod)==0){
                this.prodList.remove(i);
                break;
            }
        }
    }
}
