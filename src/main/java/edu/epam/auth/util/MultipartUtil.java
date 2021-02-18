package edu.epam.auth.util;

import edu.epam.auth.constant.PathConstant;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MultipartUtil {

    public static Map<String, String[]> extractMultipartRequestParameters(HttpServletRequest req) throws IOException, FileUploadException {
        ServletFileUpload upload = new ServletFileUpload();
        Map<String, String[]> requestParameters = new HashMap<>();

        FileItemIterator iterator = upload.getItemIterator(req);
        while (iterator.hasNext()) {
            FileItemStream item = iterator.next();
            String name = item.getFieldName();
            InputStream stream = item.openStream();
            if (item.isFormField()) {
                String[] parameter = {Streams.asString(stream)};
                requestParameters.put(name, parameter);
            } else {
                String imageName = item.getName();
                if(!imageName.isEmpty()) {
                    FileUtils.copyInputStreamToFile(
                            stream, new File(PathConstant.USER_IMAGE_PATH + imageName));
                    String[] parameter = {item.getName()};
                    requestParameters.put(name, parameter);
                }
            }
        }
        return requestParameters;
    }
}
