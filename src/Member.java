public class Member extends User {

    private final String memberId;
    private final String memberName;

    public Member(String username, String password, String memberId, String memberName) {
        super(username, password, "member");
        this.memberId = memberId;
        this.memberName = memberName;
    }

   public String getmemberName() {
        return memberName;
   }

   @Override
    public String getInfo() {
        return "Member: " + memberName + " (" + memberId + ")";
   }
}
