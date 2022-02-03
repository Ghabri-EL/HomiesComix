package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageHandler {
    //private static final String IMAGE_FOLDER_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
    private static final String IMAGE_FOLDER_PATH = System.getProperty("user.dir") + "\\images";

    //it'll take the array of images uploaded and change the name of each image to [id]_p[image number].jpg => 6969_p1.jpg
    //it returns a string of all of the names of the saved images separated by '#' character
    public String saveImages(int id, MultipartFile[] images){
        System.out.println("PATH:" + IMAGE_FOLDER_PATH);
        StringBuilder buildNameString = new StringBuilder();

        for(int i = 0; i < images.length; i++){
            String fileExt = (images[i].getContentType().split("/"))[1];
            String newFileName = id + "_p" + (i + 1) + "." + fileExt;
            newFileName = newFileName.toLowerCase();
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

    //helper function to parse the string of names
    public String[] parseNamesString(String names){
        return names.split("#");
    }
}
