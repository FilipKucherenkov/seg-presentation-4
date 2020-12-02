import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.time.LocalDateTime;


public class Board{
    //setting variables for JFrames
	private JFrame mainFrame;

    //setting variables for JPanel
    private JPanel boardPane;
    private JPanel contentPane;

    //setting variables for the menu bar
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu kanbanMenu;
    private JMenuItem aboutItem;
    private JMenuItem activityItem;
    private JMenuItem exitItem;
    private JMenuItem makeBoardItem;
    private JMenuItem deleteBoardItem;
		private JMenuItem velocityItem;

    //setting variables for buttons
    private JButton makeColumnButton;
    private JButton makeCardButton;


    private boolean boardMade;
    private boolean columnMade;

     //initializing scroll pane
    private JScrollPane scrollPane;



    //constructor for the board class
	public Board() {
       startWindow();
       boardMade = false;
       columnMade = false;
    }

		// Method to obtain the time when Board is opened.
		public static LocalDateTime openingTime() {
			LocalDateTime opening = LocalDateTime.now();
			return opening;
		}


   public void startWindow(){
    	//making a new JFrame for the board and a contetn pane
    	mainFrame = new JFrame("Kanban Board");
        contentPane = (JPanel)mainFrame.getContentPane();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    	//settings for content pane
    	contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension( 740, 580 ));

        //creating menu bar
        menuBar = new JMenuBar();
        kanbanMenu = new JMenu("Kanban Board");
        fileMenu = new JMenu("File");
        aboutItem = new JMenuItem("About");
        activityItem = new JMenuItem("Activity");
        exitItem = new JMenuItem("Exit");
        makeBoardItem = new JMenuItem("New board");
        deleteBoardItem = new JMenuItem("Delete board");
				velocityItem = new JMenuItem("Overall Velocity");

				fileMenu.setName("file");
				makeBoardItem.setName("newBoard");




        //action listeners for the buttons and Activity log update
        makeBoardItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(boardMade == false){
                    makeBoard();
                    Activity.editText("You have created a new kanban board. <br/>");
                }else{
                    JOptionPane.showMessageDialog(null, "The board is already created", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteBoardItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(boardMade == true){
                    contentPane.remove(boardPane);
                    contentPane.repaint();
                    Activity.editText("You have deleted the kanban board. <br/>");
                    boardMade = false;
                }else{
                    JOptionPane.showMessageDialog(null, "No board has been created", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        activityItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Activity.isActicityVisible(true);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

				velocityItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card.showVelocity();
            }
        });



        //adding the files to the board
        kanbanMenu.add(aboutItem);
		kanbanMenu.add(velocityItem);
        kanbanMenu.add(activityItem);
        kanbanMenu.add(exitItem);
        fileMenu.add(makeBoardItem);
        fileMenu.add(deleteBoardItem);
        menuBar.add(kanbanMenu);
        menuBar.add(fileMenu);
        contentPane.add(menuBar, BorderLayout.NORTH);


    	//packing the JFrame
    	mainFrame.pack();
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(d.width/2 - mainFrame.getWidth()/2, d.height/2 - mainFrame.getHeight()/2);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }



    public void makeBoard(){
        //creating a new JPanel for the board
        boardPane = new JPanel();
        boardPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        boardPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        boardPane.setBackground(new Color(250, 234, 203));


        scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(new Color(250, 234, 203));


        //make column button
        makeColumnButton = new JButton("Make column");
				makeColumnButton.setName("makeColumn");
        makeColumnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boardPane.remove(makeColumnButton);
                makeColumn();
                boardPane.add(makeColumnButton);
                boardPane.repaint();
                mainFrame.pack();
            }
        });

        boardPane.add(makeColumnButton);
        scrollPane.getViewport().add(boardPane, BorderLayout.CENTER);


        //adding it to the contentpane
        contentPane.add(scrollPane, BorderLayout.CENTER);

        //packing the mainframe again, because the new panel has been added
        mainFrame.pack();
        boardMade = true;
    }

    public void makeColumn(){

        //creating a new object of the column
        Column columnPane = new Column();
        //getting the jpanel
        JPanel column = columnPane.getPanel();

        //adding it to the boardpane
        boardPane.add(column);

        //packing the mainframe again, because the new panel has been added
        mainFrame.pack();
        columnMade = true;
    }

    public String getFrameName(){
        return mainFrame.getTitle();
    }

    public boolean boardCreated(){
        return boardMade;
    }

    public boolean columnCreated(){
        return columnMade;
    }


}
