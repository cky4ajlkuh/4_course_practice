package practice_3;

import practice_2.ApplicationDataSource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/table")
public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter out = resp.getWriter()) {
            Statement statement = ApplicationDataSource.getConnection().createStatement();
            ResultSet set = statement.executeQuery("select * from student join textbook on student.id = textbook.student_id;");
            createTable(set, out);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(ResultSet set, PrintWriter out) throws SQLException {
        out.println("<HTML><BODY>");
        if (set.next()) {
            out.print("<table><tr><th>student_id</th><th>firstName</th><th>lastName</th><th>title</th><th>book_id</th></tr>");
            do {
                out.print("<tr>");
                out.print("<td>" + set.getObject("id") + "</td>");
                out.print("<td>" + set.getObject("firstname") + "</td>");
                out.print("<td>" + set.getObject("lastname") + "</td>");
                out.print("<td>" + set.getObject("title") + "</td>");
                out.print("<td>" + set.getObject("book_id") + "</td>");
                out.print("</tr>");
            } while (set.next());
            out.print("</table>");
        }
        out.println("</BODY></HTML>");
    }
}
