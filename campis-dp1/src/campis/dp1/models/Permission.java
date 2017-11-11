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
 * @author Marco
 */
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id_permission;
    Integer id_view;
    Integer id_role;
    Boolean visualize;
    Boolean modify;
    
    public Permission(){
        super();
    }

    public Permission(Integer id_view, Integer id_role) {
        this.id_role = id_role;
        this.id_view = id_view;
        this.visualize = false;
        this.modify = false;
    }
    
    public Integer getId_permission() {
        return id_permission;
    }

    public void setId_permission(Integer id_permission) {
        this.id_permission = id_permission;
    }

    /**
     * @return the id_view
     */
    public Integer getId_view() {
        return id_view;
    }

    /**
     * @param id_view the id_view to set
     */
    public void setId_view(Integer id_view) {
        this.id_view = id_view;
    }

    /**
     * @return the id_role
     */
    public Integer getId_role() {
        return id_role;
    }

    /**
     * @param id_role the id_role to set
     */
    public void setId_role(Integer id_role) {
        this.id_role = id_role;
    }

    /**
     * @return the visualize
     */
    public Boolean getVisualize() {
        return visualize;
    }

    /**
     * @param visualize the visualize to set
     */
    public void setVisualize(Boolean visualize) {
        this.visualize = visualize;
    }

    /**
     * @return the modify
     */
    public Boolean getModify() {
        return modify;
    }

    /**
     * @param modify the modify to set
     */
    public void setModify(Boolean modify) {
        this.modify = modify;
    }

    public static boolean canVisualize(Integer id_role, Integer id_view) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(Restrictions.eq("id_view",id_view));
        criteria.add(Restrictions.eq("id_role",id_role));
        String descrip;
        List rsMeasure = criteria.list();
        if (rsMeasure.size() > 0) {
            Permission result = (Permission)rsMeasure.get(0);

            return result.getVisualize();
        }
        sessionFactory.close();

        return false;
    }

    public static boolean canModify(Integer id_role, Integer id_view) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Permission.class);
        criteria.add(Restrictions.eq("id_view",id_view));
        criteria.add(Restrictions.eq("id_role",id_role));
        String descrip;
        List rsMeasure = criteria.list();
        if (rsMeasure.size() > 0) {
            Permission result = (Permission)rsMeasure.get(0);

            return result.getModify();
        }
        sessionFactory.close();

        return false;
    }
}