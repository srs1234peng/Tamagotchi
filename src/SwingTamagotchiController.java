import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The controller for the Tamagotchi GUI.
 */
public class SwingTamagotchiController {
  private TamagotchiModel tamagotchiModel;
  private SwingTamagotchiView view;

  /**
   * Constructor for the TamagotchiGUIController class.
   *
   * @param tamagotchiModel The model for the Tamagotchi
   */
  public SwingTamagotchiController(TamagotchiModel tamagotchiModel) {
    this.tamagotchiModel = tamagotchiModel;

    // Create an instance of the view and pass a reference to this controller
    this.view = new SwingTamagotchiView(tamagotchiModel, this);

    // Add action listeners to buttons in the view
    view.getFeedButton().addActionListener(new FeedButtonListener());
    view.getSleepButton().addActionListener(new SleepButtonListener());
    view.getPlayButton().addActionListener(new PlayButtonListener());
    view.getCleanButton().addActionListener(new CleanButtonListener());
    view.getSwitchToAdultButton().addActionListener(new SwitchToAdultButtonListener());
    view.getSwitchToSeniorButton().addActionListener(new SwitchToSeniorButtonListener());
    view.getSwitchToBabyButton().addActionListener(new SwitchToBabyButtonListener());
  }

  /**
   * This method is called when a button is clicked in the view.
   *
   * @param buttonName The name of the button clicked
   */
  public void buttonClicked(String buttonName) {
    // Call the corresponding method in the model based on the button clicked
    switch (buttonName) {
      case "Feed":
        tamagotchiModel.performAction(new FeedStrategy());
        break;
      case "Sleep":
        tamagotchiModel.performAction(new SleepStrategy());
        break;
      case "Play":
        tamagotchiModel.performAction(new PlayStrategy());
        break;
      case "Clean":
        tamagotchiModel.performAction(new CleanStrategy());
        break;
      case "SwitchToAdult":
        tamagotchiModel.switchToAdult();
        break;
      case "SwitchToSenior":
        tamagotchiModel.switchToSenior();
        break;
      case "SwitchToBaby":
        tamagotchiModel.switchToBaby();
        break;
      // Add more cases for other buttons if needed
      default:
        System.out.println("Unknown button clicked: " + buttonName);
    }
  }

  private class FeedButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("Feed");
    }
  }

  private class SleepButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("Sleep");
    }
  }

  private class PlayButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("Play");
    }
  }

  private class CleanButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("Clean");
    }
  }

  private class SwitchToAdultButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("SwitchToAdult");
    }
  }

  private class SwitchToSeniorButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("SwitchToSenior");
    }
  }

  private class SwitchToBabyButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      buttonClicked("SwitchToBaby");
    }
  }

  public static void main(String[] args) {
    TamagotchiModel tamagotchiModel = new TamagotchiModel();
    new SwingTamagotchiController(tamagotchiModel);
  }
}
