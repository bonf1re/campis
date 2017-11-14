/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.saleConditions;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.saleConditions.CreateSaleConditionController.getCampaigns;
import static campis.dp1.controllers.saleConditions.CreateSaleConditionController.getConditionTypes;
import static campis.dp1.controllers.saleConditions.CreateSaleConditionController.getProductTypes;
import static campis.dp1.controllers.saleConditions.CreateSaleConditionController.getProducts;
import campis.dp1.models.Campaign;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import campis.dp1.models.SaleConditionType;
import campis.dp1.models.SaleCondition;

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
import javafx.scene.control.RadioButton;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class EditSaleConditionController implements Initializable {
    Main main;
    Integer id;
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
    
    @FXML
    private RadioButton brnPromo;

    @FXML
    private JFXTextField disLabel;

    @FXML
    private JFXTextField countLabel;

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
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        id = ContextFX.getInstance().getId();
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
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        criteria.add(Restrictions.eq("id_sale_condition",id));
        List rsType = criteria.list();
        SaleCondition result = (SaleCondition)rsType.get(0);
        
        this.limitField.setText(result.getLimits().toString());
        this.typeCB.setValue(getType(result.getId_sale_condition_type()));
        this.campaignCB.setValue(getCampaign(result.getId_campaign()));
        this.pckBegin.setValue(result.getInitial_date().toLocalDateTime().toLocalDate());
        this.pckEnd.setValue(result.getFinal_date().toLocalDateTime().toLocalDate());
        
        if (result.getId_sale_condition_type() == 1){
            this.objectiveCB.setValue(getProduct(result.getId_to_take()));
        }else{
            this.objectiveCB.setValue(getProductType(result.getId_to_take()));
        }
        
        Float amountGet = result.getAmount();
        
        if (amountGet < 0.00001f) {
            brnPromo.setSelected(true);
            disLabel.disableProperty().set(false);
            countLabel.disableProperty().set(false);
            amountField.disableProperty().set(true);
            this.amountField.setText(" - ");
            this.disLabel.setText(result.getN_discount().toString());
            this.countLabel.setText(result.getN_tocount().toString());
            
        }else{
            disLabel.disableProperty().set(true);
            countLabel.disableProperty().set(true);
            amountField.disableProperty().set(false);
            this.amountField.setText(result.getAmount().toString());
        }
        
        loadObjectives();
        session.close();
        sessionFactory.close();

    }    
    
    public static String getType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleConditionType.class);
        criteria.add(Restrictions.eq("id_sale_condition_type",cod));
        String descripType;
        List rsType = criteria.list();
        SaleConditionType result = (SaleConditionType) rsType.get(0);
        descripType = result.getDescription();
        session.close();
        sessionFactory.close();
        return descripType;
    }
    
    public static String getProduct(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product",cod));
        String descripType;
        List rsType = criteria.list();
        Product result = (Product) rsType.get(0);
        descripType = result.getName();
        session.close();
        sessionFactory.close();
        return descripType;
    }
    
    public static String getProductType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        criteria.add(Restrictions.eq("id_product_type",cod));
        String descripType;
        List rsType = criteria.list();
        ProductType result = (ProductType) rsType.get(0);
        descripType = result.getDescription();
        session.close();
        sessionFactory.close();
        return descripType;
    }
    
    public static String getCampaign(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Campaign.class);
        criteria.add(Restrictions.eq("id_campaign",cod));
        String descripType;
        List rsType = criteria.list();
        Campaign result = (Campaign) rsType.get(0);
        descripType = result.getName();
        session.close();
        sessionFactory.close();
        return descripType;
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
    private void saveSaleCondition (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            setIds(campaignCB.getValue(),typeCB.getValue(),objectiveCB.getValue());
            Date date_init = getDate(pckBegin.getValue());
            Date date_end = getDate(pckEnd.getValue());
            Float am = 0.0f;
            Integer n_d = 1,  n_c = 1;
            if (brnPromo.isSelected()){
                n_d = Integer.parseInt(disLabel.getText());
                n_c = Integer.parseInt(countLabel.getText());
            } else {
                
                am = Float.parseFloat(amountField.getText());
                
            }
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("update SaleCondition set initial_date = :newinDate, final_date = :newenDate,"
                    + "amount=:newAmount, id_sale_condition_type=:newIDT, limits=:newLimits, "
                    + "id_to_take=:newITT,id_campaign=:newCamp, "
                    + "n_discount=:newND, n_tocount=:newTC "
                    + "where id_sale_condition = :oldIdSC");
            
            query.setParameter("newinDate", date_init);
            query.setParameter("newenDate", date_end);
            query.setParameter("newAmount", am);
            query.setParameter("newIDT", id_type);
            query.setParameter("newLimits", Integer.parseInt(limitField.getText()));
            query.setParameter("newITT", id_objective);
            query.setParameter("newCamp", id_campaign);
            query.setParameter("newND", n_d);
            query.setParameter("newTC", n_c);
            query.setParameter("oldIdSC", id);
            int result = query.executeUpdate();
            
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListSaleConditions();
        }
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue()-1,
                                                    value.getDayOfMonth());
        return calendar.getTime();
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
    
     @FXML
    private void goListSaleConditions() throws IOException{
        main.showListSaleConditions();
    }
    
    @FXML
    private void activatePromo() {
        if (brnPromo.isSelected()){
            amountField.setText(" - ");
            amountField.disableProperty().set(true);
            disLabel.disableProperty().set(false);
            countLabel.disableProperty().set(false);
        } else {
            amountField.setText(null);
            amountField.disableProperty().set(false);
            disLabel.disableProperty().set(true);
            countLabel.disableProperty().set(true);
            disLabel.setText("");
            countLabel.setText("");
            
        }
                
    }
}
