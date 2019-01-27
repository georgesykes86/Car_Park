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
public class ParkingLotTest {

  private ParkingLot parkingLot;

  @Mock
  private ParkingLotConfig config;

  @Mock
  private BayFactory factory;

  @Mock
  private Vehicle bike;

  @Mock
  private Vehicle car;

  @Mock
  private Bay bay;

  @BeforeEach
  public void setUp() {
    Mockito.lenient().when(config.getSmallBays()).thenReturn(2);
    Mockito.lenient().when(config.getMediumBays()).thenReturn(2);
    Mockito.lenient().when(config.getLargeBays()).thenReturn(2);
    when(bike.getType()).thenReturn(VehicleType.BIKE);
    when(car.getType()).thenReturn(VehicleType.CAR);
    Mockito.lenient().when(factory.getInstance(any())).thenReturn(bay);
    parkingLot = new ParkingLot(factory, config);
  }

  @Test
  public void parkMotorBikeWhenEmptyTest() {
    when(bike.getLicense()).thenReturn("abc123");
    assertEquals(BayType.SMALL, parkingLot.park(bike));
  }

  @Test
  public void parkTwoMotorBikesTest() {
    when(bike.getLicense())
        .thenReturn("abc123")
        .thenReturn("def456");
    parkingLot.park(bike);
    assertEquals(BayType.SMALL, parkingLot.park(bike));
  }

  @Test
  public void parkBikeAndCarTest() {
    when(bike.getLicense()).thenReturn("abc123");
    when(car.getLicense()).thenReturn("car123");
    assertEquals(BayType.SMALL, parkingLot.park(bike));
    assertEquals(BayType.MEDIUM, parkingLot.park(car));
  }


}
