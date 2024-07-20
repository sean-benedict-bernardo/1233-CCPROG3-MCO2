package main.rooms;

public class DeluxeRoom extends Room {
    public DeluxeRoom(String name, float price) {
        super(name, price);
        this.roomType = "Deluxe";
    }

    @Override
    public float getPrice() {
        return this.price * 1.20f;
    }
}
