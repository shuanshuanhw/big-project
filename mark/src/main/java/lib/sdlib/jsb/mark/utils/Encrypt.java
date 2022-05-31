package lib.sdlib.jsb.mark.utils;

/**
 * 类名： Encrypt
 * 描述 TODO：
 * 创建时间： 2022/5/31 10:27
 * 创建人： Administrator
 */
/**
 * 编写一个实体类，凡是此实体类的数据都表示需要加解密
 */
public class Encrypt {

    private String value;

    public Encrypt() {
    }

    public Encrypt(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}