
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ChessGUI extends JFrame {
	public static final int width = 1600;
	public static final int height = 800;
	public static int a, b, c, d, i;
	private HashMap<Integer, Image> ChessImg = new HashMap<>();
	private ArrayList<Integer[]> ChessLocation = new ArrayList<Integer[]>();
	private int xGrid;
	private int yGrid;
	private int cxGrid;
	private int cyGrid;
	private int movePiece;
	private int movePieceId;
	private boolean Turn = false;
	private ArrayList<Integer> xLine = new ArrayList<Integer>();
	private ArrayList<Integer> yLine = new ArrayList<Integer>();
	private ArrayList<Integer> xLine_x = new ArrayList<Integer>();
	private ArrayList<Integer> xLine_y = new ArrayList<Integer>();
	private ArrayList<Integer> yLine_x = new ArrayList<Integer>();
	private ArrayList<Integer> yLine_y = new ArrayList<Integer>();
	private int castlemove = 0;
	private int bishopmove = 0;
	private int knightmove = 0;
	private int kingmove = 0;
	private int soildermove = 0;
	private JLabel turnLabel;
	private JLabel turnPic;
	private int[] p1fstep = { 1, 1, 1, 1, 1, 1, 1, 1 };
	private int[] p2fstep = { 1, 1, 1, 1, 1, 1, 1, 1 };
	private static int P2xdeath = 8;
	private static int P2ydeath = 0;
	private static int P1xdeath = 8;
	private static int P1ydeath = 4;
	private static int xdeath;
	private static int ydeath;
	public static int p1Point = 0;
	public static int p2Point = 0;
	private ArrayList<Integer[]> CanMove = new ArrayList<Integer[]>();
	private ArrayList<Image> SDG = new ArrayList<Image>();

	public static void begin() {
		JFrame gameStart = new JFrame();
		gameStart.setSize(300, 680);
		gameStart.setResizable(false);
		gameStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameStart.setLayout(new BorderLayout());
//		JPanel enter = new JPanel();
		ImageIcon bg = new ImageIcon("bg.png");
		JLabel bgp = new JLabel(bg);

		


//		enter.add(bgp);
		JButton Start = new JButton("Start");
//		enter.add(Start);
//		gameStart.add(enter);
		gameStart.add(Start);
		JButton rule = new JButton("Rule");
		rule.setBounds(160,610,90,25);
		gameStart.add(rule);
		Start.setBounds(40,610,90,25);
		gameStart.add(bgp);
		bgp.setBounds(0,0,300,642);

		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessGUI chess = new ChessGUI();
				chess.setVisible(true);
				gameStart.dispose();
			}
		});
		rule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rule rule = new rule();
				rule.setVisible(true);
			}
		});
		gameStart.setVisible(true);

	}

	public ChessGUI() {
		super("Chess");
		setSize(width, height);
		setResizable(false);
		setLayout(new BorderLayout());
		for (a = 1; a <= 8; a++) {
			Image p1soilder = new ImageIcon("p1soilder_0.png").getImage();
			ChessImg.put(a, p1soilder);
		}
		Image p1castle = new ImageIcon("p1castle_0.png").getImage();
		ChessImg.put(9, p1castle);
		ChessImg.put(10, p1castle);
		Image p1knight = new ImageIcon("p1knight_0.png").getImage();
		ChessImg.put(11, p1knight);
		ChessImg.put(12, p1knight);
		Image p1bishop = new ImageIcon("p1bishop_0.png").getImage();
		ChessImg.put(13, p1bishop);
		ChessImg.put(14, p1bishop);
		Image p1queen = new ImageIcon("p1queen_0.png").getImage();
		ChessImg.put(15, p1queen);
		Image p1king = new ImageIcon("p1king_0.png").getImage();
		ChessImg.put(16, p1king);
		for (b = 17; b <= 24; b++) {
			Image p2soilder = new ImageIcon("p2soilder_0.png").getImage();
			ChessImg.put(b, p2soilder);
		}
		Image p2castle = new ImageIcon("p2castle_0.png").getImage();
		ChessImg.put(25, p2castle);
		ChessImg.put(26, p2castle);
		Image p2knight = new ImageIcon("p2knight_0.png").getImage();
		ChessImg.put(27, p2knight);
		ChessImg.put(28, p2knight);
		Image p2bishop = new ImageIcon("p2bishop_0.png").getImage();
		ChessImg.put(29, p2bishop);
		ChessImg.put(30, p2bishop);
		Image p2queen = new ImageIcon("p2queen_0.png").getImage();
		ChessImg.put(31, p2queen);
		Image p2king = new ImageIcon("p2king_0.png").getImage();
		ChessImg.put(32, p2king);

		Integer[] p1soilder_1 = new Integer[] { 0, 1, 1 };
		ChessLocation.add(p1soilder_1);
		Integer[] p1soilder_2 = new Integer[] { 1, 1, 2 };
		ChessLocation.add(p1soilder_2);
		Integer[] p1soilder_3 = new Integer[] { 2, 1, 3 };
		ChessLocation.add(p1soilder_3);
		Integer[] p1soilder_4 = new Integer[] { 3, 1, 4 };
		ChessLocation.add(p1soilder_4);
		Integer[] p1soilder_5 = new Integer[] { 4, 1, 5 };
		ChessLocation.add(p1soilder_5);
		Integer[] p1soilder_6 = new Integer[] { 5, 1, 6 };
		ChessLocation.add(p1soilder_6);
		Integer[] p1soilder_7 = new Integer[] { 6, 1, 7 };
		ChessLocation.add(p1soilder_7);
		Integer[] p1soilder_8 = new Integer[] { 7, 1, 8 };
		ChessLocation.add(p1soilder_8);

		Integer[] p1castle_1 = new Integer[] { 0, 0, 9 };
		Integer[] p1castle_2 = new Integer[] { 7, 0, 10 };
		ChessLocation.add(p1castle_1);
		ChessLocation.add(p1castle_2);
		Integer[] p1knight_1 = new Integer[] { 1, 0, 11 };
		Integer[] p1knight_2 = new Integer[] { 6, 0, 12 };
		ChessLocation.add(p1knight_1);
		ChessLocation.add(p1knight_2);
		Integer[] p1bishop_1 = new Integer[] { 2, 0, 13 };
		Integer[] p1bishop_2 = new Integer[] { 5, 0, 14 };
		ChessLocation.add(p1bishop_1);
		ChessLocation.add(p1bishop_2);
		Integer[] p1queen_L = new Integer[] { 4, 0, 15 };
		Integer[] p1king_L = new Integer[] { 3, 0, 16 };
		ChessLocation.add(p1queen_L);
		ChessLocation.add(p1king_L);

		Integer[] p2soilder_1 = new Integer[] { 0, 6, 17 };
		ChessLocation.add(p2soilder_1);
		Integer[] p2soilder_2 = new Integer[] { 1, 6, 18 };
		ChessLocation.add(p2soilder_2);
		Integer[] p2soilder_3 = new Integer[] { 2, 6, 19 };
		ChessLocation.add(p2soilder_3);
		Integer[] p2soilder_4 = new Integer[] { 3, 6, 20 };
		ChessLocation.add(p2soilder_4);
		Integer[] p2soilder_5 = new Integer[] { 4, 6, 21 };
		ChessLocation.add(p2soilder_5);
		Integer[] p2soilder_6 = new Integer[] { 5, 6, 22 };
		ChessLocation.add(p2soilder_6);
		Integer[] p2soilder_7 = new Integer[] { 6, 6, 23 };
		ChessLocation.add(p2soilder_7);
		Integer[] p2soilder_8 = new Integer[] { 7, 6, 24 };
		ChessLocation.add(p2soilder_8);
		Integer[] p2castle_1 = new Integer[] { 0, 7, 25 };
		Integer[] p2castle_2 = new Integer[] { 7, 7, 26 };
		ChessLocation.add(p2castle_1);
		ChessLocation.add(p2castle_2);
		Integer[] p2knight_1 = new Integer[] { 1, 7, 27 };
		Integer[] p2knight_2 = new Integer[] { 6, 7, 28 };
		ChessLocation.add(p2knight_1);
		ChessLocation.add(p2knight_2);
		Integer[] p2bishop_1 = new Integer[] { 2, 7, 29 };
		Integer[] p2bishop_2 = new Integer[] { 5, 7, 30 };
		ChessLocation.add(p2bishop_1);
		ChessLocation.add(p2bishop_2);
		Integer[] p2queen_L = new Integer[] { 4, 7, 31 };
		Integer[] p2king_L = new Integer[] { 3, 7, 32 };
		ChessLocation.add(p2queen_L);
		ChessLocation.add(p2king_L);
		turnLabel = new JLabel("Turn Player 1", SwingConstants.CENTER);
		turnLabel.setBounds(600, 30, 200, 50);
		turnLabel.setFont(new Font("Consolas", Font.BOLD, 25));
		ImageIcon bishop = new ImageIcon("p2.png");
		turnPic = new JLabel(bishop);
		turnPic.setBounds(820,25,50,50);
		add(turnLabel);
		add(turnPic);
		ChessPanel panel = new ChessPanel(ChessLocation, ChessImg, CanMove, SDG);
		add(panel, BorderLayout.CENTER);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				int x, y, i, j;
				x = e.getX();
				y = e.getY();
				xGrid = (x - 400) / 75;
				yGrid = (y - 100) / 75;
				for (i = 0; i <= 7; i++) {
					for (j = 0; j <= 7; j++) {
						cxGrid = j;
						cyGrid = i;
						if (havePiece()) {
//							System.out.println("cccc" + movePiece);
//							System.out.println("cccc" + SDG.size());
							if ((movePiece >= 1 && movePiece <= 8) || (movePiece >= 17 && movePiece <= 24)) {
								Image soilder = new ImageIcon("soilder.png").getImage();
								SDG.add(soilder);
							} else if (movePiece == 9 || movePiece == 10 || movePiece == 25 || movePiece == 26) {
								Image castle = new ImageIcon("castle.png").getImage();
								SDG.add(castle);
							} else if (movePiece == 11 || movePiece == 12 || movePiece == 27 || movePiece == 28) {
								Image knight = new ImageIcon("knight.png").getImage();
								SDG.add(knight);
							} else if (movePiece == 13 || movePiece == 14 || movePiece == 29 || movePiece == 30) {
								Image bishop = new ImageIcon("bishop.png").getImage();
								SDG.add(bishop);
							} else if (movePiece == 15 || movePiece == 31) {
								Image queen = new ImageIcon("queen.png").getImage();
								SDG.add(queen);
							} else if (movePiece == 16 || movePiece == 32) {
								Image king = new ImageIcon("king.png").getImage();
								SDG.add(king);
							}
							repaint();
							if (cxGrid <= 7 && cyGrid <= 7 && cxGrid >= 0 && cyGrid >= 0) {
								if (notSame()) {
									if (isYourTurn()) {
										if (legalcan()) {
											Integer[] newLocation = new Integer[] { j, i };
											CanMove.add(newLocation);
											repaint();
										}
									}
								}
							}

						}

					}
				}
			}

			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				int x, y;
				x = e.getX();
				y = e.getY();
				cxGrid = (x - 400) / 75;
				cyGrid = (y - 100) / 75;
//				System.out.println("cx" + cxGrid);
//				System.out.println("x" + xGrid);
//				System.out.println("cy" + cyGrid);
//				System.out.println("y" + yGrid);
				if (havePiece()) {
					CanMove.clear();
					SDG.clear();
					repaint();
					if (cxGrid <= 7 && cyGrid <= 7 && cxGrid >= 0 && cyGrid >= 0) {
						if (notSame()) {
							if (isYourTurn()) {
								if (legalMove()) {
									Integer[] newLocation = new Integer[] { cxGrid, cyGrid, movePiece };
									ChessLocation.set(movePieceId, newLocation);
									repaint();
									Turn = !Turn;
									if (Turn) {
										turnLabel.setText("Turn Player 2");
										ImageIcon p2 = new ImageIcon("p1.png");
										turnPic.setIcon(p2);
									} else {
										turnLabel.setText("Turn Player 1");
										ImageIcon p2 = new ImageIcon("p2.png");
										turnPic.setIcon(p2);
									}
									if (ChessLocation.get(31)[0] >= 8 || ChessLocation.get(15)[0] >= 8) {
										for (i = 0; i < ChessLocation.size(); i++) {
											if (ChessLocation.get(i)[0] >= 8) {
												if (ChessLocation.get(i)[2] <= 8) {
													p1Point = p1Point + 5;
												} else if (ChessLocation.get(i)[2] == 9
														|| ChessLocation.get(i)[2] == 10) {
													p1Point = p1Point + 15;
												} else if (ChessLocation.get(i)[2] == 11
														|| ChessLocation.get(i)[2] == 12) {
													p1Point = p1Point + 15;
												} else if (ChessLocation.get(i)[2] == 13
														|| ChessLocation.get(i)[2] == 14) {
													p1Point = p1Point + 20;
												} else if (ChessLocation.get(i)[2] == 15) {
													p1Point = p1Point + 50;
												} else if (ChessLocation.get(i)[2] == 16) {
													p1Point = p1Point + 100;
												} else if (ChessLocation.get(i)[2] <= 24
														&& ChessLocation.get(i)[2] >= 17) {
													p2Point = p2Point + 5;
												} else if (ChessLocation.get(i)[2] == 25
														|| ChessLocation.get(i)[2] == 26) {
													p2Point = p2Point + 15;
												} else if (ChessLocation.get(i)[2] == 27
														|| ChessLocation.get(i)[2] == 28) {
													p2Point = p2Point + 15;
												} else if (ChessLocation.get(i)[2] == 29
														|| ChessLocation.get(i)[2] == 30) {
													p2Point = p2Point + 20;
												} else if (ChessLocation.get(i)[2] == 31) {
													p2Point = p2Point + 50;
												} else if (ChessLocation.get(i)[2] == 32) {
													p2Point = p2Point + 100;
												}
											}
										}
										Over over = new Over();
										over.setVisible(true);
									}
								}
							}
						}
					}
				}

			}
		});

	}

	public boolean havePiece() {
		int i, j;
//		System.out.println(xGrid);
		for (i = 0; i < ChessLocation.size(); i++) {
			if (xGrid == ChessLocation.get(i)[0]) {
				if (yGrid == ChessLocation.get(i)[1]) {
					movePiece = ChessLocation.get(i)[2];
					movePieceId = i;
					return true;
				}
			}
		}
		return false;
	}

	public boolean isYourTurn() {
		if (P2xdeath > 13) {
			P2ydeath++;
			P2xdeath = 8;
		}
		if (P1xdeath > 13) {
			P1ydeath++;
			P1xdeath = 8;
		}
		if (!Turn) {
			if (17 <= movePiece && movePiece <= 32) {
				return true;
			} else
				return false;
		} else {
			if (1 <= movePiece && movePiece <= 16) {
				return true;
			} else
				return false;
		}
	}

	public boolean legalMove() {
		if (movePiece == 9 || movePiece == 10 || movePiece == 25 || movePiece == 26) {
			if (castleMove()) {
				return true;
			} else
				return false;
		}
		if ((movePiece >= 1 && movePiece <= 8) || (movePiece >= 17 && movePiece <= 24)) {
			if (soilderMove()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 13 || movePiece == 14 || movePiece == 29 || movePiece == 30) {
			if (bishopMove()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 31 || movePiece == 15) {
			if (queenMove()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 11 || movePiece == 12 || movePiece == 27 || movePiece == 28) {
			if (knightMove()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 16 || movePiece == 32) {
			if (kingMove()) {
				return true;
			} else
				return false;
		}
		return false;

	}

	public boolean legalcan() {
		if (movePiece == 9 || movePiece == 10 || movePiece == 25 || movePiece == 26) {
			if (castle()) {
				return true;
			} else
				return false;
		}
		if ((movePiece >= 1 && movePiece <= 8) || (movePiece >= 17 && movePiece <= 24)) {
			if (soilder()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 13 || movePiece == 14 || movePiece == 29 || movePiece == 30) {
			if (bishop()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 31 || movePiece == 15) {
			if (queen()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 11 || movePiece == 12 || movePiece == 27 || movePiece == 28) {
			if (knight()) {
				return true;
			} else
				return false;
		}
		if (movePiece == 16 || movePiece == 32) {
			if (king()) {
				return true;
			} else
				return false;
		}
		return false;

	}

	public boolean soilderMove() {
		soilderhaveobstacle();
		if (soildermove == 1) {
			return true;
		} else
			return false;
	}

	public boolean soilder() {
		soildercan();
		if (soildermove == 1) {
			return true;
		} else
			return false;
	}

	public boolean castleMove() {
		int i, k = 1;
		if (cxGrid == xGrid || cyGrid == yGrid) {
			for (i = 1; i <= 8; i++) {
				if (cxGrid == xGrid + k || cyGrid == yGrid + k || cxGrid == xGrid - k || cyGrid == yGrid - k) {
					castlehaveObstacle();
					if (castlemove == 1) {
						return true;
					}

				}
				k++;
			}
			return false;
		}
		return false;

	}

	public boolean castle() {
		int i, k = 1;
		if (cxGrid == xGrid || cyGrid == yGrid) {
			for (i = 1; i <= 8; i++) {
				if (cxGrid == xGrid + k || cyGrid == yGrid + k || cxGrid == xGrid - k || cyGrid == yGrid - k) {
					castlecan();
					if (castlemove == 1) {
						return true;
					}

				}
				k++;
			}
			return false;
		}
		return false;

	}

//
	public boolean knightMove() {
		knighthaveobstacle();
		if (knightmove == 1) {
			return true;
		} else
			return false;
	}

	public boolean knight() {
		knightcan();
		if (knightmove == 1) {
			return true;
		} else
			return false;
	}

//
	public boolean bishopMove() {
		int i;
		bishophaveobstacle();
		if (bishopmove == 1) {
			return true;
		}
		return false;
	}

	public boolean bishop() {
		int i;
		bishopcan();
		if (bishopmove == 1) {
			return true;
		}
		return false;
	}

//
	public boolean queenMove() {
		bishophaveobstacle();
		castlehaveObstacle();
		if (bishopmove == 1 || castlemove == 1) {
			return true;
		} else
			return false;
	}

	public boolean queen() {
		bishopcan();
		castlecan();
		if (bishopmove == 1 || castlemove == 1) {
			return true;
		} else
			return false;
	}

//
	public boolean kingMove() {
		kinghaveobstacle();
		if (kingmove == 1) {
			return true;
		} else
			return false;
	}

	public boolean king() {
		kingcan();
		if (kingmove == 1) {
			return true;
		} else
			return false;
	}

	public boolean notSame() {
		if (cxGrid == xGrid && cyGrid == yGrid) {
			return false;
		}
		return true;
	}

	public void castlehaveObstacle() {
		int p, x = 0, y, yp = 0, xp = 0, j, i, tmp, index, eatPID = 0, eatPID_2 = 0;
		castlemove = 0;

		if (cxGrid == xGrid) {
			for (p = 0; p < ChessLocation.size(); p++) {
				if (cxGrid == ChessLocation.get(p)[0]) {
					yLine.add(ChessLocation.get(p)[1]);
				}
			}
			Collections.sort(yLine);
			for (i = 0; i < yLine.size(); i++) {
				if (yGrid == yLine.get(i)) {
					yp = i;
				}
			}

			if (yLine.size() == 1) {
				castlemove = 1;
			} else if (yp + 1 >= yLine.size()) {
				if (cyGrid > yLine.get(yp - 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp - 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}

			} else if (yp - 1 < 0) {
				if (cyGrid < yLine.get(yp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp + 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp + 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (yp + 1 <= yLine.size() && yp - 1 >= 0) {
				if (cyGrid > yLine.get(yp - 1) && cyGrid < yLine.get(yp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp - 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp + 1) == ChessLocation.get(i)[1]) {
							eatPID_2 = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
//						System.out.println("eat");
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
				if (cyGrid == yLine.get(yp + 1)) {
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
						if (eatPID_2 <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID_2 > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
						ChessLocation.set(eatPID_2 - 1, newLocation);
//						System.out.println("eatttt");
						castlemove = 1;
					}
				}
			}
		}

//		xxxxxxxxxxxxxx

		if (cyGrid == yGrid) {

			for (p = 0; p < ChessLocation.size(); p++) {
				if (cyGrid == ChessLocation.get(p)[1]) {
					xLine.add(ChessLocation.get(p)[0]);
				}
			}
			Collections.sort(xLine);

			for (i = 0; i < xLine.size(); i++) {
				if (xGrid == xLine.get(i)) {
					xp = i;
				}
			}
			if (xLine.size() == 1) {
				castlemove = 1;
			} else if (xp + 1 >= xLine.size()) {
				if (cxGrid > xLine.get(xp - 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp - 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (xp - 1 < 0) {
				if (cxGrid < xLine.get(xp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp + 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp + 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (xp + 1 <= xLine.size() && xp - 1 >= 0) {
				if (cxGrid > xLine.get(xp - 1) && cxGrid < xLine.get(xp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp - 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp + 1) == ChessLocation.get(i)[0]) {
							eatPID_2 = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
//						System.out.println("eat");
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
				if (cxGrid == xLine.get(xp + 1)) {
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
						if (eatPID_2 <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID_2 > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
						ChessLocation.set(eatPID_2 - 1, newLocation);
//						System.out.println("eatttt");
						castlemove = 1;
					}
				}
			}
		}
		yLine.clear();
		xLine.clear();

	}

	public void castlecan() {
		int p, x = 0, y, yp = 0, xp = 0, j, i, tmp, index, eatPID = 0, eatPID_2 = 0;
		castlemove = 0;

		if (cxGrid == xGrid) {
			for (p = 0; p < ChessLocation.size(); p++) {
				if (cxGrid == ChessLocation.get(p)[0]) {
					yLine.add(ChessLocation.get(p)[1]);
				}
			}
			Collections.sort(yLine);
			for (i = 0; i < yLine.size(); i++) {
				if (yGrid == yLine.get(i)) {
					yp = i;
				}
			}

			if (yLine.size() == 1) {
				castlemove = 1;
			} else if (yp + 1 >= yLine.size()) {
				if (cyGrid > yLine.get(yp - 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp - 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}

			} else if (yp - 1 < 0) {
				if (cyGrid < yLine.get(yp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp + 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp + 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (yp + 1 <= yLine.size() && yp - 1 >= 0) {
				if (cyGrid > yLine.get(yp - 1) && cyGrid < yLine.get(yp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp - 1) == ChessLocation.get(i)[1]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cxGrid == ChessLocation.get(i)[0]) {
						if (yLine.get(yp + 1) == ChessLocation.get(i)[1]) {
							eatPID_2 = ChessLocation.get(i)[2];
						}
					}

				}
				if (cyGrid == yLine.get(yp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						System.out.println("eat");
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
				if (cyGrid == yLine.get(yp + 1)) {
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
//						if(eatPID_2<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID_2>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						ChessLocation.set(eatPID_2 - 1, newLocation);
//						System.out.println("eatttt");
						castlemove = 1;
					}
				}
			}
		}

//		xxxxxxxxxxxxxx

		if (cyGrid == yGrid) {

			for (p = 0; p < ChessLocation.size(); p++) {
				if (cyGrid == ChessLocation.get(p)[1]) {
					xLine.add(ChessLocation.get(p)[0]);
				}
			}
			Collections.sort(xLine);

			for (i = 0; i < xLine.size(); i++) {
				if (xGrid == xLine.get(i)) {
					xp = i;
				}
			}
			if (xLine.size() == 1) {
				castlemove = 1;
			} else if (xp + 1 >= xLine.size()) {
				if (cxGrid > xLine.get(xp - 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp - 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (xp - 1 < 0) {
				if (cxGrid < xLine.get(xp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp + 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp + 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
			} else if (xp + 1 <= xLine.size() && xp - 1 >= 0) {
				if (cxGrid > xLine.get(xp - 1) && cxGrid < xLine.get(xp + 1)) {
					castlemove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp - 1) == ChessLocation.get(i)[0]) {
							eatPID = ChessLocation.get(i)[2];
						}
					}

				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						if (xLine.get(xp + 1) == ChessLocation.get(i)[0]) {
							eatPID_2 = ChessLocation.get(i)[2];
						}
					}

				}
				if (cxGrid == xLine.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						System.out.println("eat");
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						ChessLocation.set(eatPID - 1, newLocation);
						castlemove = 1;
					}
				}
				if (cxGrid == xLine.get(xp + 1)) {
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
//						if(eatPID_2<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID_2>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						ChessLocation.set(eatPID_2 - 1, newLocation);
//						System.out.println("eatttt");
						castlemove = 1;
					}
				}
			}
		}
		yLine.clear();
		xLine.clear();

	}

	public void soilderhaveobstacle() {
		int i, ob = 0, ob2 = 0, eatPID_L = 0, eatPID_R = 0, eat_R = 0, eat_L = 0;
		soildermove = 0;
		if (movePiece >= 1 && movePiece <= 8) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (xGrid == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						ob++;
					}
				}
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						ob2++;
					}
				}
				if (xGrid + 1 == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						eatPID_R = ChessLocation.get(i)[2];
						eat_R++;
					}
				}
				if (xGrid - 1 == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						eatPID_L = ChessLocation.get(i)[2];
						eat_L++;
					}
				}
			}
			if (p2fstep[movePiece - 1] == 1) {
				if (cyGrid == yGrid + 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
						p2fstep[movePiece - 1] = 0;
					}

				} else if (cyGrid == yGrid + 2 && cxGrid == xGrid) {
					if (ob == 0 && ob2 == 0) {
						soildermove = 1;
						p2fstep[movePiece - 1] = 0;
					}

				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
								if (eatPID_R <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_R > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
								p2fstep[movePiece - 1] = 0;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
								if (eatPID_L <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_L > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
								p2fstep[movePiece - 1] = 0;
							}
						}
					}

				}
			} else {
				if (cyGrid == yGrid + 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
					}
				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
								if (eatPID_R <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_R > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
								if (eatPID_L <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_L > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				}
			}
		} else if (movePiece >= 17 && movePiece <= 24) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (xGrid == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						ob++;
					}
				}
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						ob2++;
					}
				}
				if (xGrid + 1 == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						eatPID_R = ChessLocation.get(i)[2];
						eat_R++;
					}
				}
				if (xGrid - 1 == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						eatPID_L = ChessLocation.get(i)[2];
						eat_L++;
					}
				}
			}
			if (p1fstep[movePiece % 17] == 1) {
				if (cyGrid == yGrid - 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
						p1fstep[movePiece % 17] = 0;
					}

				} else if (cyGrid == yGrid - 2 && cxGrid == xGrid) {
					if (ob == 0 && ob2 == 0) {
						soildermove = 1;
						p1fstep[movePiece % 17] = 0;
					}

				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
								if (eatPID_R <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_R > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
								p1fstep[movePiece % 17] = 0;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
								if (eatPID_L <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_L > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
								p1fstep[movePiece % 17] = 0;
							}
						}
					}

				}
			} else {
				if (cyGrid == yGrid - 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
					}
				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
								if (eatPID_R <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_R > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
								if (eatPID_L <= 16) {
									xdeath = P2xdeath;
									ydeath = P2ydeath;
									P2xdeath++;
								} else if (eatPID_L > 16) {
									xdeath = P1xdeath;
									ydeath = P1ydeath;
									P1xdeath++;
								}
								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				}
			}
		}

	}

	public void soildercan() {
		int i, ob = 0, ob2 = 0, eatPID_L = 0, eatPID_R = 0, eat_R = 0, eat_L = 0;
		soildermove = 0;
		if (movePiece >= 1 && movePiece <= 8) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (xGrid == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						ob++;
					}
				}
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						ob2++;
					}
				}
				if (xGrid + 1 == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						eatPID_R = ChessLocation.get(i)[2];
						eat_R++;
					}
				}
				if (xGrid - 1 == ChessLocation.get(i)[0]) {
					if (yGrid + 1 == ChessLocation.get(i)[1]) {
						eatPID_L = ChessLocation.get(i)[2];
						eat_L++;
					}
				}
			}
			if (p2fstep[movePiece - 1] == 1) {
				if (cyGrid == yGrid + 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
//						p2fstep[movePiece - 1] = 0;
					}

				} else if (cyGrid == yGrid + 2 && cxGrid == xGrid) {
					if (ob == 0 && ob2 == 0) {
						soildermove = 1;
//						p2fstep[movePiece - 1] = 0;
					}

				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
//								if(eatPID_R<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_R>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
//								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
//								p2fstep[movePiece - 1] = 0;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
//								if(eatPID_L<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_L>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
//								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
//								p2fstep[movePiece - 1] = 0;
							}
						}
					}

				}
			} else {
				if (cyGrid == yGrid + 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
					}
				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
//								if(eatPID_R<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_R>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
//								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid + 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
//								if(eatPID_L<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_L>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
//								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				}
			}
		} else if (movePiece >= 17 && movePiece <= 24) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (xGrid == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						ob++;
					}
				}
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						ob2++;
					}
				}
				if (xGrid + 1 == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						eatPID_R = ChessLocation.get(i)[2];
						eat_R++;
					}
				}
				if (xGrid - 1 == ChessLocation.get(i)[0]) {
					if (yGrid - 1 == ChessLocation.get(i)[1]) {
						eatPID_L = ChessLocation.get(i)[2];
						eat_L++;
					}
				}
			}
			if (p1fstep[movePiece % 17] == 1) {
				if (cyGrid == yGrid - 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
//						p1fstep[movePiece % 17] = 0;
					}

				} else if (cyGrid == yGrid - 2 && cxGrid == xGrid) {
					if (ob == 0 && ob2 == 0) {
						soildermove = 1;
//						p1fstep[movePiece % 17] = 0;
					}

				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
//								if(eatPID_R<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_R>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
//								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
//								p1fstep[movePiece % 17] = 0;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
//								if(eatPID_L<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_L>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
//								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
//								p1fstep[movePiece % 17] = 0;
							}
						}
					}

				}
			} else {
				if (cyGrid == yGrid - 1 && cxGrid == xGrid) {
					if (ob2 == 0) {
						soildermove = 1;
					}
				} else if (cxGrid == xGrid + 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_R == 1) {
							if ((movePiece > 16 && eatPID_R <= 16) || (movePiece <= 16 && eatPID_R > 16)) {
//								if(eatPID_R<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_R>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_R };
//								ChessLocation.set(eatPID_R - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				} else if (cxGrid == xGrid - 1) {
					if (cyGrid == yGrid - 1) {
						if (eat_L == 1) {
							if ((movePiece > 16 && eatPID_L <= 16) || (movePiece <= 16 && eatPID_L > 16)) {
//								if(eatPID_L<=16) {
//									xdeath=P2xdeath;
//									ydeath=P2ydeath;
//									P2xdeath++;
//								}
//								else if(eatPID_L>16) {
//									xdeath=P1xdeath;
//									ydeath=P1ydeath;
//									P1xdeath++;
//								}
//								Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_L };
//								ChessLocation.set(eatPID_L - 1, newLocation);
//								System.out.println("eatttt");
								soildermove = 1;
							}
						}
					}

				}
			}
		}

	}

	public void bishophaveobstacle() {
		bishopmove = 0;
		int b = 0, k, j, xp = 0, yp = 0, eatPID = 0, q, i, eatPID_2 = 0;
		if (cyGrid - yGrid == -(cxGrid - xGrid)) {
			for (i = -20; i <= 20; i++) {
				if (yGrid == -xGrid + i) {
					b = i;
				}
			}
//			System.out.println(b);
			for (k = 0; k < ChessLocation.size(); k++) {
				if (ChessLocation.get(k)[1] == (-1 * ChessLocation.get(k)[0]) + b) {
//					System.out.println("+x" + ChessLocation.get(k)[0]);
//					System.out.println("+y" + ChessLocation.get(k)[1]);
					xLine_x.add(ChessLocation.get(k)[0]);
					xLine_y.add(ChessLocation.get(k)[1]);
				}
			}
			Collections.sort(xLine_x);
			Collections.sort(xLine_y);
//			for (i = 0; i < xLine_y.size(); i++) {
//				System.out.println("x" + xLine_x.get(i));
//				System.out.println("y" + xLine_y.get(i));
//
//			}

//			for (q = 0; q < xLine_x.size(); q++) {
//				System.out.println("x" + xLine_x.get(q));
//
//				System.out.println("y" + xLine_y.get(xLine_x.size() - q - i));
//			}
			for (i = 0; i < xLine_x.size(); i++) {
				if (xLine_x.get(i) == xGrid) {
					xp = i;
				}
			}
//			System.out.println("xp" + xp);
//			System.out.println("xsize" + xLine_x.size());

			if (xLine_x.size() == 1) {
				bishopmove = 1;
			} else if (xp + 1 >= xLine_x.size()) {
//				System.out.println("move");
				if (cxGrid > xLine_x.get(xp - 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == xLine_x.get(xp - 1) && cyGrid == xLine_y.get(xLine_x.size() - xp)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == xLine_x.get(xp - 1)
								&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp - 1 < 0) {
				if (cxGrid < xLine_x.get(xp + 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == xLine_x.get(xp + 1) && cyGrid == xLine_y.get(xLine_x.size() - 2)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == xLine_x.get(xp + 1)
								&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - 2)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp + 1 <= xLine_x.size() && xp - 1 >= 0) {
//				System.out.println("fdsfsedf");
//				System.out.println(xLine_x.get(xp - 1));
//				System.out.println(xLine_x.get(xp + 1));
				if (cxGrid > xLine_x.get(xp - 1) && cxGrid < xLine_x.get(xp + 1)) {
					bishopmove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == xLine_x.get(xp - 1)
							&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp)) {
						eatPID = ChessLocation.get(i)[2];
					}
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == xLine_x.get(xp + 1)
							&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp - 2)) {
						eatPID_2 = ChessLocation.get(i)[2];
					}
				}
//				System.out.println("dsad" + eatPID_2);
				if (cxGrid == xLine_x.get(xp + 1) && cyGrid == xLine_y.get(xLine_x.size() - xp - 2)) {
//					System.out.println("movedddd");
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
						if (eatPID_2 <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID_2 > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						System.out.println("eat");
						ChessLocation.set(eatPID_2 - 1, newLocation);
						bishopmove = 1;
					}
				}
				if (cxGrid == xLine_x.get(xp - 1) && cyGrid == xLine_y.get(xLine_x.size() - xp)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}

			}

		} else if (cyGrid - yGrid == (cxGrid - xGrid)) {
			for (i = -20; i <= 20; i++) {
				if (yGrid == xGrid + i) {
					b = i;
				}
			}
//			System.out.println("bbb" + b);
			for (k = 0; k < ChessLocation.size(); k++) {
				if (ChessLocation.get(k)[1] == (ChessLocation.get(k)[0]) + b) {
//					System.out.println("+x" + ChessLocation.get(k)[0]);
//					System.out.println("+y" + ChessLocation.get(k)[1]);
					yLine_x.add(ChessLocation.get(k)[0]);
					yLine_y.add(ChessLocation.get(k)[1]);
				}
			}
			Collections.sort(yLine_x);
			Collections.sort(yLine_y);
			for (i = 0; i < yLine_x.size(); i++) {
				if (yLine_x.get(i) == xGrid) {
					xp = i;
				}
			}
//			System.out.println("xp" + xp);
//			System.out.println("ysize" + yLine_x.size());

			if (yLine_x.size() == 1) {
				bishopmove = 1;
			} else if (xp + 1 >= yLine_x.size()) {
//				System.out.println("move");
				if (cxGrid > yLine_x.get(xp - 1)) {
//					System.out.println("no");
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == yLine_x.get(xp - 1) && cyGrid == yLine_y.get(xp - 1)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == yLine_x.get(xp - 1)
								&& ChessLocation.get(i)[1] == yLine_y.get(xp - 1)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp - 1 < 0) {
//				System.out.println("no");
				if (cxGrid < yLine_x.get(xp + 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == yLine_x.get(xp + 1) && cyGrid == yLine_y.get(xp + 1)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == yLine_x.get(xp + 1)
								&& ChessLocation.get(i)[1] == yLine_y.get(xp + 1)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp + 1 <= yLine_x.size() && xp - 1 >= 0) {
//				System.out.println("fdsfsedf");
//				System.out.println(xLine_x.get(xp - 1));
//				System.out.println(xLine_x.get(xp + 1));
				if (cxGrid > yLine_x.get(xp - 1) && cxGrid < yLine_x.get(xp + 1)) {
					bishopmove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == yLine_x.get(xp - 1)
							&& ChessLocation.get(i)[1] == yLine_y.get(xp - 1)) {
						eatPID = ChessLocation.get(i)[2];
					}
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == yLine_x.get(xp + 1)
							&& ChessLocation.get(i)[1] == yLine_y.get(xp + 1)) {
						eatPID_2 = ChessLocation.get(i)[2];
					}
				}
//				System.out.println("dsad"+eatPID_2);
				if (cxGrid == yLine_x.get(xp + 1) && cyGrid == yLine_y.get(xp + 1)) {
//					System.out.println("movedddd");
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
						if (eatPID_2 <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID_2 > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						System.out.println("eat");
						ChessLocation.set(eatPID_2 - 1, newLocation);
						bishopmove = 1;
					}
				}
				if (cxGrid == yLine_x.get(xp - 1) && cyGrid == yLine_y.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
						if (eatPID <= 16) {
							xdeath = P2xdeath;
							ydeath = P2ydeath;
							P2xdeath++;
						} else if (eatPID > 16) {
							xdeath = P1xdeath;
							ydeath = P1ydeath;
							P1xdeath++;
						}
						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}

			}

		}
		xLine_x.clear();
		xLine_y.clear();
		yLine_y.clear();
		yLine_x.clear();

	}

	public void bishopcan() {
		bishopmove = 0;
		int b = 0, k, j, xp = 0, yp = 0, eatPID = 0, q, i, eatPID_2 = 0;
		if (cyGrid - yGrid == -(cxGrid - xGrid)) {
			for (i = -20; i <= 20; i++) {
				if (yGrid == -xGrid + i) {
					b = i;
				}
			}
//			System.out.println(b);
			for (k = 0; k < ChessLocation.size(); k++) {
				if (ChessLocation.get(k)[1] == (-1 * ChessLocation.get(k)[0]) + b) {
//					System.out.println("+x" + ChessLocation.get(k)[0]);
//					System.out.println("+y" + ChessLocation.get(k)[1]);
					xLine_x.add(ChessLocation.get(k)[0]);
					xLine_y.add(ChessLocation.get(k)[1]);
				}
			}
			Collections.sort(xLine_x);
			Collections.sort(xLine_y);
//			for (i = 0; i < xLine_y.size(); i++) {
//				System.out.println("x" + xLine_x.get(i));
//				System.out.println("y" + xLine_y.get(i));
//
//			}

//			for (q = 0; q < xLine_x.size(); q++) {
//				System.out.println("x" + xLine_x.get(q));
//
//				System.out.println("y" + xLine_y.get(xLine_x.size() - q - i));
//			}
			for (i = 0; i < xLine_x.size(); i++) {
				if (xLine_x.get(i) == xGrid) {
					xp = i;
				}
			}
//			System.out.println("xp" + xp);
//			System.out.println("xsize" + xLine_x.size());

			if (xLine_x.size() == 1) {
				bishopmove = 1;
			} else if (xp + 1 >= xLine_x.size()) {
//				System.out.println("move");
				if (cxGrid > xLine_x.get(xp - 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == xLine_x.get(xp - 1) && cyGrid == xLine_y.get(xLine_x.size() - xp)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == xLine_x.get(xp - 1)
								&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp - 1 < 0) {
				if (cxGrid < xLine_x.get(xp + 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == xLine_x.get(xp + 1) && cyGrid == xLine_y.get(xLine_x.size() - 2)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == xLine_x.get(xp + 1)
								&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - 2)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp + 1 <= xLine_x.size() && xp - 1 >= 0) {
//				System.out.println("fdsfsedf");
//				System.out.println(xLine_x.get(xp - 1));
//				System.out.println(xLine_x.get(xp + 1));
				if (cxGrid > xLine_x.get(xp - 1) && cxGrid < xLine_x.get(xp + 1)) {
					bishopmove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == xLine_x.get(xp - 1)
							&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp)) {
						eatPID = ChessLocation.get(i)[2];
					}
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == xLine_x.get(xp + 1)
							&& ChessLocation.get(i)[1] == xLine_y.get(xLine_x.size() - xp - 2)) {
						eatPID_2 = ChessLocation.get(i)[2];
					}
				}
//				System.out.println("dsad" + eatPID_2);
				if (cxGrid == xLine_x.get(xp + 1) && cyGrid == xLine_y.get(xLine_x.size() - xp - 2)) {
//					System.out.println("movedddd");
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
//						if(eatPID_2<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID_2>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						System.out.println("eat");
//						ChessLocation.set(eatPID_2 - 1, newLocation);
						bishopmove = 1;
					}
				}
				if (cxGrid == xLine_x.get(xp - 1) && cyGrid == xLine_y.get(xLine_x.size() - xp)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}

			}

		} else if (cyGrid - yGrid == (cxGrid - xGrid)) {
			for (i = -20; i <= 20; i++) {
				if (yGrid == xGrid + i) {
					b = i;
				}
			}
//			System.out.println("bbb" + b);
			for (k = 0; k < ChessLocation.size(); k++) {
				if (ChessLocation.get(k)[1] == (ChessLocation.get(k)[0]) + b) {
//					System.out.println("+x" + ChessLocation.get(k)[0]);
//					System.out.println("+y" + ChessLocation.get(k)[1]);
					yLine_x.add(ChessLocation.get(k)[0]);
					yLine_y.add(ChessLocation.get(k)[1]);
				}
			}
			Collections.sort(yLine_x);
			Collections.sort(yLine_y);
			for (i = 0; i < yLine_x.size(); i++) {
				if (yLine_x.get(i) == xGrid) {
					xp = i;
				}
			}
//			System.out.println("xp" + xp);
//			System.out.println("ysize" + yLine_x.size());

			if (yLine_x.size() == 1) {
				bishopmove = 1;
			} else if (xp + 1 >= yLine_x.size()) {
//				System.out.println("move");
				if (cxGrid > yLine_x.get(xp - 1)) {
//					System.out.println("no");
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == yLine_x.get(xp - 1) && cyGrid == yLine_y.get(xp - 1)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == yLine_x.get(xp - 1)
								&& ChessLocation.get(i)[1] == yLine_y.get(xp - 1)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp - 1 < 0) {
//				System.out.println("no");
				if (cxGrid < yLine_x.get(xp + 1)) {
//					System.out.println("move");
					bishopmove = 1;
				} else if (cxGrid == yLine_x.get(xp + 1) && cyGrid == yLine_y.get(xp + 1)) {
//					System.out.println("move");
					for (i = 0; i < ChessLocation.size(); i++) {
						if (ChessLocation.get(i)[0] == yLine_x.get(xp + 1)
								&& ChessLocation.get(i)[1] == yLine_y.get(xp + 1)) {
							eatPID = ChessLocation.get(i)[2];
						}
					}
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}
			} else if (xp + 1 <= yLine_x.size() && xp - 1 >= 0) {
//				System.out.println("fdsfsedf");
//				System.out.println(xLine_x.get(xp - 1));
//				System.out.println(xLine_x.get(xp + 1));
				if (cxGrid > yLine_x.get(xp - 1) && cxGrid < yLine_x.get(xp + 1)) {
					bishopmove = 1;
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == yLine_x.get(xp - 1)
							&& ChessLocation.get(i)[1] == yLine_y.get(xp - 1)) {
						eatPID = ChessLocation.get(i)[2];
					}
				}
				for (i = 0; i < ChessLocation.size(); i++) {
					if (ChessLocation.get(i)[0] == yLine_x.get(xp + 1)
							&& ChessLocation.get(i)[1] == yLine_y.get(xp + 1)) {
						eatPID_2 = ChessLocation.get(i)[2];
					}
				}
//				System.out.println("dsad"+eatPID_2);
				if (cxGrid == yLine_x.get(xp + 1) && cyGrid == yLine_y.get(xp + 1)) {
//					System.out.println("movedddd");
					if ((movePiece > 16 && eatPID_2 <= 16) || (movePiece <= 16 && eatPID_2 > 16)) {
//						if(eatPID_2<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID_2>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID_2 };
//						System.out.println("eat");
//						ChessLocation.set(eatPID_2 - 1, newLocation);
						bishopmove = 1;
					}
				}
				if (cxGrid == yLine_x.get(xp - 1) && cyGrid == yLine_y.get(xp - 1)) {
					if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//						if(eatPID<=16) {
//							xdeath=P2xdeath;
//							ydeath=P2ydeath;
//							P2xdeath++;
//						}
//						else if(eatPID>16) {
//							xdeath=P1xdeath;
//							ydeath=P1ydeath;
//							P1xdeath++;
//						}
//						Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//						System.out.println("eat");
//						ChessLocation.set(eatPID - 1, newLocation);
						bishopmove = 1;
					}
				}

			}

		}
		xLine_x.clear();
		xLine_y.clear();
		yLine_y.clear();
		yLine_x.clear();

	}

	public void knighthaveobstacle() {
		knightmove = 0;

		int ob = 0, i, eatPID = 0;
		ob = 0;
		if (cxGrid == xGrid - 2 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 1 && cyGrid == yGrid - 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 1 && cyGrid == yGrid + 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 2 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 2 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 1 && cyGrid == yGrid - 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 2 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 1 && cyGrid == yGrid + 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		}

	}

	public void knightcan() {
		knightmove = 0;

		int ob = 0, i, eatPID = 0;
		ob = 0;
		if (cxGrid == xGrid - 2 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 1 && cyGrid == yGrid - 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 1 && cyGrid == yGrid + 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 2 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 2 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid + 1 && cyGrid == yGrid - 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 2 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		} else if (cxGrid == xGrid - 1 && cyGrid == yGrid + 2) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				knightmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				knightmove = 1;
			}

		}

	}

	public void kinghaveobstacle() {
		kingmove = 0;
		int i, j, eatPID = 0, ob = 0;
		ob = 0;
		if (cxGrid == xGrid + 1 && cyGrid == yGrid) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid + 1 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid + 1 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
				if (eatPID <= 16) {
					xdeath = P2xdeath;
					ydeath = P2ydeath;
					P2xdeath++;
				} else if (eatPID > 16) {
					xdeath = P1xdeath;
					ydeath = P1ydeath;
					P1xdeath++;
				}
				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
	}

	public void kingcan() {
		kingmove = 0;
		int i, j, eatPID = 0, ob = 0;
		ob = 0;
		if (cxGrid == xGrid + 1 && cyGrid == yGrid) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid + 1 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid + 1 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid - 1 && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid && cyGrid == yGrid + 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
		if (cxGrid == xGrid && cyGrid == yGrid - 1) {
			for (i = 0; i < ChessLocation.size(); i++) {
				if (cxGrid == ChessLocation.get(i)[0]) {
					if (cyGrid == ChessLocation.get(i)[1]) {
						eatPID = ChessLocation.get(i)[2];
						ob++;
					}
				}
			}
			if (ob == 0) {
				kingmove = 1;
			} else if ((movePiece > 16 && eatPID <= 16) || (movePiece <= 16 && eatPID > 16)) {
//				if(eatPID<=16) {
//					xdeath=P2xdeath;
//					ydeath=P2ydeath;
//					P2xdeath++;
//				}
//				else if(eatPID>16) {
//					xdeath=P1xdeath;
//					ydeath=P1ydeath;
//					P1xdeath++;
//				}
//				Integer[] newLocation = new Integer[] { xdeath, ydeath, eatPID };
//				System.out.println("eat");
//				ChessLocation.set(eatPID - 1, newLocation);
				kingmove = 1;
			}
		}
	}
}
