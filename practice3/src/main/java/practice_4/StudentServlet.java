package practice_4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static practice_2.command.executor.StudentCreate.createStudent;
import static practice_2.command.executor.StudentRemove.removeStudent;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameterMap().size() == 3) {
            getServletContext().getRequestDispatcher("/Create_student.jsp").forward(req, resp);
        }
        if (req.getParameterMap().size() == 1) {
            getServletContext().getRequestDispatcher("/Remove_student.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameterMap().size() == 3) {
                String id = req.getParameter("id");
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                createStudent("create " + id + " " + firstName + " " + lastName);
                resp.sendRedirect(req.getContextPath());
            }
            if (req.getParameterMap().size() == 1) {
                String id = req.getParameter("id");
                int i = removeStudent("remove " + id);
                if (i != 1) {
                    throw new RuntimeException("Check the correctness of the student ID");
                }
                resp.sendRedirect(req.getContextPath());
            }
        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
