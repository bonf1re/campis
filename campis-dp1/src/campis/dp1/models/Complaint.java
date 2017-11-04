package campis.dp1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Eddy
 */
@Entity
@Table (name="complaint")
public class Complaint { 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_complaint;
    String description;
    String status;
    Integer id_request_order;

    public Complaint() {
        super();
    }
    
    public Complaint(String description, Integer id_request_order) {
        this.description = description;
        this.status = "En trámite";
        this.id_request_order = id_request_order;
    }

    public Integer getId_complaint() {
        return id_complaint;
    }

    public void setId_complaint(Integer id_complaint) {
        this.id_complaint = id_complaint;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the id_request_order
     */
    public Integer getId_request_order() {
        return id_request_order;
    }

    /**
     * @param id_request_order the id_request_order to set
     */
    public void setId_request_order(Integer id_request_order) {
        this.id_request_order = id_request_order;
    }
}