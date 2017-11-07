package campis.dp1.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eddy
 */
@Entity
@Table (name="refund_line")
public class RefundLine { 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_refund_line;
    Integer id_request_order_line;
    Integer id_refund;
    Integer quantity;

    public RefundLine() {
        super();
    }

    public RefundLine(Integer id_request_order_line, Integer id_refund) {
        this.id_request_order_line = id_request_order_line;
        this.quantity = 0;
        this.id_refund = id_refund;
    }  

    /**
     * @return the id_refund_line
     */
    public Integer getId_refund_line() {
        return id_refund_line;
    }

    /**
     * @param id_refund_line the id_refund_line to set
     */
    public void setId_refund_line(Integer id_refund_line) {
        this.id_refund_line = id_refund_line;
    }

    /**
     * @return the id_request_order_line
     */
    public Integer getId_request_order_line() {
        return id_request_order_line;
    }

    /**
     * @param id_request_order_line the id_request_order_line to set
     */
    public void setId_request_order_line(Integer id_request_order_line) {
        this.id_request_order_line = id_request_order_line;
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
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}