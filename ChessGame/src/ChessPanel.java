import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	private HashMap<Integer, Image> ChessImg = new HashMap<>();
	private Image board = new ImageIcon("board.png").getImage();
	private Image dot = new ImageIcon("dot.png").getImage();
	private ArrayList<Integer[]> ChessLocation;
	private ArrayList<Integer[]> CanMove;
	private ArrayList<Image> SDG = new ArrayList<Image>();

	public ChessPanel(ArrayList<Integer[]> ChessLocation, HashMap<Integer, Image> ChessImg,ArrayList<Integer[]> CanMove,ArrayList<Image> SDG) {
		this.ChessImg = ChessImg;
		this.ChessLocation = ChessLocation;
		this.CanMove=CanMove;
		this.SDG=SDG;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.board, 400, 100, 600, 600, null);
		if(SDG.size()>=1) {
		g.drawImage(this.SDG.get(0), 10, 100, 386, 567, null);
		}
		for (Integer[] x : ChessLocation) {
			g.drawImage(this.ChessImg.get(x[2]), 400 + x[0] * 75 + 12, 100 + x[1] * 75 + 12, 50, 50, null);
		}
		for (Integer[] y : CanMove) {
			g.drawImage(this.dot, 400 + y[0] * 75 + 12, 100 + y[1] * 75 + 12, 50, 50, null);
		}
	}
}
