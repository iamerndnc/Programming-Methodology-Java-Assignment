/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* GitHub'dan kopyalanan dosyalardaki sınıf dosyalarını analiz eden sınıf
* </p>
*/

package pdpdeneme3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DosyaFiltreleyici {
    public static List<File> sınıfDosyalarınıFiltrele(List<File> dosyalar) {
        List<File> sınıfDosyaları = new ArrayList<>();
        for (File dosya : dosyalar) {
            try (BufferedReader okuyucu = new BufferedReader(new java.io.FileReader(dosya))) {
                String satır;
                while ((satır = okuyucu.readLine()) != null) {
                    if (satır.contains("class ")) {
                        sınıfDosyaları.add(dosya);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sınıfDosyaları;
    }
}

