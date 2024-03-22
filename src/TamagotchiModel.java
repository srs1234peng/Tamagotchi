import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The model for the Tamagotchi.
 */
public class TamagotchiModel extends Observable {
  private int age;
  private float hunger;
  private float happiness;
  private float hygiene;
  private float sleepiness;
  private boolean isSleeping;
  private final long sleepDuration; // in milliseconds
  private PetState petState;
  private String lastMessage;

  /**
   * Constructor for the TamagotchiModel class.
   */
  public TamagotchiModel() {
    this.age = 0;
    this.hunger = 5f;
    this.happiness = 5f;
    this.hygiene = 5f;
    this.sleepiness = 5f;
    this.isSleeping = false;
    this.sleepDuration = 8000; // 8 seconds
    this.petState = PetState.BABY; // Initial pet age status
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        updatePetStatus();
      }
    }, 3000, 3000);
    // Simulating 3 seconds intervals
  }

  /**
   * Updates the pet's status.
   */
  private void updatePetStatus() {
    decreaseNeeds();
    age++;
    switchAgeStatus();
    setChanged();
    notifyObservers();
  }

  /**
   * Getter for the age property.
   *
   * @return The age of the pet.
   */
  public int getAge() {
    return age;
  }

  /**
   * Setter for the hunger property.
   *
   * @return The hunger of the pet.
   */
  public float getHunger() {
    return hunger;
  }

  /**
   * Getter for the happiness property.
   *
   * @return The happiness of the pet.
   */
  public float getHappiness() {
    return happiness;
  }

  /**
   * Getter for the hygiene property.
   *
   * @return The hygiene of the pet.
   */
  public float getHygiene() {
    return hygiene;
  }

  /**
   * Getter for the sleepiness property.
   *
   * @return The sleepiness of the pet.
   */
  public float getSleepiness() {
    return sleepiness;
  }

  /**
   * Getter for the isSleeping property.
   *
   * @return Whether the pet is sleeping or not.
   */
  public boolean isSleeping() {
    return isSleeping;
  }

  /**
   * Getter for the lastMessage property.
   *
   * @return The last message of the pet.
   */
  public String getLastMessage() {
    String message = lastMessage;
    lastMessage = null;  // Clear the last message after retrieving it
    return message;
  }


  /**
   * Setter for the lastMessage property.
   *
   * @param message The message to set the lastMessage to.
   */
  private void setLastMessage(String message) {
    this.lastMessage = message;
  }

  /**
   * Getter for the pet state.
   *
   * @return The age state of the pet.
   */
  public PetState getPetState() {
    return petState;
  }

  /**
   * Setter for the hunger property.
   *
   * @param hunger The hunger to set the pet to.
   */
  public void setHunger(float hunger) {
    this.hunger = hunger;
  }

  public void setHappiness(float happiness) {
    this.happiness = happiness;
  }

  public void setHygiene(float hygiene) {
    this.hygiene = hygiene;
  }

  /**
   * Setter for the sleepiness property.
   *
   * @param sleepiness The sleepiness to set the pet to.
   */
  public void setSleepiness(float sleepiness) {
    this.sleepiness = sleepiness;
  }


  /**
   * Private helper method for the property.
   *
   * @param propertyName The name of the property to get.
   * @return The value of the property.
   */
  private float getPropertyValue(String propertyName) {
    switch (propertyName) {
      case "hunger":
        return getHunger();
      case "happiness":
        return getHappiness();
      case "hygiene":
        return getHygiene();
      case "sleepiness":
        return getSleepiness();
      default:
        throw new IllegalArgumentException("Invalid property name");
    }
  }

  /**
   * Setter for the properties of the pet.
   *
   * @param propertyName The name of the property to set.
   * @param value        The value to set the property to.
   */
  private void setPropertyValue(String propertyName, float value) {
    switch (propertyName) {
      case "hunger":
        setHunger(value);
        break;
      case "happiness":
        setHappiness(value);
        break;
      case "hygiene":
        setHygiene(value);
        break;
      case "sleepiness":
        setSleepiness(value);
        break;
      default:
        throw new IllegalArgumentException("Invalid property name");
    }
  }

  /**
   * Decreases the needs of the pet.
   */
  public void decreaseNeeds() {
    if (isSleeping) {
      // If the pet is sleeping, do not decrease sleepiness
      return;
    }
    decreaseNeed(petState.getHungerDecreaseRate(), "hunger");
    decreaseNeed(petState.getHappinessDecreaseRate(), "happiness");
    decreaseNeed(petState.getHygieneDecreaseRate(), "hygiene");
    decreaseNeed(petState.getSleepinessDecreaseRate(), "sleepiness");
  }

  /**
   * Decreases a certain need of the pet.
   *
   * @param decreaseRate The rate at which to decrease the need.
   * @param propertyName The name of the property to decrease.
   */
  public void decreaseNeed(float decreaseRate, String propertyName) {
    float currentValue = getPropertyValue(propertyName);
    float newValue = Math.max(1, currentValue - decreaseRate);
    // Update the property with the new value
    setPropertyValue(propertyName, newValue);
    // Check for critical levels and handle warnings or death
    if (newValue <= 3) {
      // Handle warnings
      setLastMessage("Warning: " + propertyName + " is at a critical level!");
      setChanged();
      notifyObservers();
    }
    if (newValue <= 1) {
      // Handle pet death
      setLastMessage("Your pet has died due to your negligence.");
      setChanged();
      notifyObservers();
      System.exit(0);
    }
  }

  /**
   * Interacts with the pet using a strategy.
   *
   * @param strategy The strategy to use to interact with the pet.
   */
  public void performAction(InteractionStrategy strategy) {
    if (!isSleeping) {
      strategy.interact(this);
    } else {
      setLastMessage("The pet is sleeping.\nYou cannot interact with it right now.");
      setChanged();
      notifyObservers();
    }
  }

  /**
   * Switches the pet's age status based on its age.
   */
  private void switchAgeStatus() {
    if ((age <= 5) && (age >= 1)) {
      petState = PetState.BABY;
    }
    if (age == 6 && petState == PetState.BABY) {
      petState = PetState.ADULT;
      setLastMessage("Grown to an adult.");
    } else if (age == 11 && petState == PetState.ADULT) {
      petState = PetState.SENIOR;
      setLastMessage("Grown to a senior.");
    }
  }

  /**
   * Switches the pet's life stage to Baby.
   */
  public void switchToBaby() {
    this.petState = PetState.BABY;
    this.age = 1;
    setLastMessage("Switched to Baby stage.");
    notifyObservers();
  }

  /**
   * Switches the pet's life stage to Adult.
   */
  public void switchToAdult() {
    this.petState = PetState.ADULT;
    this.age = 6;
    setLastMessage("Switched to Adult stage.");
    notifyObservers();
  }

  /**
   * Switches the pet's life stage to Senior.
   */
  public void switchToSenior() {
    this.petState = PetState.SENIOR;
    this.age = 11;
    setLastMessage("Switched to Senior stage.");
    notifyObservers();
  }

  /**
   * Increases a certain need of the pet.
   *
   * @param increaseAmount The amount to increase the need by.
   * @param propertyName   The name of the property to increase.
   * @throws IllegalArgumentException if the pet need is already full or dead.
   */
  public void increaseNeed(float increaseAmount, String propertyName) {
    float currentValue = getPropertyValue(propertyName);
    if (currentValue < 5) {
      float newValue = Math.min(5, currentValue + increaseAmount);
      setPropertyValue(propertyName, newValue);
      setLastMessage("Interacted: " + propertyName + " increased by " + increaseAmount);
      setChanged();
      notifyObservers();
    } else {
      setLastMessage("The pet is already satisfied in terms of " + propertyName);
      setChanged();
      notifyObservers();
    }
  }

  /**
   * Put the pet to sleep. Once falling asleep, the pet will sleep for sleepDuration.
   * The pet will not decrease sleepiness while sleeping.
   * After sleepDuration, the pet will wake up.
   */
  public void sleep() {
    isSleeping = true;
    sleepiness = 5; // Reset sleepiness value
    notifyObservers("The pet is now sleeping.");
    // Schedule a task to wake the pet up after sleepDuration, which is 50 seconds.
    Timer timer = new Timer(true);
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        wakeUp();
      }
    }, sleepDuration);
  }

  /**
   * Wakes the pet up.
   */
  private void wakeUp() {
    isSleeping = false;
    notifyObservers("The pet has woken up.");
  }

  /**
   * Cleans the pet.
   */
  public void displayCurrentNeeds() {
    System.out.printf("Current Needs: Hunger: %d, Happiness: %d, Hygiene: %d, Sleepiness: %d%n",
            (int) getHunger(), (int) getHappiness(), (int) getHygiene(), (int) getSleepiness());
    System.out.printf("Current Age: %d%n", getAge());
  }

  /**
   * Cleans the pet.
   *
   * @param o an observer to be added.
   */
  @Override
  public void addObserver(Observer o) {
    super.addObserver(o);
    setChanged();
    notifyObservers(); // Initial update when an observer is added
  }
}
