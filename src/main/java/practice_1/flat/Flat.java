package practice_1.flat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "practice_1/flat")
public class Flat {

    private int floor, number, area;
    private List<Room> room;

    public Flat() {
    }

    public Flat(int floor, int number, int area, List<Room> room) {
        this.floor = floor;
        this.number = number;
        this.area = area;
        this.room = room;
    }

    @XmlAttribute
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @XmlAttribute
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Flat \n" + "      floor = ").append(floor).append(", number = ").append(number).append("\n");
        for (Room room : room) {
            builder.append("            height = ").append(room.getHeight()).append(", width = ").append(room.getWidth()).append("\n");
        }
        builder.append("      area = ").append(area);
        return builder.toString();
    }
}
