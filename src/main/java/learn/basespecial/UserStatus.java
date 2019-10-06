package learn.basespecial;

/**
 * @author Danny.
 * @version 1.0
 * @date 2019/10/3 19:30
 * @description 定义一个枚举，成员必须是私有并且不可变
 */
public enum UserStatus {
    /**
     * 用户状态-禁用
     */
    DISABLE(0, "禁用"),
    /**
     * 用户状态-启用
     */
    ENABLE(1, "启用");

    /**
     * 防止被修改，最好加上final
     */
    private final int value;
    private final String description;

    UserStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }


}
