public class Admin extends User {

    private final String adminId;
    private final String adminName;

    public Admin(String  username, String password, String adminId, String adminName) {
        super(username, password, "Admin");
        this.adminId = adminId;
        this.adminName = adminName;
    }

    public String getadminName() {
        return adminName;
    }

    @Override
    public String getInfo() {
        return "Admin: " + adminName + " ("+ adminId +") ";
    }

}
