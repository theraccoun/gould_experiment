package generic.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by IntelliJ IDEA.
 * User: theraccoun
 * Date: 10/14/11
 * Time: 3:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageHandler {


    public static BufferedImage getImage(String imageName)
    {

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imageName));
            return img;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new Error("Could not load " + imageName + " with JPEGImageDecoder");
        }
    }
}

