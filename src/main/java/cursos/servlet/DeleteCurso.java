package cursos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;

@WebServlet("/DeleteCurso")
public class DeleteCurso extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cursoIdStr = request.getParameter("cursoId");
        if (cursoIdStr != null && !cursoIdStr.isEmpty()) {
            int cursoId = Integer.parseInt(cursoIdStr);
            deleteCurso(cursoId);
        }
        response.sendRedirect(request.getContextPath() + "/AdminCursos");
    }

    private void deleteCurso(int cursoId) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM cursos WHERE id_curso = ?")) {
            ps.setInt(1, cursoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }
}

