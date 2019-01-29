package config;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import model.BayType;
import model.VehicleType;

@Data
public class ParkingLotConfig {

  private Map<BayType, Integer> bayCountMap;
  private final Map<VehicleType, BayType> vehicleMap = new HashMap<VehicleType, BayType>() {{
    put(VehicleType.BIKE, BayType.SMALL);
    put(VehicleType.CAR, BayType.MEDIUM);
    put(VehicleType.TRUCK, BayType.LARGE);
    put(VehicleType.BUS, BayType.LARGE);
  }};


  public ParkingLotConfig(int smallBays, int mediumBays, int largeBays) {
    bayCountMap = new HashMap<>();
      bayCountMap.put(BayType.SMALL, smallBays);
      bayCountMap.put(BayType.MEDIUM, mediumBays);
      bayCountMap.put(BayType.LARGE, largeBays);
  }
}
