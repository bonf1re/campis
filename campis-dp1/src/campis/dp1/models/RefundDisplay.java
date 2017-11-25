package campis.dp1.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Eddy
 */
public class RefundDisplay {
    final IntegerProperty id_refund;
    final IntegerProperty id_invoice;
    final StringProperty status;
    
    public RefundDisplay(Integer id_refund, Integer id_complaint, String status) {
        this.id_refund = new SimpleIntegerProperty(id_refund);
        this.id_invoice = new SimpleIntegerProperty(id_complaint);
        this.status = new SimpleStringProperty(status);
    }

    /**
     * @return the id_refund
     */
    public IntegerProperty getId_refund() {
        return id_refund;
    }

    /**
     * @return the id_complaint
     */
    public IntegerProperty getId_invoice() {
        return id_invoice;
    }

    /**
     * @return the status
     */
    public StringProperty getStatus() {
        return status;
    }
}