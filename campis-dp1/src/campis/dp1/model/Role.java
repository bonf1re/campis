/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package campis.dp1.model;

/**
 *
 * @author Marco
 */
public class Role {
    String description;
    Integer role_id;
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getRoleId() {
        return role_id;
    }

    public void setRoleId(Integer role_id) {
        this.role_id = role_id;
    }

    public Role(String description) {
        this.description = description;
    }

    public Role() {
    }
}
