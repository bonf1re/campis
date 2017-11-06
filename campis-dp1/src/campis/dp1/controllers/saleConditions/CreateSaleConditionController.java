/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.saleConditions;

import campis.dp1.Main;
import campis.dp1.models.Campaign;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import campis.dp1.models.SaleConditionType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
public class CreateSaleConditionController implements Initializable {
    Main main;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private JFXComboBox<String> campaignCB;

    @FXML
    private JFXTextField amountField;

    @FXML
    private JFXDatePicker pckBegin;

    @FXML
    private JFXComboBox<String> typeCB;

    @FXML
    private JFXComboBox<String> objectiveCB;

    @FXML
    private JFXTextField limitField;

    @FXML
    private JFXDatePicker pckEnd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Campaign> list = getCampaigns();
        for (int i = 0; i < list.size(); i++) {
            campaignCB.getItems().addAll(list.get(i).getName());
        }
        List<SaleConditionType> typeList = getConditionTypes(); 
        for (int i = 0; i < typeList.size(); i++) {
            typeCB.getItems().addAll(typeList.get(i).getDescription());
        }
    }    
    
    @FXML
    private void goListSaleConditions() throws IOException{
        main.showListSaleConditions();
    }
    
    
    public static List<SaleConditionType> getConditionTypes() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleConditionType.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("description"),"description"))
                .setResultTransformer(Transformers.aliasToBean(SaleConditionType.class));
        List<SaleConditionType> types = criteria.list();
        return types;
    }
    
    public static List<Campaign> getCampaigns() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Campaign.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("name"),"name"))
                .setResultTransformer(Transformers.aliasToBean(Campaign.class));
        List<Campaign> types = criteria.list();
        return types;
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
        return types;
    }
    
    
    public void loadObjectives(){
        objectiveCB.getItems().clear();
        if (typeCB.getValue().equals("Por producto")) {
            List<Product> list = getProducts();
            for (int i = 0; i < list.size(); i++) {
                objectiveCB.getItems().addAll(list.get(i).getName());
            }
        }else{
            List<ProductType> list = getProductTypes();
            for (int i = 0; i < list.size(); i++) {
                objectiveCB.getItems().addAll(list.get(i).getDescription());
            }            
        }
    }
    
    
    
    
}
