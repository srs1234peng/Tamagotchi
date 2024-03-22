import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the TamagotchiModel class.
 */
public class TamagotchiModelTest {
  private TamagotchiModel tamagotchi;

  @Before
  public void setUp() {
    tamagotchi = new TamagotchiModel();
  }

  @Test
  public void testInitialValues() {
    assertEquals(0, tamagotchi.getAge());
    assertEquals(5.0f, tamagotchi.getHunger(), 0.0f);
    assertEquals(5.0f, tamagotchi.getHappiness(), 0.0f);
    assertEquals(5.0f, tamagotchi.getHygiene(), 0.0f);
    assertEquals(5.0f, tamagotchi.getSleepiness(), 0.0f);
    assertFalse(tamagotchi.isSleeping());
    assertEquals(PetState.BABY, tamagotchi.getPetState());
  }

  @Test
  public void testSetters() {
    tamagotchi.setHunger(3.5f);
    assertEquals(3.5f, tamagotchi.getHunger(), 0.0f);

    tamagotchi.setHappiness(4.2f);
    assertEquals(4.2f, tamagotchi.getHappiness(), 0.0f);

    tamagotchi.setHygiene(2.8f);
    assertEquals(2.8f, tamagotchi.getHygiene(), 0.0f);

    tamagotchi.setSleepiness(1.3f);
    assertEquals(1.3f, tamagotchi.getSleepiness(), 0.0f);

  }

  @Test
  public void testDecreaseNeeds() {
    // Simulate multiple time steps
    for (int i = 0; i < 10; i++) {
      tamagotchi.decreaseNeeds();
    }
    // Ensure that needs have decreased over time
    assertTrue(tamagotchi.getHunger() < 5.0f);
    assertTrue(tamagotchi.getHappiness() < 5.0f);
    assertTrue(tamagotchi.getHygiene() < 5.0f);
    assertTrue(tamagotchi.getSleepiness() < 5.0f);
  }

  @Test
  public void testInteractWhileSleeping() {
    tamagotchi.sleep();
    // Interacting while sleeping should not change needs
    InteractionStrategy strategy = new CleanStrategy();  // Use a valid strategy class
    tamagotchi.performAction(strategy);
    assertEquals(5.0f, tamagotchi.getHunger(), 0.0f);
    assertEquals(5.0f, tamagotchi.getHappiness(), 0.0f);
    assertEquals(5.0f, tamagotchi.getHygiene(), 0.0f);
    assertEquals(5.0f, tamagotchi.getSleepiness(), 0.0f);
  }

  @Test
  public void testSwitchAgeStatus() {
    tamagotchi.switchToAdult();
    assertEquals(PetState.ADULT, tamagotchi.getPetState());
    tamagotchi.switchToSenior();
    assertEquals(PetState.SENIOR, tamagotchi.getPetState());
  }

  @Test
  public void testSwitchToBaby() {
    tamagotchi.switchToBaby();
    assertEquals(PetState.BABY, tamagotchi.getPetState());
  }

  @Test
  public void testSwitchToAdult() {
    tamagotchi.switchToAdult();
    assertEquals(PetState.ADULT, tamagotchi.getPetState());
  }

  @Test
  public void testSwitchToSenior() {
    tamagotchi.switchToSenior();
    assertEquals(PetState.SENIOR, tamagotchi.getPetState());
  }

  @Test
  public void testSleep() {
    tamagotchi.sleep();
    assertTrue(tamagotchi.isSleeping());
    assertEquals(5.0f, tamagotchi.getSleepiness(), 0.0f);
  }

  @Test
  public void testDisplayCurrentNeeds() {
    // Redirect console output for testing
    ConsoleOutputTestUtility consoleOutputUtility = new ConsoleOutputTestUtility();
    consoleOutputUtility.redirectSystemOut();
    tamagotchi.displayCurrentNeeds();
    // Retrieve the console output and restore normal output
    String consoleOutput = consoleOutputUtility.recoverSystemOut();
    assertTrue(consoleOutput.contains("Current Needs"));
    // Reset the console output to the original System.out
    consoleOutputUtility.resetSystemOut();
  }

  // Helper class for redirecting and recovering System.out
  private static class ConsoleOutputTestUtility {
    private final StringBuilder consoleOutput = new StringBuilder();
    private final PrintStream originalSystemOut = System.out;
    void redirectSystemOut() {
      System.setOut(new PrintStream(new OutputStream() {
        @Override
        public void write(int b) {
          consoleOutput.append((char) b);
        }
      }));
    }
    String recoverSystemOut() {
      System.setOut(originalSystemOut);
      return consoleOutput.toString();
    }
    void resetSystemOut() {
      System.setOut(originalSystemOut);
    }
  }
}
