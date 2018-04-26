package eapli.ecafeteria.domain.kitchen;

public enum CanteenShiftState {
    OPEN, CLOSED;
    
    public static CanteenShiftState[] ShiftStateValues() {
        return new CanteenShiftState[]{OPEN,CLOSED};
    }
}
