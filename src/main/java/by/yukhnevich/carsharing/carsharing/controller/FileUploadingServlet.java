package by.yukhnevich.carsharing.carsharing.controller;

import by.yukhnevich.carsharing.carsharing.controller.command.Command;
import by.yukhnevich.carsharing.carsharing.controller.command.CommandType;
import by.yukhnevich.carsharing.carsharing.util.RequestParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Servlet that used for uploading images
 *
 * @see HttpServlet
 */
@WebServlet(name = "FileUploadServlet", urlPatterns = "/Upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {


    private static final Logger LOGGER = LogManager.getLogger();
    private static final String IMAGE_EDITOR_PART = "image_editor";
    private static final String IMAGES_DIRECTORY_NAME = "images";
    private static final String UPLOAD_DIR = "D:\\Java\\source\\carsharing\\src\\main\\webapp\\"
            + IMAGES_DIRECTORY_NAME;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart(IMAGE_EDITOR_PART);
        String filename = part.getSubmittedFileName();
        String upload_path = UPLOAD_DIR + File.separator + filename;
        LOGGER.debug(File.separator);
        LOGGER.debug(upload_path);

        String imagePath = IMAGES_DIRECTORY_NAME + File.separator + filename;

        LOGGER.debug(Paths.get(upload_path + filename).toAbsolutePath());
        if (Files.exists(Paths.get(upload_path + filename).toAbsolutePath())) {
            imagePath = request.getParameter(RequestParameter.IMAGE_PATH);
        }

        boolean isSuccess;
        try (InputStream inputStream = part.getInputStream()) {
            isSuccess = uploadFile(inputStream, upload_path);
            LOGGER.debug(isSuccess);
        }
        if (isSuccess) {
            request.setAttribute(RequestParameter.IMAGE_PATH, imagePath);
            LOGGER.debug(request);
            process(request, response);
        }
    }

    /**
     * Uploads image with stream
     *
     * @param inputStream stream taken from {@link Part}
     * @param path        directory where image will be stored
     * @return true if file was successfully uploaded
     * @throws ServletException if IOException was thrown while uploading
     */
    private boolean uploadFile(InputStream inputStream, String path) throws ServletException {
        try {
            byte[] bytes = new byte[inputStream.available()];
            int result = inputStream.read(bytes);
            LOGGER.debug(inputStream.toString());
            LOGGER.debug(result);
            if (result != -1) {
                try (FileOutputStream fops = new FileOutputStream(path)) {
                    fops.write(bytes);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ServletException(e);
        }
        return true;
    }

    /**
     * Process the request
     *
     * @param request contains the command that will be executed
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandType.defineCommand(name);
        command.execute(request, response);
    }

}
