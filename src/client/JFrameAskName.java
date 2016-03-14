package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Юлия on 14.03.2016.
 */
public class JFrameAskName extends JFrame implements KeyListener{
    JPanel panel;
    JTextField textField;
    private ArrayList<NameListener> listeners;

    public JFrameAskName(){
        super();
        listeners = new ArrayList<NameListener>();

        panel = new JPanel();
        panel.setOpaque(true);
        this.setContentPane(panel);

        JLabel question = new JLabel("Write your name");
        panel.add(question);

        textField = new JTextField();
        textField.addKeyListener(this);
        textField.setMinimumSize(new Dimension(300,20));
        panel.add(textField);

        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));


        this.setSize(300,70);
        this.setFocusable(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);





    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            this.setVisible(false);
            this.dispose();
            fireListeners(textField.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }



    public void addNameListener(NameListener listener) {
        listeners.add(listener);
    }

    public void removeNameListener(NameListener listener) {
        listeners.remove(listener);
    }

    private void fireListeners(String string) {
        for(NameListener listener : listeners) {
            listener.nameAppear(string);
        }
    }

    public interface NameListener{
        void nameAppear(String string);
        }
}
