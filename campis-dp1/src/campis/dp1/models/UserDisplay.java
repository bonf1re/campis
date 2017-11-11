package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marco
 */
public class UserDisplay {
    private final IntegerProperty id_user;
    private final StringProperty names;
    private final StringProperty email;
    private final StringProperty username;
    final StringProperty statusname;

    public UserDisplay(int id, String firstname, String lastname, String email, String username, boolean status) {
        String names = firstname + " "  + lastname;
        this.id_user = new SimpleIntegerProperty(id);
        this.names = new SimpleStringProperty(names);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.statusname = new SimpleStringProperty(((status) ? "Activo" : "Inactivo"));
    }
    
    public IntegerProperty idUserProperty() {
        return id_user;
    }

    public StringProperty namesProperty() {
        return names;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    /**
     * @return the statusname
     */
    public StringProperty getStatusname() {
        return statusname;
    }
}