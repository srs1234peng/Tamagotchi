/**
 * An enum representing the state of a pet.
 * A pet can be a baby, adult, or senior.
 */
public enum PetState {
  // baby pet need to be fed, played with and put to sleep more often
  BABY(0.33f, 0.2f,
          0.1f, 0.33f),
  ADULT(0.2f, 0.2f,
          0.2f, 0.2f),
  // senior pet need to be fed, played with and put to sleep less often
  // need to be cleaned more often
  SENIOR(0.1f, 0.1f,
          0.33f, 0.1f);

  private final float hungerDecreaseRate;
  private final float happinessDecreaseRate;
  private final float cleanlinessDecreaseRate;
  private final float sleepinessDecreaseRate;

  /**
   * Constructor for the PetState enum.
   *
   * @param hungerDecreaseRate      The rate at which the pet's hunger decreases.
   * @param happinessDecreaseRate   The rate at which the pet's happiness decreases.
   * @param cleanlinessDecreaseRate The rate at which the pet's cleanliness decreases.
   * @param sleepinessDecreaseRate  The rate at which the pet's sleepiness decreases.
   */
  PetState(float hungerDecreaseRate, float happinessDecreaseRate,
           float cleanlinessDecreaseRate, float sleepinessDecreaseRate) {
    this.hungerDecreaseRate = hungerDecreaseRate;
    this.happinessDecreaseRate = happinessDecreaseRate;
    this.cleanlinessDecreaseRate = cleanlinessDecreaseRate;
    this.sleepinessDecreaseRate = sleepinessDecreaseRate;
  }

  /**
   * Getter for the hunger decrease rate.
   *
   * @return The hunger decrease rate.
   */
  public float getHungerDecreaseRate() {
    return hungerDecreaseRate;
  }

  /**
   * Getter for the happiness decrease rate.
   *
   * @return The happiness decrease rate.
   */
  public float getHappinessDecreaseRate() {
    return happinessDecreaseRate;
  }

  /**
   * Getter for the cleanliness decrease rate.
   *
   * @return The cleanliness decrease rate.
   */
  public float getHygieneDecreaseRate() {
    return cleanlinessDecreaseRate;
  }

  /**
   * Getter for the sleepiness decrease rate.
   *
   * @return The sleepiness decrease rate.
   */
  public float getSleepinessDecreaseRate() {
    return sleepinessDecreaseRate;
  }
}

