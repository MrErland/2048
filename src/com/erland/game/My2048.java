package com.erland.game;

import java.awt.*;
import javax.swing.*;

public class My2048 extends JFrame 
{ 
  /**
	 * @author Erland 
	 */
	
	private static final long serialVersionUID = -1116750574086699660L;

	public My2048()
	{
		setTitle("2048");
		setSize(450, 500);
		setLocation(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(4, 4, 5, 5));
		new Operation(this);
		this.setVisible(true);
	}
 
	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | InstantiationException| IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		new My2048();
	}
}
