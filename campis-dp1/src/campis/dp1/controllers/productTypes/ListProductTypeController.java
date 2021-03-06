/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.productTypes;

import campis.dp1.Main;
import javafx.event.ActionEvent;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.ProductTypeDisplay;
import campis.dp1.models.ProductType;
import campis.dp1.ContextFX;
import java.io.IOException;
import campis.dp1.models.Product;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author joseamz
 */
public class ListProductTypeController implements Initializable {
    private Main main;
    private ObservableList<ProductType> productTypes;
    private ObservableList<ProductTypeDisplay> productTypesView;
    private int selected_id;
    private int id_role;
    
    @FXML
    private TableView<ProductTypeDisplay> tableProductType;
    @FXML
    private TableColumn<ProductTypeDisplay,String> descriptionColumn;
    @FXML
    private JFXTextField searchField;
    @FXML
    private Button createButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;
    
    @FXML
    private Label lblprodType;

    @FXML
    private void goCreateProductType() throws IOException {
        main.showCreateProductType();
    }
    
    @FXML
    private void goEditProductType() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditProductType();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        this.lblprodType.setText("");
        ContextFX.getInstance().modifyValidation(createButton, editButton, deleteButton, id_role, "product_types");
        tableProductType.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idProductTypeProperty().getValue().intValue();
            }
        );
        try {
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<ProductType> getProductTypes() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        List lista = criteria.list();
        ObservableList<ProductType> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((ProductType)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        productTypes = FXCollections.observableArrayList();
        productTypesView = FXCollections.observableArrayList();
        productTypes = getProductTypes();
        for (int i = 0; i < productTypes.size(); i++) {
            ProductTypeDisplay status = new ProductTypeDisplay(productTypes.get(i).getId_prodType(), productTypes.get(i).getDescription());
            productTypesView.add(status);
        }
        tableProductType.setItems(null);
        tableProductType.setItems(productTypesView);
    }
    
    private void deleteProductType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        ProductType product_type = new ProductType();
        product_type.setId_prodType(cod);
        session.delete(product_type);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
    
    @FXML
    private void deleteProductType(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            if (anyProducts(selected_id)){
                deleteProductType(selected_id);
                loadData();
                this.lblprodType.setText("");
            } else {
                this.lblprodType.setText("No es posible eliminar. Existen productos de este tipo.");
            }
            
        }
    }
    
    private boolean anyProducts(int type_id){
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.add(Restrictions.eq("id_product_type",type_id));
        List lista = criteria.list();

        session.close();
        sessionFactory.close();
        return (lista.isEmpty());
    }
}
