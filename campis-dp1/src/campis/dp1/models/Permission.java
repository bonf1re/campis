package campis.dp1.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
}