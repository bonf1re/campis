/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.freights;

import campis.dp1.Main;
import javafx.event.ActionEvent;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.DistrictDisplay;
import campis.dp1.models.District;
import campis.dp1.ContextFX;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author joseamz
 */
public class ListFreightController implements Initializable {
    private Main main;
    private ObservableList<District> districts;
    private ObservableList<DistrictDisplay> districtsView = FXCollections.observableArrayList();
    private int selected_id;

    @FXML
    private TableView<DistrictDisplay> tableDistrict;
    @FXML
    private TableColumn<DistrictDisplay,String> nameColumn;
    @FXML
    private TableColumn<DistrictDisplay,Float> freightColumn;

    @FXML
    private void goEditFreight() throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditFreight();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableDistrict.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.idDistrictProperty().getValue().intValue();
            }
        );
        try {
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            freightColumn.setCellValueFactory(cellData -> cellData.getValue().freightProperty().asObject());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            freightColumn.setCellValueFactory(cellData -> cellData.getValue().freightProperty().asObject());
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<District> getDistricts() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(District.class);
        List lista = criteria.list();
        ObservableList<District> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((District)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    } 

    private void loadData() throws SQLException, ClassNotFoundException {
        districts = FXCollections.observableArrayList();
        districts = getDistricts();
        for (int i = 0; i < districts.size(); i++) {
            DistrictDisplay district = new DistrictDisplay(districts.get(i).getId_district(), districts.get(i).getName(), districts.get(i).getFreight());
            districtsView.add(district);
        }
        tableDistrict.setItems(null);
        tableDistrict.setItems(districtsView);
    }
}
