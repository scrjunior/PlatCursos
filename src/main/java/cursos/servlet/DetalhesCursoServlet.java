package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cursos.base.DB;
import cursos.bean.CursosBean;
import cursos.bean.VideoBean;

@WebServlet("/detalhescurso")
public class DetalhesCursoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("usuarioLogado") == null) {
            
            response.sendRedirect("getcursos#loginModal");
            return;
        }

        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
            int cursoId = Integer.parseInt(request.getParameter("cursoId"));

            con = DB.getConnection();
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

                
                InputStream bannerInputStream = rs.getBinaryStream("banner");
                if (bannerInputStream != null) {
                    byte[] bytes = bannerInputStream.readAllBytes();
                    String base64Image = Base64.getEncoder().encodeToString(bytes);
                    curso.setBannerBase64(base64Image);
                }

                
                List<VideoBean> videos = new ArrayList<>();
                ps = con.prepareStatement("SELECT * FROM videos WHERE id_curso = ?");
                ps.setInt(1, cursoId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    VideoBean video = new VideoBean();
                    video.setId(rs.getInt("id_video"));
                    video.setTitulo(rs.getString("titulo"));
                    video.setVideo(rs.getBlob("video"));
                    videos.add(video);
                }
                curso.setVideos(videos);

                
                request.setAttribute("curso", curso);

                
                request.getRequestDispatcher("course.jsp").forward(request, response);
            } else {
                
                response.sendRedirect("error.jsp"); 
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
        } finally {
            
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
