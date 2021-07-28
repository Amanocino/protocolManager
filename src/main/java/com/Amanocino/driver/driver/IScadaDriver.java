package com.Amanocino.driver.driver;

import java.util.List;

public interface IScadaDriver {

    public byte[] encode(List<ScadaTag> tags);

    public List<ScadaTag> decode(byte[] message);

}
