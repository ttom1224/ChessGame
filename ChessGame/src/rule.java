import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rule extends JFrame{
	public rule() {
		setSize(365,735);
		ImageIcon rulepic = new ImageIcon("rule.png");
		JLabel rulePic = new JLabel(rulepic);
		add(rulePic);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		rulePic.setBounds(0,0,727,1500);



	}
}
