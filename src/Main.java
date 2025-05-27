import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Field> fields = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static User loggedInUser = null;

    public static void main(String[] args) {
        seedData();
        while (true) {
            if (loggedInUser == null) {
                showAuthMenu();
            } else {
                if (loggedInUser instanceof Admin) {
                    showAdminMenu();
                } else {
                    showMemberMenu();
                }
            }
        }
    }

    static void showAuthMenu() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Login");
        System.out.println("2. Registrasi");
        System.out.println("0. Keluar");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> System.exit(0);
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login berhasil sebagai " + user.getRole());
                return;
            }
        }
        System.out.println("Login gagal.");
    }

    static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine();

        Member newMember = new Member(username, password, name, memberId);
        users.add(newMember);
        System.out.println("Registrasi berhasil.");
    }

    static void showAdminMenu() {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Tambah Lapangan");
        System.out.println("2. Lihat Semua Lapangan");
        System.out.println("3. Logout");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1 -> tambahLapangan();
            case 2 -> tampilkanLapangan();
            case 3 -> loggedInUser = null;
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    static void showMemberMenu() {
        System.out.println("\n=== MENU MEMBER ===");
        System.out.println("1. Booking Lapangan");
        System.out.println("2. Lihat Booking Saya");
        System.out.println("3. Logout");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        switch (pilihan) {
            case 1 -> bookingLapangan();
            case 2 -> lihatBookingSaya();
            case 3 -> loggedInUser = null;
            default -> System.out.println("Pilihan tidak valid.");
        }
    }

    static void tambahLapangan() {
        System.out.print("ID Lapangan: ");
        String id = scanner.nextLine();
        System.out.print("Nama: ");
        String name = scanner.nextLine();
        System.out.print("Tipe: ");
        String type = scanner.nextLine();

        fields.add(new Field(id, name, type));
        System.out.println("Lapangan berhasil ditambahkan.");
    }

    static void tampilkanLapangan() {
        for (Field f : fields) {
            System.out.println(f.getInfo());
        }
    }

    static void bookingLapangan() {
        tampilkanLapangan();
        System.out.print("Masukkan ID lapangan yang ingin dibooking: ");
        String fieldId = scanner.nextLine();
        Field selectedField = null;
        for (Field f : fields) {
            if (f.getFieldId().equals(fieldId)) {
                selectedField = f;
                break;
            }
        }

        if (selectedField == null) {
            System.out.println("Lapangan tidak ditemukan.");
            return;
        }

        System.out.print("Tanggal (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();
        System.out.print("Waktu: ");
        String waktu = scanner.nextLine();

        Booking newBooking = new Booking("B" + (bookings.size() + 1), tanggal, waktu, selectedField, (Member) loggedInUser);
        bookings.add(newBooking);
        System.out.println("Booking berhasil.");
    }

    static void lihatBookingSaya() {
        for (Booking b : bookings) {
            if (b.getMember().equals(loggedInUser)) {
                System.out.println(b.getBookingDetails());
            }
        }
    }

    static void seedData() {
        users.add(new Admin("admin", "admin123", "Administrator", "A01"));
        fields.add(new Field("F01", "Lapangan Futsal", "Futsal"));
        fields.add(new Field("F02", "Lapangan Badminton", "Badminton"));
    }
}
