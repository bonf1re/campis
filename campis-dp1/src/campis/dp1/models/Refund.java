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
}