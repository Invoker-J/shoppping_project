package uz.pdp.shopping_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "fileServlet",value = "/admin/file/servlet")
@MultipartConfig
public class FileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Part part = req.getPart("filebek");
//        InputStream inputStream = part.getInputStream();
//        System.getProperty("");
//
//        OutputStream outputStream = Files.newOutputStream(Path.of("C:/Users/user/file/coming_from_web.jpg"));
//        outputStream.write(inputStream.readAllBytes());
        System.out.println("Oxshadi");
    }
}
