package model;

public class Car extends Vehicle {

  public Car(String license, String colour) {
    super(license, colour);
    this.setType(VehicleType.CAR);
  }

}
