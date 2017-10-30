/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.requestOrder;

import campis.dp1.Main;
import static campis.dp1.controllers.products.CreateController.getTypes;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class AddItemController implements Initializable{
    
    Main main;
    private ObservableList<ProductDisplay> productsView;
    private ObservableList<Product> products;
    
    @FXML
    private JFXTextField codField;
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXComboBox typeField;
    @FXML
    private TableView<ProductDisplay> tableProd;
    @FXML
    private TableColumn<ProductDisplay, Integer> codColumn;
    @FXML
    private TableColumn<ProductDisplay, String> nameColumn;
    @FXML
    private TableColumn<ProductDisplay, Integer> typeColumn;
    @FXML
    private JFXTextField quantityField;
    
    @FXML
    private void goCreateRequestOrder() throws IOException {
        main.showCreateRequestOrder();
    }
    
    private ObservableList<Product> getSearchList(String text, String text2, String text3) throws SQLException, ClassNotFoundException {
        int codType = getCodType(text3);
        ObservableList<Product>  returnable;
        returnable = FXCollections.observableArrayList();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> list = criteria.list();
        for (int i = 0; i < list.size(); i++) {
            if((text.compareTo("")!=0) && (list.get(i).getId_product().equals(Integer.parseInt(text)))) {
                returnable.add(list.get(i));
                break;
            }else if((text2.compareTo("")!=0) && (list.get(i).getName().contains(text2)==TRUE)){
                returnable.add(list.get(i));
            }else if((text3.compareTo("")!=0) && (text2.compareTo("")==0) && (list.get(i).getId_product_type().equals(codType))){
                returnable.add(list.get(i));
            }
        }
        sessionFactory.close();
        return returnable;
    }
    
    public static Integer getCodType(String type) throws SQLException, ClassNotFoundException {
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
        sessionFactory.close();
        return codType;
    }

    @FXML
    private void goSearchProd(ActionEvent event) throws SQLException, ClassNotFoundException {
        String text = this.codField.getText();
        String text2 = this.nameField.getText();
        String text3 = this.typeField.getEditor().getText();
        if((text.compareTo("") != 0) || (text2.compareTo("") != 0) || (text3.compareTo("") != 0)) {
            products = FXCollections.observableArrayList();
            productsView = FXCollections.observableArrayList();
            products = getSearchList(text,text2,text3);
            if(products == null){
                tableProd.setItems(null);
            } else {
               for (int i = 0; i < products.size(); i++) {
                    ProductDisplay prod = new ProductDisplay(products.get(i).getId_product(), products.get(i).getName(),
                            products.get(i).getDescription(), products.get(i).getP_stock(), products.get(i).getC_stock(),
                            products.get(i).getWeight(), products.get(i).getTrademark(), products.get(i).getBase_price(),
                            products.get(i).getId_unit_of_measure(), products.get(i).getId_product_type());
                    productsView.add(prod);
                }
               tableProd.setItems(null);
               tableProd.setItems(productsView);
            }
        }else {
            loadData();
        }
    }
    
    private ObservableList<Product> getProd() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        List list = criteria.list();
        ObservableList<Product> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            returnable.add((Product)list.get(i));
        }
        sessionFactory.close();
        return returnable;
    }
    
    private void loadData() {
        products = FXCollections.observableArrayList();
        productsView = FXCollections.observableArrayList();
        products = getProd();
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
        return types;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
        
        List<ProductType> typeList = getTypes(); 
        for (int i = 0; i < typeList.size(); i++) {
            typeField.getItems().addAll(typeList.get(i).getDescription());
        }
    }
    
    
}
