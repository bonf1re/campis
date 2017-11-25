/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.campaigns;

import campis.dp1.Main;
import campis.dp1.models.Campaign;
import campis.dp1.models.utils.GraphicsUtils;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * FXML Controller class
 *
 * @author david
 */
public class CreateController implements Initializable {

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
    
    @FXML
    private Label errorMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.errorMessage.setText("");
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
    private void insertCampaign(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        //converting to timestamps
        SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_init = getDate(pckBegin.getValue());
        Date date_end = getDate(pckEnd.getValue());
        
        GraphicsUtils g = new GraphicsUtils();
        
        if (date_init.after(date_end)) {
            g.popupError("Error", "Fechas ingresadas no válidas.", "OK");
            //this.errorMessage.setText("Fechas ingresadas no válidas.");
            return;
        }
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();   
        
        Campaign c = new Campaign(nameField.getText(),
                                             descrField.getText(),
                                             Timestamp.valueOf(formatIn.format(date_init)),
                                             Timestamp.valueOf(formatIn.format(date_end)));
        
        
        session.save(c);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close(); 
        this.goListCampaigns();
    }
}
