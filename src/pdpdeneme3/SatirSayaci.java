/**
*
* @Eren Dinç eren.dinc@ogr.sakarya.edu.tr
* @05.04.2024
* <p>
* Analiz edilen dosyalardaki Javadoc, yorum satır, kod satır, LOC, fonksiyon sayısını hesaplayan fonksiyonların bulunduğu sınıf
* </p>
*/

package pdpdeneme3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SatirSayaci {
	
    public static int kodSatırSayısınıHesapla(File dosya) {
        int kodSatırSayısı = 0;
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                if (!satir.trim().isEmpty() && !satir.trim().startsWith("//") && !satir.trim().startsWith("/*") && !satir.trim().startsWith("/**") && !satir.trim().startsWith("*")) {
                    kodSatırSayısı++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kodSatırSayısı;
    }

    public static int locHesapla(File dosya) {
        int loc = 0;
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) {
            while (okuyucu.readLine() != null) {
                loc++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loc;
    }
    
    public static int JavadocHesapla(File dosya) {
        int javadocSatirSayisi = 0;
        boolean javadocIcerisinde = false;

        Pattern javadocBaslangicDeseni = Pattern.compile("^\\s*/\\*\\*.*");
        Pattern javadocBitisDeseni = Pattern.compile(".*\\*/\\s*$");
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {

                if (!javadocIcerisinde) {
                    Matcher javadocBaslangicEslesme = javadocBaslangicDeseni.matcher(satir);
                    if (javadocBaslangicEslesme.matches()) { // Javadoc bloğu başlangıcı bulundu
                        javadocIcerisinde = true;
                    }
                } else {
                    Matcher javadocBitisEslesme = javadocBitisDeseni.matcher(satir);
                    if (javadocBitisEslesme.matches()) { // Javadoc bloğu sonu bulundu
                        javadocIcerisinde = false;
                    } else {
                        javadocSatirSayisi++;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return javadocSatirSayisi;
    }
    
    public static int FonksiyonHesapla(File dosya) {

        int fonksiyonSayisi = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            StringBuilder content = new StringBuilder();
            String satir;

            while ((satir = reader.readLine()) != null) {
            	content.append(satir).append("\n");
            }

            String fileContent = content.toString();
            String regex = "(?s)\\b(?:public|private|protected)?\\s+(static\\s+)?(\\w+)\\s+([\\w<>]+)\\s*\\([^)]*\\)\\s*\\{[^{}]*\\}";



            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fileContent);

            while (matcher.find()) {
                fonksiyonSayisi++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fonksiyonSayisi;
    }

    public static int YorumHesapla(File dosya) {

            int yorumSayisi = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(dosya))) {
                String satir;
                boolean yorumIcinde = false;

                while ((satir = br.readLine()) != null) {
                    // Tek satırlık yorum satırlarını kontrol et
                    if (!yorumIcinde && satir.trim().contains("//")) {
                        yorumSayisi++;
                    }

                    // Çok satırlı yorum satırlarını kontrol et
                    if (!yorumIcinde && satir.contains("/*")&&!satir.contains("/**")) {
                        // Geçerli satır içerisinde "/*" varsa ve yorum içerisinde değilse, yorum içine al
                        yorumIcinde = !satir.contains("*/");
                        if (!yorumIcinde) {
                            yorumSayisi++;
                        }
                    } else if (yorumIcinde && satir.contains("*/")) {
                        // Geçerli satır içerisinde "*/" varsa ve yorum içindeyse, yorumdan çık
                        yorumIcinde = false;
                    } else if (yorumIcinde) {
                        // Yorum içindeysek, yorum satırı sayısını artır
                        yorumSayisi++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return yorumSayisi;
        }
    
    public static double YGHesapla(int javadocSatirSayisi, int digerYorumlarSatirSayisi, int fonksiyonSayisi) {
        double yg = ((javadocSatirSayisi + digerYorumlarSatirSayisi) * 0.8) / fonksiyonSayisi;
        return yg;
        
    }

    public static double YHHesapla(int kodSatirSayisi, int fonksiyonSayisi) {
        double yh = (kodSatirSayisi / (double) fonksiyonSayisi) * 0.3;
        return yh;
    }

    public static double YorumSapmaYuzdesiHesapla(int javadocSatirSayisi, int digerYorumlarSatirSayisi, int kodSatirSayisi, int fonksiyonSayisi) {
        double yg = YGHesapla(javadocSatirSayisi, digerYorumlarSatirSayisi, fonksiyonSayisi);
        double yh = YHHesapla(kodSatirSayisi, fonksiyonSayisi);
        return ((100 * yg) / yh) - 100;
    }
}
    
