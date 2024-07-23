package model.rooms;

/**
 * ExecutiveRoom extends Room with the difference of
 * its price being 35% more expensive than the base price
 * 
 * @author Sean Benedict Bernardo
 * @author Luis Andrew Madridijo
 */
public class ExecutiveRoom extends Room {
    /**
     * ExecutiveRoom constructor
     * 
     * @param name  String name of room
     * @param price Float base price of Room, notwithstanding price difference
     */
    public ExecutiveRoom(String name, float price) {
        super(name, price);
        this.roomType = "Executive";
    }

    /**
     * Overriden method with the +35% change
     */
    @Override
    public float getPrice() {
        return this.price * 1.35f;
    }
}
