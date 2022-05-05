package Models;

public class ResLogin {
    private String token;

    public ResLogin(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResLogin{" +
                "token='" + token + '\'' +
                '}';
    }

}
