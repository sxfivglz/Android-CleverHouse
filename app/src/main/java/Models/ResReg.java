package Models;

public class ResReg {

    String message;
    String token;

    public ResReg(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RespuestaReg{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }



}
