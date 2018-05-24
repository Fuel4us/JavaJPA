/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation.booking;

import eapli.ecafeteria.application.booking.CreateComplaintController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.ComplaintState;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hernani Gil
 */
public class CreateComplaintUI extends AbstractUI {

    private final CreateComplaintController createComplaintController = new CreateComplaintController();

    @Override
    protected boolean doShow() {

        boolean flag = false;
        final String input = Console.readLine("ReadCard simulation: insert Cafeteria Username");

        Username username = new Username(input);
        flag = createComplaintController.selectUser(username);

        if (flag) {
            final List<Booking> list = new ArrayList<>();

            final Iterable<Booking> iterableDeliveredBookings = this.createComplaintController.getBookingsDeliveredFromUser();

            if (!iterableDeliveredBookings.iterator().hasNext()) {
                System.out.println("There is no registered delivered bookings");
            } else {
                int count = 1;
                System.out.printf("%-6s%-10s%-30s%-30s\n", "Nº:", "Username", "Firstname", "Lastname");
                for (final Booking booking : iterableDeliveredBookings) {
                    list.add(booking);
                    System.out.printf("Option: " + count + " Booking: " + booking.toString() + "\n");
                    count++;
                }

                final int option = Console.readInteger("Enter the number corresponding to the user that you wish to add credits to or press 0 to deactivate.");
                if (option == 0) {
                    System.out.println("No user selected");
                } else {
                    Booking selectedBooking = list.get(option - 1);
                    
                    if(selectedBooking.hasComplaint()){
                        boolean change = Console.readBoolean(selectedBooking.Complaint().toString()+"\nBooking has complaint. Do you want to change? (y or n)");
                        
                        if(!change){
                            return false;
                        }
                    }  
                    
                    String complaint = Console.readLine("Write the complaint:");
                    
                    boolean anonymous = Console.readBoolean("Anonymous complaint? (y or n)");
                    ComplaintState complaintState;

                    if (anonymous) {
                        complaintState = ComplaintState.ANONYMOUS;
                    } else {
                        complaintState = ComplaintState.AVAILABLE;
                    }

                    createComplaintController.createComplaint(selectedBooking, complaint, complaintState);
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Charge Card";
    }

}
