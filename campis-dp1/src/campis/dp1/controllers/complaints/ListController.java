package campis.dp1.controllers.complaints;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import campis.dp1.models.Complaint;
import campis.dp1.models.ComplaintDisplay;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Boolean.TRUE;
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

public class ListController implements Initializable {
    private Main main;
    private ObservableList<Complaint> complaints;
    private ObservableList<ComplaintDisplay> complaintsView;
    private int selected_id;

    @FXML
    private Button searchButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<ComplaintDisplay> tableComplaint;
    @FXML
    private TableColumn<ComplaintDisplay,String> descriptionColumn;
    @FXML
    private TableColumn<ComplaintDisplay,Integer> idRequestColumn;
    @FXML
    private TableColumn<ComplaintDisplay,String> statusColumn;

    @FXML
    private void goCreateComplaint() throws IOException {
        main.showCreateComplaint();
    }

    @FXML
    private void goEditComplaint(ActionEvent event) throws IOException {
        ContextFX.getInstance().setId(selected_id);
        main.showEditComplaint();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableComplaint.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_complaint().getValue().intValue();
            }
        );
        try {
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getDescription());
            statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
            idRequestColumn.setCellValueFactory(cellData -> cellData.getValue().getId_request_order().asObject());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Complaint> getComplaints() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Complaint.class);
        List lista = criteria.list();
        ObservableList<Complaint> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Complaint)lista.get(i));
        }
        sessionFactory.close();
        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        complaints = FXCollections.observableArrayList();
        complaintsView = FXCollections.observableArrayList();
        complaints = getComplaints();
        for (int i = 0; i < complaints.size(); i++) {
            ComplaintDisplay complaint = new ComplaintDisplay(complaints.get(i).getId_complaint(), complaints.get(i).getDescription(), complaints.get(i).getStatus(), complaints.get(i).getId_request_order());
            complaintsView.add(complaint);
        }
        tableComplaint.setItems(null);
        tableComplaint.setItems(complaintsView);
    }

    private void deleteComplaint(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Complaint.class);
        Complaint complaint = new Complaint();
        complaint.setId_complaint(cod);
        session.delete(complaint);
        session.getTransaction().commit();
        sessionFactory.close();
    }

    @FXML
    private void deleteComplaint(ActionEvent event) throws SQLException, ClassNotFoundException {
        ContextFX.getInstance().setId(selected_id);
        Integer id_complaint = ContextFX.getInstance().getId();
        deleteComplaint(selected_id);
        for (int i = 0; i < complaints.size(); i++) {
            if (complaints.get(i).getId_complaint() == selected_id) {
                complaints.remove(i);
            }
        }
        loadData();
    }
}
