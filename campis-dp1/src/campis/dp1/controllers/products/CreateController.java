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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Eddy
 */
public class CreateController implements Initializable {
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
    private JFXComboBox<String> currencyType;
    @FXML
    private JFXTextField maxQtField;
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

    public boolean validation() {
        boolean nameValid = nameField.getText().length() == 0;
        boolean priceValid = priceField.getText().length() == 0;
        boolean trademarkValid = trademarkField.getText().length() == 0;
        boolean typeValid = typeField.getEditor().getText().length() == 0;
        boolean weightValid = weightField.getText().length() == 0;
        boolean maxQTValid = maxQtField.getText().length() == 0;
        
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
    
    @FXML
    private void goListProduct() throws IOException{
        main.showListProduct();
    }
    
    private void goCreateProduct(ActionEvent event) throws IOException{
        main.showCreateProduct();
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
            String curTyp = this.currencyType.getValue();
            float price;
            if(curTyp.compareTo("S/.")==0){
                price = Float.parseFloat(priceField.getText());
            }else if(curTyp.compareTo("$")==0){
                price = Float.parseFloat(priceField.getText()) * ContextFX.getInstance().getDollar();
            }else {
                price = Float.parseFloat(priceField.getText()) * ContextFX.getInstance().getEuro();
            }
            
            Product product = new Product(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                         trademarkField.getText(), price, measure, type, Integer.parseInt(maxQtField.getText()));
            
            session.save(product);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            this.goListProduct();
        }
    }
    
    public static List<UnitOfMeasure> getUnitsOfMeasure() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UnitOfMeasure.class)
                .setProjection(Projections.projectionList()
                .add(Projections.property("description"),"description"))
                .setResultTransformer(Transformers.aliasToBean(UnitOfMeasure.class));
        List<UnitOfMeasure> measures = criteria.list();
        session.close();
        sessionFactory.close();
        return measures;
    }
    
    public static List<ProductType> getTypes() {
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
        maxQtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    maxQtField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        currencyType.getItems().addAll("S/.","$","€");
        List<UnitOfMeasure> list = getUnitsOfMeasure();
        for (int i = 0; i < list.size(); i++) {
            measureField.getItems().addAll(list.get(i).getDescription());
        }
        List<ProductType> typeList = getTypes(); 
        for (int i = 0; i < typeList.size(); i++) {
            typeField.getItems().addAll(typeList.get(i).getDescription());
        }
    }
}
