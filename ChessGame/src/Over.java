import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Over extends JFrame {
	public Over() {
		this.setBounds(500, 300, 300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		JLabel GameOver = new JLabel("Game Over");
        GameOver.setHorizontalAlignment(SwingConstants.CENTER);
        GameOver.setFont(new Font("Calibri", Font.BOLD, 28));
        GameOver.setBounds(0,20, 300, 50);
        JLabel win = new JLabel();
        if(ChessGUI.p1Point>ChessGUI.p2Point) {
        	win.setText("Playr 1 WIN");
        }
        else {
        	win.setText("Player 2 WIN");
        }
        JLabel p1p = new JLabel();
        JLabel p2p = new JLabel();
        p1p.setText("P1 Point:"+Integer.toString(ChessGUI.p1Point));
        p2p.setText("P2 Point:"+Integer.toString(ChessGUI.p2Point));
        p1p.setBounds(0,70,300,50);
        p2p.setBounds(0,90,300,50);
        p1p.setHorizontalAlignment(SwingConstants.CENTER);
        p2p.setHorizontalAlignment(SwingConstants.CENTER);
        win.setHorizontalAlignment(SwingConstants.CENTER);
        p1p.setFont(new Font("Calibri", Font.BOLD, 20));
        p2p.setFont(new Font("Calibri", Font.BOLD, 20));
        win.setFont(new Font("Calibri", Font.BOLD, 20));
        win.setBounds(0,150,300,50);
        this.getContentPane().add(GameOver);
        this.getContentPane().add(win);
        this.getContentPane().add(p1p);
        this.getContentPane().add(p2p);
        JButton btnConfirm = new JButton("Exit");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        btnConfirm.setBounds(75, 200, 150, 30);
        this.getContentPane().add(btnConfirm);
	}
}
