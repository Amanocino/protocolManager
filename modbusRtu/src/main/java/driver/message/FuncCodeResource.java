package driver.message;

/**
 * @author : zhicheng chen
 * @date : 2021/7/23
 * @time : 17:10
 */
public class FuncCodeResource {
    // 因为已经定义了带参数的构造器，所以在列出枚举值时必须传入对应的参数
    public static final int TCP_OP_READ_COIL_STATUS = 1;
    public static final int TCP_OP_READ_INPUT_STATUS = 2;
    public static final int TCP_OP_READ_HOLDING_REGISTER = 3;
    public static final int TCP_OP_READ_INPUT_REGISTER = 4;
    public static final int TCP_OP_WRITE_SINGLE_COIL = 5;
    public static final int TCP_OP_WRITE_SINGLE_REGISTER = 6;
    public static final int TCP_OP_WRITE_MULTIPLE_COIL = 15;
    public static final int OP_WRITE_MULTIPLE_REGISTER = 16;
}
