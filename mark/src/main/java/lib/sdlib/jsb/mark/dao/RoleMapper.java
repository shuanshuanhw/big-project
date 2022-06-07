package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface RoleMapper {
    @Delete({
        "delete from role",
        "where role_id = #{role_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long role_id);

    @Insert({
        "insert into role (role_id, role_name, ",
        "role_key, role_sort, ",
        "data_scope, status, del_flag, ",
        "create_by, create_time, ",
        "update_by, update_time, ",
        "remark)",
        "values (#{role_id,jdbcType=BIGINT}, #{role_name,jdbcType=VARCHAR}, ",
        "#{role_key,jdbcType=VARCHAR}, #{role_sort,jdbcType=INTEGER}, ",
        "#{data_scope,jdbcType=CHAR}, #{status,jdbcType=CHAR}, #{del_flag,jdbcType=CHAR}, ",
        "#{create_by,jdbcType=VARCHAR}, #{create_time,jdbcType=TIMESTAMP}, ",
        "#{update_by,jdbcType=VARCHAR}, #{update_time,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Role row);

    @Select({
        "select",
        "role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, ",
        "create_time, update_by, update_time, remark",
        "from role",
        "where role_id = #{role_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="role_id", property="role_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_name", property="role_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="role_key", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_sort", property="role_sort", jdbcType=JdbcType.INTEGER),
        @Result(column="data_scope", property="data_scope", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Long role_id);

    @Select({
        "select",
        "role_id, role_name, role_key, role_sort, data_scope, status, del_flag, create_by, ",
        "create_time, update_by, update_time, remark",
        "from role"
    })
    @Results({
        @Result(column="role_id", property="role_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_name", property="role_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_key", property="role_key", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_sort", property="role_sort", jdbcType=JdbcType.INTEGER),
        @Result(column="data_scope", property="data_scope", jdbcType=JdbcType.CHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectAll();

    @Update({
        "update role",
        "set role_name = #{role_name,jdbcType=VARCHAR},",
          "role_key = #{role_key,jdbcType=VARCHAR},",
          "role_sort = #{role_sort,jdbcType=INTEGER},",
          "data_scope = #{data_scope,jdbcType=CHAR},",
          "status = #{status,jdbcType=CHAR},",
          "del_flag = #{del_flag,jdbcType=CHAR},",
          "create_by = #{create_by,jdbcType=VARCHAR},",
          "create_time = #{create_time,jdbcType=TIMESTAMP},",
          "update_by = #{update_by,jdbcType=VARCHAR},",
          "update_time = #{update_time,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where role_id = #{role_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role row);
}