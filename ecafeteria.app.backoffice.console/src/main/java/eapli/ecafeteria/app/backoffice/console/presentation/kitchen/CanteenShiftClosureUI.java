package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CanteenShiftClosureController;
import eapli.ecafeteria.domain.kitchen.CanteenShift;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import java.util.Calendar;

public class CanteenShiftClosureUI extends AbstractListUI<CanteenShift>{
    
    private final CanteenShiftClosureController controller = new CanteenShiftClosureController();
    
    public void CanteenShiftClosure(){
        System.out.println("Begins the closure of canteen shift");
        
//        Calendar cal = Calendar.getInstance();
//        
//        if (controller.canteenShiftClosure(cal) == true)
//            System.out.println("The operation is over");
//        else
//            System.out.println("The operation wasn't concluded");
    }

    @Override
    protected Iterable<CanteenShift> elements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Visitor<CanteenShift> elementPrinter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String elementName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String listHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
}
