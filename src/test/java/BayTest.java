import static org.junit.jupiter.api.Assertions.*;

import domain.BayFactory;
import model.Bay;
import model.BayType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BayTest {

  private BayFactory factory;
  private Bay bay;

  @BeforeEach
  public void setUp() {
    factory = new BayFactory();
    bay = factory.getInstance(BayType.MEDIUM);
  }

  @Test
  public void setsIDTest() {
    assertTrue(bay.getId().length() == 36);
  }

}