/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.dispatch;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.controllers.products.ListController;
import campis.dp1.models.DispatchMoveDisplay;
import campis.dp1.models.DispatchMove;
import campis.dp1.models.Permission;
import campis.dp1.models.View;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ListEntryController implements Initializable {
    
    private Main main;
    private ObservableList<DispatchMove> entries;
    private ObservableList<DispatchMoveDisplay> entriesView;
    private int selected_id;
    private int id_role;

    @FXML
    private Button createButton;
    
    @FXML
    private void goVisualizeEntry() throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showVisualizeEntry();
        }
    }
    
    @FXML
    private void goNewEntry() throws IOException {
        main.showNewEntry();
    }
    
    @FXML
    private TableView<DispatchMoveDisplay> tablaEntries;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> idIngresCol;
    @FXML
    private TableColumn<DispatchMoveDisplay,  Integer> prov_AlmCol;
    @FXML
    private TableColumn<DispatchMoveDisplay, String> dateCol;
    @FXML
    private TableColumn<DispatchMoveDisplay, Integer> reasonsCol;
    
    private ObservableList<DispatchMove> getEntries() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int typeOwner1 = 3;
        int typeOwner2 = 4;
        Criteria criteria = session.createCriteria(DispatchMove.class);
        criteria.add(Restrictions.disjunction()
                .add(Restrictions.eq("type_owner", typeOwner1))
                .add(Restrictions.eq("type_owner", typeOwner2)));
        
        List lista = criteria.list();
        
        ObservableList<DispatchMove> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((DispatchMove)lista.get(i));
        }
        session.close();
        sessionFactory.close();
        return returnable;        
    }
    
    private void loadData() throws SQLException, ClassNotFoundException  {
        entries = FXCollections.observableArrayList();
        entriesView = FXCollections.observableArrayList();
        entries = getEntries();
        
        for (int i = 0; i < entries.size(); i++) {
            
            DispatchMoveDisplay e = new DispatchMoveDisplay(entries.get(i).getId_dispatch_move(), 
                                              entries.get(i).getType_owner(),
                                              entries.get(i).getId_owner(),
                                              entries.get(i).getMov_date().toString(),
                                              entries.get(i).getReason());
            entriesView.add(e);
        }
        
        tablaEntries.setItems(null);  
        tablaEntries.setItems(entriesView);  
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        id_role = (ContextFX.getInstance().getUser().getId_role());
        View whView = View.getView("entries_dispatch");

        if (!Permission.canModify(id_role, whView.getId_view())) {
            createButton.setVisible(false);
        }
        tablaEntries.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue == null) return;
                this.selected_id = newValue.id_dispatch_moveProperty().getValue();
            }
        );
        
        try {
            idIngresCol.setCellValueFactory(cellData -> cellData.getValue().id_dispatch_moveProperty().asObject());
            prov_AlmCol.setCellValueFactory(cellData -> cellData.getValue().id_ownerProperty().asObject());
            dateCol.setCellValueFactory(cellData -> cellData.getValue().arrival_dateProperty());
            reasonsCol.setCellValueFactory(cellData -> cellData.getValue().reasonProperty().asObject());
             
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

}
