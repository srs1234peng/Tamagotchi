import javax.swing.SwingUtilities;

/**
 * Driver class for the Tamagotchi program.
 */
public class Driver {
  /**
   * Main method for the Tamagotchi program.
   *
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TamagotchiModel tamagotchiModel = new TamagotchiModel();
      SwingTamagotchiController tamaController = new SwingTamagotchiController(tamagotchiModel);

    });
  }
}