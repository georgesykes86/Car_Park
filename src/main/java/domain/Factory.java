package domain;

import model.Bay;
import model.BayType;

public interface Factory {

  Bay getInstance(BayType Type);

}
