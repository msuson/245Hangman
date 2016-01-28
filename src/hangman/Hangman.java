package hangman;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.awt.font.TextAttribute;
//import java.util.Map;
import javax.swing.*;
import java.util.Random;

public class Hangman {
    
    private final int HEIGHT = 400;
    private final int WIDTH = 600;
    private JFrame startScreen;
    private JFrame menuScreen;
    private JFrame scoresScreen;
    private JFrame creditsScreen;
    private JFrame gameScreen;
    private JFrame endScreen;
    private JButton backButton;
    private JButton skipButton;
    final int[] score = {100};
    
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
                        scoresScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                        creditsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JLabel hangmanLabel = new JLabel("HANGMAN");
        JLabel dateTime = new JLabel();
        final DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:mm:ss");
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                dateTime.setText(dateFormat.format(now.getTime()));
            }
        });
        timer.start();
        
        String[] wordBank = {"ABSTRACT", "CEMETARY", "NURSE", "PHARMACY", "CLIMBING"};
        String wordToGuess = wordBank[new Random().nextInt(5)];
        
        skipButton = new JButton("Skip");
        skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score[0] = 0;
                endGame();
            }
        });
        
        JPanel blankPanel = new JPanel();
        JLabel[] blankSpace = new JLabel[wordToGuess.length()];
        for(int i = 0; i < blankSpace.length; i++) {
            blankSpace[i] = new JLabel(" __ ");
            blankSpace[i].setFont(new Font(blankSpace[i].getFont().getFontName(), Font.BOLD, 28));
            blankPanel.add(blankSpace[i]);
        }
        
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        JLabel scoreLabel = new JLabel("Score: " + Integer.toString(score[0]));
        
        final int[] correctGuesses = {0};
        final int[] wrongGuesses = {0};
        JButton[] letters = new JButton[26];
        char alphabet = 'A';
        for(int i = 0; i < letters.length; i++) {
            letters[i] = new JButton(Character.toString(alphabet++));
            letters[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(wrongGuesses[0] < 6 && correctGuesses[0] < wordToGuess.length()) {
                        String guess = e.getActionCommand();
                        for(int i = 0; i < wordToGuess.length(); i++) {
                            String currentLetter = Character.toString(wordToGuess.charAt(i));
                            if(currentLetter.equals(guess)) {
                                correctGuesses[0]++;
                                blankSpace[i].setText("  " + guess + "  ");
                                blankSpace[i].setFont(new Font(blankSpace[i].getFont().getFontName(), Font.BOLD, 28));
                                ((JButton)e.getSource()).setEnabled(false);
                            }
                            else if(i == wordToGuess.length()-1 && ((JButton)e.getSource()).isEnabled()) {
                                wrongGuesses[0]++;
                                score[0] -= 10;
                                scoreLabel.setText("Score: " + Integer.toString(score[0]));
                                ((JButton)e.getSource()).setEnabled(false);
                            }
                        }
                    }
                    if(correctGuesses[0] == wordToGuess.length() || wrongGuesses[0] == 6)
                        endGame();
                }
            });
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
        JPanel testPanel = new JPanel(new BorderLayout());
        scorePanel.add(dateTime);
        scorePanel.add(scoreLabel);
        scorePanel.add(skipButton);
        testPanel.add(hangmanLabel, BorderLayout.WEST);
        testPanel.add(scorePanel, BorderLayout.EAST);
        gamePanel.add(testPanel, BorderLayout.NORTH);
        gamePanel.add(blankPanel, BorderLayout.CENTER);
        gamePanel.add(keys, BorderLayout.SOUTH);
        gameScreen.add(gamePanel);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void endGame() {
        Timer endDelay = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameScreen.setVisible(false);
                endScreen = new JFrame();
                endScreen.setSize(WIDTH, HEIGHT);
                endScreen.setLocationRelativeTo(null);
                JPanel endPanel = new JPanel(new GridBagLayout());
                GridBagConstraints c1 = new GridBagConstraints();
                JLabel scoreTitle = new JLabel("SCORE");
                c1.gridy = 0;
                endPanel.add(scoreTitle, c1);
                c1.gridy = 1;
                JLabel endScore = new JLabel(Integer.toString(score[0]));
                endPanel.add(endScore, c1);
                c1.gridy = 2;
                JButton endButton = new JButton("End");
                endPanel.add(endButton, c1);
                endScreen.add(endPanel);
                endScreen.setVisible(true);
                endScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                endButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameScreen.dispose();
                        endScreen.setVisible(false);
                        endScreen.dispose();
                        menuScreen.setVisible(true);
                        score[0] = 100;
                    }
                });
            }
        });
        endDelay.setRepeats(false);
        endDelay.start();
    }
    
}