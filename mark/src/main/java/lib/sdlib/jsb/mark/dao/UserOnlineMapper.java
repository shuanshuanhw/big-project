package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.UserOnline;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface UserOnlineMapper {
    @Delete({
        "delete from user_online",
        "where sessionId = #{sessionId,jdbcType=VARCHAR}"
    })
    int deleteOnlineById(String sessionId);

    @Insert({
        "insert into user_online (sessionId, login_name, ",
        "dept_name, ipaddr, ",
        "login_location, browser, ",
        "os, status, start_timestamp, ",
        "last_access_time, expire_time)",
        "values (#{sessionId,jdbcType=VARCHAR}, #{login_name,jdbcType=VARCHAR}, ",
        "#{dept_name,jdbcType=VARCHAR}, #{ipaddr,jdbcType=VARCHAR}, ",
        "#{login_location,jdbcType=VARCHAR}, #{browser,jdbcType=VARCHAR}, ",
        "#{os,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, #{start_timestamp,jdbcType=TIMESTAMP}, ",
        "#{last_access_time,jdbcType=TIMESTAMP}, #{expire_time,jdbcType=INTEGER})"
    })
    int saveOnline(UserOnline row);

    @Select({
        "select",
        "sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, ",
        "start_timestamp, last_access_time, expire_time",
        "from user_online",
        "where sessionId = #{sessionId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="sessionId", property="sessionId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="ipaddr", property="ipaddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_location", property="login_location", jdbcType=JdbcType.VARCHAR),
        @Result(column="browser", property="browser", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_timestamp", property="start_timestamp", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_access_time", property="last_access_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="expire_time", property="expire_time", jdbcType=JdbcType.INTEGER)
    })
    UserOnline selectOnlineById(String sessionId);


    @Select({
            "select",
            "sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, ",
            "start_timestamp, last_access_time, expire_time",
            "from user_online",
            "where login_name like concat('%', #{login_name,jdbcType=VARCHAR}, '%')"
    })
    @Results({
            @Result(column="sessionId", property="sessionId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="ipaddr", property="ipaddr", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_location", property="login_location", jdbcType=JdbcType.VARCHAR),
            @Result(column="browser", property="browser", jdbcType=JdbcType.VARCHAR),
            @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_timestamp", property="start_timestamp", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_access_time", property="last_access_time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="expire_time", property="expire_time", jdbcType=JdbcType.INTEGER)
    })
    public List<UserOnline> selectUserOnlineList(UserOnline userOnline);

    @Select({
            "select",
            "sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, ",
            "start_timestamp, last_access_time, expire_time",
            "from user_online",
            "where last_access_time < #{last_access_time}"
    })
    @Results({
            @Result(column="sessionId", property="sessionId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
            @Result(column="ipaddr", property="ipaddr", jdbcType=JdbcType.VARCHAR),
            @Result(column="login_location", property="login_location", jdbcType=JdbcType.VARCHAR),
            @Result(column="browser", property="browser", jdbcType=JdbcType.VARCHAR),
            @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
            @Result(column="start_timestamp", property="start_timestamp", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_access_time", property="last_access_time", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="expire_time", property="expire_time", jdbcType=JdbcType.INTEGER)
    })
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime);

    @Select({
        "select",
        "sessionId, login_name, dept_name, ipaddr, login_location, browser, os, status, ",
        "start_timestamp, last_access_time, expire_time",
        "from user_online"
    })
    @Results({
        @Result(column="sessionId", property="sessionId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="login_name", property="login_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="ipaddr", property="ipaddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_location", property="login_location", jdbcType=JdbcType.VARCHAR),
        @Result(column="browser", property="browser", jdbcType=JdbcType.VARCHAR),
        @Result(column="os", property="os", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="start_timestamp", property="start_timestamp", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_access_time", property="last_access_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="expire_time", property="expire_time", jdbcType=JdbcType.INTEGER)
    })
    List<UserOnline> selectAll();

    @Update({
        "update user_online",
        "set login_name = #{login_name,jdbcType=VARCHAR},",
          "dept_name = #{dept_name,jdbcType=VARCHAR},",
          "ipaddr = #{ipaddr,jdbcType=VARCHAR},",
          "login_location = #{login_location,jdbcType=VARCHAR},",
          "browser = #{browser,jdbcType=VARCHAR},",
          "os = #{os,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "start_timestamp = #{start_timestamp,jdbcType=TIMESTAMP},",
          "last_access_time = #{last_access_time,jdbcType=TIMESTAMP},",
          "expire_time = #{expire_time,jdbcType=INTEGER}",
        "where sessionId = #{sessionId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserOnline row);
}