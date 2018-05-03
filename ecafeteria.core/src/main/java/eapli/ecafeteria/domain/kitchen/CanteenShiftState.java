package eapli.ecafeteria.domain.kitchen;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;

public enum CanteenShiftState implements Serializable, ValueObject{
    OPEN, CLOSED;
    
    public static CanteenShiftState[] CanteenShiftState() {
        return new CanteenShiftState[]{OPEN,CLOSED};
    }
}
