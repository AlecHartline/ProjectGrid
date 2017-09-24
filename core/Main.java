package core;

import static core.Draw.height;
import static core.Draw.heightPadding;
import static core.Draw.originX;
import static core.Draw.originY;
import static core.Draw.width;
import static core.Draw.widthPadding;

import java.awt.Dimension;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {

	public static Draw drawingPane = new Draw();
	public static Mouse m = new Mouse(drawingPane);

	public static int[][] squareValues;
	public static boolean[][] onSquares;
	
	public static ArrayList<Button> buttons = new ArrayList<Button>();
	public static Map<Button, Integer> buttonValueMap = new HashMap<Button, Integer>();
	public static Button submit = new Button(50, 12, 50, 24, " Submit");
	
	public static int secondsLeft = 15;
	public static boolean hasEnded = false;
	public static int onDuration = 0;
	
	private static int rows, cols;
	public static int hintCount;
	public static boolean displayHint = false;
	public static int randomHintValue = -1;
	
	static {
		
		do {
			 rows = Integer.parseInt(JOptionPane.showInputDialog("How many rows (less than 10)?"));
		}while(rows > 9);
		
		do {
			cols = Integer.parseInt(JOptionPane.showInputDialog("How many columns (less than 11)?"));
		}while(cols > 10);
		
		hintCount = rows*cols/10;
		
		do {
			secondsLeft = Integer.parseInt(JOptionPane.showInputDialog("How much time to complete in seconds (less than 1201)?"));
		}while(secondsLeft > 1200);
		
		do {
			onDuration  = Integer.parseInt(JOptionPane.showInputDialog("How long should a box flash? (less than 1000ms)"));
		}while(onDuration >= 1000);
		
		squareValues = new int[rows][cols];
		onSquares = new boolean[rows][cols];
		
		Random random = new Random();
		for (int row = 0; row < squareValues.length; ++row) {
			for (int col = 0; col < squareValues[row].length; ++col) {
				squareValues[row][col] = random.nextInt(9) + 1;
				onSquares[row][col] = true;
			}
		}
		
		for (int row = 0; row < squareValues.length; ++row) {
			for (int col = 0; col < squareValues[row].length; ++col) {
				
				int xpos = originX + width*col + widthPadding*col;
				int ypos = originY + height*row + heightPadding*row;
				
				Button button = new Button(xpos, ypos, width, height);
				buttons.add(button);
				
				buttonValueMap.put(button, 1);
				
			}
		}
		
		
		
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {

		SwingUtilities.invokeAndWait(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Project Grid");
				f.setMinimumSize(new Dimension(360, 390));
				f.setSize(cols * 100 + 60, rows * 100 + 90);
				f.setResizable(false);
				f.setVisible(true);
				f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
				
				drawingPane.addMouseListener(m);
				drawingPane.addMouseMotionListener(m);
				drawingPane.addKeyListener(new Keyboard());
				drawingPane.setFocusable(true);

				f.add(drawingPane);

				drawingPane.paintComponent(f.getGraphics());
			}
		});

		Thread gameThread = new Thread(new GameThread());
		gameThread.start();

	}

}
