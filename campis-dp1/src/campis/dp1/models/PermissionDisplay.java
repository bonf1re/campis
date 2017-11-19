package campis.dp1.models;

import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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

    public BooleanProperty getModify() {
        return modify;
    }

    public BooleanProperty getVisualize() {
        return visualize;
    }

    public IntegerProperty idPermissionProperty() {
        return id_permission;
    }

    private final IntegerProperty id_permission;
    private final StringProperty view;
    private final IntegerProperty id_role;
    private final IntegerProperty id_view;
    private final BooleanProperty modify;
    private final BooleanProperty visualize;
    
    public PermissionDisplay(Integer permission_id, Integer role_id, Integer view_id, Boolean modify, Boolean visualize) {
        this.id_permission = new SimpleIntegerProperty(permission_id);
        this.id_role = new SimpleIntegerProperty(role_id);
        this.id_view = new SimpleIntegerProperty(view_id);
        this.view = new SimpleStringProperty(getView(view_id));
        this.modify = new SimpleBooleanProperty(modify);;
        this.visualize = new SimpleBooleanProperty(visualize);;
    }

    public static String getView(int cod) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String queryStr = "select description as description\n" +
                            "from campis.view\n" +
                            " WHERE id_view =" + cod;
        SQLQuery query = session.createSQLQuery(queryStr);
        List list = query.list();
        String returnable = (String) list.get(0);

        session.close();
        sessionFactory.close();

        return returnable;
    }
}
