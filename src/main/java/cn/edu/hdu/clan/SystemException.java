package cn.edu.hdu.clan;

public class SystemException extends RuntimeException {
    private String msg;

    public SystemException(String error) {
        this.printStackTrace();
        msg = error;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
