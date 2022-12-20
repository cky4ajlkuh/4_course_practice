package practice_2.command.executor;

import lombok.SneakyThrows;
import practice_2.ApplicationDataSource;
import practice_2.command.CommandType;

import java.sql.Statement;

public class TextBookCreate implements CommandExecutor {
    @Override
    public int execute(String text) {
        return createTextBook(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_TEXTBOOK;
    }

    @SneakyThrows
    public static int createTextBook(String text) {
        String[] words = text.split(" ");
        StringBuilder title = new StringBuilder();
        String req;
        int set = 0;
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        if (words.length > 4) {
            for (int i = 2; i != words.length - 1; i++) {
                title.append(words[i]).append(" ");
            }
            req = "insert into textbook(book_id, title, student_id) values(" + words[1]
                    + ",'" + title + "'," + words[words.length - 1] + ");";
            set = statement.executeUpdate(req);
            return set;
        }
        req = "insert into textbook(book_id, title, student_id) values(" + words[1]
                + ",'" + words[2] + "'," + words[3] + ");";
        set = statement.executeUpdate(req);
        statement.close();
        return set;
    }
}
