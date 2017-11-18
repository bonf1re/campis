/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductDisplay;
import campis.dp1.models.ProductType;
import campis.dp1.models.ProductWH_Move;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

/**
 *
 * @author sergio
 */
public class EntryMoveSpecialAddItemController implements Initializable {
    Main main;
    private ObservableList<ProductDisplay> productsView;
    private ObservableList<Product> products;
    private int selected_id;
    private int warehouse_id;
    
     @FXML
    private JFXDatePicker expDateField;
    
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selected_id = 0;
        this.warehouse_id = ContextFX.getInstance().getId();
        tableProd.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.codProdProperty().getValue().intValue();
            }
        );
        codColumn.setCellValueFactory(cellData -> cellData.getValue().codProdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty().asObject());
        
        List<ProductType> typeList = getTypes(); 
        for (int i = 0; i < typeList.size(); i++) {
            typeField.getItems().addAll(typeList.get(i).getDescription());
        }
    }
    
    
    private ObservableList<Product> getSearchList(String text, String text2, String text3) throws SQLException, ClassNotFoundException {
        int codType = 0;
        if(text3.compareTo("")!=0){
            codType = getCodType(text3);
        }
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
        session.close();
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
        session.close();
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
                            products.get(i).getId_unit_of_measure(), products.get(i).getId_product_type(), products.get(i).getMax_qt());
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
        session.close();
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
        session.close();
        sessionFactory.close();
        return types;
    }
    
    @FXML
    private void addItemAction() throws IOException {
        if (selected_id > 0) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Product.class);
            criteria.add(Restrictions.eq("id_product", selected_id));
            Product prod =  (Product) criteria.list().get(0);
            
            Query query = session.createSQLQuery("SELECT p_stock FROM campis.productxwarehouse WHERE id_product ="+prod.getId_product()+"AND id_warehouse = "+this.warehouse_id);
            int stock = (int) query.list().get(0);
            
            //obtenemos la maxima cantidad 
            
            SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_exp = getDate(expDateField.getValue());
            Timestamp expTimestamp = Timestamp.valueOf((String)formatIn.format(date_exp));
            
            ProductWH_Move ppp = new ProductWH_Move(prod, Integer.parseInt(quantityField.getText()),stock, 0, Integer.parseInt(quantityField.getText()), expTimestamp);
            ContextFX.getInstance().setId(warehouse_id);
            ContextFX.getInstance().setMode(1);
            
            ArrayList<Object> aux_pol = ContextFX.getInstance().getPolymorphic_list();
            ObservableList aux_prod = FXCollections.observableArrayList((ObservableList)aux_pol.get(0));
            aux_prod.add(ppp);
            aux_pol.set(0, aux_prod);
            
            ContextFX.getInstance().setPolymorphic_list(aux_pol);
            
            session.close();
            sessionFactory.close();
            
            this.goBackCreateEspecialEntry();
        }
    }
    
     private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue()-1,
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
    @FXML
    private void goBackCreateEspecialEntry() throws IOException {
        ContextFX.getInstance().setId(warehouse_id);
        //ContextFX.getInstance().setMode(1);
        main.showWhEntryMoveSpecialCreate();
    }
}
