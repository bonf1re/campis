package campis.dp1.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Marco
 */

public class ClientDisplay {
    private final IntegerProperty id_client;
    private final StringProperty dni;
    private final StringProperty name;
    private final StringProperty ruc;
    private final StringProperty email;
    private final StringProperty phone;

    public ClientDisplay(int id, String name, String dni, String ruc, String email, String phone) {
        this.id_client = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dni = new SimpleStringProperty(dni);
        this.ruc = new SimpleStringProperty(ruc);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    /**
     * @return the id_client
     */
    public IntegerProperty getId_client() {
        return id_client;
    }

    /**
     * @return the name
     */
    public StringProperty getName() {
        return name;
    }
    
    /**
     * @return the dni
     */
    public StringProperty getDni() {
        return dni;
    }

    /**
     * @return the ruc
     */
    public StringProperty getRuc() {
        return ruc;
    }

    /**
     * @return the email
     */
    public StringProperty getEmail() {
        return email;
    }

    /**
     * @return the phone
     */
    public StringProperty getPhone() {
        return phone;
    }
}
