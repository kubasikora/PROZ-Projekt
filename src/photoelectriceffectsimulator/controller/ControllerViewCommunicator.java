package photoelectriceffectsimulator.controller;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługi komunikacji kontrolera z widokiem
 * @author kuba
 */
public interface ControllerViewCommunicator {
    public void waveLengthChange(ExpNumber newValue);
    public void intensityChange(int newValue);
    public void voltageChange(double newValue);
    public void metalTypeChange(String newMetalType);
}
