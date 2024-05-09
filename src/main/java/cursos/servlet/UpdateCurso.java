package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import cursos.base.DB;

@WebServlet("/UpdateCurso")
@MultipartConfig
public class UpdateCurso extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cursoId = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        int duracao = Integer.parseInt(request.getParameter("duracao"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        Connection con = null;
        PreparedStatement psCurso = null;
        PreparedStatement psVideo = null;

        try {
            con = DB.getConnection();
            con.setAutoCommit(false);

            
            psCurso = con.prepareStatement("UPDATE cursos SET titulo = ?, descricao = ?, duracao = ?, preco = ? WHERE id_curso = ?");
            psCurso.setString(1, titulo);
            psCurso.setString(2, descricao);
            psCurso.setInt(3, duracao);
            psCurso.setDouble(4, preco);
            psCurso.setInt(5, cursoId);
            psCurso.executeUpdate();

            
         
            Part bannerPart = request.getPart("bannerImage");
            if (bannerPart != null && bannerPart.getSize() > 0) {
                InputStream bannerInputStream = bannerPart.getInputStream();

                
                PreparedStatement psUpdateBanner = con.prepareStatement("UPDATE cursos SET banner = ? WHERE id_curso = ?");
                psUpdateBanner.setBlob(1, bannerInputStream);
                psUpdateBanner.setInt(2, cursoId);
                psUpdateBanner.executeUpdate();

                bannerInputStream.close();
                System.out.println("Novo Banner adicionado para o curso com ID: " + cursoId);
            }

            
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if (paramName.startsWith("videoTitle-")) {
                    int videoId = Integer.parseInt(paramName.substring("videoTitle-".length()));
                    String videoTitle = request.getParameter(paramName);

                    
                    psVideo = con.prepareStatement("UPDATE videos SET titulo = ? WHERE id_video = ?");
                    psVideo.setString(1, videoTitle);
                    psVideo.setInt(2, videoId);
                    psVideo.executeUpdate();
                }
            }

            
            for (Part part : request.getParts()) {
                if ("novoVideo".equals(part.getName()) && part.getSize() > 0) {
                    String fileName = extractFileName(part);
                    String videoTitulo = fileName.replaceFirst("\\.[^.]+$", "");
                    InputStream is = part.getInputStream();

                    psVideo = con.prepareStatement("INSERT INTO videos (id_curso, titulo, video) VALUES (?, ?, ?)");
                    psVideo.setInt(1, cursoId);
                    psVideo.setString(2, videoTitulo);
                    psVideo.setBlob(3, is);
                    psVideo.executeUpdate();

                    is.close();
                    System.out.println("Novo Vídeo Adicionado: " + videoTitulo);
                }
            }

            con.commit();
            response.sendRedirect("AdminCursos");
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
            request.setAttribute("error", "Error updating curso");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            try {
                if (psCurso != null) psCurso.close();
                if (psVideo != null) psVideo.close();
                if (con != null) con.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
