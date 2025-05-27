public class Member extends User {

    private final String memberId;

    public Member(String username, String password, String name, String memberId) {
        super(username, password, name);
        this.memberId = memberId;
    }

    @Override
    public String getRole() {
        return "Member";
    }

    public String getMemberId() {
        return memberId;
    }
}
