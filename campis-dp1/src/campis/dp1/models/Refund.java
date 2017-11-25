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
    Integer id_invoice;
    String status;

    public Refund() {
        super();
    }

    public Refund(Integer id_invoice) {
        this.status = "Por Llegar";
        this.id_invoice = id_invoice;
    }
    
    public Refund(Integer idrefund,Integer id_invoice) {
        this.status = "Por Llegar";
        this.id_invoice = id_invoice;
        this.id_refund = idrefund;
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
    public Integer getId_invoice() {
        return id_invoice;
    }

    /**
     * @param id_complaint the id_complaint to set
     */
    public void setId_invoice(Integer id_invoice) {
        this.id_invoice = id_invoice;
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