package lib.sdlib.jsb.mark.dao;

import java.util.List;
import lib.sdlib.jsb.mark.entity.Dept;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface DeptMapper {
    @Delete({
        "delete from dept",
        "where dept_id = #{dept_id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long dept_id);

    @Insert({
        "insert into dept (dept_id, parent_id, ",
        "ancestors, dept_name, ",
        "order_num, leader, ",
        "phone, email, status, ",
        "del_flag, create_by, ",
        "create_time, update_by, ",
        "update_time)",
        "values (#{dept_id,jdbcType=BIGINT}, #{parent_id,jdbcType=BIGINT}, ",
        "#{ancestors,jdbcType=VARCHAR}, #{dept_name,jdbcType=VARCHAR}, ",
        "#{order_num,jdbcType=INTEGER}, #{leader,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{status,jdbcType=CHAR}, ",
        "#{del_flag,jdbcType=CHAR}, #{create_by,jdbcType=VARCHAR}, ",
        "#{create_time,jdbcType=TIMESTAMP}, #{update_by,jdbcType=VARCHAR}, ",
        "#{update_time,jdbcType=TIMESTAMP})"
    })
    int insert(Dept row);

    @Select({
        "select",
        "dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, ",
        "del_flag, create_by, create_time, update_by, update_time",
        "from dept",
        "where dept_id = #{dept_id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="dept_id", property="dept_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parent_id", jdbcType=JdbcType.BIGINT),
        @Result(column="ancestors", property="ancestors", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="order_num", jdbcType=JdbcType.INTEGER),
        @Result(column="leader", property="leader", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP)
    })
    Dept selectByPrimaryKey(Long dept_id);

    @Select({
        "select",
        "dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, ",
        "del_flag, create_by, create_time, update_by, update_time",
        "from dept"
    })
    @Results({
        @Result(column="dept_id", property="dept_id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parent_id", jdbcType=JdbcType.BIGINT),
        @Result(column="ancestors", property="ancestors", jdbcType=JdbcType.VARCHAR),
        @Result(column="dept_name", property="dept_name", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_num", property="order_num", jdbcType=JdbcType.INTEGER),
        @Result(column="leader", property="leader", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.CHAR),
        @Result(column="del_flag", property="del_flag", jdbcType=JdbcType.CHAR),
        @Result(column="create_by", property="create_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="create_time", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="update_by", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="update_time", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Dept> selectAll();

    @Update({
        "update dept",
        "set parent_id = #{parent_id,jdbcType=BIGINT},",
          "ancestors = #{ancestors,jdbcType=VARCHAR},",
          "dept_name = #{dept_name,jdbcType=VARCHAR},",
          "order_num = #{order_num,jdbcType=INTEGER},",
          "leader = #{leader,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=CHAR},",
          "del_flag = #{del_flag,jdbcType=CHAR},",
          "create_by = #{create_by,jdbcType=VARCHAR},",
          "create_time = #{create_time,jdbcType=TIMESTAMP},",
          "update_by = #{update_by,jdbcType=VARCHAR},",
          "update_time = #{update_time,jdbcType=TIMESTAMP}",
        "where dept_id = #{dept_id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Dept row);
}