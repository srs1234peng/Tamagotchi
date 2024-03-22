/**
 * SleepStrategy.java
 * Strategy for putting a Tamagotchi to sleep.
 */
public class SleepStrategy implements InteractionStrategy {
  @Override
  public void interact(TamagotchiModel pet) {
    pet.sleep();
  }
}