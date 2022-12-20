package practice_2.command.executor;

import lombok.SneakyThrows;
import practice_2.ApplicationDataSource;
import practice_2.command.CommandType;

import java.sql.Statement;

public class StudentCreate implements CommandExecutor {
    @Override
    public int execute(String text) {
        return createStudent(text);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_STUDENT;
    }

    @SneakyThrows
    public static int createStudent(String text) {
        String[] words = text.split(" ");
        int set = 0;
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        set = statement.executeUpdate("insert into student(id, firstname, lastname) values(" + words[1]
                + ",'" + words[2] + "','" + words[3] + "');");
        statement.close();
        return set;
    }
}
