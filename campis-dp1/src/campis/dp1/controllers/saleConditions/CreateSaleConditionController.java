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
import campis.dp1.models.SaleCondition;
import campis.dp1.models.SaleConditionType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateSaleConditionController implements Initializable {
    Main main;
    int id_campaign;
    int id_type;
    int id_objective;
    List<Product> listProducts;
    List<ProductType> listProductTypes;
    List<Campaign> listCampaigns;
    List<SaleConditionType> listSaleConditionTypes;
    
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

    @FXML
    private Label campaignCBMessage;

    @FXML
    private Label amountFieldMessage;

    @FXML
    private Label pckBeginMessage;

    @FXML
    private Label typeCBMessage;

    @FXML
    private Label objectiveCBMessage;

    @FXML
    private Label limitFieldMessage;

    @FXML
    private Label pckEndMessage;

    public boolean validation() {
        boolean campaignCBValid = campaignCB.getValue() == null;
        boolean amountFieldValid = amountField.getText().length() == 0;
        boolean pckBeginValid = pckBegin.getValue() == null;
        boolean typeCBValid = typeCB.getValue() == null;
        boolean objectiveCBValid = objectiveCB.getValue() == null;
        boolean limitFieldValid = limitField.getText().length() == 0;
        boolean pckEndValid = pckEnd.getValue() == null;
        
        campaignCBMessage.setText("");
        amountFieldMessage.setText("");
        pckBeginMessage.setText("");
        typeCBMessage.setText("");
        objectiveCBMessage.setText("");
        limitFieldMessage.setText("");
        pckEndMessage.setText("");

        if (campaignCBValid)
            campaignCBMessage.setText("Campo obligatorio");
        if (amountFieldValid)
            amountFieldMessage.setText("Campo obligatorio");
        if (pckBeginValid)
            pckBeginMessage.setText("Campo obligatorio");
        if(typeCBValid)
            typeCBMessage.setText("Campo obligatorio");
        if (objectiveCBValid)
            objectiveCBMessage.setText("Campo obligatorio");
        if (limitFieldValid)
            limitFieldMessage.setText("Campo obligatorio");
        if(pckEndValid)
            pckEndMessage.setText("Campo obligatorio");

        return (!campaignCBValid && !amountFieldValid && !pckBeginValid && !typeCBValid && !objectiveCBValid && !limitFieldValid && !pckEndValid);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCampaigns = getCampaigns();
        listSaleConditionTypes = getConditionTypes();
        listProducts = getProducts();
        listProductTypes = getProductTypes();
        for (int i = 0; i < listCampaigns.size(); i++) {
            campaignCB.getItems().addAll(listCampaigns.get(i).getName());
        }
        for (int i = 0; i < listSaleConditionTypes.size(); i++) {
            typeCB.getItems().addAll(listSaleConditionTypes.get(i).getDescription());
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
        session.close();
        sessionFactory.close();
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
        session.close();
        sessionFactory.close();
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
        if (typeCB.getValue().equals("Por producto")) {
            for (int i = 0; i < listProducts.size(); i++) {
                objectiveCB.getItems().addAll(listProducts.get(i).getName());
            }
        }else{
            for (int i = 0; i < listProductTypes.size(); i++) {
                objectiveCB.getItems().addAll(listProductTypes.get(i).getDescription());
            }            
        }
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue()-1,
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
    public static Integer searchCodCampaign(String campaign) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Campaign.class);
        criteria.add(Restrictions.eq("name",campaign));
        Integer cod;
        List rsCampaign = criteria.list();
        Campaign result = (Campaign)rsCampaign.get(0);
        cod = result.getId_campaign();
        session.close();
        sessionFactory.close();
        return cod;
    }
    
    public static Integer searchCodSaleConditionType(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleConditionType.class);
        criteria.add(Restrictions.eq("description",type));
        Integer cod;
        List rsSCT = criteria.list();
        SaleConditionType result = (SaleConditionType)rsSCT.get(0);
        cod = result.getId_sale_condition_type();
        session.close();
        sessionFactory.close();
        return cod;
    }
    
    
    public static Integer searchCodProduct(String product) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("name",product));
        Integer cod;
        List rsProduct = criteria.list();
        Product result = (Product)rsProduct.get(0);
        cod = result.getId_product();
        session.close();
        sessionFactory.close();
        return cod;
    }
    
    public static Integer searchCodProductType(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        criteria.add(Restrictions.eq("description",type));
        Integer cod;
        List rsProductT = criteria.list();
        ProductType result = (ProductType)rsProductT.get(0);
        cod = result.getId_prodType();
        session.close();
        sessionFactory.close();
        return cod;
    }
    
    
    public void setIds (String campaign, String type, String objective) throws SQLException, ClassNotFoundException{
        id_campaign = searchCodCampaign(campaign);
        id_type = searchCodSaleConditionType(type);
        if (id_type == 1)
            id_objective = searchCodProduct(objective);
        else
            id_objective = searchCodProductType(objective);
    }
    
    
    @FXML
    private void insertSaleCondition(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_init = getDate(pckBegin.getValue());
            Date date_end = getDate(pckEnd.getValue());
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            setIds(campaignCB.getValue(),typeCB.getValue(),objectiveCB.getValue());
            
            
            SaleCondition sc = new SaleCondition(Timestamp.valueOf(formatIn.format(date_init)),
                                                 Timestamp.valueOf(formatIn.format(date_end)),
                                                 Float.parseFloat(amountField.getText()),
                                                 id_type,
                                                 Integer.parseInt(limitField.getText()),
                                                 id_objective,
                                                 id_campaign);
            
            
            session.save(sc);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close(); 
            this.goListSaleConditions();
        }
    }
}