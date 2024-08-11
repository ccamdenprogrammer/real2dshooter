import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
public class textBoxFrame extends JFrame implements ActionListener
{
    JButton button;
    JTextField textField;

    textBoxFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        button = new JButton("submit");
        this.add(button);
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        button.addActionListener(this);
        this.add(textField);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==button)
        {
            textField.getText();
        }

    }


}
