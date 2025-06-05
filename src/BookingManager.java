
public class BookingManager {

    public static boolean isFieldAvaiable (String fieldId, String date, String timeSlot) {
        String[] requested = timeSlot.split("-");
        int requestedStart = Integer.parseInt(requested[0].replace(":", ""));
        int requestedEnd = Integer.parseInt(requested[1].replace(":", ""));

        for (Booking booking : Database.bookings) {
            if (booking.getField().getFieldId().equals(fieldId) &&
                    booking.getDate().equals(date)) {

                String[] existing = booking.getTimeSlot().split("-");
                int existingStart = Integer.parseInt(existing[0].replace(":", ""));
                int existingEnd = Integer.parseInt(existing[1].replace(":", ""));

                // Cek overlap waktu
                if (requestedStart < existingEnd && existingStart < requestedEnd) {
                    return false; // Bertabrakan
                }
            }
        }

        return true; // Aman
    }
}
