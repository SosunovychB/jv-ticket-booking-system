package mate.academy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;

public class TicketBookingSystem {
    private int totalSeats;

    public TicketBookingSystem(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public BookingResult attemptBooking(String user) {
        Semaphore semaphore = new Semaphore(totalSeats + 1);

        try {
            if (totalSeats <= 0) {
                return new BookingResult(user, false, "No seats available.");
            }
            semaphore.acquire();
            totalSeats--;
            return new BookingResult(user, true, "Booking successful.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
