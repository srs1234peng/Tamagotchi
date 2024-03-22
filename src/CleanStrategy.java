/**
 * CleanStrategy.java
 * Strategy for cleaning a Tamagotchi.
 */
public class CleanStrategy implements InteractionStrategy {
  @Override
  public void interact(TamagotchiModel pet) {
    pet.increaseNeed(1, "hygiene");
    pet.notifyObservers("Cleaning the pet.");
  }
}