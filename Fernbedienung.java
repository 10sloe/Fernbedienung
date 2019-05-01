import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/*
 * Mit dieser Fernbedienung kann man eine echte Hue-Lampe steuern oder 
 * eine simulierte Hue-Lampe
 */
public class Fernbedienung {

    // Attribute
    Lampe lampe; // Referenz zur Lampe
    //HueLampe lampe; // Referenz zur HueLampe

    // Konstruktor
    public Fernbedienung()
    {
        lampe = new Lampe();
        // lampe = new HueLampe();

    }

    // Methoden
    /*
     * Diese Methode wird aufgerufen, wenn der an-Button gedrückt wurde
     */
    @FXML
    void an(ActionEvent event) {
        lampe.setOn(true);
    }

    /*
     * Diese Methode wird aufgerufen, wenn der aus-Button gedrückt wurde
     */
    @FXML
    void aus(ActionEvent event) {
        lampe.setOn(false);
    }

    /*
     * Diese Methode wird aufgerufen, wenn der minus-Button gedrückt wurde
     */
    @FXML
    void minus(ActionEvent event) {
        lampe.setBriInc(-20);

    }

    /*
     * Diese Methode wird aufgerufen, wenn der plus-Button gedrückt wurde
     */
    @FXML
    void plus(ActionEvent event) {        
        lampe.setBriInc(20);
    }

    /*
     * Diese Methode wird aufgerufen, wenn die Oberfläche erstellt worden ist
     */
    @FXML
    void initialize() {
        lampe.init();
        //lampe.infosAusgeben();
    }
}
