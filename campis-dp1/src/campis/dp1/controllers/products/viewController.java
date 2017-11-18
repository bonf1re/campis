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
import javafx.scene.control.Label;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
    Integer pStock = 0;
    Integer cStock = 0;
    
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
    private JFXTextField priceField;
    @FXML
    private JFXTextField trademarkField;
    @FXML
    private JFXTextField weightField;
    @FXML
    private JFXTextField minQTField;
    @FXML
    private JFXTextField currencyField;
    @FXML
    private JFXTextField unitOfMeasureField;
    @FXML
    private Label stockMinMessage;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
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
        getStock(id);
        String measure = EditController.getMeasure(result.getId_unit_of_measure());
        this.idField.setText(Integer.toString(id));
        this.stockPField.setText(Integer.toString(pStock));
        this.stockLField.setText(Integer.toString(cStock));
        this.nameField.setText(result.getName());
        this.descripField.setText(result.getDescription());
        this.priceField.setText(Float.toString(result.getBase_price()));
        this.trademarkField.setText(result.getTrademark());
        String type = getType(result.getId_product_type());
        this.typeField.setText(type);
        this.weightField.setText(Float.toString(result.getWeight()));
        this.minQTField.setText(Float.toString(result.getMin_stock()));
        this.currencyField.setText("S/.");
        this.unitOfMeasureField.setText(measure);
        if((pStock - result.getMin_stock() < 5) && (pStock - result.getMin_stock() > 0)){
            stockMinMessage.setText("Se necesita más productos el stock esta cerca a terminarse.");
        }
        if(pStock - result.getMin_stock() == 0){
            stockMinMessage.setText("El stock del producto esta en su mínimo permitido.");
        }
    }
    
    private void getStock(int id_product) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String qryStr = "SELECT p_stock, c_stock FROM campis.productxwarehouse WHERE id_warehouse = 1 AND "
                + "id_product = " + id_product;
        SQLQuery qry = session.createSQLQuery(qryStr);
        List<Object[]> rows = qry.list();
        session.close();
        sessionFactory.close();
        for (Object[] row : rows) {
            this.pStock = Integer.parseInt(row[0].toString());
            this.cStock = Integer.parseInt(row[1].toString());
        }
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
