package model;

public class Bike extends Vehicle {

  public Bike(String license, String colour) {
    super(license, colour);
    this.setType(VehicleType.BIKE);
  }
}
