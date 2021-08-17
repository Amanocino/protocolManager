package com.Amanocino.driver.driver;

import com.Amanocino.driver.driver.message.ModbusTcpEntity;

import java.util.List;

public interface IScadaDriver {

    public byte[] encode(List<ScadaTag> tags);

    public List<ModbusTcpEntity> decode(byte[] message);

}
