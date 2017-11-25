package campis.dp1.controllers.refunds;

import campis.dp1.Main;
import campis.dp1.ContextFX;
import campis.dp1.models.Permission;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundDisplay;
import campis.dp1.models.View;
import campis.dp1.models.utils.GraphicsUtils;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ListController implements Initializable {

    private Main main;
    private ObservableList<Refund> refunds;
    private ObservableList<RefundDisplay> refundsView;
    private int selected_id;
    private String selected_status;
    private int id_role;

    @FXML
    private Button editButton;
    @FXML
    private TableView<RefundDisplay> tableRefund;
    @FXML
    private TableColumn<RefundDisplay, Integer> idComplaintColumn;
    @FXML
    private TableColumn<RefundDisplay, Integer> idRequestOrderColumn;
    @FXML
    private TableColumn<RefundDisplay, String> statusColumn;
    @FXML
    private Button createButton;
    @FXML
    private Button confirmButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.selected_id = 0;
        id_role = (ContextFX.getInstance().getUser().getId_role());
        View whView = View.getView("refunds");

        if (!Permission.canModify(id_role, whView.getId_view())) {
            editButton.setVisible(false);
        }
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
            idRequestOrderColumn.setCellValueFactory(cellData -> cellData.getValue().getId_invoice().asObject());
            statusColumn.setCellValueFactory(cellData -> cellData.getValue().getStatus());
            idComplaintColumn.setCellValueFactory(cellData -> cellData.getValue().getId_refund().asObject());
            /**/
            loadData();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ObservableList<Refund> getRefunds() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Refund.class);
        List lista = criteria.list();
        ObservableList<Refund> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((Refund) lista.get(i));
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
            RefundDisplay complaint = new RefundDisplay(refunds.get(i).getId_refund(), refunds.get(i).getId_invoice(), refunds.get(i).getStatus());
            refundsView.add(complaint);
        }
        tableRefund.setItems(null);
        tableRefund.setItems(refundsView);
    }

    @FXML
    private void goEditRefund(ActionEvent event) throws IOException {
        if (selected_id > 0 && selected_status.equals("En proceso")) {
            ContextFX.getInstance().setId(selected_id);
            main.showEditRefund();
        }
    }

    @FXML
    private void goCreateRefund(ActionEvent event) throws IOException {
        main.showCreateRefund();
    }

    private Refund getRefund(int id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery qry = session.createSQLQuery("SELECT DISTINCT * FROM campis.refund WHERE id_refund = " + id);
        List<Object[]> rows = qry.list();
        Refund returnable = new Refund();
        for (Object[] row : rows) {
            returnable = new Refund(Integer.parseInt(row[0].toString()), Integer.parseInt(row[1].toString()));
        }
        return returnable;
    }

    @FXML
    private void goConfirm(ActionEvent event) throws SQLException, ClassNotFoundException {
        Refund refaux = getRefund(selected_id);
        if (refaux.getStatus().compareTo("Por Ingresar") == 0) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Refund.class);
            Refund ref = new Refund();
            ref.setId_refund(refaux.getId_refund());
            session.delete(ref);
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            loadData();
        }else{
            GraphicsUtils gu = new GraphicsUtils();
            gu.popupError("Error de Devoluciones", "Error de estado de devolución, debe ser Por Llegar", "Aceptar");
            loadData();
        }
    }
}
