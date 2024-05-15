/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* GitHub reposunu analiz eden sınıf
* </p>
*/

package pdpdeneme3;

import java.io.File;
import java.util.List;

public class Analiz {
    public static void analizEt(String repoAdı) {
        File repoDizini = new File(repoAdı);
        if (!repoDizini.exists() || !repoDizini.isDirectory()) {
            System.err.println("Klonlanan depo dizini bulunamadı.");
            return;
        }

        List<File> javaDosyaları = DosyaBulucu.javaDosyalarınıBul(repoDizini);
        List<File> sınıfDosyaları = DosyaFiltreleyici.sınıfDosyalarınıFiltrele(javaDosyaları);

        for (File sınıfDosyası : sınıfDosyaları) {
            int kodSatırSayısı = SatirSayaci.kodSatırSayısınıHesapla(sınıfDosyası);
            int loc = SatirSayaci.locHesapla(sınıfDosyası);
            int javadocSatirSayisi = SatirSayaci.JavadocHesapla(sınıfDosyası);
            int fonksiyonSayisi = SatirSayaci.FonksiyonHesapla(sınıfDosyası);
            int yorumSayisi = SatirSayaci.YorumHesapla(sınıfDosyası);
            double yg = SatirSayaci.YGHesapla(javadocSatirSayisi, yorumSayisi, fonksiyonSayisi);
            double yh = SatirSayaci.YHHesapla(kodSatırSayısı,fonksiyonSayisi);
            double YorumSapmaYuzdesi = SatirSayaci.YorumSapmaYuzdesiHesapla(javadocSatirSayisi,yorumSayisi,kodSatırSayısı,fonksiyonSayisi);
            
            
            
            System.out.println("Sınıf: " + sınıfDosyası.getName());
            System.out.println("Javadoc satır sayısı: " + javadocSatirSayisi);
            System.out.println("Yorum satır sayısı: " + yorumSayisi);
            System.out.println("Kod satır sayısı: " + kodSatırSayısı);
            System.out.println("LOC: " + loc);
            System.out.println("Fonksiyon sayısı: " + fonksiyonSayisi);
            System.out.println("Yorum Sapma Yüzdesi: %" + YorumSapmaYuzdesi);
            System.out.println("----------------------");
        }
        
    }
}
