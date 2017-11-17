/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.controllers.currencyIGV;

import campis.dp1.ContextFX;
import static campis.dp1.controllers.products.EditController.searchCodMeasure;
import static campis.dp1.controllers.products.EditController.searchCodType;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Eddy
 */
public class editController implements Initializable {

    @FXML
    private JFXTextField dolarField;
    @FXML
    private JFXTextField euroField;
    @FXML
    private JFXTextField igvField;
    @FXML
    private JFXCheckBox editDolar;
    @FXML
    private JFXCheckBox editEuro;
    @FXML
    private JFXCheckBox editIGV;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dolarField.setText(Float.toString(ContextFX.getInstance().getDollar()));
        this.dolarField.disableProperty().set(true);
        this.euroField.setText(Float.toString(ContextFX.getInstance().getEuro()));
        this.euroField.disableProperty().set(true);
        this.igvField.setText(Float.toString(ContextFX.getInstance().getIGV()));
        this.igvField.disableProperty().set(true);
    }

    private void updateCurrencyIGV(float dol, float eur, float igv) {
        Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("UPDATE campis.parameters SET igv = :newIGV,dollar = :newDollar,"
                    + "euro=:newEuro");
            query.setParameter("newIGV", igv);
            query.setParameter("newDollar", dol);
            query.setParameter("newEuro",eur);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
    }

    @FXML
    private void saveCurrencyIGV(ActionEvent event) {
        float dol = Float.parseFloat(this.dolarField.getText());
        float eur = Float.parseFloat(this.euroField.getText());
        float igv = Float.parseFloat(this.igvField.getText());
        updateCurrencyIGV(dol, eur, igv);
        this.dolarField.disableProperty().set(true);
        this.euroField.disableProperty().set(true);
        this.igvField.disableProperty().set(true);
        this.editDolar.selectedProperty().set(false);
        this.editEuro.selectedProperty().set(false);
        this.editIGV.selectedProperty().set(false);
    }

    @FXML
    private void updateDollar(ActionEvent event) {
        this.dolarField.disableProperty().set(false);
    }

    @FXML
    private void updateEuro(ActionEvent event) {
        this.euroField.disableProperty().set(false);
    }

    @FXML
    private void updateIGV(ActionEvent event) {
        this.igvField.disableProperty().set(false);
    }

}
