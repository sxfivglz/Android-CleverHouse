package Models;

import java.io.Serializable;

public class DetalleSensorRegistro implements Serializable {
  private String message;
  private Data data;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }
}
