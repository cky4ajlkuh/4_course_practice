package practice_4;

public class SelectReq {
    String firstName;
    String lastName;
    String textBook;
    String idStudent;
    String idTextBook;

    public SelectReq(String idStudent, String firstName, String lastName, String idTextBook, String textBook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.textBook = textBook;
        this.idStudent = idStudent;
        this.idTextBook = idTextBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTextBook() {
        return textBook;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public String getIdTextBook() {
        return idTextBook;
    }
}
