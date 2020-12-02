import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.time.LocalDateTime;

public class Card {

// names for input dialogs
private String cardName;
private String cardID;
private String cardDescription;
private String cardStoryPoints;

private JFrame descriptionFrame;

// card panels
private JPanel cardPane;
private JPanel bottomCard;
private JPanel mainCard;

// text areas responsible for showing text on the card.
private JTextField cardTitle;
private JTextField cardIDField;
private JTextField cardDescriptionField;
private JTextArea cardText;
private JScrollPane scroll;

private JButton showDescriptionButton;
private JButton saveButton;
private JButton deleteCardButton;

private static int velocity = 0;
private int count = 0;


  public Card() {
    makeCard();
    velocity ++;
  }

  public void makeCard(){

    // creating input dialogs for creating a card
    cardName = JOptionPane.showInputDialog("Enter Card Title");
    cardID = JOptionPane.showInputDialog("Enter Card ID");
    cardDescription = JOptionPane.showInputDialog("Enter Card Description");
    cardStoryPoints = JOptionPane.showInputDialog("Enter Card Story Points");

    // main card panel to display a card on the board
    cardPane = new JPanel();
    cardPane.setBorder(new EtchedBorder(Color.BLACK, Color.BLACK));
    cardPane.setBackground(new Color(238, 238, 238));
    cardPane.setLayout(new BorderLayout());
    cardPane.setPreferredSize(new Dimension( 180, 200 ));


    // auxillary panel on the card to house objects at the bottom of the card
    bottomCard = new JPanel();
    bottomCard.setLayout(new BorderLayout());

    // main portion of the card sitting atop the bottomCard
    mainCard = new JPanel();
    mainCard.setLayout(new BoxLayout(mainCard, BoxLayout.Y_AXIS));


    // area of the card that displas the text of the card (editable)
    cardText = new JTextArea();
    cardText.setBackground(new Color(238, 238, 238));
    cardText.setLayout(new BorderLayout());
    cardText.setPreferredSize(new Dimension( 180, 180 ));
    cardText.setEditable(true);
    scroll = new JScrollPane(cardText);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    // title of the card passed from the input dialog
    cardTitle = new JTextField();
    cardTitle.setEditable(false);
    cardTitle.setText("Name: " + cardName);
    Activity.editText("You have created a new cad named '" + cardName + "'. <br/>");

    cardDescriptionField = new JTextField();
    cardDescriptionField.setEditable(false);
    cardDescriptionField.setText("Description: " + cardDescription);

    // card's id displayed at the bottom of the card
    cardIDField = new JTextField();
    cardIDField.setEditable(false);
    cardIDField.setText("id: " + cardID);

    // button on the card to display the description and story points.
    showDescriptionButton = new JButton("Edit");
    showDescriptionButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (count > 0){
            isEditVisible(true);
          }
          else{
            showDesc();
          }

        }
    });

    // button to delete the card.
    deleteCardButton  = new JButton("Delete card");
        deleteCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              deleteCardPanel();
              //Display the modifications in the activity log page
              Activity.editText("You have deleted the card '" + cardName + "'. <br/>");
            }
        });


    // adding all elements and objects to the card panel
    bottomCard.add(cardIDField, BorderLayout.NORTH);
    bottomCard.add(showDescriptionButton, BorderLayout.CENTER);
    bottomCard.add(deleteCardButton, BorderLayout.SOUTH);
    mainCard.add(cardDescriptionField, BorderLayout.CENTER);
    cardPane.add(cardTitle, BorderLayout.NORTH);
    cardPane.add(bottomCard, BorderLayout.SOUTH);
    cardPane.add(mainCard, BorderLayout.CENTER);


    // redisplays the card with the added items
    cardPane.repaint();
  }

  // called when "show description" button is pressed - displays card's description and
  // story points and allow user to modify them.

  public void showDesc() {

    // Outer frame displays card description and story points
    descriptionFrame = new JFrame(cardName);
    descriptionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    descriptionFrame.setSize(300, 300);
    descriptionFrame.setBackground(new Color(238, 238, 238));
    descriptionFrame.setResizable(true);

    // Broder used for bordering each component of frame
    Border border = BorderFactory.createLineBorder(Color.BLUE, 1);

    // Inner frame at top part of outer frame to house card description
    JPanel descriptionPane = new JPanel();
    descriptionPane.setPreferredSize(new Dimension( 140, 140 ));
    descriptionPane.setBorder(border);

    // Create save button
    JButton saveButton = new JButton("Save");

    // Label that goes atop description pane
    JLabel descriptionLabel = new JLabel("Card Description: ");
    descriptionLabel.setBorder(border);

    // Text of the description pane that displays the input description
    JTextArea descriptionText = new JTextArea(cardDescription);
    descriptionText.setPreferredSize(new Dimension( 280, 110 ));
    descriptionText.setEditable(true);
    descriptionText.setBackground(new Color(238, 238, 238));

    // Action listener for the save button : get the input text then on button click
    // save it a temp string and set the new text in the card description.
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String temp = new String(descriptionText.getText());
        cardDescriptionField.setText("Description: " + temp);
        descriptionText.setText(temp);
        descriptionFrame.dispose();
      }
  });

    // Label for lower part of frame where story points go
    JLabel storyPointsLabel = new JLabel("Story Points: ");
    storyPointsLabel.setBorder(border);

    // Secondary pane for story points to be displayed
    JPanel storyPane = new JPanel();
    storyPane.setPreferredSize(new Dimension( 140, 140 ));
    storyPane.setBorder(border);

    // Text area for card story points (text) to be displayed
    JTextArea storyText = new JTextArea(cardStoryPoints);
    storyText.setPreferredSize(new Dimension( 280, 110 ));
    storyText.setEditable(true);
    storyText.setBackground(new Color(238, 238, 238));

    // adding label and text to secondary description pane
    descriptionPane.add(descriptionLabel, BorderLayout.NORTH);
    descriptionPane.add(descriptionText, BorderLayout.SOUTH);

    // adding label and text to secondary story points pane
    storyPane.add(storyPointsLabel, BorderLayout.NORTH);
    storyPane.add(storyText, BorderLayout.SOUTH);

    // adding everything to the main outer frame.
    descriptionFrame.getContentPane().add(descriptionPane, BorderLayout.NORTH);
    descriptionFrame.getContentPane().add(storyPane, BorderLayout.CENTER);
    descriptionFrame.getContentPane().add(saveButton, BorderLayout.SOUTH);
    descriptionFrame.setLocationRelativeTo(null);
    descriptionFrame.setVisible(true);

    count++;
  }

  //Method to obtain number of cards created
  public int getVelocity() {
    return velocity;
  }

  // Obtain Average Lead Time
  public static double getALT() {
    double alt = velocity / 1;
    return alt;
  }

// Method to show overall velocity expressed in Story points created,
// Average Lead time and average work in progress.
  public static void showVelocity() {
      JOptionPane.showMessageDialog(null, "Overall Velocity Is: " + velocity + " Story Point(s) Created"
      + "\n" + "Average Lead Time: " + getALT() + " Days"
      + "\n" + "Average Work in Progress: " + velocity + " Task(s)");
  }


  // Method to display whole card.
  public JPanel getCard() {
    return cardPane;
  }

   // Method to allow the edit window to be visible/
  public void isEditVisible(boolean b){
    descriptionFrame.setVisible(b);
  }

  // Method to make the Card not visible.
  public void deleteCardPanel(){
    cardPane.setVisible(false);
  }


  public String getTitle(){
    return cardName;
  }

  public String getDescription(){
    return cardDescription;
  }




}
