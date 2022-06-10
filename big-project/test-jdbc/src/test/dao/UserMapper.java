package test.dao;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static test.dao.UserDynamicSqlSupport.*;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import test.model.User;

@Mapper
public interface UserMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, truename, department, phone, email, company, role, if_del, if_admin, ifdel, token, sort, password);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="row.id", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.CHAR),
        @Result(column="truename", property="truename", jdbcType=JdbcType.CHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.CHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.CHAR),
        @Result(column="Email", property="email", jdbcType=JdbcType.CHAR),
        @Result(column="company", property="company", jdbcType=JdbcType.CHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.CHAR),
        @Result(column="if_del", property="if_del", jdbcType=JdbcType.CHAR),
        @Result(column="if_admin", property="if_admin", jdbcType=JdbcType.CHAR),
        @Result(column="ifdel", property="ifdel", jdbcType=JdbcType.CHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="password", property="password", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User row) {
        return MyBatis3Utils.insert(this::insert, row, user, c ->
            c.map(username).toProperty("username")
            .map(truename).toProperty("truename")
            .map(department).toProperty("department")
            .map(phone).toProperty("phone")
            .map(email).toProperty("email")
            .map(company).toProperty("company")
            .map(role).toProperty("role")
            .map(if_del).toProperty("if_del")
            .map(if_admin).toProperty("if_admin")
            .map(ifdel).toProperty("ifdel")
            .map(token).toProperty("token")
            .map(sort).toProperty("sort")
            .map(password).toProperty("password")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User row) {
        return MyBatis3Utils.insert(this::insert, row, user, c ->
            c.map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(truename).toPropertyWhenPresent("truename", row::getTruename)
            .map(department).toPropertyWhenPresent("department", row::getDepartment)
            .map(phone).toPropertyWhenPresent("phone", row::getPhone)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(company).toPropertyWhenPresent("company", row::getCompany)
            .map(role).toPropertyWhenPresent("role", row::getRole)
            .map(if_del).toPropertyWhenPresent("if_del", row::getIf_del)
            .map(if_admin).toPropertyWhenPresent("if_admin", row::getIf_admin)
            .map(ifdel).toPropertyWhenPresent("ifdel", row::getIfdel)
            .map(token).toPropertyWhenPresent("token", row::getToken)
            .map(sort).toPropertyWhenPresent("sort", row::getSort)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<User> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(User row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalTo(row::getUsername)
                .set(truename).equalTo(row::getTruename)
                .set(department).equalTo(row::getDepartment)
                .set(phone).equalTo(row::getPhone)
                .set(email).equalTo(row::getEmail)
                .set(company).equalTo(row::getCompany)
                .set(role).equalTo(row::getRole)
                .set(if_del).equalTo(row::getIf_del)
                .set(if_admin).equalTo(row::getIf_admin)
                .set(ifdel).equalTo(row::getIfdel)
                .set(token).equalTo(row::getToken)
                .set(sort).equalTo(row::getSort)
                .set(password).equalTo(row::getPassword);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalToWhenPresent(row::getUsername)
                .set(truename).equalToWhenPresent(row::getTruename)
                .set(department).equalToWhenPresent(row::getDepartment)
                .set(phone).equalToWhenPresent(row::getPhone)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(company).equalToWhenPresent(row::getCompany)
                .set(role).equalToWhenPresent(row::getRole)
                .set(if_del).equalToWhenPresent(row::getIf_del)
                .set(if_admin).equalToWhenPresent(row::getIf_admin)
                .set(ifdel).equalToWhenPresent(row::getIfdel)
                .set(token).equalToWhenPresent(row::getToken)
                .set(sort).equalToWhenPresent(row::getSort)
                .set(password).equalToWhenPresent(row::getPassword);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(truename).equalTo(row::getTruename)
            .set(department).equalTo(row::getDepartment)
            .set(phone).equalTo(row::getPhone)
            .set(email).equalTo(row::getEmail)
            .set(company).equalTo(row::getCompany)
            .set(role).equalTo(row::getRole)
            .set(if_del).equalTo(row::getIf_del)
            .set(if_admin).equalTo(row::getIf_admin)
            .set(ifdel).equalTo(row::getIfdel)
            .set(token).equalTo(row::getToken)
            .set(sort).equalTo(row::getSort)
            .set(password).equalTo(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(truename).equalToWhenPresent(row::getTruename)
            .set(department).equalToWhenPresent(row::getDepartment)
            .set(phone).equalToWhenPresent(row::getPhone)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(company).equalToWhenPresent(row::getCompany)
            .set(role).equalToWhenPresent(row::getRole)
            .set(if_del).equalToWhenPresent(row::getIf_del)
            .set(if_admin).equalToWhenPresent(row::getIf_admin)
            .set(ifdel).equalToWhenPresent(row::getIfdel)
            .set(token).equalToWhenPresent(row::getToken)
            .set(sort).equalToWhenPresent(row::getSort)
            .set(password).equalToWhenPresent(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }
}