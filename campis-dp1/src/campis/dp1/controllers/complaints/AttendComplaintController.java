package campis.dp1.controllers.complaints;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Complaint;
import campis.dp1.models.Refund;
import campis.dp1.models.RefundLine;
import campis.dp1.models.RequestOrder;
import campis.dp1.models.RequestOrderLine;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
public class AttendComplaintController implements Initializable {
    Integer id;
    Integer id_request_oder;
    Main main;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private JFXTextField requestField;
    @FXML
    private JFXComboBox<String> typeField;
    @FXML
    private JFXComboBox<String> statusField;

    @FXML
    private void goListComplaint() throws IOException {
        main.showListComplaint();
    }
    
    private Integer getIdRefund(int id_com) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery qry = session.createSQLQuery("SELECT id_refund FROM campis.refund WHERE id_complaint="+id_com);
        List<Object> rows = qry.list();
        Integer id_ref = Integer.parseInt(rows.get(0).toString());
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        return id_ref;
    }
    
    @FXML
    private void goEditRefund() throws IOException {
        //if (selected_id > 0 && selected_status.equals("En proceso")) {
            id = getIdRefund(id);
            ContextFX.getInstance().setId(id);
            main.showEditRefund();
       // }
    }

    @FXML
    private void attendComplaint (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<RequestOrderLine> request_order_lines = RequestOrder.getRequestOrderLines(id_request_oder);
        SQLQuery query = session.createSQLQuery("update campis.complaint set status = :newstatus"
                + " where id_complaint = :oldIdProd");
        query.setParameter("newstatus", statusField.getValue());
        query.setParameter("oldIdProd", id);
        int result = query.executeUpdate();
        if (statusField.getValue().equals("Aceptado")) {
            Refund refund = new Refund(id);
            session.save(refund);
            for (int i = 0; i < request_order_lines.size(); i++) {
                RefundLine refund_line = new RefundLine(refund.getId_refund(),0);
                session.save(refund_line);
            }
        }
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListComplaint();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = ContextFX.getInstance().getId();
        typeField.getItems().addAll("Nota de crédito");
        typeField.getItems().addAll("Físico");
        statusField.getItems().addAll("Aceptado");
        statusField.getItems().addAll("Rechazado");
        requestField.setText(Integer.toString(id));
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        SQLQuery qry = session.createSQLQuery("SELECT * FROM campis.complaint WHERE id_complaint =" + id);
        /*Criteria criteria = session.createCriteria(Complaint.class);
        criteria.add(Restrictions.eq("id_complaint", id));
        List rsType = criteria.list();*/
       
        List<Object[]> rows = qry.list();
        Complaint result = new Complaint();
        for (Object[] row : rows) {
             result = new Complaint(row[1].toString(),Integer.parseInt(row[3].toString()));
        }
        id_request_oder = result.getId_request_order();
        this.requestField.setText(Integer.toString(result.getId_request_order()));
        this.descriptionField.setText(result.getDescription());
        session.close();
        sessionFactory.close();
    }
}