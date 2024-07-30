package model.rooms;

/**
 * DeluxeRoom extends Room with the difference of
 * its price being 20% more expensive than the base price
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class DeluxeRoom extends Room {
    /**
     * DeluxeRoom constructor
     * 
     * @param name  String name of room
     * @param price Float base price of Room, notwithstanding price difference
     */
    public DeluxeRoom(String name, float price) {
        super(name, price);
        this.roomType = "Deluxe";
    }

    /**
     * Overriden method with the +20% change
     */
    @Override
    public float getPrice() {
        return this.price * 1.20f;
    }
}
