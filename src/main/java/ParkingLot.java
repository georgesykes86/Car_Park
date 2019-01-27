import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.inject.Inject;

public class ParkingLot {

  private ArrayList<Bay> freeSmallBays = new ArrayList<>();
  private ArrayList<Bay> freeMediumBays = new ArrayList<>();
  private ArrayList<Bay> freeLargeBays = new ArrayList<>();
  private HashMap<String, Bay> bayMap = new HashMap<>();
  private Map<VehicleType, BayType> vehicleBayCompatibilityMap;
  private Factory bayFactory;

  public ParkingLot(Factory bayFactory, ParkingLotConfig config) {
    this.bayFactory = bayFactory;
    vehicleBayCompatibilityMap = config.getVehicleMap();
    populateBays(freeSmallBays, BayType.SMALL, config.getSmallBays());
    populateBays(freeMediumBays, BayType.MEDIUM, config.getMediumBays());
    populateBays(freeLargeBays, BayType.LARGE, config.getLargeBays());
  }

  private void populateBays(ArrayList stack, BayType type, int quantity) {
    IntStream.rangeClosed(1, quantity)
        .forEach(x -> this.addBaysTo(stack, type));
  }

  private void addBaysTo(ArrayList stack, BayType type) {
    stack.add(bayFactory.getInstance(type));
  }

  public Bay park(Vehicle vehicle) {
    Bay freeBay = getSuitableBayFor(vehicle);
    bayMap.put(vehicle.getLicense(), freeBay);
    return freeBay;
  }

  private Bay getSuitableBayFor(Vehicle vehicle) {
    BayType type = vehicleBayCompatibilityMap.get(vehicle.getType());
    if (type == BayType.SMALL) {
      if (freeSmallBays.size() > 0) { return freeSmallBays.remove(0); }
      else if (freeMediumBays.size() > 0) { return freeMediumBays.remove(0); }
      else if (freeLargeBays.size() > 0) { return freeLargeBays.remove(0); }
      else { throw new RuntimeException("No free parking bays"); }
    } else if (type == BayType.MEDIUM) {
      if (freeMediumBays.size() > 0) { return freeMediumBays.remove(0); }
      else if (freeLargeBays.size() > 0) { return freeLargeBays.remove(0); }
      else { throw new RuntimeException("No free parking bays"); }
    } else {
      if (freeLargeBays.size() > 0) { return freeLargeBays.remove(0); }
      else { throw new RuntimeException("No free parking bays"); }
    }
  }

}
