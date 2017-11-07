/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

/**
 * FXML Controller class
 *
 * @author david
 */
public class KardexConfController implements Initializable {

    
    Main main;
    int id_campaign;
    int id_type;
    int id_objective;
    List<Product> listProducts;
    List<ProductType> listProductTypes;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button generateBtn;

    @FXML
    private JFXDatePicker pckBegin;

    @FXML
    private JFXComboBox<String> typeCB;

    @FXML
    private JFXComboBox<String> objectiveCB;

    @FXML
    private JFXDatePicker pckEnd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCB.getItems().addAll("Todos los productos","Por tipo de producto","Por Producto");
        listProducts = getProducts();
        listProductTypes = getProductTypes();

    }

    public static List<Product> getProducts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("name"),"name"))
                .setResultTransformer(Transformers.aliasToBean(Product.class));
        List<Product> ps = criteria.list();
        session.close();
        sessionFactory.close();
        return ps;
    }
    
    
    public static List<ProductType> getProductTypes() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("description"),"description"))
                .setResultTransformer(Transformers.aliasToBean(ProductType.class));
        List<ProductType> types = criteria.list();
        session.close();
        sessionFactory.close();
        return types;
    }
    
    public void loadObjectives(){
        objectiveCB.getItems().clear();
        if (typeCB.getSelectionModel().getSelectedIndex() == 1) {
            for (int i = 0; i < listProductTypes.size(); i++) {
                objectiveCB.getItems().addAll(listProductTypes.get(i).getDescription());
            }
        }else if (typeCB.getSelectionModel().getSelectedIndex() == 2){
            for (int i = 0; i < listProducts.size(); i++) {
                objectiveCB.getItems().addAll(listProducts.get(i).getName());
            }            
        }
    }
    
}
