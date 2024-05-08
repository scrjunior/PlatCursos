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

@WebServlet("/UpdateCurso")
public class UpdateCurso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve curso details from the form submission
        int cursoId = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getConnection();
            ps = con.prepareStatement("UPDATE cursos SET titulo = ?, descricao = ?, duracao = ?, preco = ? WHERE id_curso = ?");
            ps.setString(1, titulo);
            ps.setString(2, descricao);
            ps.setInt(3, duracao);
            ps.setDouble(4, preco);
            ps.setInt(5, cursoId);

            // Execute the update query
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                // Redirect back to AdminCursos after successful update
                response.sendRedirect("AdminCursos");
            } else {
                // Handle update failure
                request.setAttribute("error", "Failed to update curso");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating curso");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
