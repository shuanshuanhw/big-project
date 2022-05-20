package jap.test.testjpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 100)
    String token;//批准token

    @Column(length = 50)
    String username;

    @Column(length = 50)
    String password;

    @Column(length = 50)
    String truename;

    @Column(length = 50)
    String department;

    @Column(length = 50)
    String phone;

    @Column(length = 50)
    String Email;

    @Column(length = 50)
    String company;

    @Column(name = "`role`",length = 50)
    String role;

    @Column(length = 50)
    String if_del;

    @Column(columnDefinition="char(10) default '0'")
    String ifdel;

    @Column(length = 50)
    String if_admin;

    // 这个是专门用来排序用的
    @Column(columnDefinition="int default 0")
    private int sort;

    @Override
    public int compareTo(User o) {
        return o.getSort() - this.getSort();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", truename='" + truename + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", Email='" + Email + '\'' +
                ", company='" + company + '\'' +
                ", role='" + role + '\'' +
                ", if_del='" + if_del + '\'' +
                ", ifdel='" + ifdel + '\'' +
                ", if_admin='" + if_admin + '\'' +
                ", sort=" + sort +
                '}';
    }
}
