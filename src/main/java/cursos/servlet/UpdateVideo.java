package cursos.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cursos.base.DB;

@WebServlet("/UpdateVideoServlet")
public class UpdateVideo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String videoIdStr = request.getParameter("videoId");
        String videoTitle = request.getParameter("videoTitle");
        Part videoFilePart = request.getPart("videoFile");

        if (videoIdStr == null || videoTitle == null || videoFilePart == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int videoId = Integer.parseInt(videoIdStr);
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DB.getConnection();
            ps = con.prepareStatement("UPDATE videos SET titulo = ?, video = ? WHERE id_video = ?");
            ps.setString(1, videoTitle);
            ps.setBlob(2, videoFilePart.getInputStream());
            ps.setInt(3, videoId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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

