package lib.sdlib.jsb.mark.common;

/**
 * 类名： OnlineStatus
 * 描述 TODO：
 * 创建时间： 2022/5/31 16:56
 * 创建人： Administrator
 */
/**
 * 用户会话
 *
 * @author ruoyi
 */
public enum OnlineStatus
{
    /** 用户状态 */
    on_line("在线"), off_line("离线");

    private final String info;

    private OnlineStatus(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return info;
    }
}

