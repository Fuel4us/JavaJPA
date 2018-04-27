package eapli.ecafeteria.domain.kitchen;

public enum CanteenShiftState {
    OPEN, CLOSED;
    
    public static CanteenShiftState[] CanteenShiftState() {
        return new CanteenShiftState[]{OPEN,CLOSED};
    }
}
