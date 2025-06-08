public class Booking {
    private final String bookingId;
    private final String date;
    private final String timeSlot;
    private final Field field;
    private final Member member;
    private String priority;

    public Booking(String bookingId, String date, String timeSlot, Field field, Member member, String priority) {
        this.bookingId = bookingId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.field = field;
        this.member = member;
        this.priority = priority;
    }

    public Booking(String bookingId, String date, String timeSlot, Field field, Member member) {
        this(bookingId, date, timeSlot, field, member, "Medium");
    }

    // Getter untuk field
    public Field getField() {
        return field;
    }

    // Getter untuk date
    public String getDate() {
        return date;
    }

    // Getter untuk timeSlot
    public String getTimeSlot() {
        return timeSlot;
    }

    // Getter untuk member
    public Member getMember() {
        return member;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBookingDetails() {
        return "Booking ID: " + bookingId +
               "\nTanggal: " + date +
               "\nWaktu: " + timeSlot +
               "\nLapangan: " + field.getFieldName() +
               "\nMember: " + member.getUsername() +
               "\nPrioritas: " + priority;
    }
}