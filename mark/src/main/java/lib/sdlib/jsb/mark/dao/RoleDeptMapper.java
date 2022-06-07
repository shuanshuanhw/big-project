package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.RoleDept;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface RoleDeptMapper {
    @Delete({
        "delete from role_dept",
        "where role_id = #{role_id,jdbcType=BIGINT}",
          "and dept_id = #{dept_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(@Param("role_id") Long role_id, @Param("dept_id") Long dept_id);

    @Insert({
        "insert into role_dept (role_id, dept_id)",
        "values (#{role_id,jdbcType=BIGINT}, #{dept_id,jdbcType=BIGINT})"
    })
    int insert(RoleDept row);

    @Select({
        "select",
        "role_id, dept_id",
        "from role_dept"
    })
    @Results({
        @Result(column="role_id", property="role_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="dept_id", property="dept_id", jdbcType=JdbcType.BIGINT, id=true)
    })
    List<RoleDept> selectAll();
}