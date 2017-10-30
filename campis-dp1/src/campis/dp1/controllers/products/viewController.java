/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.products;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import static campis.dp1.controllers.products.EditController.getType;
import campis.dp1.models.Product;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author Eddy
 */
public class viewController implements Initializable{
    Integer id;
    Main main;
    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextArea descripField;
    @FXML
    private JFXTextField stockPField;
    @FXML
    private JFXTextField stockLField;
    @FXML
    private JFXTextField typeField;
    @FXML
    private JFXTextField posXField;
    @FXML
    private JFXTextField posYField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXTextField trademarkField;
    @FXML
    private JFXTextField weightField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product",id));
        List rsType = criteria.list();
        Product result = (Product)rsType.get(0);
        this.idField.setText(Integer.toString(id));
        this.stockPField.setText(Integer.toString(result.getP_stock()));
        this.stockLField.setText(Integer.toString(result.getC_stock()));
        this.nameField.setText(result.getName());
        this.descripField.setText(result.getDescription());
        this.priceField.setText(Float.toString(result.getBase_price()));
        this.trademarkField.setText(result.getTrademark());
        String type = getType(result.getId_product_type());
        this.typeField.setText(type);
        this.weightField.setText(Float.toString(result.getWeight()));
    }

    @FXML
    private void goEditProduct(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(id);
        main.showEditProduct();
    }

    @FXML
    private void goListproduct(ActionEvent event) throws IOException {
        main.showListProduct();
    }
    
}
