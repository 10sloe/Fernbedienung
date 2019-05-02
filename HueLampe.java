import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.concurrent.*;

class HueLampe
{
    String bridgeIP = "10.10.83.204";    // Adresse der HueBridge im Schulnetzwerk
    String username = "ZWKT3UaornxBVQks5HpDli8V1Nmbqi9SE48p8bRT";  //Name eines in der Hue-Bridge registrierten Users
    int nummer = 1;
    String adresse = "http://"+bridgeIP+"/api/"+username + "/lights/" + nummer;  // Adresse der Lampe

    // Methoden der HUE
    /*
     * Gibt alle Informationen über die Lampe auf der Konsole aus
     */
    public HueLampe(String ip)
    {
        bridgeIP = ip;
    }

    void infosAusgeben()
    {
        get(adresse, this);
    }

    /*
     * Schaltet die Lampe an oder aus
     */
    void setOn(boolean wert)
    {
        String nachricht = "{\"on\":" + wert + "}";
        put(adresse + "/state",nachricht);
    }

    /*
     * Setzt den Farbwert der Lampe
     * Werte zwischen 0 und 65535
     */
    void setHue(int hue)
    {
        String nachricht = "{\"hue\":"+hue+"}";
        put(adresse + "/state",nachricht);
    } 

    /*
     * Setzt die Helligkeit der Lampe
     * Werte zwischen 1 und 254
     */
    void setBri(int wert)
    {
        if ((wert > 0 )&&(wert < 255))
        {
            String nachricht = "{\"bri\":"+wert+"}";
            put(adresse + "/state",nachricht);
        }
    }

    /*
     * Verändert den Wert der Helligkeit der Lampe
     * Erlaubte Wertee zwischen -254 und 254
     */
    void setBriInc(int wert)
    {
        if ((wert > -255 )&&(wert < 255))
        {
            String nachricht = "{\"bri_inc\":"+wert+"}";
            put(adresse + "/state",nachricht);
        }
    }

    /*
     * Setzt die Sättigung der Lampe 
     * Werte zwischen 0 und 254
     */
    void setSat(int wert)
    {
        if ((wert > 0 )&&(wert < 255))
        {
            String adresse = "http://"+bridgeIP+"/api/"+username+"/lights/1/state";
            String nachricht = "{\"sat\":"+wert+"}";
            put(adresse,nachricht);
        }
    }

    void setNummer(int wert)
    {
        nummer = wert;
        adresse = "http://"+bridgeIP+"/api/"+username + "/lights/" + nummer;
    }

    void init()
    {
        // tue nichts
    }

    /*
     * Mit dieser Methode kann man den Zustand der Lampe setzen
     */

    private void put(String adresse, String nachricht){  

        final Task<String> task = new Task<String>() {

                protected String call() throws Exception {
                    String erg = null;
                    try {
                        URL url = new URL(adresse);  

                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("PUT");
                        con.setDoOutput(true);
                        OutputStreamWriter out = new OutputStreamWriter(
                                con.getOutputStream());
                        out.write(nachricht);
                        out.close();

                        String readStream = readStream(con.getInputStream());
                        // Give output for the command line
                        System.out.println(readStream);
                        erg = readStream;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return erg;
                }
            };

        Thread thread = new Thread(task);
        thread.start();

    }

    /*
     * Mit dieser Methode kann man Information von der Lampe bekommen
     */
    private void get(String adresse, HueLampe lampe){  
        final Task<String> task = new Task<String>() {
                protected String call() throws Exception {
                    String erg = null;
                    try {
                        URL url = new URL(adresse);                      

                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        String readStream = readStream(con.getInputStream());
                        // Give output for the command line
                        System.out.println(readStream);
                        erg = readStream;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return erg;
                }
            };

        Thread thread = new Thread(task);
        thread.start();

    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
