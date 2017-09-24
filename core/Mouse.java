package core;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

public class Mouse implements MouseListener, MouseMotionListener {

	Draw d = null;

	public Mouse(Draw input) {
		d = input;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Main.hasEnded)
			return;
		
		for(Button b : Main.buttons) {
			if(b.isWithin(e.getX(), e.getY())) {
				Integer value = Main.buttonValueMap.get(b);
				if(value == 9)
					value = 1;
				else
					value++;
				Main.buttonValueMap.put(b, value);
			}
		}
		
		if(Main.submit.isWithin(e.getX(), e.getY())) {
			boolean isCorrect = true;
			for(int i = 0; i < Main.buttons.size(); ++i) {
				
				int row = i / Main.squareValues.length;
				int col = i % Main.squareValues[row].length;
				Button currentButton = Main.buttons.get(i);
				
				if(Main.squareValues[row][col] != Main.buttonValueMap.get(currentButton)) {
					isCorrect = false;
				}
				
			}
			
			if(isCorrect) {
				Main.hasEnded = true;
				Main.drawingPane.repaint();
				JOptionPane.showMessageDialog(null, "You Win!");
				System.exit(0);
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect submission");
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		/*
		 * Button b = new Button(50, 50, 100, 100); if(b.isWithin(e.getX(), e.getY()))
		 * Main.boxColor = Color.RED; else Main.boxColor = Color.BLUE;
		 * 
		 * d.repaint();
		 */
	}

}