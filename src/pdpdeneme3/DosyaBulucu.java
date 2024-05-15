/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* GitHub linkinden .java uzantılı dosyaları ayıklayan sınıf
* </p>
*/

package pdpdeneme3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DosyaBulucu {
    public static List<File> javaDosyalarınıBul(File dizin) {
        List<File> javaDosyaları = new ArrayList<>();
        File[] dosyalar = dizin.listFiles();
        if (dosyalar != null) {
            for (File dosya : dosyalar) {
                if (dosya.isFile() && dosya.getName().endsWith(".java")) {
                    javaDosyaları.add(dosya);
                } else if (dosya.isDirectory()) {
                    javaDosyaları.addAll(javaDosyalarınıBul(dosya));
                }
            }
        }
        return javaDosyaları;
    }
}

