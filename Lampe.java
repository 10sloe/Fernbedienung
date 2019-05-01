import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.Group;
import javafx.scene.control.Button;

import javafx.scene.effect.Lighting;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.Text;
import javafx.scene.shape.Circle;

public class Lampe
{
    // Attribute
    Circle kreis;   // Darstellung der Lampe

    int hue;    // Farbe der Lampe von 0 bis 65535
    int sat;    // Sättigung der Farbe von 1 bis 254
    int bri;    // Helligkeit von 1 bis 254

    boolean on;  // an oder aus

    // Konstruktor
    public Lampe()
    {
        kreis = new Circle(100,120,50, Color.RED);
        kreis.setStroke(Color.BLACK);
        kreis.setStrokeWidth(8); 
        hue = 20000;
        sat = 150;
        bri = 254;
        aktualisieren();
    }
    // Methoden
    /*
     * Gibt alle Informationen über die Lampe auf der Konsole aus
     */
    void infosAusgeben()
    {
        System.out.println("Eigenschaften der Lampe:");
        System.out.println("on: " + on);
        System.out.println("hue: " + hue);
        System.out.println("bri: " + bri);
        System.out.println("sat: " + sat);
    }

    /*
     * Setzt den Farbwert der Lampe Nr. 1
     * Werte zwischen 0 und 65535
     */
    void setHue(int wert)
    {
        hue = wert;
        aktualisieren();
    }

    /*
     * Setzt die Helligkeit der Lampe Nr. 1
     * Werte zwischen 1 und 254
     */
    void setBri(int wert)
    {
        if ((wert > 0 )&&(wert < 255))
        {
            bri = wert;
            aktualisieren();
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
            bri = bri + wert;
            if (bri > 254)
            {
                bri = 254;
            }
            if (bri < 0)
            {
                bri = 0;
            }
            aktualisieren();
        }
    }

    /*
     * Setzt die Sättigung der Lampe Nr. 1
     * Werte zwischen 0 und 254
     */
    void setSat(int wert)
    {
        if ((wert > 0 )&&(wert < 255))
        {
            sat = wert;
            aktualisieren();
        }
    }

    /*
     * Schaltet die Lampe an oder aus
     */
    void setOn(boolean wert)
    {
        on = wert;
        aktualisieren();
    }

    public void aktualisieren()
    {
        if (on)
        {
            kreis.setFill(Color.hsb(hue/182.0, sat/255.0, bri/255.0));
        }
        else
        {
            kreis.setFill(Color.BLACK);
        }
    }

    /*
     *  Die Methode erstellt ein neues Fenster und zeichnet dort
     *  einen Kreis, der eine Lampe symbolisieren soll!
     */
    public void init()
    {
        final Stage stage = new Stage();
        Group rootGroup = new Group();
        Scene scene = new Scene(rootGroup, 200, 200, Color.WHITESMOKE);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        //add some node to scene

        Text text = new Text(10, 30, "Lampe");
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 20));

        //add text to the main root group

        rootGroup.getChildren().addAll(text, kreis);

    }
}
