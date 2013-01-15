import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * @author Steven MacCoun
 */
public class InstructionReader {

    private Scanner scanner;

    public InstructionReader(File fis) throws FileNotFoundException {

        this.scanner = new Scanner(fis);
    }

//    public JPanel getNextInstruction(){
//
//          if(scanner.hasNextLine()){
//
//          }
//    }
}
