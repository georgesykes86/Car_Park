package domain;

import model.Bay;
import model.BayType;

public class BayFactory implements Factory {

  @Override
  public Bay getInstance(BayType Type) {
    return new Bay(Type);
  }
}
