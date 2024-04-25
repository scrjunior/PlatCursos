package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cursos.base.DB;

@WebServlet(name = "uploadImage", urlPatterns = {"/uploadImage"})
@MultipartConfig(maxFileSize = 156177216) 
public class uploadImage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Get form data for curso
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            int duracao = Integer.parseInt(request.getParameter("duracao"));
            double preco = Double.parseDouble(request.getParameter("preco"));

            // Insert curso data into cursos table
            Connection con = DB.getConnection();
            PreparedStatement psCurso = con.prepareStatement("INSERT INTO cursos (titulo, descricao, duracao, preco) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            psCurso.setString(1, titulo);
            psCurso.setString(2, descricao);
            psCurso.setInt(3, duracao);
            psCurso.setDouble(4, preco);
            psCurso.executeUpdate();

            // Retrieve the auto-generated ID of the inserted curso
            int idCurso;
            try (ResultSet generatedKeys = psCurso.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCurso = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve auto-generated ID for curso.");
                }
            }

            // Get form data for videos
            for (Part part : request.getParts()) {
                if ("videos".equals(part.getName())) {
                    // Extract video title without file extension
                    String fileName = extractFileName(part);
                    String videoTitulo = fileName.replaceFirst("\\.[^.]+$", "");

                    // Set default duration to 0
                    int videoDuracao = 0;

                    // Insert video data into videos table
                    InputStream is = part.getInputStream();
                    PreparedStatement psVideo = con.prepareStatement("INSERT INTO videos (id_curso, titulo, video, duracao) VALUES (?, ?, ?, ?)");
                    psVideo.setInt(1, idCurso);
                    psVideo.setString(2, videoTitulo);
                    psVideo.setBlob(3, is);
                    psVideo.setInt(4, videoDuracao);
                    psVideo.executeUpdate();
                    is.close();

                    System.out.println("Video Title: " + videoTitulo);
                    System.out.println("Video Duration: " + videoDuracao);
                }
            }

            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            // Print error message to console
            e.printStackTrace();
            // Redirect to error page
            response.sendRedirect("error.jsp");
        }

    }

    // Method to extract file name from content-disposition header of part
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
