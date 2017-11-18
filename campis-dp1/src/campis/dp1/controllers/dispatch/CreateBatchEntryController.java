/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Batch;
import campis.dp1.models.Product;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
public class CreateBatchEntryController implements Initializable {

    private Main main;
    ObservableList<Product> products;
    Batch batch;

    @FXML
    private JFXComboBox<String> prodField;
    @FXML
    private JFXTextField quantityField;
    @FXML
    private JFXDatePicker arrivalDateField;
    @FXML
    private JFXDatePicker expDateField;
    @FXML
    private JFXTextField batchPriceField;
    @FXML
    private JFXComboBox<String> currencyField;
    
    @FXML
    private Label messageField1;
//    @FXML
//    private Label messageField2;
//    
    String message = "";
    
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue()-1,
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
    private Integer getIdProd(String prod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("name", prod));
        List<Product> list = criteria.list();
        Integer id = list.get(0).getId_product();
        session.close();
        sessionFactory.close();
        return id;
    }

    private Boolean verifyDates(Date creation, Date exp) {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
       
        Boolean flag = true;
        
        //verificamos que la fecha de ingreso sea menor, igual a la fecha de hoy dia
        if (creation.compareTo(currentTimestamp) > 0) {
            flag = false;
            message = "Fecha de creación debe ser menor o igual a la actual";
        }
        if (exp.compareTo(creation) <= 0) {
            flag = false;
            message = "Fecha de expiración, debe ser mayor a la fecha de creación";
        }
        return flag;
    }
        
        
    @FXML
    private void insertBatchEntry() throws IOException {
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String prod = prodField.getValue();
        Integer quantity = Integer.parseInt(quantityField.getText());
        
        Date date_creation = getDate(arrivalDateField.getValue());
        Date date_exp = getDate(expDateField.getValue());
        
        float batchPrice = Float.parseFloat(batchPriceField.getText());
        Integer idprod = getIdProd(prod);
        
        /*Verificacion de las fechas*/
        Boolean verify = verifyDates(date_creation, date_exp);

        if (verify == TRUE) {
            Batch b = new Batch(quantity, batchPrice, Timestamp.valueOf((String)formatIn.format(date_creation)), 
                            Timestamp.valueOf((String)formatIn.format(date_exp)), idprod, 4, "-1", true);

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();

            session.beginTransaction();
            session.save(b);
            session.getTransaction().commit();

            session.close();
            sessionFactory.close();
            ContextFX.getInstance().setId(b.getId_batch());
            this.goNewEntry();
        } else {
            messageField1.setText(message);
        }
 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products = getProducts();
        for (int i = 0; i < products.size(); i++) {
            prodField.getItems().add(products.get(i).getName());
        }
        currencyField.getItems().addAll("S/.","$","€");
    }

    private ObservableList<Product> getProducts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        ObservableList<Product> returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add(list.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

}
