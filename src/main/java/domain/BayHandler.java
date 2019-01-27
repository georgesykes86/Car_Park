package domain;

import config.ParkingLotConfig;
import model.Bay;
import model.BayType;
import model.Vehicle;

public class BayHandler {

  private BayRepository repository;


  public void configure(BayRepository repository, ParkingLotConfig config) {
    this.repository = repository;
    this.repository.configure(config);
  }

  private Bay getSuitableBayFor(Vehicle vehicle) throws RuntimeException {
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

  public int getFreeSpaces() {
    return this.repository.freeSpaces();
  }

  public Vehicle makeBayAvailable(Bay bay) {
    Vehicle exitingVehicle = bay.removeOccupyingVehicle();
    this.repository.returnBayToStack(bay);
    return exitingVehicle;
  }

}
