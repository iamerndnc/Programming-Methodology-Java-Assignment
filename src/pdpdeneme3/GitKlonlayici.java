/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* GitHub'dan dosyaları kopyalayan sınıf
* </p>
*/

package pdpdeneme3;

import java.io.IOException;

public class GitKlonlayici {
    public static void klonla(String repoUrl, String repoAdı) {
        try {
            String klonKomutu = "git clone " + repoUrl;
            Process işlem = Runtime.getRuntime().exec(klonKomutu);
            işlem.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
