 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.reports;

import campis.dp1.ContextFX;
import campis.dp1.Main;
import campis.dp1.models.Product;
import campis.dp1.models.ProductType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 * FXML Controller class
 *
 * @author david
 */
public class ExpirationConfigController implements Initializable {   
    Main main;
 
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button generateBtn;

    @FXML
    private JFXDatePicker pckBegin;

    @FXML
    private JFXDatePicker pckEnd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setVariables() throws SQLException, ClassNotFoundException {
       ContextFX.getInstance().setInit_date(getDate(pckBegin.getValue()));
       ContextFX.getInstance().setEnd_date(getDate(pckEnd.getValue()));
    }
    
    private Date getDate(LocalDate value) {
        
        Calendar calendar = new GregorianCalendar(value.getYear(),
                                                    value.getMonthValue(),
                                                    value.getDayOfMonth());
        return calendar.getTime();
    }
    
    @FXML
    private void goGenerateReport(ActionEvent event) throws IOException, SQLException, ClassNotFoundException  {
        setVariables();
        main.showGenerateExpirationReport();
    }
}
