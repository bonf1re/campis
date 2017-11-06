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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private void goListProduct() throws IOException {
        main.showListProduct();
    }
    
    public static Integer searchCodMeasure(String measure) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UnitOfMeasure.class);
        criteria.add(Restrictions.eq("description",measure));
        Integer codMeasure;
        List rsMeasure = criteria.list();
        UnitOfMeasure result = (UnitOfMeasure)rsMeasure.get(0);
        codMeasure = result.getId_unit_of_measure();
        return codMeasure;
    }
    
    public static Integer searchCodType(String type) throws SQLException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
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
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int measure = searchCodMeasure(measureField.getEditor().getText());
        int type = searchCodType(typeField.getEditor().getText());
        Query query = session.createQuery("update Product set name = :newName,description = :newDescrip,"
                + "weight=:newWeight,trademark=:newTrademark,base_price=:newPrice,id_unit_of_measure=:newMeasure,id_product_type=:newType where id_product = :oldIdProd");
        query.setParameter("newName", nameField.getText());
        query.setParameter("newName", nameField.getText());
        query.setParameter("newDescrip",descripField.getText());
        query.setParameter("newWeight", Float.parseFloat(weightField.getText()));
        query.setParameter("newTrademark", trademarkField.getText());
        query.setParameter("newPrice", Float.parseFloat(priceField.getText()));
        query.setParameter("newMeasure", measure);
        query.setParameter("newType", type);
        query.setParameter("oldIdProd", id);
        int result = query.executeUpdate();
        
        /*Product product = new Product(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                     trademarkField.getText(), Float.parseFloat(priceField.getText()), measure, type);
        product.setId_product(this.id);
        session.update(product);
        session.save(product);*/
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListProduct();
    }
    
    public static String getMeasure(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(UnitOfMeasure.class);
        criteria.add(Restrictions.eq("id_unit_of_measure",cod));
        String descrip;
        List rsMeasure = criteria.list();
        UnitOfMeasure result = (UnitOfMeasure)rsMeasure.get(0);
        descrip = result.getDescription();
        return descrip;
    }
    
    public static String getType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
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
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product",id));
        List rsType = criteria.list();
        Product result = (Product)rsType.get(0);
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
