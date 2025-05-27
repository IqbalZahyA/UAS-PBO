import java.util.*;

public class FieldManagementSystem {

    private final List<User> users = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public void registerUser(User user) {
        users.add(user);
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void deleteField(String fieldId) {
        fields.removeIf(f -> f.getFieldId().equals(fieldId));
    }

    public void editField(String fieldId, Field updateField) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getFieldId().equals(fieldId)) {
                fields.set(i, updateField);
                break;
            }
        }
    }

    public List<Field> searchField(String keyword) {
        List<Field> result = new ArrayList<>();
        for (Field f : fields) {
            if (f.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(f);
            }
        }
        return result;
    }

    public void bookField(Member member, Field field, String date, String timeSlot) {
        String bookingId = "BKG" + (bookings.size() + 1);
        Booking booking = new Booking(bookingId, date, timeSlot, field, member);
        field.setStatus("Booked");
        bookings.add(booking);
    }

    public void viewAllBookings() {
        for (Booking b : bookings) {
            System.out.println(b.getBookingDetails());
            System.out.println("--------------------");
        }
    }

}
