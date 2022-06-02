package shuanshuan.pay.testpay;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(2000, "成功"), ERROR(999, "失败");
    private final Integer code;
    private final String desc;
}
