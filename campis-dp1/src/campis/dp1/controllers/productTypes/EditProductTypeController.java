/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.productTypes;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
/**
 *
 * @author joseamz
 */
public class EditProductTypeController implements Initializable{
    Integer id;
    Main main;
    @FXML
    private JFXTextField descriptionField;
    
    @FXML
    private void goListProductTypes() throws IOException {
        main.showListProductType();
    }

    @FXML
    private void editProductType (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update ProductType set description = :newDescription"
                + " where id_product_type = :oldIdProductType");
        query.setParameter("newDescription", descriptionField.getText());
        query.setParameter("oldIdProductType", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListProductTypes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        criteria.add(Restrictions.eq("id_product_type",id));
        List rsType = criteria.list();
        ProductType result = (ProductType)rsType.get(0);
        this.descriptionField.setText(result.getDescription());
        session.close();
        sessionFactory.close();
    }
}
