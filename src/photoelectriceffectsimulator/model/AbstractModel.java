package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 *
 * @author kuba
 */
public interface AbstractModel {
    public void setIntensity(int newValue);
    public void setWaveLength(ExpNumber newValue);
    public void setVoltage(double newVoltage);
    public void setMetalType(String newType);
}
