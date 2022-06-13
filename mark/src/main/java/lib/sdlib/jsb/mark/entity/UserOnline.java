package lib.sdlib.jsb.mark.entity;

import java.io.Serializable;
import java.util.Date;

public class UserOnline implements Serializable {
    private String sessionId;

    private String login_name;

    private String dept_name;

    private String ipaddr;

    private String login_location;

    private String browser;

    private String os;

    private String status;

    private Date start_timestamp;

    private Date last_access_time;

    private Integer expire_time;

    private static final long serialVersionUID = 1L;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name == null ? null : dept_name.trim();
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr == null ? null : ipaddr.trim();
    }

    public String getLogin_location() {
        return login_location;
    }

    public void setLogin_location(String login_location) {
        this.login_location = login_location == null ? null : login_location.trim();
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(Date start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public Date getLast_access_time() {
        return last_access_time;
    }

    public void setLast_access_time(Date last_access_time) {
        this.last_access_time = last_access_time;
    }

    public Integer getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Integer expire_time) {
        this.expire_time = expire_time;
    }
}