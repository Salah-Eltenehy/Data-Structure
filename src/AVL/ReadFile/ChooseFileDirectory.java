package AVL.ReadFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ChooseFileDirectory extends Component {
    public  String chooseFile() {
        JFileChooser jFileChooser = new JFileChooser();
        /*
        set default place
         */
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        //set title of window
        jFileChooser.setDialogTitle("choose file to open");
        int result = jFileChooser.showOpenDialog(this);
        String directory = "";
        if(result == JFileChooser.APPROVE_OPTION) {
            /*
            get the required direction from user
             */
            directory = jFileChooser.getSelectedFile().getAbsolutePath();
        }
        String resultPath = "";
        // handle "\"
        for (int i=0; i<directory.length(); i++) {
            if(directory.charAt(i) != '\\')
                resultPath += directory.charAt(i);
            else {
                resultPath += '\\';
                resultPath += '\\';
            }
        }
        return resultPath;
    }
}
