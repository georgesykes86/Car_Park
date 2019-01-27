package domain;

import config.ParkingLotConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import model.Bay;
import model.BayType;

public class BayRepository {

  private HashMap<BayType, ArrayList<Bay>> bayMap = new HashMap();
  private BayFactory factory;

  public BayRepository(BayFactory factory) {
    this.factory = factory;
  }

  public void configure(ParkingLotConfig config) {
    Map<BayType, Integer> bayCountMap = config.getBayCountMap();
    for(BayType type: bayCountMap.keySet()) {
      bayMap.put(type, getBayArrayList(type, bayCountMap.get(type)));
    }
  }

  public Bay getBayOfType(BayType type) {
    return bayMap.get(type).remove(0);
  }

  public void returnBayToStack(Bay bay) {
    bayMap.get(bay.getType()).add(bay);
  }

  public int freeSpaces() {
    return bayMap.values().stream()
        .map(stack -> stack.size())
        .reduce(0, (count1, count2) -> count1 + count2);
  }

  private ArrayList<Bay> getBayArrayList(BayType type, int quantity) {
    ArrayList<Bay> stack = new ArrayList<>();
    IntStream.rangeClosed(1, quantity)
        .forEach(x -> this.addBaysTo(stack, type));
    return stack;
  }

  private void addBaysTo(ArrayList stack, BayType type) {
    stack.add(factory.getInstance(type));
  }

}
