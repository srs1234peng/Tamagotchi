import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Observer;


public class TamagotchiViewGUI extends JFrame implements Observer {
  private TamagotchiModel tamagotchiModel;
  private TamagotchiGUIController controller;  // Add a reference to the controller

  private JButton feedButton;
  private JButton sleepButton;
  private JButton playButton;
  private JButton cleanButton;
  private JButton switchToAdultButton;
  private JButton switchToSeniorButton;
  private JButton switchToBabyButton;
  private JLabel statusLabel;
  private JLabel gifLabel;

  /**
   * Constructor for the TamagotchiViewGUI class.
   *
   * @param tamagotchiModel The model for the Tamagotchi.
   * @param controller      The controller for the Tamagotchi.
   */
  public TamagotchiViewGUI(TamagotchiModel tamagotchiModel, TamagotchiGUIController controller) {
    this.tamagotchiModel = tamagotchiModel;
    this.controller = controller;  // Set the controller reference

    setTitle("Tamagotchi");
    setSize(2000, 1600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    initializeComponents();

    // Set layout to GridBagLayout
    setLayout(new GridBagLayout());

    // Create GridBagConstraints for the status label
    GridBagConstraints statusLabelConstraints = new GridBagConstraints();
    statusLabelConstraints.gridx = 0;
    statusLabelConstraints.gridy = 0;
    statusLabelConstraints.anchor = GridBagConstraints.PAGE_START;
    add(statusLabel, statusLabelConstraints);

    // Create GridBagConstraints for the gifLabel
    GridBagConstraints gifLabelConstraints = new GridBagConstraints();
    gifLabelConstraints.gridx = 0;
    gifLabelConstraints.gridy = 1;
    gifLabelConstraints.weighty = 1.0;
    gifLabelConstraints.anchor = GridBagConstraints.CENTER;
    gifLabel = new JLabel();
    gifLabel.setPreferredSize(new Dimension(500, 500));  // Adjust the size as needed
    updateGif("empty.gif");
    add(gifLabel, gifLabelConstraints);

    // Create GridBagConstraints for the button panels
    GridBagConstraints buttonsPanelConstraints = new GridBagConstraints();
    buttonsPanelConstraints.gridx = 0;
    buttonsPanelConstraints.gridy = 2;
    buttonsPanelConstraints.anchor = GridBagConstraints.PAGE_END;

    // Create a panel for the first row (buttons feed, sleep, play, clean)
    JPanel firstRowPanel = new JPanel(new FlowLayout());
    firstRowPanel.add(feedButton);
    firstRowPanel.add(sleepButton);
    firstRowPanel.add(playButton);
    firstRowPanel.add(cleanButton);

    // Create a panel for the second row (buttons switchToAdultButton, switchToSeniorButton, switchToBabyButton)
    JPanel secondRowPanel = new JPanel(new FlowLayout());
    secondRowPanel.add(switchToAdultButton);
    secondRowPanel.add(switchToSeniorButton);
    secondRowPanel.add(switchToBabyButton);

    // Add the panels to a container panel
    JPanel buttonsContainerPanel = new JPanel(new GridLayout(2, 1));
    buttonsContainerPanel.add(firstRowPanel);
    buttonsContainerPanel.add(secondRowPanel);

    add(buttonsContainerPanel, buttonsPanelConstraints);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);

    tamagotchiModel.addObserver(this);

    addActionListeners();
  }

  /**
   * Getter for the feed button.
   *
   * @return The feed button.
   */
  public JButton getFeedButton() {
    return feedButton;
  }

  /**
   * Getter for the sleep button.
   *
   * @return The sleep button.
   */
  public JButton getSleepButton() {
    return sleepButton;
  }

  /**
   * Getter for the play button.
   *
   * @return The play button.
   */
  public JButton getPlayButton() {
    return playButton;
  }

  /**
   * Getter for the clean button.
   *
   * @return The clean button.
   */
  public JButton getCleanButton() {
    return cleanButton;
  }

  /**
   * Getter for the switchToAdult button.
   *
   * @return The switchToAdult button.
   */
  public JButton getSwitchToAdultButton() {
    return switchToAdultButton;
  }

  /**
   * Getter for the switchToSenior button.
   *
   * @return The switchToSenior button.
   */
  public JButton getSwitchToSeniorButton() {
    return switchToSeniorButton;
  }

  /**
   * Getter for the switchToBaby button.
   *
   * @return The switchToBaby button.
   */
  public JButton getSwitchToBabyButton() {
    return switchToBabyButton;
  }

  /**
   * Initializes the components of the GUI.
   */
  private void initializeComponents() {
    statusLabel = new JLabel(getStatusLabelText());
    feedButton = new JButton("Feed");
    sleepButton = new JButton("Sleep");
    playButton = new JButton("Play");
    cleanButton = new JButton("Clean");
    switchToAdultButton = new JButton("Switch to Adult");
    switchToSeniorButton = new JButton("Switch to Senior");
    switchToBabyButton = new JButton("Switch to Baby");
  }

  /**
   * Adds an action listener to a button if it doesn't already have one.
   *
   * @param button         the button to add the action listener to.
   * @param actionListener the action listener to add.
   */
  private void addActionListenerIfAbsent(AbstractButton button, ActionListener actionListener) {
    if (button.getActionListeners().length == 0) {
      button.addActionListener(actionListener);
    }
  }

  /**
   * Adds action listeners to the buttons in the GUI.
   * updateGif() is called when a button is clicked.
   */
  private void addActionListeners() {
    addActionListenerIfAbsent(feedButton, e -> {
      controller.buttonClicked("Feed");
      updateGif("eating.gif");
    });
    // Update the GIF based on the button clicked
    addActionListenerIfAbsent(sleepButton, e -> {
      controller.buttonClicked("Sleep");
      updateGif("sleep.gif");
    });
    addActionListenerIfAbsent(playButton, e -> {
      controller.buttonClicked("Play");
      updateGif("playing.gif");
    });
    addActionListenerIfAbsent(cleanButton, e -> {
      controller.buttonClicked("Clean");
      updateGif("bath.gif");
    });
    addActionListenerIfAbsent(switchToAdultButton, e -> controller.buttonClicked("SwitchToAdult"));
    addActionListenerIfAbsent(switchToSeniorButton, e -> controller.buttonClicked("SwitchToSenior"));
    addActionListenerIfAbsent(switchToBabyButton, e -> controller.buttonClicked("SwitchToBaby"));
  }

  /**
   * Returns the text to be displayed in the status label.
   *
   * @return the text to be displayed in the status label.
   */
  private String getStatusLabelText() {
    return String.format("Needs: Hunger: %d, Happiness: %d, Hygiene: %d, Sleepiness: %d, Age: %d",
            (int) tamagotchiModel.getHunger(), (int) tamagotchiModel.getHappiness(),
            (int) tamagotchiModel.getHygiene(), (int) tamagotchiModel.getSleepiness(),
            tamagotchiModel.getAge());
  }

  /**
   * Updates the status label.
   *
   * @param o   the observable object.
   * @param arg an argument passed to the {@code notifyObservers} method.
   */
  @Override
  public void update(Observable o, Object arg) {
    statusLabel.setText(getStatusLabelText());
    String message = tamagotchiModel.getLastMessage();
    if (message != null && !message.isEmpty()) {
      JOptionPane.showMessageDialog(this, message);
    }
  }

  /**
   * Updates the gif displayed in the GUI.
   *
   * @param fileName the name of the gif file to be displayed.
   */
  public void updateGif(String fileName) {
    ImageIcon newIcon = new ImageIcon(getClass().getResource(fileName));
    Image img = newIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
    gifLabel.setIcon(new ImageIcon(img));
  }

  /**
   * Updates the gif displayed in the GUI to the default gif.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TamagotchiModel tamagotchiModel = new TamagotchiModel();
      TamagotchiGUIController controller = new TamagotchiGUIController(tamagotchiModel);
      new TamagotchiViewGUI(tamagotchiModel, controller);
    });
  }
}
