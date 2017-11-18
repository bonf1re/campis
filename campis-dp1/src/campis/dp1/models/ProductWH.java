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
@Table (name="productxwarehouse")
public class ProductWH { 
    @Id
    Integer id_product;
    Integer id_warehouse;
    Integer p_stock;
    Integer c_stock;

    public ProductWH() {
        super();
    }
}