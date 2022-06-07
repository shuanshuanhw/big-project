package lib.sdlib.jsb.mark.entity;

import java.io.Serializable;

public class RoleDept implements Serializable {
    private Long role_id;

    private Long dept_id;

    private static final long serialVersionUID = 1L;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }
}