package domain;

import config.ParkingLotConfig;
import java.util.ArrayList;
import model.Bay;
import model.BayType;
import model.Vehicle;

public class BayHandler {

  private BayRepository repository;
  private static ArrayList<BayType> bayHierachy = new ArrayList<BayType>() {{
      add(BayType.SMALL);
      add(BayType.MEDIUM);
      add(BayType.LARGE);
    }};

  public BayHandler(BayRepository repository) {
    this.repository = repository;
  }

  public void configure(ParkingLotConfig config) {
    this.repository.configure(config);
  }

  public Bay getSuitableBay(BayType type) throws RuntimeException {
    for(int i = bayHierachy.indexOf(type); i < bayHierachy.size(); i++) {
      Bay repositoryReturn = repository.getBayOfType(bayHierachy.get(i));
      if (repositoryReturn.getClass() == Bay.class) { return repositoryReturn; }
    }
    throw new RuntimeException("No free bays");
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
