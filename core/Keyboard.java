package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == ' ') {
			if(Main.hintCount > 0) {
				System.out.println("hi");
				Main.displayHint = true;
				Main.hintCount--;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
