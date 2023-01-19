
package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReaderBD {
        public static List<String> read(String nameFile) {
        List<String> list = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(nameFile)));
            while(true){
                String line = reader.readLine();
                if (line == null) break;
                
                if (isMail(line)){
                    list.add(line);
                }
            }
        }
        catch(FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
        }
        
        return list;
    }
        
        public static boolean isMail(String line){
        return line.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    
    } 
}

