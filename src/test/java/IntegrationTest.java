import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import config.ParkingLotConfig;
import domain.BayFactory;
import domain.BayHandler;
import domain.BayRepository;
import domain.ParkingLot;
import model.BayType;
import model.Bike;
import model.Bus;
import model.Car;
import model.Truck;
import model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {

  private ParkingLot parkingLot;
  private BayRepository repository;
  private BayHandler bayHandler;
  private ParkingLotConfig config;
  private BayFactory factory;
  private Vehicle bike;
  private Vehicle secondBike;
  private Vehicle car;
  private Vehicle truck;
  private Vehicle bus;

  @BeforeEach
  public void setUp() {
    config = new ParkingLotConfig(2,2,2);
    factory = new BayFactory();
    repository = new BayRepository(factory);
    bayHandler = new BayHandler(repository);
    bike = new Bike("BIKE1", "BLUE");
    secondBike = new Bike("BIKE2", "BLACK");
    car = new Car("CAR1", "RED");
    truck = new Truck("TRUCK1", "GREEN");
    bus = new Bus("BUS1", "RED");
    parkingLot = new ParkingLot(config, bayHandler);
  }

  @Test
  public void parkMotorBikeWhenEmptyTest() {
    assertEquals(BayType.SMALL, parkingLot.park(bike).getType());
  }

  @Test
  public void parkTwoMotorBikesTest() {
    parkingLot.park(bike);
    assertEquals(BayType.SMALL, parkingLot.park(secondBike).getType());
  }

  @Test
  public void parkBikeAndCarTest() {
    assertEquals(BayType.SMALL, parkingLot.park(bike).getType());
    assertEquals(BayType.MEDIUM, parkingLot.park(car).getType());
  }

  @Test
  public void parkTruckTest() {
    assertEquals(BayType.LARGE, parkingLot.park(truck).getType());
  }

  @Test
  public void parkBusTest() {
    assertEquals(BayType.LARGE, parkingLot.park(bus).getType());
  }

  @Test
  public void bikeExitsTest() {
    parkingLot.park(bike);
    parkingLot.exit(bike);
    assertEquals(6, parkingLot.freeSpaces());
  }

  @Test
  public void noParkingVehicleIfAlreadyRegisteredTest(){
    parkingLot.park(bike);
    assertThrows(RuntimeException.class, () -> parkingLot.park(bike));
  }
}
