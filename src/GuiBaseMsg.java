import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GuiBaseMsg extends JFrame implements ActionListener{
    JButton button;                 // button
    GuiMainMenu menu;               // main menu object
    JLabel headerLabel;
    JLabel msgLabel;                // header and message label
    String myFont = "Courier";

    // GuiBaseMsg Constructor
    GuiBaseMsg(GuiMainMenu mainMenu)
    {
        menu = mainMenu;        

        // if this window is closed, return to main menu
        final GuiBaseMsg guiBaseMsg = this;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.returnToMainMenu(guiBaseMsg);
            }
        });

        // set background and size
        Color c1 = new Color(39, 150, 81);
        this.getContentPane().setBackground(c1);
        this.setLayout(null);
        this.setSize(500, 320);

        // back button
        button = new JButton();
        button.setBounds(175, 200, 150, 40);
        button.setFont(new Font(myFont, Font.PLAIN, 20));
        button.addActionListener(this);

        // header label
        headerLabel = new JLabel();
        headerLabel.setFont(new Font(myFont, Font.BOLD, 20));
        headerLabel.setBounds(145, 100, 300, 30);

        // message label
        msgLabel = new JLabel();
        msgLabel.setFont(new Font(myFont, Font.PLAIN, 18));
        msgLabel.setBounds(60, 130, 400, 30);

        // add components
        this.add(headerLabel);
        this.add(msgLabel);
        this.add(button);

        // set resizable to false
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {                  // if button clicked
            menu.returnToMainMenu(this);      // return to main menu
        }
    }
}
