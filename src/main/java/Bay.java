import java.util.UUID;
import lombok.Data;

@Data
public class Bay {

  private BayType type;
  private String id;

  public Bay(BayType Type) {
    setId();
    setType(Type);
  }

  public void setId() {
    this.id = UUID.randomUUID().toString();
  }

}
