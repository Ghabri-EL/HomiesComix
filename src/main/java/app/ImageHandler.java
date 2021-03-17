package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageHandler {
    private static final String IMAGE_FOLDER_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

    public String saveImages(String title, int id, MultipartFile[] images){
        StringBuilder buildNameString = new StringBuilder();

        for(int i = 0; i < images.length; i++){
            String fileExt = (images[i].getContentType().split("/"))[1];
            String newFileName = title + id + "_p" + i + "." + fileExt;
            Path fileNamePath = Paths.get(IMAGE_FOLDER_PATH, newFileName);
            buildNameString.append(newFileName);

            if(i != images.length - 1){
                buildNameString.append("#");
            }
            try{
                Files.write(fileNamePath, images[i].getBytes());
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        return buildNameString.toString();
    }

    public String[] parseNamesString(String names){
        return  names.split("#");
    }
}
