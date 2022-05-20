package test.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final User user = new User();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = user.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> username = user.username;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> truename = user.truename;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> department = user.department;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> phone = user.phone;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> email = user.email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> company = user.company;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> role = user.role;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> if_del = user.if_del;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> if_admin = user.if_admin;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> ifdel = user.ifdel;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> token = user.token;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> sort = user.sort;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<byte[]> password = user.password;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class User extends AliasableSqlTable<User> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> username = column("username", JDBCType.CHAR);

        public final SqlColumn<String> truename = column("truename", JDBCType.CHAR);

        public final SqlColumn<String> department = column("department", JDBCType.CHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.CHAR);

        public final SqlColumn<String> email = column("Email", JDBCType.CHAR);

        public final SqlColumn<String> company = column("company", JDBCType.CHAR);

        public final SqlColumn<String> role = column("role", JDBCType.CHAR);

        public final SqlColumn<String> if_del = column("if_del", JDBCType.CHAR);

        public final SqlColumn<String> if_admin = column("if_admin", JDBCType.CHAR);

        public final SqlColumn<String> ifdel = column("ifdel", JDBCType.CHAR);

        public final SqlColumn<String> token = column("token", JDBCType.VARCHAR);

        public final SqlColumn<Integer> sort = column("sort", JDBCType.INTEGER);

        public final SqlColumn<byte[]> password = column("password", JDBCType.LONGVARBINARY);

        public User() {
            super("user", User::new);
        }
    }
}