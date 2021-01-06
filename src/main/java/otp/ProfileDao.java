package otp;

public class ProfileDao {
    public String getPassword(String account) {
        return Context.getPassword(account);
    }
}
