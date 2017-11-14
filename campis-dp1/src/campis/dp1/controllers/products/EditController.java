/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.products;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import campis.dp1.models.UnitOfMeasure;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class EditController implements Initializable{
    
    Integer id;
    Main main;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextArea descripField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXTextField trademarkField;
    @FXML
    private JFXComboBox measureField;
    @FXML
    private JFXComboBox typeField;
    @FXML
    private JFXTextField weightField;
    @FXML
    private JFXComboBox currencyType;
    @FXML
    private JFXTextField maxQTField;
    @FXML
    private Label nameMessage;
    @FXML
    private Label priceMessage;
    @FXML
    private Label trademarkMessage;
    @FXML
    private Label typeMessage;
    @FXML
    private Label weightMessage;
    @FXML
    private Label maxQTMessage;
    
    @FXML
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    
    public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean priceValid = priceField.getText().length() == 0;
        boolean trademarkValid = trademarkField.getText().length() == 0;
        boolean typeValid = typeField.getEditor().getText().length() == 0;
        boolean weightValid = weightField.getText().length() == 0;
        boolean maxQTValid = maxQTField.getText().length() == 0;
        
        nameMessage.setText("");
        priceMessage.setText("");
        trademarkMessage.setText("");
        typeMessage.setText("");
        weightMessage.setText("");
        maxQTMessage.setText("");

        if (nameValid)
            nameMessage.setText("Campo obligatorio");

        if (priceValid)
            priceMessage.setText("Campo obligatorio");

        if(trademarkValid)
            trademarkMessage.setText("Campo obligatorio");

        if (typeValid)
            typeMessage.setText("Campo obligatorio");

        if (weightValid)
            weightMessage.setText("DNI o RUC obligatorio");

        if(maxQTValid)
            maxQTMessage.setText("Campo obligatorio");

        return (!nameValid && !priceValid && !trademarkValid && !typeValid && !weightValid && !maxQTValid);
    }

    public static Integer searchCodMeasure(String measure) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UnitOfMeasure.class);
        criteria.add(Restrictions.eq("description",measure));
        Integer codMeasure;
        List rsMeasure = criteria.list();
        UnitOfMeasure result = (UnitOfMeasure)rsMeasure.get(0);
        codMeasure = result.getId_unit_of_measure();
        session.close();
        sessionFactory.close();
        return codMeasure;
    }
    
    public static Integer searchCodType(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        criteria.add(Restrictions.eq("description",type));
        Integer codType;
        List rsType = criteria.list();
        ProductType result = (ProductType) rsType.get(0);
        codType = result.getId_prodType();
        session.close();
        sessionFactory.close();
        return codType;
    }
    
    @FXML
    private void insertProduct (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (validation()) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            int measure = searchCodMeasure(measureField.getEditor().getText());
            int type = searchCodType(typeField.getEditor().getText());
            Query query = session.createQuery("update Product set name = :newName,description = :newDescrip,"
                    + "weight=:newWeight,trademark=:newTrademark,base_price=:newPrice,id_unit_of_measure=:newMeasure,"
                    + "id_product_type=:newType,max_qt=:newMaxQT where id_product = :oldIdProd");
            query.setParameter("newName", nameField.getText());
            query.setParameter("newName", nameField.getText());
            query.setParameter("newDescrip",descripField.getText());
            query.setParameter("newWeight", Float.parseFloat(weightField.getText()));
            query.setParameter("newTrademark", trademarkField.getText());
            query.setParameter("newPrice", Float.parseFloat(priceField.getText()));
            query.setParameter("newMeasure", measure);
            query.setParameter("newType", type);
            query.setParameter("newMaxQT", Integer.parseInt(maxQTField.getText()));
            query.setParameter("oldIdProd", id);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListProduct();
        }
    }
    
    public static String getMeasure(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UnitOfMeasure.class);
        criteria.add(Restrictions.eq("id_unit_of_measure",cod));
        String descrip;
        List rsMeasure = criteria.list();
        UnitOfMeasure result = (UnitOfMeasure)rsMeasure.get(0);
        descrip = result.getDescription();
        session.close();
        sessionFactory.close();
        return descrip;
    }
    
    public static String getType(int cod) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        weightField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    weightField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        maxQTField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    maxQTField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        id = ContextFX.getInstance().getId();
        currencyType.getItems().addAll("S/.","$","€");
        List<UnitOfMeasure> list = CreateController.getUnitsOfMeasure();
        for (int i = 0; i < list.size(); i++) {
            measureField.getItems().addAll(list.get(i).getDescription());
        }
        List<ProductType> typeList = CreateController.getTypes(); 
        for (int i = 0; i < typeList.size(); i++) {
            typeField.getItems().addAll(typeList.get(i).getDescription());
        }
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product",id));
        List rsType = criteria.list();
        Product result = (Product)rsType.get(0);
        session.close();
        sessionFactory.close();
        
        this.nameField.setText(result.getName());
        this.descripField.setText(result.getDescription());
        String measure = getMeasure(result.getId_unit_of_measure());
        this.measureField.setValue(measure);
        this.priceField.setText(Float.toString(result.getBase_price()));
        this.trademarkField.setText(result.getTrademark());
        String type = getType(result.getId_product_type());
        this.typeField.setValue(type);
        this.weightField.setText(Float.toString(result.getWeight()));
    }
    
}
