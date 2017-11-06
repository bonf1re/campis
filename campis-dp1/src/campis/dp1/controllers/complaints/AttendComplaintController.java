package campis.dp1.controllers.complaints;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Complaint;
import campis.dp1.models.Refund;
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
    Main main;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private JFXComboBox requestField;
    @FXML
    private JFXComboBox<String> typeField;
    @FXML
    private JFXComboBox<String> statusField;

    @FXML
    private void goListComplaint() throws IOException {
        main.showListComplaint();
    }

    @FXML
    private void attendComplaint (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Complaint set status = :newstatus"
                + " where id_complaint = :oldIdProd");
        query.setParameter("newstatus", statusField.getValue());
        query.setParameter("oldIdProd", id);
        int result = query.executeUpdate();
        if (statusField.getValue().equals("Aceptado")) {
            Refund refund = new Refund(typeField.getValue(), id);
            session.save(refund);
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
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Complaint.class);
        criteria.add(Restrictions.eq("id_complaint", id));
        List rsType = criteria.list();
        Complaint result = (Complaint)rsType.get(0);
        this.requestField.setValue(result.getId_request_order());
        this.descriptionField.setText(result.getDescription());
    }
}
