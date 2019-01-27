package domain;

import config.ParkingLotConfig;
import java.util.HashMap;
import java.util.Map;
import model.Bay;
import model.BayType;
import model.Vehicle;
import model.VehicleType;

public class ParkingLot {

  private BayHandler bayHandler;
  private Map<VehicleType, BayType> vehicleBayCompatibilityMap;
  private Map<String, Bay> registeredVehicleMap = new HashMap<>();

  public ParkingLot(ParkingLotConfig config, BayRepository repository, BayHandler handler) {
    this.bayHandler = handler;
    this.bayHandler.configure(repository, config);
    vehicleBayCompatibilityMap = config.getVehicleMap();
  }

  public Bay park(Vehicle vehicle) throws RuntimeException {
    checkIfAlreadyParked(vehicle);
    Bay freeBay = bayHandler.getSuitableBay(vehicle);
    registerVehicleAsParked(vehicle, freeBay);
    return freeBay;
  }

  public Vehicle exit(Vehicle vehicle) {
    Bay occupiedBay = registeredVehicleMap.remove(vehicle.getLicense());
    return bayHandler.makeBayAvailable(occupiedBay);
  }

  public int freeSpaces() {
    return bayHandler.getFreeSpaces();
  }

  public void registerVehicleAsParked(Vehicle vehicle, Bay freeBay) {
    registeredVehicleMap.put(vehicle.getLicense(), freeBay);
  }

  public void checkIfAlreadyParked(Vehicle vehicle) {
    if (registeredVehicleMap.containsKey(vehicle.getLicense())) { throw new RuntimeException("model.Vehicle already registered as parked"); }
  }
}
