import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class UserInput {

	
	public UserInput(){
		
		createUI();
		while(true){
			if(n != 0) break;
		}
	}
	
	
	public int getN(){
		
		return n;
	}
	
	
	private void createUI(){
		
		window = new JFrame();
		window.setSize(200, 110);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter N :");
		window.add(panel);
		panel.add(label);
		input = new JTextField(10);
		panel.add(input);
		button = new JButton("OK");
		panel.add(button);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (dimension.height - 110)/2;
	    int screenWidth = (dimension.width - 200)/2;
		window.setLocation(screenWidth, screenHeight);
		window.setVisible(true);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				n = Integer.parseInt(input.getText());
				if(n > 0) window.setVisible(false);
			}
		});
		input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				n = Integer.parseInt(input.getText());
				if(n > 0) window.setVisible(false);			
			}
		});
	}
	
	
	
	
	
	private JFrame window;
	private JTextField input;
	private JButton button;
	private int n = 0;
}
