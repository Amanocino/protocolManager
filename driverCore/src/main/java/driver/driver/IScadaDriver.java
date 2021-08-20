package driver.driver;

import java.util.List;

public interface IScadaDriver<T> {

    public byte[] encode(List<ScadaTag> tags);

    public List<T> decode(byte[] message);

}
