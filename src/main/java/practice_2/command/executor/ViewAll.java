package practice_2.command.executor;

import practice_2.ApplicationDataSource;
import practice_2.TableGenerator;
import practice_2.command.CommandType;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewAll implements CommandExecutor {
    @Override
    public int execute(String text) {
        return viewAll();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.WRITE_ALL;
    }

    private int viewAll() {
        try {
            Statement statement = ApplicationDataSource.getConnection().createStatement();
            ResultSet set = statement.executeQuery("select * from student join textbook on student.id = textbook.studentid;");
            createTable(set);
            statement.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 1;
    }

    public void createTable(ResultSet set) throws SQLException {
        TableGenerator tableGenerator = new TableGenerator();
        List<String> headersList = new ArrayList<>();
        List<List<String>> rowsList = new ArrayList<>();
        ResultSetMetaData data = set.getMetaData();
        int columnsNumber = data.getColumnCount();
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                headersList.add(data.getColumnName(i));
                row.add(set.getString(i));
            }
            rowsList.add(row);
            break;
        }
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                row.add(set.getString(i));
            }
            rowsList.add(row);
        }
        System.out.print(tableGenerator.generateTable(headersList, rowsList));
    }
}
