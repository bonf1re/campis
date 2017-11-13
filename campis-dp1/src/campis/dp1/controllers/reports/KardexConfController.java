/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class KardexConfController implements Initializable {

    
    Main main;
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
    
    public void setVariables() throws SQLException, ClassNotFoundException {

        id_type = typeCB.getSelectionModel().getSelectedIndex();
        id_objective = 0;
        if (id_type == 1)
            id_objective = searchCodProductType(objectiveCB.getValue());
        else if (id_type == 2)
            id_objective = searchCodProduct(objectiveCB.getValue());
       ContextFX.getInstance().setId_type(id_type);
       ContextFX.getInstance().setId_objective(id_objective);
       ContextFX.getInstance().setInit_date(getDate(pckBegin.getValue()));
       ContextFX.getInstance().setEnd_date(getDate(pckEnd.getValue()));       
       ContextFX.getInstance().setLabeltoPrint1(objectiveCB.getValue());
        
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
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue(),
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
    @FXML
    private void goGenerateReport(ActionEvent event) throws IOException, SQLException, ClassNotFoundException  {
        setVariables();
        main.showGenerateKardexReport();
    }
    
}
