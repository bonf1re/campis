/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.warehouse;

import campis.dp1.ContextFX;
import javafx.event.ActionEvent;
import campis.dp1.Main;
import campis.dp1.models.Warehouse;
import campis.dp1.models.Area;
import campis.dp1.models.ProductType;
import campis.dp1.models.AreaDisplay;
import java.io.IOException;
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
import jdk.nashorn.internal.runtime.Context;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author sergio
 */
public class AreaListController implements Initializable {
    private Main main;
    private ObservableList<Area> whAreas;
    private ObservableList<AreaDisplay> whAreasView;
    private int selected_id;
    private int warehouse_id;
    @FXML
    private TableView<AreaDisplay> whAreaTable;
    @FXML
    private TableColumn<AreaDisplay, Integer> idCol;
    @FXML
    private TableColumn<AreaDisplay, String> nameCol;
    @FXML
    private TableColumn<AreaDisplay, Integer> lengthCol;
    @FXML
    private TableColumn<AreaDisplay, Integer> widthCol;
    @FXML
    private TableColumn<AreaDisplay, Integer> posXCol;
    @FXML
    private TableColumn<AreaDisplay, Integer> posYCol;
    @FXML
    private TableColumn<AreaDisplay, String> productTypeCol;
    
    @FXML
    private void goWhList() throws IOException{
        main.showWhList();
    }
    
    @FXML
    private void goAreaCreate() throws IOException{
        ContextFX.getInstance().setId(this.warehouse_id);
        main.showAreaCreate();
    }
    
    @FXML
    private void goAreaEdit() throws IOException{
        ContextFX.getInstance().setId(this.selected_id);
        main.showAreaEdit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.warehouse_id=ContextFX.getInstance().getId();
        
        whAreaTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_area().getValue().intValue();
            }
        );
        try{
            idCol.setCellValueFactory(cellData -> cellData.getValue().getId_area().asObject());
            nameCol.setCellValueFactory(cellData -> cellData.getValue().getName());
            lengthCol.setCellValueFactory(cellData -> cellData.getValue().getLength().asObject());
            widthCol.setCellValueFactory(cellData -> cellData.getValue().getWidth().asObject());
            posXCol.setCellValueFactory(cellData -> cellData.getValue().getPos_x().asObject());
            posYCol.setCellValueFactory(cellData -> cellData.getValue().getPos_y().asObject());
            productTypeCol.setCellValueFactory(cellData -> cellData.getValue().getProduct_type());
            areasLoadData();
        } catch(SQLException | ClassNotFoundException ex){
            Logger.getLogger(AreaListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void areasLoadData() throws SQLException, ClassNotFoundException{
        whAreas = FXCollections.observableArrayList();
        whAreasView = FXCollections.observableArrayList();
        whAreas = getWhAreas();
        for (int i = 0; i < whAreas.size(); i++) {
            Area whArea = (Area) whAreas.get(i);
            AreaDisplay whArea_display = new AreaDisplay(whArea, getProductType(whArea.getProduct_type()));
            whAreasView.add(whArea_display);
        }
        whAreaTable.setItems(null);
        whAreaTable.setItems(whAreasView);
    }

    private ObservableList<Area> getWhAreas() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Area.class);
        System.out.println(this.warehouse_id);
        
        criteria.add(Restrictions.eq("id_warehouse",this.warehouse_id));
        List whList = criteria.list();
        ObservableList<Area> returnable=FXCollections.observableArrayList();
        for (int i = 0; i < whList.size(); i++) {
            returnable.add((Area) whList.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
    
    public static String getProductType(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(ProductType.class);
        criteria.add(Restrictions.eq("id_product_type",cod));
        String productTypeName;
        List rsType = criteria.list();
        ProductType result = (ProductType) rsType.get(0);
        productTypeName = result.getDescription();
        session.close();
        sessionFactory.close();

        return productTypeName;
    }
    
    private void deleteArea(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Area.class);
        Area area = new Area();
        area.setId_area(cod);
        session.delete(area);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void deleteArea(ActionEvent event) throws SQLException, ClassNotFoundException {
        ContextFX.getInstance().setId(selected_id);
        Integer id_area = ContextFX.getInstance().getId();
        deleteArea(selected_id);
        areasLoadData();
    }
}
