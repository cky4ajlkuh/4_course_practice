package practice_1.practiceJAXB;

import practice_1.flat.Flat;
import practice_1.flat.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class PracticeJAXB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inFile, outFile;
        try {
            System.out.print("Введите имя входного XML-файла: ");
            inFile = scanner.nextLine();
            System.out.print("Введите имя выходного XML-файла: ");
            outFile = scanner.nextLine();
            InputStream is = new FileInputStream(inFile + ".xml");
            OutputStream os = new FileOutputStream(outFile + ".xml");

            JAXBContext jc = JAXBContext.newInstance(Flat.class);
            Unmarshaller um = jc.createUnmarshaller();
            Marshaller m = jc.createMarshaller();
            Flat flat = (Flat) um.unmarshal(is);
            checkFlat(flat);
            m.marshal(flat, os);
            os.close();
            is.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkFlat(Flat flat) {
        List<Room> rooms = flat.getRoom();
        int someArea = 0;
        for (Room room : rooms) {
            someArea = someArea + room.getHeight() * room.getWidth();
        }
        if (someArea != flat.getArea()) {
            flat.setArea(someArea);
        }
    }
}
