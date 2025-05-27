public class Admin extends User {

    private String adminId;

    public Admin(String username, String password, String name, String adminId) {
        super(username, password, name);
        this.adminId = adminId;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public String getAdminId() {
        return adminId;
    }
}
