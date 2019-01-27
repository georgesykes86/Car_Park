public class Truck extends Vehicle {

  public Truck(String license, String colour) {
    super(license, colour);
    this.setType(VehicleType.TRUCK);
  }

}
