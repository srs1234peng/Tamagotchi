/**
 * FeedStrategy.java
 * Strategy for feeding a Tamagotchi.
 */
public class FeedStrategy implements InteractionStrategy {
  @Override
  public void interact(TamagotchiModel pet) {
    pet.increaseNeed(1, "hunger");
  }
}
