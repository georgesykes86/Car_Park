package model;

public class Bus extends Vehicle {

  public Bus(String license, String colour) {
    super(license, colour);
    this.setType(VehicleType.BUS);
  }

}
