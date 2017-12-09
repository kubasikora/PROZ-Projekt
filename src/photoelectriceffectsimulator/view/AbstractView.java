package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 *
 * @author kuba
 */
public interface AbstractView {
    public void setElementType(String newType);
    public void setExitEnergy(double exitEnergy);
    public void setOutcomeCurrent(ExpNumber current);
    public void setPhotonEnergy(double photonEnergy);
}
