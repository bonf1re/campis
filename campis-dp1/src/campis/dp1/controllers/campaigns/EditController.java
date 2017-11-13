/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.campaigns;


import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Campaign;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.Query;
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
public class EditController implements Initializable {

    Main main;
    Integer id;
    
    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private JFXDatePicker pckBegin;

    @FXML
    private JFXDatePicker pckEnd;

    @FXML
    private JFXTextArea descrField;

    @FXML
    private JFXTextField nameField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = ContextFX.getInstance().getId();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Campaign.class);
        criteria.add(Restrictions.eq("id_campaign",id));
        List rsType = criteria.list();
        Campaign result = (Campaign)rsType.get(0);
        this.nameField.setText(result.getName());
        this.descrField.setText(result.getDescription());
        this.pckBegin.setValue(result.getInitial_date().toLocalDateTime().toLocalDate());
        this.pckEnd.setValue(result.getFinal_date().toLocalDateTime().toLocalDate());
        
        session.close();
        sessionFactory.close();
    }

    @FXML
    private void goListCampaigns() throws IOException{
        main.showListCampaigns();
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue()-1,
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
        
     @FXML
    private void saveCampaign (ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        
        
        Date date_init = getDate(pckBegin.getValue());
        Date date_end = getDate(pckEnd.getValue());
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("update Campaign set initial_date = :newinDate, final_date = :newenDate,"
                + "name=:newName, description=:newDescription where id_campaign = :ID");
        
        query.setParameter("newinDate", date_init);
        query.setParameter("newenDate", date_end);
        query.setParameter("newName",  nameField.getText());
        query.setParameter("newDescription", descrField.getText());

        query.setParameter("ID", id);
        
        int result = query.executeUpdate();
        
        /*Product product = new Product(nameField.getText(), descripField.getText(), 1, 1, Float.parseFloat(weightField.getText()),
                                     trademarkField.getText(), Float.parseFloat(priceField.getText()), measure, type);
        product.setId_product(this.id);
        session.update(product);
        session.save(product);*/
        
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        this.goListCampaigns();
    }
}