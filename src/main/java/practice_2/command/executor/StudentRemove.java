package practice_2.command.executor;

import lombok.SneakyThrows;
import practice_2.ApplicationDataSource;
import practice_2.command.CommandType;

import java.sql.PreparedStatement;

public class StudentRemove implements CommandExecutor {

    private static final String SQL_DELETE = "delete from student where id = ?";

    @Override
    public int execute(String text) {
        return removeStudent(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_STUDENT;
    }

    @SneakyThrows
    public static int removeStudent(String text) {
        String[] words = text.split(" ");
        int set = 0;
        PreparedStatement stat = ApplicationDataSource.getConnection().prepareStatement(SQL_DELETE);
        stat.setInt(1, Integer.parseInt(words[1]));
        set = stat.executeUpdate();
        return set;
    }
}
