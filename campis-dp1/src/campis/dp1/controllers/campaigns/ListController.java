/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.campaigns;

import campis.dp1.Main;
import campis.dp1.controllers.saleConditions.ListSaleConditionController;
import campis.dp1.models.Campaign;
import campis.dp1.models.CampaignDisplay;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.hibernate.transform.Transformers;

/**
 * FXML Controller class
 *
 * @author david
 * 
 * 
 * 
 V 
 
 * 
 */
public class ListController implements Initializable {

    private Main main;
    private int selected_id;
    private ObservableList<Campaign> camps;
    private ObservableList<CampaignDisplay> campsView;
    
    
    @FXML
    private TableView<CampaignDisplay> CampaignsTable;

    @FXML
    private TableColumn<CampaignDisplay, Integer> codColumn;

    @FXML
    private TableColumn<CampaignDisplay, String> nameColumn;

    @FXML
    private TableColumn<CampaignDisplay, String> descriptionColumn;

    @FXML
    private TableColumn<CampaignDisplay, String> initColumn;

    @FXML
    private TableColumn<CampaignDisplay, String> endColumn;
    
    @FXML
    private void goSaleConditions(ActionEvent event) throws IOException {
        main.showListSaleConditions();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        CampaignsTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_campaign().getValue().intValue();
            }
        );
        try {
            codColumn.setCellValueFactory(cellData -> cellData.getValue().getId_campaign().asObject());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            initColumn.setCellValueFactory(cellData -> cellData.getValue().getInitial_date());
            endColumn.setCellValueFactory(cellData -> cellData.getValue().getEnd_date());
            
            /**/
            cargarData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListSaleConditionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    private void cargarData() throws SQLException, ClassNotFoundException {
        String campaign, type, objective;
        int id_type;
        
        camps = FXCollections.observableArrayList();
        campsView = FXCollections.observableArrayList();
        camps = getCampaigns();
        
        for (int i = 0; i < camps.size(); i++) {

            CampaignDisplay campD = new CampaignDisplay(camps.get(i).getId_campaign(), camps.get(i).getName() ,
                    camps.get(i).getDescription(), camps.get(i).getInitial_date().toString(), camps.get(i).getFinal_date().toString());
            campsView.add(campD);
        }
        CampaignsTable.setItems(null);
        CampaignsTable.setItems(campsView);
    }
    
    public static ObservableList<Campaign> getCampaigns() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Campaign.class);
        List lista = criteria.list();
        ObservableList<Campaign> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Campaign) lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;
    }
}
