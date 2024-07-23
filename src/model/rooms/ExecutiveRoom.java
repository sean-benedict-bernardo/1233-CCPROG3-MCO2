package model.rooms;

public class ExecutiveRoom extends Room {
    public ExecutiveRoom(String name, float price) {
        super(name, price);
        this.roomType = "Executive";
    }

    @Override
    public float getPrice() {
        return this.price * 1.35f;
    }
}
