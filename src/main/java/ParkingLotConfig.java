import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class ParkingLotConfig {

  private int smallBays;
  private int mediumBays;
  private int largeBays;
  private final Map<VehicleType, BayType> vehicleMap = new HashMap<VehicleType, BayType>() {{
    put(VehicleType.BIKE, BayType.SMALL);
    put(VehicleType.CAR, BayType.MEDIUM);
    put(VehicleType.TRUCK, BayType.LARGE);
    put(VehicleType.BUS, BayType.LARGE);
  }};


  public ParkingLotConfig(int smallBays, int mediumBays, int largeBays) {
    this.smallBays = smallBays;
    this.mediumBays = mediumBays;
    this.largeBays = largeBays;
  }
}
