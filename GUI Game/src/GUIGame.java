/* Name: Shelley Chen
 * Course Code: ICS4U0-A
 * Program Name: GUI Guessing Numbers Game
 * Date: February 15, 2022
 * Program Description: This game generates a random number from 1 to 6 and allows the user up to 3 tries to guess the correctly guess the number.
 * Each number can only be selected once in a game. If the user loses the game or starts a new game before their three tries are up, the program displays 
 * the correct number. The user has the ability to quit the game or start a new game at any time. The program displays the number of games won and lost 
 * during the session.
 * */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GUIGame
{
	static double rand; // declare static variables
	static int iRand;
	static JLabel titleLabel;
	static JLabel triesLeftLabel;
	static JLabel gamesWonLabel;
	static JLabel gamesLostLabel;
	static JButton button1; // buttons for numbers 1-6
	static JButton button2;
	static JButton button3;
	static JButton button4;
	static JButton button5;
	static JButton button6;
	static JTextArea textArea; 
	static JButton quitButton; 
	static JButton playAgainButton;
	static int triesLeft;
	static int gamesWon = 0; // initialize games won and lost to 0
	static int gamesLost = 0;
	static int guess;

	public static void main(String[] args) {
		
		JPanel panel;
		
		titleLabel = new JLabel ("Guess my number from 1-6:\n", JLabel.RIGHT); // instantiate a label with the text set to right justified on the label
		titleLabel.setFont (new Font ("Comic Sans", Font.BOLD, 14)); // set font
		titleLabel.setForeground (Color.blue); // set colour

		triesLeftLabel = new JLabel ("Tries left: 3", JLabel.RIGHT); // instantiate a label displaying number of tries left
		triesLeftLabel.setFont (new Font ("Comic Sans", Font.BOLD, 14)); // set font
		triesLeftLabel.setForeground (Color.blue); // set colour

		gamesWonLabel = new JLabel ("Games won: 0", JLabel.RIGHT); // instantiate a label displaying number of games won
		gamesWonLabel.setFont (new Font ("Comic Sans", Font.BOLD, 14)); // set font
		gamesWonLabel.setForeground (Color.blue); // set colour

		gamesLostLabel = new JLabel ("Games lost: 0", JLabel.RIGHT); // instantiate a label displaying number of games lost
		gamesLostLabel.setFont (new Font ("Comic Sans", Font.BOLD, 14)); // set font
		gamesLostLabel.setForeground (Color.blue); // set colour

		button1 = new JButton ("1"); //instantiate buttons with the label text as numbers 1-6
		button2 = new JButton ("2");
		button3 = new JButton ("3");
		button4 = new JButton ("4");
		button5 = new JButton ("5");
		button6 = new JButton ("6");
		quitButton = new JButton ("Quit Game"); // instantiate quit and play again buttons
		playAgainButton = new JButton ("Play Again");

		textArea = new JTextArea (10,20); // instantiate text area
		JScrollPane scrollPane = new JScrollPane (textArea); // add the text area to a scroll component, so if more data than fit into set size a scroll bar is presented
		textArea.setEditable (false); // make text area as read only

		panel = new JPanel (); // instantiate the panel
		panel.add (titleLabel); // add components to the panel in the order of appearance
		panel.add (triesLeftLabel);
		panel.add (gamesWonLabel);
		panel.add (gamesLostLabel);
		panel.add (scrollPane);
		panel.add (button1);
		panel.add (button2);
		panel.add (button3);
		panel.add (button4);
		panel.add (button5);
		panel.add (button6);
		panel.add (quitButton);
		panel.add (playAgainButton);

		JFrame frame = new JFrame ("Random Number Guessing Game"); // instantiate the frame with a title
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
		frame.setSize (306, 400);

		frame.getContentPane ().add (panel); //add the panel with components to the frame

		startGame(); // call method to start game

		quitButton.addActionListener (new ActionListener ()  //adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				System.exit(0); // if user presses the quit button, exit the program
			}
		}
				);

		playAgainButton.addActionListener (new ActionListener () // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				if (triesLeft > 0) // if user wants to quit before their last try
				{
					textArea.append ("The number was " + iRand + "\n"); // display correct number
				}
				startGame(); // if user presses play again button, call start game method
			}
		}
				);

		button1.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (1); // call method, passing in 1 as the argument
				button1.setEnabled(false); // grey-out 1 button
			}
		}
				);

		button2.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (2); // call method, passing in 2 as the argument
				button2.setEnabled(false); // grey-out 2 button
			}
		}
				);

		button3.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (3); // call method, passing in 2 as the argument
				button3.setEnabled(false); // grey-out 3 button
			}
		}
				);

		button4.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (4); // call method, passing in 4 as the argument
				button4.setEnabled(false); // grey-out 4 button
			}
		}
				);

		button5.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (5); // call method, passing in 5 as the argument
				button5.setEnabled(false); // grey-out 5 button
			}
		}
				);

		button6.addActionListener (new ActionListener ()  // adding action listener to the button component and creating a specific action performed method (override) for the button
		{
			public void actionPerformed (ActionEvent e)
			{
				userGuess (6); // call method, passing in 6 as the argument
				button6.setEnabled(false); // grey-out 6 button
			}
		}
				);

		frame.setVisible (true); // make frame visible on screen
	}

	static void startGame () // method called whenever user starts a new game
	{
		textArea.append("\nNEW GAME\n");
		triesLeft = 3; // initiate tries left to 3 when games starts
		triesCounter(triesLeft); // call method to display number of tries left
		greyOut (0); // un-grey all number buttons
		iRand = 0;
		while (iRand < 1 || iRand > 6) // while random number is out of the bounds of 1-6
		{
			rand = Math.random(); // generate random number
			iRand = (int)(rand * 7); // obtain number from 1-6, cast to integer
		}
	}

	static void userGuess(int guess) // run this code when user selects a number
	{
		if (triesLeft > 0) // if user still has tries left
		{
			textArea.append (String.valueOf(guess) + ": "); // display user's guess in text area
			if (guess == iRand) // if user guesses correctly
			{
				textArea.append ("Correct!\n"); 
				greyOut (1); // call method to grey out all number buttons
				gamesWon++; // increase number of games won by 1
				updateScore (0, gamesWon); // call method to update number of games won by 1
			}
			else if (guess != iRand)// if user doesn't guess correctly
			{
				textArea.append ("Wrong\n");
				if (triesLeft == 1) // if user is on their last try
				{
					textArea.append ("The number was " + iRand + "\n"); // display correct number
					greyOut (1); // call method to grey out all buttons
					gamesLost++; // increase number of games lost by 1
					updateScore (1, gamesLost); // call method to update number of games lost by 1 
				}
			}
			triesLeft--; // decrement number of tries by one
			if (guess == iRand) // if user guesses correctly, set tries left to zero right away 
			{
				triesLeft = 0;
			}
			triesCounter(triesLeft); // call method to update number of tries displayed on screen
		}
	}

	static void greyOut (int grey) // method to grey out/ un-grey all the number buttons
	{
		if (grey == 0) // if argument passed in is 0, un-grey all number buttons
		{
			button1.setEnabled(true);
			button2.setEnabled(true);
			button3.setEnabled(true);
			button4.setEnabled(true);
			button5.setEnabled(true);
			button6.setEnabled(true);
		}
		else if (grey == 1) // if argument passed in is 1, grey out all number buttons
		{
			button1.setEnabled(false);
			button2.setEnabled(false);
			button3.setEnabled(false);
			button4.setEnabled(false);
			button5.setEnabled(false);
			button6.setEnabled(false);
		}
	}

	static void triesCounter (int numOfTriesLeft) // method to update tries left counter on screen
	{
		triesLeftLabel.setText("Tries Left: " + String.valueOf(numOfTriesLeft)); // update label to show tries left after each turn
	}

	static void updateScore (int result, int score) // method to update number of games won and lost
	{
		if (result == 0) // if first argument passed in is 0
		{
			gamesWonLabel.setText("Games Won: " + score); // update label to show games won 
		}
		else if (result == 1) // if first argument passed in is 1 
		{
			gamesLostLabel.setText("Games Lost: " + score); // update label to show games lost
		}
	}
}