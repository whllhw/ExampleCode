package nlp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame implements ActionListener {
    private TextArea display = new TextArea(5, 40);
    private TextArea inputTextArea = new TextArea(5, 40);
    private Button button = new Button("ok");

    public Windows() {
        setTitle("分词程序");
        setLayout(new FlowLayout());
        button.addActionListener(this);
        add(button);
        add(inputTextArea);
        add(display);
        setSize(400, 400);
        setLocationByPlatform(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Windows();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("ok");
        }
    }
}
