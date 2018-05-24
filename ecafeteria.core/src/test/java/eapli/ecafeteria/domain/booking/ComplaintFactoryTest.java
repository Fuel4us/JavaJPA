/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import com.google.common.base.Optional;
import eapli.ecafeteria.application.booking.RegisterBookingController;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hernani Gil
 */
public class ComplaintFactoryTest {
    final Set<RoleType> roles = new HashSet<>();
    private final DishType dishType = new DishType("dishtype", "dishtype description");
    private final Dish dish = new Dish(dishType, Designation.valueOf("prato"), new Money(2, Currency.getInstance(Locale.ITALY)));
    private final Date date = new Date();
    private final Meal meal = new Meal(MealType.LUNCH, date, dish);
    private final SystemUser su = new SystemUser("TestUsername", "TestPassword1", "TestFirstName","TestLastName", "Testeemail@hotmail.com", roles);
    private final MecanographicNumber mn = new MecanographicNumber("1000330");
    private final CafeteriaUser cu = new CafeteriaUser(su, mn);
    private final Booking booking = new Booking(cu, meal);
    private final BookingRepository bookingRepository = PersistenceContext.repositories().booking();
    
    public ComplaintFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



//    /**
//     * Test of createComplaint method, of class ComplaintFactory.
//     */
//    @Test
//    public void testCreateComplaint() throws Exception {
//        System.out.println("createComplaint");
//        booking.isDelivered();
//        bookingRepository.save(booking);
//        
//        ComplaintState state = ComplaintState.ANONYMOUS;
//        ComplaintFactory instance = ComplaintFactory.getInstance();
//        
//        
//        instance.createComplaint(booking, cu, new Description("nao gosto de favas"), state);
//        boolean result = false;
//        Iterable<Booking> list = bookingRepository.findBookingsDeliveredByUser(cu);
//        Iterator<Booking> it = list.iterator();
// 
//        while(it.hasNext()){
//            Booking b = it.next();
//            result = b.equals(booking);
//            if(result == true){
//                if(b.Complaint().sameAs(new Complaint(booking.getMeal(), cu, new Description("nao gosto de favas"), state))){
//                    result = true;
//                    break;
//                }else{
//                    result = false;
//                }  
//            }
//        }
//        
//        assertEquals(true, result);
//    }
    
}
