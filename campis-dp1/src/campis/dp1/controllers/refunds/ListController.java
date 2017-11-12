package campis.dp1.controllers.refunds;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundDisplay;
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
    private ObservableList<Refund> refunds;
    private ObservableList<RefundDisplay> refundsView;
    private int selected_id;
    private String selected_status;

    @FXML
    private TableView<RefundDisplay> tableRefund;
    @FXML
    private TableColumn<RefundDisplay,String> typeRefundColumn;
    @FXML
    private TableColumn<RefundDisplay,Integer> idComplaintColumn;
    @FXML
    private TableColumn<RefundDisplay,Integer> idRequestOrderColumn;
    @FXML
    private TableColumn<RefundDisplay,String> statusColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        tableRefund.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            this.selected_id = newValue.getId_refund().getValue().intValue();
            this.selected_status = newValue.getStatus().getValue().toString();
            }
        );
        try {
            typeRefundColumn.setCellValueFactory(cellData -> cellData.getValue().getType_refund());
            statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
            idComplaintColumn.setCellValueFactory(cellData -> cellData.getValue().getId_complaint().asObject());
            idRequestOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getId_request_order().asObject());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Refund> getRefunds() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Refund.class);
        List lista = criteria.list();
        ObservableList<Refund> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Refund)lista.get(i));
        }
        session.close();
        sessionFactory.close();

        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        refunds = FXCollections.observableArrayList();
        refundsView = FXCollections.observableArrayList();
        refunds = getRefunds();
        for (int i = 0; i < refunds.size(); i++) {
            RefundDisplay complaint = new RefundDisplay(refunds.get(i).getId_refund(), refunds.get(i).getId_complaint(), refunds.get(i).getStatus(), refunds.get(i).getType_refund());
            refundsView.add(complaint);
        }
        tableRefund.setItems(null);
        tableRefund.setItems(refundsView);
    }

    @FXML
    private void goEditRefund(ActionEvent event) throws IOException {
        if (selected_id > 0) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRefund();
        }
    }
}