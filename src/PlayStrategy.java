/**
 * PlayStrategy.java
 * Strategy for playing with a Tamagotchi.
 */
public class PlayStrategy implements InteractionStrategy {
  @Override
  public void interact(TamagotchiModel pet) {
    pet.increaseNeed(1, "happiness");
    pet.notifyObservers("Playing with the pet.");
  }
}