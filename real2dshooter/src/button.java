import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class button extends JFrame implements ActionListener

{

    int distance = 0;
    JButton button1;
    button()
    {
        button1 = new JButton();
        button1.setBounds(0, 0, 100, 50);
        button1.addActionListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(100, 100);
        this.setVisible(true);
        this.add(button1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1)
        {
            
            distance = distance + 100;
        }
        
    }

}
