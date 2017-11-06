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
@Table (name="refund")
public class Refund { 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_refund;
    Integer id_complaint;
    String type_refund;
    String status;

    public Refund() {
        super();
    }

    public Refund(String type_refund, Integer id_complaint) {
        this.type_refund = type_refund;
        this.status = "En proceso";
        this.id_complaint = id_complaint;
    }

    /**
     * @return the id_refund
     */
    public Integer getId_refund() {
        return id_refund;
    }

    /**
     * @param id_refund the id_refund to set
     */
    public void setId_refund(Integer id_refund) {
        this.id_refund = id_refund;
    }

    /**
     * @return the id_complaint
     */
    public Integer getId_complaint() {
        return id_complaint;
    }

    /**
     * @param id_complaint the id_complaint to set
     */
    public void setId_complaint(Integer id_complaint) {
        this.id_complaint = id_complaint;
    }

    /**
     * @return the type_refund
     */
    public String getType_refund() {
        return type_refund;
    }

    /**
     * @param type_refund the type_refund to set
     */
    public void setType_refund(String type_refund) {
        this.type_refund = type_refund;
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
}