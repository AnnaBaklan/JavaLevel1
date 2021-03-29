package Lesson4;

import javax.swing.*;

public class ErrorWindow extends JFrame {
    public ErrorWindow (String errorMessage) {
        setBounds(350, 350, 400, 200);
        setTitle("Error!");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JLabel errorLable = new JLabel(errorMessage);
        errorLable.setHorizontalAlignment(SwingConstants.CENTER);
        add (errorLable);
        setVisible(true);
    }
}
