package practice_1.practiceSAX;

import practice_1.flat.Flat;
import practice_1.flat.Room;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PracticeSAX {

    public static void main(String[] args) {
        DefaultHandler handler = new SAXReader();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        Scanner scanner = new Scanner(System.in);
        String fileName;
        try {
            System.out.print("Введите имя XML-файла: ");
            fileName = scanner.nextLine();

            parser = factory.newSAXParser();
            parser.parse(fileName + ".xml", handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private static class SAXReader extends DefaultHandler {
        private String name;
        private int floor = 0, number = 0, area = 0, len = 0;
        List<Room> rooms = new LinkedList<>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            name = qName;
            if (qName.equals("practice_1/flat")) {
                floor = Integer.parseInt(attributes.getValue("floor"));
                number = Integer.parseInt(attributes.getValue("number"));
            }
            if (qName.equals("room")) {
                int height = Integer.parseInt(attributes.getValue("height"));
                int width = Integer.parseInt(attributes.getValue("width"));
                rooms.add(new Room(width, height));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String str = new String(ch, start, length);
            str = str.replace("\n", "").trim();
            if (!str.isEmpty()) {
                if (name.equals("area")) {
                    area = Integer.parseInt(str);
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            createFlat(floor, number, area, rooms);
        }

        public void createFlat(int floor, int number, int area, List<Room> rooms) {
            Flat flat;
            int someArea = 0;
            String xml;
            for (Room room : rooms) {
                someArea = someArea + room.getHeight() * room.getWidth();
            }
            if (len < rooms.size()) {
                len = rooms.size();
            } else if (floor != 0 && number != 0) {
                if (someArea == area && area != 0) {
                    flat = new Flat(floor, number, area, rooms);
                    xml = flat.toString();
                    System.out.println(xml);
                }
                if (someArea != area | area == 0) {
                    warning();
                    flat = new Flat(floor, number, someArea, rooms);
                    xml = flat.toString();
                    System.out.println(xml);
                }
                this.floor = this.number = this.area = 0;
                rooms.clear();
            }
        }

        public void warning() {
            System.out.println("Неправильная площадь была исправлена!");
        }
    }
}
