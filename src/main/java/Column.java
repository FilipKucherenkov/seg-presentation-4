import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Column {

	private JPanel columnPane;
    private JPanel mainColumn;
    private JPanel bottomColumn;


	private JButton deleteColumnButton;


	private JButton editColumnButton;
	private JButton makeCardButton;
	private JTextArea columnName;
  //	private JTextArea newColumnName;
	private String newName;
	private String name;




	public Column(){

		makeColumn();

	}


	public void makeColumn(){
	 //making input dialog
        name = JOptionPane.showInputDialog("Enter the name of the column");

        //creating a new JPanel for the card
        columnPane = new JPanel();
        mainColumn = new JPanel();
        bottomColumn = new JPanel();

        mainColumn.setLayout(new BoxLayout(mainColumn, BoxLayout.Y_AXIS));

        bottomColumn.setLayout(new BorderLayout());

        columnPane.setBorder(new EtchedBorder(Color.PINK, Color.RED));
        columnPane.setBackground(new Color(238, 238, 238));
        columnPane.setLayout(new BorderLayout());
        columnPane.setPreferredSize(new Dimension( 180, 520 ));

        //creating jtextarea for the name of the column
        columnName = new JTextArea(1, 1);
        columnName.setEditable(false);
        columnName.setText(name);
				columnName.setName("columnName");
        Activity.editText("You have created a new column named '" + columnName.getText() + "'. <br/>");

        //System.out.println(columnPane.getName());

        //delete button action
        deleteColumnButton = new JButton("Delete column");
        deleteColumnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              deletePanel();
              //Display the modifications in the activity log page
              Activity.editText("You have deleted the column '" + columnName.getText() + "'. <br/>");
            }
        });

				//edit name button
				editColumnButton = new JButton("Edit Column Name");
				editColumnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              editColumnName();
            }
        });


        makeCardButton = new JButton("Create Card");
        makeCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //columnPane.remove(makeCardButton);
                makeCard();

            }
        });



        //adding objects to the column
        bottomColumn.add(makeCardButton,BorderLayout.NORTH);
        bottomColumn.add(editColumnButton,BorderLayout.CENTER);
        bottomColumn.add(deleteColumnButton,BorderLayout.SOUTH);
        columnPane.add(bottomColumn, BorderLayout.SOUTH);
        columnPane.add(mainColumn, BorderLayout.CENTER);
        columnPane.add(columnName, BorderLayout.NORTH);
				//columnPane.add(makeCardButton, BorderLayout.SOUTH);
        columnPane.repaint();
    }


    public void makeCard(){

      //creating a new object of the column
      Card cardPane = new Card();
      //getting the jpanel
      JPanel card = cardPane.getCard();
      //adding it to the boardpane
      mainColumn.add(card);

      columnName.setText(columnName.getText());

    }

		public void editColumnName() {

      newName = JOptionPane.showInputDialog("Edit Column Name");
      String oldColName = columnName.getText();
      columnName.setText(newName);
      //Display the name modifications in the activity log page
      Activity.editText("You have change the name of the column ''" + oldColName + "'' by ''" + columnName.getText() + "'. <br/>");
      columnPane.repaint();
		}

    public String getCurColName(){
      return columnName.getText();
    }

    public JPanel getPanel(){
    	return columnPane;
    }

    public void deletePanel(){
      columnPane.setVisible(false);
    }

		public boolean checkVisibility() {
			return columnPane.isVisible();
		}


		// public void makeCard(){
		//
    //     //creating a new object of the column
    //     Card cardPane = new Card();
    //     //getting the jpanel
    //     JTextArea card = cardPane.getCard();
		//
    //     //adding it to the boardpane
    //     columnPane.add(card);
		//
    //     //packing the mainframe again, because the new panel has been added
    //     //mainFrame.pack();
		//
    // }




}
