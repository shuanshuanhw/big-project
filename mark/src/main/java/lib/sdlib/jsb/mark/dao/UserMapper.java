package lib.sdlib.jsb.mark.dao;

import java.util.List;

import lib.sdlib.jsb.mark.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserMapper {
    @Delete({
        "delete from user",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long user_id);

    @Insert({
        "insert into user (user_id, dept_id, ",
        "login_name, user_name, ",
        "user_type, email, ",
        "phonenumber, sex, avatar, ",
        "password, salt, ",
        "status, del_flag, login_ip, ",
        "login_date, pwd_update_date, ",
        "create_by, create_time, ",
        "update_by, update_time, ",
        "remark)",
        "values (#{user_id,jdbcType=BIGINT}, #{dept_id,jdbcType=BIGINT}, ",
        "#{login_name,jdbcType=VARCHAR}, #{user_name,jdbcType=VARCHAR}, ",
        "#{user_type,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{phonenumber,jdbcType=VARCHAR}, #{sex,jdbcType=CHAR}, #{avatar,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=LONGVARBINARY}, #{salt,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=CHAR}, #{del_flag,jdbcType=CHAR}, #{login_ip,jdbcType=VARCHAR}, ",
        "#{login_date,jdbcType=TIMESTAMP}, #{pwd_update_date,jdbcType=TIMESTAMP}, ",
        "#{create_by,jdbcType=VARCHAR}, #{create_time,jdbcType=TIMESTAMP}, ",
        "#{update_by,jdbcType=VARCHAR}, #{update_time,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(User row);


    @Select({
        "select",
        "user_id, dept_id, login_name, user_name, user_type, email, phonenumber, sex, ",
        "avatar, password, salt, status, del_flag, login_ip, login_date, pwd_update_date, ",
        "create_by, create_time, update_by, update_time, remark",
        "from user",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dept_id", property="dept_id", jdbcType=JdbcType.BIGINT),
        @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_type", property="user_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phonenumber", property="phonenumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="login_ip", property="login_ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_date", property="login_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pwd_update_date", property="pwd_update_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Long user_id);

    @Select({
        "select",
        "user_id, dept_id, login_name, user_name, user_type, email, phonenumber, sex, ",
        "avatar, password, salt, status, del_flag, login_ip, login_date, pwd_update_date, ",
        "create_by, create_time, update_by, update_time, remark",
        "from user"
    })
    @Results({
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dept_id", property="dept_id", jdbcType=JdbcType.BIGINT),
        @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="user_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_type", property="user_type", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phonenumber", property="phonenumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.CHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="login_ip", property="login_ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_date", property="login_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="pwd_update_date", property="pwd_update_date", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set dept_id = #{dept_id,jdbcType=BIGINT},",
          "login_name = #{login_name,jdbcType=VARCHAR},",
          "user_name = #{user_name,jdbcType=VARCHAR},",
          "user_type = #{user_type,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phonenumber = #{phonenumber,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=CHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=LONGVARBINARY},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR},",
          "del_flag = #{del_flag,jdbcType=CHAR},",
          "login_ip = #{login_ip,jdbcType=VARCHAR},",
          "login_date = #{login_date,jdbcType=TIMESTAMP},",
          "pwd_update_date = #{pwd_update_date,jdbcType=TIMESTAMP},",
          "create_by = #{create_by,jdbcType=VARCHAR},",
          "create_time = #{create_time,jdbcType=TIMESTAMP},",
          "update_by = #{update_by,jdbcType=VARCHAR},",
          "update_time = #{update_time,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where user_id = #{user_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User row);
}