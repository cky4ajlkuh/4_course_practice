package practice_1.flat;

import javax.xml.bind.annotation.XmlAttribute;

public class Room {

    private int width, height;

    public Room() {
    }

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @XmlAttribute
    public int getWidth() {
        return width;
    }

    @XmlAttribute
    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
