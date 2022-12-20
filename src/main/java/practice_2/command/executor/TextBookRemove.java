package practice_2.command.executor;

import lombok.SneakyThrows;
import practice_2.ApplicationDataSource;
import practice_2.command.CommandType;

import java.sql.PreparedStatement;

public class TextBookRemove implements CommandExecutor {

    private static final String SQL_DELETE = "delete from textbook where book_id = ?";

    @Override
    public int execute(String text) {
        return removeTextBook(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_TEXTBOOK;
    }

    @SneakyThrows
    public static int removeTextBook(String text) {
        String[] words = text.split(" ");
        int set = 0;
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE);
        stat.setInt(1, Integer.parseInt(words[1]));
        set = stat.executeUpdate();
        return set;
    }
}
