package campis.dp1.controllers.refunds;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import campis.dp1.models.RefundLineDisplay;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

/** 
 *
 * @author Gina Bustamante
 */
public class EditController implements Initializable {
    Integer id;
    Main main;
    int selected_id;
    private ObservableList<RefundLineDisplay> refundLinesView;
    private ObservableList<RefundLine> refundLines;
    @FXML
    private JFXComboBox<String> statusField;
    @FXML
    private TableView<RefundLineDisplay> tableRefundLine;
    @FXML
    private TableColumn<RefundLineDisplay,Integer> orderRequestQuantityColumn;
    @FXML
    private TableColumn<RefundLineDisplay,Integer> quantityColumn;
    @FXML
    private TableColumn<RefundLineDisplay,String> productColumn;

    @FXML
    private void goListRefunds() throws IOException {
        main.showListRefund();
    }

    @FXML
    private void editRefund (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (int i = 0; i < refundLinesView.size(); i++) {
            Query query = session.createQuery("update RefundLine set quantity = :newQuantity"
                + " where id_refund_line = :IdRefundLine");
            query.setParameter("newQuantity", refundLinesView.get(i).getQuantity().intValue());
            query.setParameter("IdRefundLine", refundLinesView.get(i).getId_refund_line().intValue());
            int result = query.executeUpdate();
        }

        Query query = session.createQuery("update Refund set status= :newStatus"
                + " where id_refund = :IdRefund");
            query.setParameter("newStatus", statusField.getValue());
            query.setParameter("IdRefund", id);
            int result = query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListRefunds();
        
    }
    
    private ObservableList<RefundLine> getRefundLines() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(RefundLine.class);
        criteria.add(Restrictions.eq("id_refund", id));
        List lista = criteria.list();
        ObservableList<RefundLine> returnable;
        returnable = FXCollections.observableArrayList();
        for (int i = 0; i < lista.size(); i++) {
            returnable.add((RefundLine)lista.get(i));
        }
        session.close();
        sessionFactory.close();

        return returnable;
    }

    private void loadData() throws SQLException, ClassNotFoundException {
        refundLines = FXCollections.observableArrayList();
        refundLinesView = FXCollections.observableArrayList();
        refundLines = getRefundLines();
        for (int i = 0; i < refundLines.size(); i++) {
            RefundLineDisplay complaint = new RefundLineDisplay(refundLines.get(i).getId_refund_line(), refundLines.get(i).getId_request_order_line(), refundLines.get(i).getQuantity());
            refundLinesView.add(complaint);
        }
        tableRefundLine.setItems(null);
        tableRefundLine.setItems(refundLinesView);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.id = ContextFX.getInstance().getId();
        statusField.getItems().addAll("En proceso");
        statusField.getItems().addAll("Entregado");

        try {
            productColumn.setCellValueFactory(cellData -> cellData.getValue().getProduct_name());
            orderRequestQuantityColumn.setCellValueFactory(cellData -> cellData.getValue().getOrder_request_quantity().asObject());
            
            quantityColumn.setCellFactory(
                TextFieldTableCell.<RefundLineDisplay,Integer>forTableColumn(new StringConverter<Integer>(){
                    @Override
                    public String toString(Integer value){
                        return value.toString();
                    }
                    @Override
                    public Integer fromString(String string){
                        return Integer.parseInt(string);
                    }
                }));
            quantityColumn.setCellValueFactory(
                    cellData->cellData.getValue().getQuantity().asObject()
            );

            tableRefundLine.setEditable(true);
            
            
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            
            loadData();
            
            session.close();;
            sessionFactory.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}