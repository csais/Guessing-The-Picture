import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main extends Thread {

	JPanel pnl = new JPanel();
	JTextField input = new JTextField(38);
	JTextArea output = new JTextArea(6, 37);
	JScrollPane pane = new JScrollPane(output);
	String inputString;

	public void run() {

		getInputFromUser();
	}

	public void getInputFromUser() {
		JTextField textField = new JTextField("");
		textField.setColumns(50);

		textField.setVisible(true);

		JDialog jd = new JDialog();
		jd.setTitle("What animal is it?");
		jd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jd.setSize(300, 75);
		jd.add(textField);
		jd.requestFocus();
		jd.setVisible(true);

		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				Game.guess = textField.getText().stripLeading().stripTrailing().toLowerCase();
				textField.setText("");
				System.out.println(Game.guess);
			}
		});
	}

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		Game game = new Game();
		int width = game.getScreenWidth();
		int height = game.getScreenHeight();
		obj.setBounds(0, 0, width, height);
		obj.setTitle("Guess the Animal!");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
		Main obj2 = new Main();
		obj2.start();

	}
}
