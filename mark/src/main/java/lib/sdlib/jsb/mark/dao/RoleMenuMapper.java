package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.RoleMenu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface RoleMenuMapper {
    @Delete({
        "delete from role_menu",
        "where role_id = #{role_id,jdbcType=BIGINT}",
          "and menu_id = #{menu_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(@Param("role_id") Long role_id, @Param("menu_id") Long menu_id);

    @Insert({
        "insert into role_menu (role_id, menu_id)",
        "values (#{role_id,jdbcType=BIGINT}, #{menu_id,jdbcType=BIGINT})"
    })
    int insert(RoleMenu row);

    @Select({
        "select",
        "role_id, menu_id",
        "from role_menu"
    })
    @Results({
        @Result(column="role_id", property="role_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="menu_id", property="menu_id", jdbcType=JdbcType.BIGINT, id=true)
    })
    List<RoleMenu> selectAll();
}