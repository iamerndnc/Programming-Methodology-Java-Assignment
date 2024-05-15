/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* Klonlanacak GitHub deposunun linkini alan sınıf
* </p>
*/

package pdpdeneme3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepoUrlAlma {
    public static String al() {
        try {
            // Kullanıcıdan GitHub depo linkini al
            BufferedReader okuyucu = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Lütfen GitHub depo linkini girin:");
            return okuyucu.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

