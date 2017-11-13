/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Area;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Gina Bustamante
 */
public class AreaEditController implements Initializable{
    private int area_id;
    private Main main;
    private ArrayList<ProductType> product_type_list= new ArrayList<>();
    
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField lengthField;
    @FXML
    private JFXTextField widthField;
    @FXML
    private JFXTextField posXField;
    @FXML
    private JFXTextField posYField;
    @FXML
    private JFXComboBox cbProductType;

    @FXML
    private void goListArea() throws IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Restrictions.eq("id_area",area_id));
        List rsType = criteria.list();
        Area result = (Area)rsType.get(0);
        ContextFX.getInstance().setId(result.getId_warehouse());
        session.close();
        sessionFactory.close();
        main.showAreaList();
    }

    @FXML
    private void editArea (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id_pt = product_type_list.get(cbProductType.getSelectionModel().getSelectedIndex()).getId_prodType();
        Query query = session.createQuery("update Area set name = :newName," + 
                "length = :newLength," + "width = :newWidth," +
                "pos_x = :newPosX," + "pos_y = :newPosY,"
                + "product_type = :newProductType"
                + " where id_area = :oldIdArea");
        query.setParameter("newName", nameField.getText());
        query.setParameter("newLength", Integer.parseInt(lengthField.getText()));
        query.setParameter("newWidth", Integer.parseInt(widthField.getText()));
        query.setParameter("newPosX", Integer.parseInt(posXField.getText()));
        query.setParameter("newPosY", Integer.parseInt(posYField.getText()));
        query.setParameter("newProductType", id_pt);
        query.setParameter("oldIdArea", area_id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListArea();
    }
    
    private void loadProductTypeNames() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        
        Criteria criteria = session.createCriteria(ProductType.class);
        this.product_type_list = (ArrayList<ProductType>) criteria.list();
        ArrayList<String> pt_names = new ArrayList<>();
        for (ProductType pt: this.product_type_list) {
            pt_names.add(pt.getDescription());
        }
        session.close();
        sessionFactory.close();
        this.cbProductType.getItems().addAll(pt_names);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        area_id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Area.class);
        criteria.add(Restrictions.eq("id_area",area_id));
        List rsType = criteria.list();
        Area result = (Area)rsType.get(0);
        this.nameField.setText(result.getName());
        this.lengthField.setText(String.valueOf(result.getLength()));
        this.widthField.setText(String.valueOf(result.getWidth()));
        this.posXField.setText(String.valueOf(result.getPos_x()));
        this.posYField.setText(String.valueOf(result.getPos_y()));
        session.close();
        sessionFactory.close();
        
        loadProductTypeNames();
    } 
}
