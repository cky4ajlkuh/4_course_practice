package practice_1.practiceDOM;

import practice_1.flat.Room;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import practice_1.flat.Flat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PracticeDOM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inFile, outFile;
        try {
            System.out.print("Введите имя входного XML-файла: ");
            inFile = scanner.nextLine();
            System.out.print("Введите имя выходного XML-файла: ");
            outFile = scanner.nextLine();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inFile + ".xml");
            Flat flat = parse(document);
            Document doc = builder.newDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(saveFile(doc, flat));
            File myFile = new File(outFile + ".xml");
            StreamResult file = new StreamResult(myFile);
            transformer.transform(source, file);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static Document saveFile(Document document, Flat flat) {
        Element root = document.createElement("practice_1/flat");
        Element area = document.createElement("area");
        document.appendChild(root);
        for (int i = 0; i < flat.getRoom().size(); i++) {
            Element element = document.createElement("root");
            element.setAttribute("height", String.valueOf(flat.getRoom().get(i).getHeight()));
            element.setAttribute("width", String.valueOf(flat.getRoom().get(i).getWidth()));
            root.appendChild(element);
        }
        area.setTextContent(String.valueOf(flat.getArea()));
        root.appendChild(area);
        return document;
    }

    public static Flat parse(Document document) {
        int floor = 0, number = 0, height = 0, width = 0;
        String area = null;
        List<Room> rooms = new LinkedList<>();
        Element element = document.getDocumentElement();

        if (element.getNodeType() == Node.ELEMENT_NODE) {
            floor = Integer.parseInt(element.getAttribute("floor"));
            number = Integer.parseInt(element.getAttribute("number"));
        }
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (list.item(i).getNodeName().equals("room")) {
                    NamedNodeMap nnm = list.item(i).getAttributes();
                    for (int j = 0; j < nnm.getLength(); j++) {
                        Attr attr = (Attr) nnm.item(j);
                        if (attr.getName().equals("height")) {
                            height = Integer.parseInt(attr.getValue());
                        }
                        if (attr.getName().equals("width")) {
                            width = Integer.parseInt(attr.getValue());
                        }
                    }
                    if (width != 0 && height != 0) {
                        rooms.add(new Room(width, height));
                    }
                } else if (list.item(i).getNodeName().equals("area")) {
                    area = list.item(i).getTextContent();
                }
            }
        }
        int someArea = 0;
        for (Room room : rooms) {
            someArea = someArea + room.getHeight() * room.getWidth();
        }
        if (area != null && !area.isEmpty()) {
            if (someArea == Integer.parseInt(area)) {
                return new Flat(floor, number, Integer.parseInt(area), rooms);
            }
            if (someArea != Integer.parseInt(area)) {
                System.out.println("Площадь исправлена!");
                return new Flat(floor, number, someArea, rooms);
            }
        }
        return new Flat(floor, number, someArea, rooms);
    }
}
