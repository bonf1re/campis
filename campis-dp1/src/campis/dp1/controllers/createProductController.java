/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers;

import campis.dp1.Main;
import campis.dp1.model.ProductDAO;
import campis.dp1.model.ProductType;
import campis.dp1.model.UnitOfMeasure;
import campis.dp1.util.DBUtil;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Eddy
 */
public class createProductController {
    private Main main;
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField weightField;
    @FXML
    private JFXComboBox measureField;
    @FXML
    private JFXTextField trademarkField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXComboBox typeField;
    @FXML
    private JFXTextArea descripField;
    
    @FXML
    private void goListProduct() throws IOException{
        main.showListProduct();
    }
    
    private void goCreateProduct(ActionEvent event) throws IOException{
        main.showCreateProduct();
    }
    
    public static int searchCodMeasure(String measure) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT id_unit_of_measure FROM campis.unit_of_measure WHERE  description='" + measure +"';";
        try {
            int codMeasure;
            UnitOfMeasure meas = null;
            ResultSet rsMeasure = DBUtil.dbExecuteQuery(selectStmt);
            while(rsMeasure.next()){
                meas = new UnitOfMeasure();
                meas.setId_measure(rsMeasure.getInt(1));
            }
            codMeasure = meas.getId_measure();
            return codMeasure;
        } catch (SQLException e) {
            System.out.print("No se encuentra la unidad de medida con esa descripcion, error ocurrido: " + e);
            throw e;
        }
    }
    
    public static String searchCodType(String type) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT DISTINCT id_product_type FROM campis.product_type WHERE  description='" + type +"';";
        try {
            String codType = null;
            ProductType typ = null;
            ResultSet rsType = DBUtil.dbExecuteQuery(selectStmt);
            while(rsType.next()){
                typ = new ProductType();
                typ.setId_prodType(rsType.getString(1));
            }
            codType = typ.getId_prodType();
            return codType;
        } catch (SQLException e) {
            System.out.print("No se encuentra la unidad de medida con esa descripcion, error ocurrido: " + e);
            throw e;
        }
    }
    
    @FXML
    private void insertProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        try{
            int measure = searchCodMeasure(measureField.getEditor().getText());
            String type = searchCodType(typeField.getEditor().getText());
            ProductDAO.insertProduct(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                     trademarkField.getText(), Float.parseFloat(priceField.getText()), measure, type);
            System.out.println("Product inserted! \n");
            this.goListProduct();
        }catch (SQLException e){
            System.out.println("Error al insertar producto : " + e);
        }
    }
}
