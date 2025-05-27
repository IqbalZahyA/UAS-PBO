public class Booking {

    private final String bookingId;
    private final String date;
    private final String timeSlot;
    private final Field field;
    private final Member member;

    public Booking(String bookingId, String date, String timeslot, Field field, Member member) {
        this.bookingId = bookingId;
        this.date = date;
        this.timeSlot = timeslot;
        this.field = field;
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public String getBookingDetails() {
        return "Booking Id: " + bookingId + "\nField: " + field.getInfo() + "\nMember; " + member.getUsername() + "\nDate: " + date + "Time: " + timeSlot;
    }

}
