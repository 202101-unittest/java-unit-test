package otp;

import java.util.Random;

public class RsaTokenDao {
    public String getRandom(String account) {
        Random seed = new Random((int) System.currentTimeMillis() & 0x0000FFFF);
        String result = String.format("%06d", seed.nextInt(999999));
        System.out.printf("randomCode:%s%n", result);

        return result;
    }
}
