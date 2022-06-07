package lib.sdlib.jsb.mark.entity;

import java.io.Serializable;

public class RoleMenu implements Serializable {
    private Long role_id;

    private Long menu_id;

    private static final long serialVersionUID = 1L;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }
}