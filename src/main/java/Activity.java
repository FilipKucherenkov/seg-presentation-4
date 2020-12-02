import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity{
    //setting variables for JFrames
    private static JFrame activityFrame;

    //setting variables for JPanel
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private static JLabel acText;


    public Activity(){

        startActivityWindow();

    }


    public void startActivityWindow(){
    	//making a new JFrame for the board, a content pane, a scroll pane and a Jlabel
    	activityFrame = new JFrame("Activity");
        contentPane = (JPanel)activityFrame.getContentPane();
        scrollPane = new JScrollPane();
        acText = new JLabel();

        //settings for content pane and scrollPane
    	contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension( 500, 400 ));
        contentPane.setBackground(new Color(250, 234, 203));

        scrollPane.setPreferredSize(new Dimension( 500, 300 ));
        scrollPane.getViewport().setBackground(new Color(250, 234, 203));

        //adding JLabel to the page and setting the base text
        acText.setText("<html> Your Activity on the app : <br/><br/>");
        scrollPane.getViewport().add(acText, BorderLayout.PAGE_START);
        contentPane.add(scrollPane);

        //packing the JFrame
        activityFrame.pack();

        //place the frame at the center of the screen and make it visible
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        activityFrame.setLocation(d.width/2 - activityFrame.getWidth()/2, d.height/2 - activityFrame.getHeight()/2);

        activityFrame.setResizable(true);
    }


    //Method to allow the activity window to be visible
    public static void isActicityVisible(boolean b){
        activityFrame.setVisible(b);
    }

    //static method to be accesed in every class in order to modify the activity Jlabel string
    public static void editText(String s){

        // indicates the time and date of the modification made in the app
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        acText.setText(acText.getText() + formatter.format(date) + " : " + s );
    }

}
