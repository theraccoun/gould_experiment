import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Scanner;

public class CSVHandler
{

    private static CSVHandler instance;
    private static final String EXPERIMENT_LAYOUT_FILE_NAME = "exerperiment_layout.csv";
    private Scanner experimentLayoutScanner;

    public static CSVHandler getInstance(){

        if(instance == null){
            instance = new CSVHandler();
        }

        return instance;
    }

    private CSVHandler(){

        try {

            InputStream is = getFileResource(EXPERIMENT_LAYOUT_FILE_NAME);
            experimentLayoutScanner = new Scanner(is);

        } catch (FileNotFoundException e) {
            System.out.println("Could not retrieve: " + EXPERIMENT_LAYOUT_FILE_NAME);
            e.printStackTrace();
        }
    }

    public String[] getNextLine(){

        String[] nextLine = null;

        if(experimentLayoutScanner.hasNextLine()){
            nextLine = experimentLayoutScanner.nextLine().split(",");
        }

        return nextLine;
    }

    private synchronized InputStream getFileResource(final String fileName)
            throws FileNotFoundException
    {
        InputStream iStream = ClassLoader.getSystemResourceAsStream(fileName);

        if(iStream == null)
        {
            throw new FileNotFoundException
                    (
                            "File not found: \'"+fileName+"\'"
                    );
        }

        return(iStream);
    }
}