package lib.sdlib.jsb.mark.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DataSdlibStati implements Serializable,Comparable<DataSdlibStati> {
    private Integer id;

    private String agv_name;

    private String clientip;

    private Date create_time;

    private String name;

    private String opinion;

    private String phone;

    private String name_implication;

    private Boolean ifSelect = false;//用来传递最后有选择的数据
    private String ifSelectString;//用来传递最后有选择的提示语

    private  int score;
    private String ifId;//

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgv_name() {
        return agv_name;
    }

    public void setAgv_name(String agv_name) {
        this.agv_name = agv_name == null ? null : agv_name.trim();
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip == null ? null : clientip.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName_implication() {
        return name_implication;
    }

    public void setName_implication(String name_implication) {
        this.name_implication = name_implication == null ? null : name_implication.trim();
    }

    @Override
    public int compareTo(DataSdlibStati o) {
        return o.getScore() - this.getScore();
    }
}