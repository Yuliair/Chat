package client;

import main.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Created by Юлия on 03.03.2016.
 */
//deal with close
public class JFrameChat extends JFrame implements KeyListener {

    JTextField textField;
    TextArea textAreaMessages;

    JPanel panel;
    DefaultListModel<String> names;
    Client client;
    public JFrameChat(Client client){
        super();
        this.client  = client;
        panel = new JPanel();
        panel.setOpaque(true);
        this.setContentPane(panel);

        this.setSize(600, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        addElementsToPanel();
    }

    public void addElementsToPanel(){

        Container pane = this.getContentPane();

        textField = new JTextField(20);
        textField.setVisible(true);
        textField.addKeyListener(this);
        pane.add(textField);

        names = new DefaultListModel<>();
        JList textAreaNiksList = new JList(names);
        JScrollPane textAreaNiks = new JScrollPane(textAreaNiksList);

        JLabel string = new JLabel("type your message");
        textField.setPreferredSize(new Dimension(100,20));
        textField.setMaximumSize(new Dimension(2000,20));
        string.setPreferredSize(new Dimension(100,20));
        string.setForeground(Color.blue);
        string.setBackground(Color.cyan);



        textAreaMessages = new TextArea();
        textAreaMessages.append("Chat started");
        textAreaMessages.setEditable(false);
        textAreaMessages.setForeground(Color.BLACK);
        textAreaMessages.setBackground(Color.ORANGE);


        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(textAreaMessages)
                                .addComponent(textAreaNiks))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(string)
                                .addComponent(textField))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(textAreaMessages)
                                .addComponent(textAreaNiks))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(string)
                                .addComponent(textField))

        );

        //layout.setAutoCreateGaps(true);
      //  layout.setAutoCreateContainerGaps(true);

    }


    public void messageComes(String message){

        if (message.endsWith(Const.newPerson)){
            String name = message.substring(0,message.length()-Const.newPerson.length());
            names.addElement(name);
        }
        else if (message.endsWith(Const.personLeft)){
            String name = message.substring(0,message.length()-Const.personLeft.length());
            names.removeElement(name);
        }
        textAreaMessages.append("\n"+message);
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            client.sendMessage(textField.getText());
            if(textField.getText().equals("exit")){
                this.setVisible(false);
                this.dispose();
            }
            else {
                textField.setText("");
                this.repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
