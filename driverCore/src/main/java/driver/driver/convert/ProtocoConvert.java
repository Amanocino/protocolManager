package driver.driver.convert;


import driver.driver.ScadaTag;

/**
 * @author : zhicheng chen
 * @date : 2021/7/26
 * @time : 10:05
 */
public interface ProtocoConvert<T> {
    public abstract T convertDataFromTag(ScadaTag scadaTag);

    public abstract ScadaTag reConvertDataFromTag(T data);
}
