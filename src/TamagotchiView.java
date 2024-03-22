import java.util.Observable;
import java.util.Observer;

/**
 * The view for the Tamagotchi.
 */
public class TamagotchiView implements Observer {
  private TamagotchiModel tamagotchi;

  /**
   * Constructor for the TamagotchiView class.
   *
   * @param tamagotchi The model for the Tamagotchi
   */
  public TamagotchiView(TamagotchiModel tamagotchi) {
    this.tamagotchi = tamagotchi;
    tamagotchi.addObserver(this);
  }

  private void displayCurrentState() {
    System.out.printf("Current Needs: Hunger: %d, Happiness: %d, Hygiene: %d, Sleepiness: %d%n",
            (int) tamagotchi.getHunger(), (int) tamagotchi.getHappiness(),
            (int) tamagotchi.getHygiene(), (int) tamagotchi.getSleepiness());
    System.out.printf("Current Age: %d%n", tamagotchi.getAge());
  }

  /**
   * This method is called whenever the observed object is changed. An
   * application calls an {@code Observable} object's
   * {@code notifyObservers} method to have all the object's
   * observers notified of the change.
   *
   * @param o   the observable object.
   * @param arg an argument passed to the {@code notifyObservers}
   *            method.
   */
  @Override
  public void update(Observable o, Object arg) {
    if (arg != null) {
      System.out.println(arg.toString());
    } else {
      displayCurrentState();
    }
  }
}
