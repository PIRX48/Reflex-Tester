package test;



import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;



import javax.swing.JButton;





import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class ReflexTester extends JFrame {



    private JButton[] buttons;

    private long startTime;
    private JTextField wynik;
    private ArrayList<Long> czasy = new ArrayList<Long>();
  


    public ReflexTester() {

        setTitle("Reflex Tester");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gra = new JPanel(); 
        gra.setLayout(new GridLayout(3, 3));
        getContentPane().add(gra);

        wynik = new JTextField();
        getContentPane().add(wynik,BorderLayout.SOUTH);

        buttons = new JButton[9];



        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("Button " + (i + 1));
            buttons[i].setText(null);

            buttons[i].addActionListener(new ButtonClickListener());

            gra.add(buttons[i]);

        }


        startTest();

        pack();

        setLocationRelativeTo(null);

        setVisible(true);

    }



    private void startTest() {

    	for (int i = 0; i < 9; i++) {

            buttons[i].setText(null);

        }
    	
        Random random = new Random();

        int buttonToChange = random.nextInt(9);

        buttons[buttonToChange].setText("Click me!");

        startTime = System.currentTimeMillis();

    }



    private void endTest() {

        long endTime = System.currentTimeMillis();

        long reactionTime = endTime - startTime;

        long sum=0;
        czasy.add((Long)reactionTime);
        for (int i = 0; i < czasy.size(); i++) {
            sum += czasy.get(i);
        }
        //JOptionPane.showMessageDialog(this, "Your reaction time: " + reactionTime + " milliseconds");
        System.out.println( "Your reaction time: " + reactionTime + " milliseconds");
        wynik.setText("Aktualny wynik: " + reactionTime + "ms " + "Sredni czas: " +sum/czasy.size()+"ms");
        

    }



    private class ButtonClickListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
        	if(((JButton) e.getSource()).getText()!=null)
        	{
	            endTest();
	
	            
	            ((JButton) e.getSource()).setText(null);
	
	            startTest();
	
	        }
        }

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new ReflexTester());

    }

}