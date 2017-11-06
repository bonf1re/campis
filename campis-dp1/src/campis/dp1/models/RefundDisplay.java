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
    final IntegerProperty id_complaint;
    final IntegerProperty id_request_order;
    final StringProperty status;
    final StringProperty type_refund;
    
    public RefundDisplay(Integer id_refund, Integer id_complaint, String status, String type_refund) {
        this.id_refund = new SimpleIntegerProperty(id_refund);
        this.id_complaint = new SimpleIntegerProperty(id_complaint);
        this.status = new SimpleStringProperty(status);
        this.type_refund = new SimpleStringProperty(type_refund);
        this.id_request_order = new SimpleIntegerProperty(Complaint.getComplaint(id_complaint).getId_request_order());
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
    public IntegerProperty getId_complaint() {
        return id_complaint;
    }

    /**
     * @return the status
     */
    public StringProperty getStatus() {
        return status;
    }

    /**
     * @return the type_refund
     */
    public StringProperty getType_refund() {
        return type_refund;
    }

    /**
     * @return the id_request_order
     */
    public IntegerProperty getId_request_order() {
        return id_request_order;
    }
}