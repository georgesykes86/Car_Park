import lombok.Data;

@Data
public abstract class Vehicle {

  private String license;
  private String colour;
  private VehicleType type;

  public Vehicle(String license, String colour) {
    this.license = license;
    this.colour = colour;
  }

}
