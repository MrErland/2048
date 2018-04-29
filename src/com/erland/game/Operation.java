package com.erland.game;

import java.awt.event.*;
import javax.swing.*;

public class Operation implements KeyListener
{
	private Block[] block;
	private JPanel panel;
	private boolean up,down,left,right;
	private int moveFlag;
	private boolean numFlag;
	
//	private JButton restartButton;
//	private JButton lastStepButton;
//	private JTextArea stepsText;
	
	public Operation(JFrame frame) 
	{
		this.panel = (JPanel)frame.getContentPane();
		block = new Block[16];
		numFlag = true;
		moveFlag = 0;
		up=true;down=true;left=true;right=true;
		addBlock();
		for (int i = 0; i < 2; i++)
			appearBlock();
		frame.addKeyListener(this);
	}
 
	private void addBlock() 
	{
		for (int i = 0; i < 16; i++) 
		{
			block[i] = new Block();
			block[i].setHorizontalAlignment(JLabel.CENTER);
			block[i].setOpaque(true);
			panel.add(block[i]);  
		}
	} 
	
	public void appearBlock() 
	{
		while (numFlag)
		{
			int index = (int) (Math.random() * 16);
			if (block[index].getValue() == 0)
			{
				if (Math.random() < 0.5)
				{
					block[index].setValue(2);
				}
				else
				{
					block[index].setValue(4);
				}
				break;
	      	}
	    }
	}
 
	public void judgeAppear()
	{
		int sum = 0;
		for (int i = 0; i < 16; i++) 
		{
			if (block[i].getValue() != 0)
			{
				sum++;
			}
		}
		if (sum == 16)
			numFlag = false;
	}
 
	public int Find(int i,int j,int a,int b)
	{
		while(i<b && i>=a)
		{
			if(block[i].getValue()!=0)
			{
				return i;
			}
			i=i+j;
		}
		return -1;
	}
	
	public void upBlock()
	{
		int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
		moveFlag++;
		for(i=0;i<4;i++)
		{
			index=i;
			for(j=i+4;j<16;j+=4)
			{  
				valueJ=0; valueI=0;
				if(block[index].getValue()==0)
				{
					t=Find(index,4,0,16);
					if(t!=-1)
					{
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueI=block[index].getValue();	
				if(block[j].getValue()==0)
				{
					t=Find(j,4,0,16);
					if(t!=-1)
					{
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueJ=block[j].getValue();
				if(valueI==valueJ && valueI!=0 && valueJ!=0)
				{
					block[index].setValue(valueI+valueJ);
					block[j].setValue(0);
					numFlag = true;
				}
				index=j;
			}      
		}
	}
	
	public void downBlock()
	{
		int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
		moveFlag++;
		for(i=12;i<16;i++)
		{
			index=i;
			for(j=i-4;j>=0;j-=4)
			{  
				valueJ=0; valueI=0;
				if(block[index].getValue()==0)
				{
					t=Find(index,-4,0,16);
					if(t!=-1)
					{
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueI=block[index].getValue();
				if(block[j].getValue()==0)
				{
					t=Find(j,-4,0,16);
					if(t!=-1)
					{
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueJ=block[j].getValue();
				if(valueI==valueJ && valueI!=0 && valueJ!=0)
				{
					block[index].setValue(valueI+valueJ);
					block[j].setValue(0);
					numFlag = true;
				}
				index=j;
			}
		}
	}
	
	public void rightBlock()
	{
		int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
		moveFlag++;
		for(i=3;i<16;i+=4)
		{
			index=i;
			for(j=i-1;j>i-4;j--)
			{  
				valueJ=0; valueI=0;
				if(block[index].getValue()==0)
				{
					t=Find(index,-1,i-3,index+1);
					if(t!=-1)
					{
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueI=block[index].getValue();
				if(block[j].getValue()==0)
				{
					t=Find(j,-1,i-3,j+1);
					if(t!=-1)
					{
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueJ=block[j].getValue();
				if(valueI==valueJ && valueI!=0 && valueJ!=0)
				{
					block[index].setValue(valueI+valueJ);
					block[j].setValue(0);
					numFlag = true;
				}
				index=j;
			} 
		}
	}
	
	public void leftBlock() 
	{
		int i=0,j=0;int t=0;int valueJ=0;int valueI=0;int index=0;
		moveFlag++;
		for(i=0;i<16;i+=4)
		{
			index=i;
			for(j=i+1;j<i+4;j++)
			{  
				valueJ=0; valueI=0;
				if(block[index].getValue()==0)
				{
					t=Find(index,1,index,i+4);
					if(t!=-1)
					{
						block[index].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueI=block[index].getValue();
				if(block[j].getValue()==0)
				{
					t=Find(j,1,j,i+4);
					if(t!=-1)
					{
						block[j].setValue(block[t].getValue());
						block[t].setValue(0);
					}
					else
					{
						break;
					}
				}
				valueJ=block[j].getValue();
				if(valueI==valueJ && valueI!=0 && valueJ!=0)
				{
					block[index].setValue(valueI+valueJ);
					block[j].setValue(0);
					numFlag = true;
				}
				index=j;
			}
		}
	}
	
	public void over() 
	{
		if (numFlag==false && up==false && down==false && left==false && right==false)
		{
			for(int i=0;i<4;i++)
				block[i].setText("");
			for(int i=12;i<15;i++)
				block[i].setText("");
			block[4].setText("G");
			block[5].setText("A");
			block[6].setText("M");
			block[7].setText("E");
			block[8].setText("O");
			block[9].setText("V");
			block[10].setText("E");
			block[11].setText("R"); 
			block[15].setText("OK");
			for(int i=0;i<16;i++)
				block[i].resetColor();
			block[15].addMouseListener(new MouseAdapter() {public void mousePressed(MouseEvent e){reStart();}});
		}
	}
   
	public void win()
	{ 
		for(int n=0;n<16;n++)
		{
			if(block[n].getValue() == 2048)
			{
				for(int i=0;i<4;i++)
					block[i].setText("");
				for(int i=13;i<15;i++)
					block[i].setText("");
				block[4].setText("Y");
				block[5].setText("O");
				block[6].setText("U");
				block[7].setText("");
				block[8].setText("");
				block[9].setText("W");
				block[10].setText("I");
				block[11].setText("N");
				block[12].setText(""+moveFlag);
				block[15].setText("OK");
				for(int i=0;i<16;i++)
					block[i].resetColor();
				block[15].addMouseListener(new MouseAdapter() {public void mousePressed(MouseEvent e) {reStart();}});
			}
		}
	}
	
	public void reStart()
	{
		numFlag=true;
		moveFlag=0;	
  	 	up=true;down=true;left=true;right=true;
  	 	for(int i=0;i<16;i++)
  	 		block[i].setValue(0);
  	 	for (int i = 0; i < 2; i++)
  	 		appearBlock();
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(up)				
					upBlock();
				judgeAppear();
				appearBlock();
				win();
				over();
				if(numFlag==false)
				{
					up=false;
				}
				else
				{
					up=true;down=true;left=true;right=true;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(down)
					downBlock();
				judgeAppear();
				appearBlock();
				win();
				over();
				if(numFlag==false)
				{
					down=false;
				}
				else
				{
					up=true;down=true;left=true;right=true;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(left)
					leftBlock();
				judgeAppear();
				appearBlock();
				win();
				over();
				if(numFlag==false)
				{
					left=false;
				}
				else
				{
					up=true;down=true;left=true;right=true;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(right)
					rightBlock();
				judgeAppear();
				appearBlock();
				win();
				over();     
				if(numFlag==false)
				{
					right=false;
				}
				else
				{
					up=true;down=true;left=true;right=true;
				}
				break;
		}
	}
	
	public void keyTyped(KeyEvent e) {
 
	}
	public void keyReleased(KeyEvent e) {
 
	}
}