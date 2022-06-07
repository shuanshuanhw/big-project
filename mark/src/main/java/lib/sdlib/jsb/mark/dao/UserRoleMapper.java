package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface UserRoleMapper {
    @Delete({
        "delete from user_role",
        "where user_id = #{user_id,jdbcType=BIGINT}",
          "and role_id = #{role_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

    @Insert({
        "insert into user_role (user_id, role_id)",
        "values (#{user_id,jdbcType=BIGINT}, #{role_id,jdbcType=BIGINT})"
    })
    int insert(UserRole row);

    @Select({
        "select",
        "user_id, role_id",
        "from user_role"
    })
    @Results({
        @Result(column="user_id", property="user_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="role_id", jdbcType=JdbcType.BIGINT, id=true)
    })
    List<UserRole> selectAll();
}