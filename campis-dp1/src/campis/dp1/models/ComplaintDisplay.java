package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marco
 */

public class ComplaintDisplay {
    final IntegerProperty id_complaint;
    final StringProperty description;
    final StringProperty status;
    final IntegerProperty id_request_order;

    public ComplaintDisplay(int id, String description, String status, int id_request_order) {
        this.id_complaint = new SimpleIntegerProperty(id);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleStringProperty(status);
        this.id_request_order = new SimpleIntegerProperty(id_request_order);
    }

    /**
     * @return the id_complaint
     */
    public IntegerProperty getId_complaint() {
        return id_complaint;
    }

    /**
     * @return the description
     */
    public StringProperty getDescription() {
        return description;
    }

    /**
     * @return the status
     */
    public StringProperty getStatus() {
        return status;
    }

    /**
     * @return the id_request_order
     */
    public IntegerProperty getId_request_order() {
        return id_request_order;
    }
}