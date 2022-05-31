package lib.sdlib.jsb.mark.utils;

/**
 * 类名： TypeHandler
 * 描述 TODO：
 * 创建时间： 2022/5/31 10:32
 * 创建人： Administrator
 */

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 加解密TypeHandler
 */
@MappedJdbcTypes(JdbcType.VARCHAR)  //表示处理器处理的JDBC类型
@MappedTypes(Encrypt.class)     //表示该处理器处理的java类型是什么
public class TypeHandler extends BaseTypeHandler<Encrypt> {

    private static final byte[] KEYS = "12345678asdfghjkzxcv".getBytes(StandardCharsets.UTF_8);


    /**
     * 设置参数
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Encrypt parameter, org.apache.ibatis.type.JdbcType jdbcType) throws SQLException {
        if (parameter == null || parameter.getValue() == null){
            ps.setString(i,null);
            return;
        }
        AES aes = SecureUtil.aes(KEYS);
        String encrypt = aes.encryptHex(parameter.getValue());
        ps.setString(i,encrypt);
    }



    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return decrypt(rs.getString(columnName));
    }

    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return decrypt(rs.getString(columnIndex));
    }

    /**
     * 获取值
     */
    @Override
    public Encrypt getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decrypt(cs.getString(columnIndex));
    }

    private Encrypt decrypt(String value) {
        if (null == value){
            return null;
        }
        return new Encrypt(SecureUtil.aes(KEYS).decryptStr(value));
    }

}