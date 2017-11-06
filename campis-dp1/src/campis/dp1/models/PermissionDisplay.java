package campis.dp1.models;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marco
 */
public class PermissionDisplay {

    public StringProperty getView() {
        return view;
    }

    public IntegerProperty getId_role() {
        return id_role;
    }

    public IntegerProperty getId_view() {
        return id_view;
    }

    public StringProperty getModify() {
        return modify;
    }

    public StringProperty getVisualize() {
        return visualize;
    }

    public IntegerProperty idPermissionProperty() {
        return id_permission;
    }

    private final IntegerProperty id_permission;
    private final StringProperty view;
    private final IntegerProperty id_role;
    private final IntegerProperty id_view;
    private final StringProperty modify;
    private final StringProperty visualize;
    
    public PermissionDisplay(Integer permission_id, Integer role_id, Integer view_id, Boolean modify, Boolean visualize) {
        this.id_permission = new SimpleIntegerProperty(permission_id);
        this.id_role = new SimpleIntegerProperty(role_id);
        this.id_view = new SimpleIntegerProperty(view_id);
        this.view = new SimpleStringProperty(getView(view_id));
        this.modify = ((modify) ? (new SimpleStringProperty("Asignado")) : (new SimpleStringProperty("No Asignado")));
        this.visualize = ((visualize) ? (new SimpleStringProperty("Asignado")) : (new SimpleStringProperty("No Asignado")));
    }

    public static String getView(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(View.class);
        criteria.add(Restrictions.eq("id_view",cod));
        String descrip;
        List rsType = criteria.list();
        View result = (View) rsType.get(0);
        descrip = result.getDescription();
        session.close();
        sessionFactory.close();

        return descrip;
    }
}
