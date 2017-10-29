package campis.dp1.models;

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

    public UserDisplay(String firstname, String lastname, String email, String username) {
        String names = firstname + ", " + lastname;
        this.names = new SimpleStringProperty(names);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
    }
    
    public IntegerProperty idUserProperty() {
        return id_product;
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
}