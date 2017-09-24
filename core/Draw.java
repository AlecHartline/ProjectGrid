package core;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel {
	
	final static int originX = 50;
	final static int originY = 50;
	
	final static int width = 50;
	final static int height = 50;
	
	final static int widthPadding = 50;
	final static int heightPadding = 50;
	
	final static int FONT_SIZE = 18;
	
	public static Font mainFont = new Font("Consolas", Font.PLAIN, FONT_SIZE);
	
	final Color onColor = Color.BLUE;
	final Color offColor = Color.GRAY;
	
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 10000, 10000);
		g.setFont(mainFont);
		
		for(int row = 0; row < Main.onSquares.length; ++row) {
			for(int col = 0; col < Main.onSquares[row].length; ++col) {
				
				if(Main.onSquares[row][col]) {
					g.setColor(onColor);
				}else {
					g.setColor(offColor);
				}
				
				int xpos = originX + width*col + widthPadding*col;
				int ypos = originY + height*row + heightPadding*row;
				
				g.fillRect(xpos, ypos, width, height);
				g.setColor(Color.BLACK);
				
				if(Main.randomHintValue != -1 && col + row*Main.onSquares.length == Main.randomHintValue) {
					System.out.println("hi");
					g.drawString(""+Main.squareValues[row][col], xpos + width/2 - FONT_SIZE/2 + 3, ypos + height + 10 + FONT_SIZE/2);
				}
				
				if(Main.hasEnded)
					g.drawString(""+Main.squareValues[row][col], xpos + width/2 - FONT_SIZE/2 + 3, ypos + height + 10 + FONT_SIZE/2);
			}
		}
		g.setColor(Color.MAGENTA);
		g.fillRect(Main.submit.x, Main.submit.y, Main.submit.width, Main.submit.height);
		Main.submit.draw(g);
		
		
		for(Button b : Main.buttons) {
			b.draw(g);
		}

		g.setColor(Color.BLACK);
		g.drawString(String.format("Time Left: %d:%02d", Main.secondsLeft / 60, Main.secondsLeft % 60), 150, 30);
	}

}