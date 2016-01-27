package hangman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hangman {
    
    private final int HEIGHT = 400;
    private final int WIDTH = 600;
    private JFrame startScreen;
    private JFrame menuScreen;
    private JFrame scoresScreen;
    private JFrame creditsScreen;
    private JFrame gameScreen;
    private JButton backButton;
    
    public Hangman() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Hangman newGame = new Hangman();
    }
    
    private void prepareGUI() {
        startScreen = new JFrame();
        startScreen.setSize(WIDTH, HEIGHT);
        startScreen.setLocationRelativeTo(null);
        JPanel introPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("CS 245 Quarter Project");
        JLabel author = new JLabel("A Savage Sloth Production");
        introPanel.add(title, BorderLayout.NORTH);
        introPanel.add(author, BorderLayout.SOUTH);
        startScreen.add(introPanel);
        startScreen.setVisible(true);
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startScreen.setVisible(false);
                startScreen.dispose();
                
                menuScreen = new JFrame();
                menuScreen.setSize(WIDTH, HEIGHT);
                menuScreen.setLocationRelativeTo(null);
                JPanel p = new JPanel(new GridBagLayout());
                backButton = new JButton("Back");
                JButton playButton = new JButton("Play");
                playButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameScreen = new JFrame();
                        gameScreen.setSize(WIDTH, HEIGHT);
                        gameScreen.setLocationRelativeTo(null);
                        gameScreen.setVisible(true);
                        menuScreen.setVisible(false);
                        playGame();
                    }
                });
                JButton highScoresButton = new JButton("Highscores");
                highScoresButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        scoresScreen = new JFrame();
                        scoresScreen.setSize(WIDTH, HEIGHT);
                        scoresScreen.setLocationRelativeTo(null);
                        JPanel backPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        JLabel title = new JLabel("HIGHSCORES");
                        JLabel score1 = new JLabel("ABC.....00000");
                        JLabel score2 = new JLabel("ABC.....00000");
                        JLabel score3 = new JLabel("ABC.....00000");
                        JLabel score4 = new JLabel("ABC.....00000");
                        JLabel score5 = new JLabel("ABC.....00000");
                        c.gridy = 0;
                        backPanel.add(title, c);
                        c.gridy = 1;
                        backPanel.add(score1, c);
                        c.gridy = 2;
                        backPanel.add(score2, c);
                        c.gridy = 3;
                        backPanel.add(score3, c);
                        c.gridy = 4;
                        backPanel.add(score4, c);
                        c.gridy = 5;
                        backPanel.add(score5, c);
                        backButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                scoresScreen.setVisible(false);
                                menuScreen.setVisible(true);
                            }
                        });
                        c.gridy = 6;
                        backPanel.add(backButton, c);
                        scoresScreen.add(backPanel);
                        scoresScreen.setVisible(true);
                        menuScreen.setVisible(false);
                    }
                });
                JButton creditsButton = new JButton("Credits");
                creditsButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        creditsScreen = new JFrame();
                        creditsScreen.setSize(WIDTH, HEIGHT);
                        creditsScreen.setLocationRelativeTo(null);
                        JPanel backPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        JLabel title = new JLabel("CREDITS");
                        JLabel isolde = new JLabel("Isolde Alfaro, 009624719");
                        JLabel marco = new JLabel("Marco Suson, 009532432");
                        backButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                creditsScreen.setVisible(false);
                                menuScreen.setVisible(true);
                            }
                        });
                        c.gridy = 1;
                        backPanel.add(title, c);
                        c.gridy = 2;
                        backPanel.add(isolde, c);
                        c.gridy = 3;
                        backPanel.add(marco, c);
                        c.gridy = 4;
                        backPanel.add(backButton, c);
                        creditsScreen.add(backPanel);
                        creditsScreen.setVisible(true);
                        menuScreen.setVisible(false);
                    }
                });
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(5, 5, 5, 5);
                c.gridy = 1;
                p.add(playButton, c);
                c.gridy = 2;
                p.add(highScoresButton, c);
                c.gridy = 3;
                p.add(creditsButton, c);
                menuScreen.add(p);
                menuScreen.setVisible(true);
                menuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void playGame() {
        JButton[] letters = new JButton[26];
        char alphabet = 'A';
        for(int i = 0; i < letters.length; i++) {
            letters[i] = new JButton(Character.toString(alphabet++));
        }
        JPanel gamePanel = new JPanel(new BorderLayout());
        JPanel keys = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = (new Insets(1, 1, 1, 1));
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < letters.length/2; j++) {
                c.gridy = i;
                if(i == 0)
                    keys.add(letters[j], c);
                else
                    keys.add(letters[j+13], c);
            }
        }
        gamePanel.add(keys, BorderLayout.SOUTH);
        gameScreen.add(gamePanel);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}