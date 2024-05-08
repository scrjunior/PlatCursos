package cursos.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cursos.base.DB;
import cursos.bean.CursosBean;
import cursos.bean.VideoBean;

@WebServlet("/EditCurso")
public class EditCursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cursoIdParam = request.getParameter("id");

        if (cursoIdParam == null || cursoIdParam.isEmpty()) {
            response.sendRedirect("AdminCursos");
            return;
        }

        int cursoId = Integer.parseInt(cursoIdParam);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DB.getConnection();
            
            // Retrieve curso details
            ps = con.prepareStatement("SELECT * FROM cursos WHERE id_curso = ?");
            ps.setInt(1, cursoId);
            rs = ps.executeQuery();

            if (rs.next()) {
                CursosBean curso = new CursosBean();
                curso.setId(rs.getInt("id_curso"));
                curso.setTitulo(rs.getString("titulo"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setDuracao(rs.getInt("duracao"));
                curso.setPreco(rs.getDouble("preco"));

                // Retrieve related videos
                List<VideoBean> videos = new ArrayList<>();
                ps = con.prepareStatement("SELECT * FROM videos WHERE id_curso = ?");
                ps.setInt(1, cursoId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    VideoBean video = new VideoBean();
                    video.setId(rs.getInt("id_video"));
                    video.setTitulo(rs.getString("titulo"));
                    // Assuming 'video' is a BLOB or similar, modify as needed
                    video.setVideo(rs.getBlob("video"));
                    videos.add(video);
                }

                curso.setVideos(videos);

                // Set curso and videos as request attributes
                request.setAttribute("curso", curso);
                request.setAttribute("videos", videos);
                
                // Forward to EditCurso.jsp
                request.getRequestDispatcher("EditCurso.jsp").forward(request, response);
            } else {
                response.sendRedirect("AdminCursos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving curso details");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

