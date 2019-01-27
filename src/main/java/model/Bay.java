package model;

import java.util.UUID;
import lombok.Data;

@Data
public class Bay {

  private BayType type;
  private String id;
  private Vehicle occupyingVehicle = null;

  public Bay(BayType Type) {
    setId();
    setType(Type);
  }

  public void setId() {
    this.id = UUID.randomUUID().toString();
  }

  private void removeVehicle() {
    occupyingVehicle = null;
  }

  public Vehicle removeOccupyingVehicle() {
    Vehicle occupyingVehicle = this.getOccupyingVehicle();
    this.removeVehicle();
    return occupyingVehicle;
  }
}
