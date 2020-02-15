package cn.edu.hdu.clan.helper;


import java.util.UUID;

public class UUIDHelper {
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
}
