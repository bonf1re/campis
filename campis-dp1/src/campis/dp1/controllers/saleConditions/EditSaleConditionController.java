/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.saleConditions;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.searchCodMeasure;
import static campis.dp1.controllers.products.EditController.searchCodType;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(SaleCondition.class);
        criteria.add(Restrictions.eq("id_sale_condition",id));
        List rsType = criteria.list();
        SaleCondition result = (SaleCondition)rsType.get(0);
        this.amountField.setText(result.getAmount().toString());
        this.limitField.setText(result.getLimits().toString());
        this.typeCB.setValue(getType(result.getId_sale_condition_type()));
        this.campaignCB.setValue(getCampaign(result.getId_campaign()));
        session.close();
        sessionFactory.close();

    }    
    
    
    public static String getType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
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
    
    public static String getCampaign(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
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
        
        setIds(campaignCB.getValue(),typeCB.getValue(),objectiveCB.getValue());
        Date date_init = getDate(pckBegin.getValue());
        Date date_end = getDate(pckEnd.getValue());
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("update Sale_condition set initial_date = :newinDate, final_date = :newenDate,"
                + "amount=:newAmount,id_sale_condition_type=:newIDT, limits=:newLimits, "
                + "id_to_take=:newITT,id_campaign=:newCamp where id_sale_condition = :oldIdSC");
        
        query.setParameter("newinDate", date_init.toString());
        query.setParameter("newenDate", date_end.toString());
        query.setParameter("newAmount", Float.parseFloat(amountField.getText()));
        query.setParameter("newIDT", id_type);
        query.setParameter("newLimits", Integer.parseInt(limitField.getText()));
        query.setParameter("newITT", id_objective);
        query.setParameter("newCamp", id_campaign);
        query.setParameter("oldIdSC", id);
        int result = query.executeUpdate();
        
        /*Product product = new Product(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                     trademarkField.getText(), Float.parseFloat(priceField.getText()), measure, type);
        product.setId_product(this.id);
        session.update(product);
        session.save(product);*/
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListSaleConditions();
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue(),
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
}
