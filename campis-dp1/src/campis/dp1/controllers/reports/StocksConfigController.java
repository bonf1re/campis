/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.ProductType;
import campis.dp1.models.Warehouse;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class StocksConfigController implements Initializable {

    Main main;
    List<Warehouse> listWarehouses;
    List<ProductType> listProductTypes;
    
    
    
    @FXML
    private Button generateBtn;

    @FXML
    private JFXComboBox<String> typeproductCB;

    @FXML
    private JFXComboBox<String> warehouseCB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeproductCB.setValue("Todos");
        typeproductCB.getItems().add("Todos");
        warehouseCB.setValue("Todos");
        warehouseCB.getItems().add("Todos");
        
        listWarehouses = getWarehouses();
        listProductTypes = getProductTypes();
        
        for (int i = 0; i < listProductTypes.size(); i++) {
                typeproductCB.getItems().add(listProductTypes.get(i).getDescription());
        }
        
        for (int i = 0; i < listWarehouses.size(); i++) {
                warehouseCB.getItems().add(listWarehouses.get(i).getName());
        }
        
    }    
    
    
    public static List<Warehouse> getWarehouses() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Warehouse.class);
        List<Warehouse> whs = criteria.list();
        session.close();
        sessionFactory.close();
        return whs;
    }
    
    
    public static List<ProductType> getProductTypes() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        List<ProductType> types = criteria.list();
        session.close();
        sessionFactory.close();
        return types;
    }
    
    public void setVariables() throws SQLException, ClassNotFoundException {
        
        int id_ptype = typeproductCB.getSelectionModel().getSelectedIndex();
        int id_wh = warehouseCB.getSelectionModel().getSelectedIndex();
        
        if (id_ptype != 0)
            id_ptype = listProductTypes
                        .get(id_ptype -1)
                        .getId_prodType();
        if (id_wh != 0)
            id_wh = listWarehouses
                    .get(id_wh -1)
                    .getId();
        
        
            
       ContextFX.getInstance().setId_type(id_ptype);
       ContextFX.getInstance().setId_objective(id_wh);
       ContextFX.getInstance().setLabeltoPrint1(typeproductCB.getValue());
       ContextFX.getInstance().setLabeltoPrint2(warehouseCB.getValue());
       
        
    }
    
    @FXML
    private void goGenerateReport(ActionEvent event) throws IOException, SQLException, ClassNotFoundException  {
        setVariables();
        main.showGenerateStocksReport();
    }
    
}
