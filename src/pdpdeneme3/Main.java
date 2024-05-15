/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* Ana işlemin gerçekleştiği Main sınıfı
* </p>
*/

package pdpdeneme3;

public class Main {
    public static void main(String[] args) {
        String repoUrl = RepoUrlAlma.al();
        if (repoUrl == null) {
            System.err.println("GitHub depo URL'si alınamadı.");
            return;
        }

        String repoAdı = repoUrlDenAdıAl(repoUrl);
        GitKlonlayici.klonla(repoUrl, repoAdı);
        Analiz.analizEt(repoAdı);
    }

    public static String repoUrlDenAdıAl(String repoUrl) {
        String[] parçalar = repoUrl.split("/");
        return parçalar[parçalar.length - 1].split("\\.")[0];
    }
}


