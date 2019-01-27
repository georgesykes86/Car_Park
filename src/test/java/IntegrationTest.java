import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {

  private ParkingLot parkingLot;
  private ParkingLotConfig config;
  private BayFactory factory;
  private Vehicle bike;
  private Vehicle secondBike;
  private Vehicle car;

  @BeforeEach
  public void setUp() {
    config = new ParkingLotConfig(2,2,2);
    factory = new BayFactory();
    bike = new Bike("BIKE1", "BLUE");
    secondBike = new Bike("BIKE2", "BLACK");
    car = new Car("CAR1", "RED");
    parkingLot = new ParkingLot(factory, config);
  }

  @Test
  public void parkMotorBikeWhenEmptyTest() {
    assertEquals(BayType.SMALL, parkingLot.park(bike).getType());
  }

  @Test
  public void parkTwoMotorBikesTest() {
    parkingLot.park(bike);
    assertEquals(BayType.SMALL, parkingLot.park(bike).getType());
  }

  @Test
  public void parkBikeAndCarTest() {
    assertEquals(BayType.SMALL, parkingLot.park(bike).getType());
    assertEquals(BayType.MEDIUM, parkingLot.park(car).getType());
  }


}
