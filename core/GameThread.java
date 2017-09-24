package core;
import java.awt.Color;

import javax.swing.JOptionPane;

public class GameThread implements Runnable {

	@Override
	public void run() {
		long lastTime = 0;
		long tickCount = 0;
		long cycleCount = 0;

		long timeActive = 1000;
		long hintTimeActive = 1000;
		while (true) {
			
			if(Main.hasEnded)
				break;
			
			long currentTime = System.nanoTime() / (long) 1000000;
			long deltaTime = currentTime - lastTime;

			if (deltaTime > 17) {

				if (tickCount % 59 == 0) {
					//System.out.println(cycleCount + " seconds have passed");
					cycleCount++;
					Main.secondsLeft--;
					timeActive = 0;

					for (int row = 0; row < Main.squareValues.length; ++row) {
						for (int col = 0; col < Main.squareValues[row].length; ++col) {
							if (cycleCount % Main.squareValues[row][col] == 0)
								Main.onSquares[row][col] = true;
							else
								Main.onSquares[row][col] = false;
						}
					}
					
					if(Main.secondsLeft <= 0) {
						Main.hasEnded = true;
						Main.drawingPane.repaint();
						JOptionPane.showMessageDialog(null, "You ran out of time!");
						System.exit(0);
					}

				}

				if (timeActive < Main.onDuration / 17) {
					timeActive++;
				} else {

					for (int row = 0; row < Main.squareValues.length; ++row) {
						for (int col = 0; col < Main.squareValues[row].length; ++col) {
							Main.onSquares[row][col] = false;
						}
					}

				}

				if(Main.randomHintValue != -1 && hintTimeActive > 10) {
					Main.randomHintValue = -1;
				}
					
				if(Main.displayHint) {
					hintTimeActive = 0;
					Main.randomHintValue = new java.util.Random().nextInt(9) + 1;
					Main.displayHint = false;
				}
				
				hintTimeActive++;
				lastTime = currentTime;
				tickCount++;
			}

			Main.drawingPane.repaint();

		}

	}

}
