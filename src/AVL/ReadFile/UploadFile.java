package AVL.ReadFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UploadFile {
    public static String[] upload(String path) throws  IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine().replaceAll("\n", "");
            while (line != null) {
                sb.append(line.replaceAll("\n", ""));
                sb.append(" ");
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String[] everything = sb.toString().split(" ");
            return everything;
        } finally {
            br.close();
        }
    }
}
