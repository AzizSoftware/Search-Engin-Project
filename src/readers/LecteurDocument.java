package readers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LecteurDocument implements DocumentReader {

    @Override
    public List<String> lireDocument(String path) {
        List<String> contenu = new ArrayList<String>();
        BufferedReader br = null;
        try {
            String currentLine;
            br = new BufferedReader(new FileReader(path));
            while ((currentLine = br.readLine()) != null) {
                contenu.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return contenu;

    }

}

