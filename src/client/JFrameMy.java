package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Юлия on 03.03.2016.
 */
public class JFrameMy extends JFrame implements  KeyListener {

    JTextField textField;
    int typeMessageWidth=10;

    JPanel panel;

    public JFrameMy(){
        super();
        panel = new JPanel();
        panel.setOpaque(true);
        this.setContentPane(panel);

        Container pane = this.getContentPane();
        pane.setLayout(null);


        //crap
//        JMenu menu = new JMenu("Menu");
//
//        menu.setMnemonic(MouseEvent.MOUSE_CLICKED);
//        JMenuBar menuBar = new JMenuBar();
//        menuBar.setOpaque(true);
//        menuBar.add(menu);
//        menuBar.setBackground(new Color(150,200,150));
//        menuBar.setPreferredSize(new Dimension(200,30));
//
//        JMenuItem menuItem1 = new JMenuItem("New Game", MouseEvent.MOUSE_CLICKED);
//        menuItem1.setActionCommand("N");
//        menuItem1.addActionListener(this);
//        menu.add(menuItem1);
//        JMenuItem menuItem2 = new JMenuItem("Old Game", MouseEvent.MOUSE_CLICKED);
//        menuItem2.setActionCommand("O");
//        menuItem2.addActionListener(this);
//        menu.add(menuItem2);





      // this.setJMenuBar(menuBar);
        //this.add(panel);
        this.setContentPane(panel);

        this.setSize(600, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addElementsToPanel(){

        Container pane = this.getContentPane();


        //JButton b1 = new JButton("one");

       // pane.add(b1);

        Insets insets = pane.getInsets();
       // Dimension size = b1.getPreferredSize();
      //  b1.setBounds(25 + insets.left, 5 + insets.top,
       //         size.width, size.height);

        textField = new JTextField(20);

      //  textField.setLocation(typeMessageWidth,30);
        textField.setVisible(true);
        textField.addKeyListener(this);

        pane.add(textField);

        Dimension sizeText = textField.getPreferredSize();
        //textField.setBounds(typeMessageWidth+ insets.left,  panel.getHeight()-sizeText.height,
        //        panel.getWidth() - typeMessageWidth,sizeText.height);

        TextArea textAreaMessages = new TextArea();
        textAreaMessages.setEditable(false);

        TextArea textAreaNiks = new TextArea();
        textAreaNiks.setEditable(false);
        JLabel string = new JLabel("type your message");
        textField.setPreferredSize(new Dimension(100,20));
        string.setPreferredSize(new Dimension(100,20));
        //string.setSize(100,30);
        //textField.setSize(200,30);
        string.setForeground(Color.blue);
        string.setBackground(Color.cyan);


        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        textAreaMessages.setText("Dkdjfvdfjjdfv kdfvb dfvb  dkfjvn");
        textAreaMessages.append("\n dfjvhbdfjhvb dfvhbdfhbv ");
        textAreaMessages.setForeground(Color.BLACK);
        textAreaMessages.setBackground(Color.ORANGE);

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


    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println(textField.getText());
            textField.setText("");
            this.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
