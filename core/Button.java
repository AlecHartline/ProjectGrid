package core;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Button {

	int x;
	int y;
	int width;
	int height;
	String name;
	
	public Button(int x, int y, int width, int height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}
	
	public Button(int x, int y, int width, int height, String name) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;

	}
	
	public boolean isWithin(int xpos, int ypos) {

		if (x <= xpos && x + width >= xpos)
			if (y <= ypos && y + height >= ypos)
				return true;

		return false;

	}
	
	public void draw(Graphics g) {
		
		g.setFont(Draw.mainFont);
		
		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
		
		g.setColor(Color.WHITE);
		FontMetrics fm = g.getFontMetrics();
		
		
		String value = "";
		if(Main.buttonValueMap.get(this) != null)
			value = Main.buttonValueMap.get(this).toString();
		else {
			value = name;
			g.setFont(new Font("Consolas", Font.PLAIN, 12));
		}
		
		Rectangle2D bounds = fm.getStringBounds(value, g);
		int halfStringWidth = (int) (bounds.getWidth() / 2);
		int halfStringHeight = (int) (bounds.getHeight() / 4);
		
		
		g.drawString(value, x + width/2 - halfStringWidth + fm.stringWidth(value)/8, y + height/2 + halfStringHeight);
		
	}
	
}
