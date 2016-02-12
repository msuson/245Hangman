/*****************************************************************
 * 
 * file: Hangman.java
 * author: Marco Suson, Isolde Alfaro
 * class: CS 245 - Programming Graphical User Interfaces
 * 
 * assignment: Quarter Project Checkpoint V.1.0
 * date last modified: 2/9/16
 * 
 * purpose: An introductory program to demonstrate GUI programming
 * through the implementation of a simple point and click game.
 * 
 *****************************************************************/

package hangman;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private JFrame colorGameScreen;
    private JFrame endScreen;
    private JButton backButton;
    private JButton skipButton;
    final int[] score = {100};
    
    //method: Hangman
    //purpose: constructor for the Hangman class.
    public Hangman() {
        prepareGUI();
    }

    //method: main
    //purpose: start up the UI so the user can play the game.
    public static void main(String[] args) {
        Hangman newGame = new Hangman();
    }
    
    //method: prepareGUI
    //purpose: this method prepares the various UI components that
    //need to be displayed throughout the game.
    private void prepareGUI() {
        startScreen = new JFrame();
        startScreen.setSize(WIDTH, HEIGHT);
        startScreen.setLocationRelativeTo(null);
        JPanel introPanel = new JPanel(new BorderLayout());
        introPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        JLabel title = new JLabel("CS 245 Quarter Project");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 35));
        JLabel author = new JLabel("A Savage Sloth Production");
        author.setHorizontalAlignment(JLabel.CENTER);
        author.setFont(new Font(author.getFont().getFontName(), Font.BOLD, 18));
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
                JPanel p = new JPanel(new GridLayout(3, 1, 1, 5));
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
                        JPanel highScores = new JPanel(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        JPanel backPanel = new JPanel(new BorderLayout());
                        JLabel title = new JLabel("HIGHSCORES");
                        title.setHorizontalAlignment(JLabel.CENTER);
                        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 28));
                        backPanel.add(title, BorderLayout.NORTH);
                        JLabel score1 = new JLabel("ABC.....00000");
                        score1.setFont(new Font(score1.getFont().getFontName(), Font.BOLD, 18));
                        JLabel score2 = new JLabel("ABC.....00000");
                        score2.setFont(new Font(score2.getFont().getFontName(), Font.BOLD, 18));
                        JLabel score3 = new JLabel("ABC.....00000");
                        score3.setFont(new Font(score3.getFont().getFontName(), Font.BOLD, 18));
                        JLabel score4 = new JLabel("ABC.....00000");
                        score4.setFont(new Font(score4.getFont().getFontName(), Font.BOLD, 18));
                        JLabel score5 = new JLabel("ABC.....00000");
                        score5.setFont(new Font(score5.getFont().getFontName(), Font.BOLD, 18));
                        c.gridy = 1;
                        highScores.add(score1, c);
                        c.gridy = 2;
                        highScores.add(score2, c);
                        c.gridy = 3;
                        highScores.add(score3, c);
                        c.gridy = 4;
                        highScores.add(score4, c);
                        c.gridy = 5;
                        highScores.add(score5, c);
                        backButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                scoresScreen.setVisible(false);
                                menuScreen.setVisible(true);
                            }
                        });
                        JPanel backButtonPanel = new JPanel(new GridBagLayout());
                        backButtonPanel.add(backButton);
                        backPanel.add(backButtonPanel, BorderLayout.SOUTH);
                        backPanel.add(highScores, BorderLayout.CENTER);
                        backPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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
                        JPanel authorsPanel = new JPanel(new GridBagLayout());
                        GridBagConstraints c = new GridBagConstraints();
                        JPanel backPanel = new JPanel(new BorderLayout());
                        JLabel title = new JLabel("CREDITS");
                        title.setHorizontalAlignment(JLabel.CENTER);
                        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 28));
                        JLabel isolde = new JLabel("Isolde Alfaro, 009624719");
                        isolde.setFont(new Font(isolde.getFont().getFontName(), Font.BOLD, 18));
                        JLabel marco = new JLabel("Marco Suson, 009532432");
                        marco.setFont(new Font(marco.getFont().getFontName(), Font.BOLD, 18));
                        backButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                creditsScreen.setVisible(false);
                                menuScreen.setVisible(true);
                            }
                        });
                        backPanel.add(title, BorderLayout.NORTH);
                        c.gridy = 1;
                        authorsPanel.add(isolde, c);
                        c.gridy = 2;
                        authorsPanel.add(marco, c);
                        backPanel.add(authorsPanel, BorderLayout.CENTER);
                        JPanel backButtonPanel = new JPanel(new GridBagLayout());
                        backButtonPanel.add(backButton);
                        backPanel.add(backButtonPanel, BorderLayout.SOUTH);
                        backPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                        creditsScreen.add(backPanel);
                        creditsScreen.setVisible(true);
                        creditsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        menuScreen.setVisible(false);
                    }
                });
                p.add(playButton);
                p.add(highScoresButton);
                p.add(creditsButton);
                JPanel buttonsPanel = new JPanel(new BorderLayout());
                buttonsPanel.add(p, BorderLayout.EAST);
                ImageIcon graphic = new ImageIcon(getClass().getResource("sloth.jpg"));
                JLabel image = new JLabel(graphic);
                JPanel formPane = new JPanel(new BorderLayout());
                formPane.add(image, BorderLayout.CENTER);
                formPane.add(buttonsPanel, BorderLayout.SOUTH);
                formPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                menuScreen.add(formPane);
                menuScreen.setVisible(true);
                menuScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    //method: playGame
    //purpose: this method prepares and displays the UI components that need
    //to be shown while the game is ongoing.
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
                playColors();
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
        final ImageIcon[] graphics = {new ImageIcon(getClass().getResource("Guillotine.png")),
                                      new ImageIcon(getClass().getResource("Head.png")),
                                      new ImageIcon(getClass().getResource("Body.png")),
                                      new ImageIcon(getClass().getResource("RightArm.png")),
                                      new ImageIcon(getClass().getResource("LeftArm.png")),
                                      new ImageIcon(getClass().getResource("RightLeg.png")),
                                      new ImageIcon(getClass().getResource("LeftLeg.png"))};
        ImageIcon graphic = graphics[wrongGuesses[0]];
        JLabel hangmanGraphic = new JLabel(graphic);
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
                                blankSpace[i].setFont(new Font(blankSpace[i].getFont().getFontName(), Font.BOLD, 20));
                                ((JButton)e.getSource()).setEnabled(false);
                            }
                            else if(i == wordToGuess.length()-1 && ((JButton)e.getSource()).isEnabled()) {
                                hangmanGraphic.setIcon(graphics[++wrongGuesses[0]]);
                                score[0] -= 10;
                                scoreLabel.setText("Score: " + Integer.toString(score[0]));
                                ((JButton)e.getSource()).setEnabled(false);
                            }
                        }
                    }
                    if(correctGuesses[0] == wordToGuess.length() || wrongGuesses[0] == 6) {
                        gameScreen.setVisible(false);
                        playColors();
                    }
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
        JPanel graphicsPanel = new JPanel();
        graphicsPanel.setLayout(new BoxLayout(graphicsPanel, BoxLayout.Y_AXIS));
        JPanel hangGraphicPane = new JPanel();
        hangGraphicPane.add(hangmanGraphic);
        graphicsPanel.add(hangGraphicPane);
        graphicsPanel.add(blankPanel);
        JPanel topBarPanel = new JPanel(new BorderLayout());
        scorePanel.add(dateTime);
        scorePanel.add(scoreLabel);
        scorePanel.add(skipButton);
        topBarPanel.add(hangmanLabel, BorderLayout.WEST);
        topBarPanel.add(scorePanel, BorderLayout.EAST);
        topBarPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        gamePanel.add(topBarPanel, BorderLayout.NORTH);
        gamePanel.add(graphicsPanel, BorderLayout.CENTER);
        gamePanel.add(keys, BorderLayout.SOUTH);
        gameScreen.add(gamePanel);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //method: playColors
    //purpose: this method prepares and displays the screen that is
    //shown to the player when they play the color game.
    public void playColors() {
        colorGameScreen = new JFrame();
        colorGameScreen.setSize(WIDTH, HEIGHT);
        colorGameScreen.setLocationRelativeTo(null);
        final int[] round = {0};
        final ImageIcon[] colorImg =
            {new ImageIcon(getClass().getResource("Red_Button.png")),
             new ImageIcon(getClass().getResource("Yellow_Button.png")),
             new ImageIcon(getClass().getResource("Green_Button.png")),
             new ImageIcon(getClass().getResource("Blue_Button.png")),
             new ImageIcon(getClass().getResource("Purple_Button.png"))};
        final ImageIcon[] mouseOverImg =
            {new ImageIcon(getClass().getResource("Red_Button_Mouseover.png")),
             new ImageIcon(getClass().getResource("Yellow_Button_Mouseover.png")),
             new ImageIcon(getClass().getResource("Green_Button_Mouseover.png")),
             new ImageIcon(getClass().getResource("Blue_Button_Mouseover.png")),
             new ImageIcon(getClass().getResource("Purple_Button_Mouseover.png")),
            };
        final String[] buttonAction = {"RED", "YELLOW", "GREEN", "BLUE", "PURPLE"};
        final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(128,0,255)};
        Random r = new Random();
        JLabel text = new JLabel(buttonAction[r.nextInt(colors.length)]);
        text.setFont(new Font(text.getFont().getFontName(), Font.BOLD, 28));
        final int[] correctAns  = {r.nextInt(colors.length)};
        while(text.getText().equals(buttonAction[correctAns[0]])) {
            correctAns[0] = r.nextInt(colors.length);
        }
        text.setForeground(colors[correctAns[0]]);
        JLabel scoreLabel = new JLabel("Score: " + score[0]);
        final boolean[] picked = new boolean[5];
        final JButton[] button = {new JButton(), new JButton(), new JButton(), new JButton(), new JButton()};
        setColorButtons(button, colorImg, mouseOverImg, buttonAction, picked);
        for(int i = 0; i < button.length; i++) {
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(round[0]++ < 5) {
                        if(((JButton)e.getSource()).getActionCommand().equals(buttonAction[correctAns[0]]))
                            scoreLabel.setText("Score: " + (score[0] += 100));
                        correctAns[0] = r.nextInt(colors.length);
                        setColorButtons(button, colorImg, mouseOverImg, buttonAction, picked);
                        text.setText(buttonAction[r.nextInt(colors.length)]);
                        while(text.getText().equals(buttonAction[correctAns[0]])) {
                            correctAns[0] = r.nextInt(colors.length);
                        }
                        text.setForeground(colors[correctAns[0]]);
                    }
                    if(round[0] == 5) {
                        colorGameScreen.setVisible(false);
                        endGame();
                    }
                }
            });
        }
        JLabel dateTime = new JLabel();
        final DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy  HH:mm:ss");
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                dateTime.setText(dateFormat.format(now.getTime()));
            }
        });
        timer.start();
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.add(dateTime);
        scorePanel.add(scoreLabel);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(scorePanel, BorderLayout.EAST);
        JPanel contents = new JPanel(new BorderLayout());
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        JPanel textPanel = new JPanel();
        textPanel.add(text);
        textPanel.setBorder(BorderFactory.createEmptyBorder(0,125,0,0));
        topPanel.add(textPanel, BorderLayout.CENTER);
        JPanel centerButtonPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(button[1], BorderLayout.NORTH);
        leftPanel.add(button[2], BorderLayout.SOUTH);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(15,45,15,15));
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(button[3], BorderLayout.NORTH);
        rightPanel.add(button[4], BorderLayout.SOUTH);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,45));
        centerButtonPanel.add(button[0]);
        center.add(centerButtonPanel);
        contents.add(center, BorderLayout.CENTER);
        contents.add(topPanel, BorderLayout.NORTH);
        contents.add(leftPanel, BorderLayout.WEST);
        contents.add(rightPanel, BorderLayout.EAST);
        colorGameScreen.add(contents);
        colorGameScreen.setVisible(true);
        colorGameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //method: setColorButtons
    //purpose: this method randomly changes the colors of each
    //button on the screen for the color game.
    public void setColorButtons(JButton[] buttons, ImageIcon[] icons, ImageIcon[] moIcons, String[] actions, boolean[] chosen) {
        Random rand = new Random();
        int index = rand.nextInt(5);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setIcon(icons[index]);
            buttons[i].setActionCommand(actions[index]);
            buttons[i].setRolloverIcon(moIcons[index]);
            buttons[index].setBorder(BorderFactory.createEmptyBorder());
            buttons[index].setContentAreaFilled(false);
            chosen[index] = true;
            if(i < buttons.length-1) {
                while(chosen[index]) {
                    index = rand.nextInt(buttons.length);
                }
            }
        }
        for(int i = 0; i < chosen.length; i++) {
            chosen[i] = false;
        }
    }
    
    //method: endGame
    //purpose: this method prepares and displays the screen that is
    //shown to the player when they finish the game.
    public void endGame() {
        Timer endDelay = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameScreen.setVisible(false);
                endScreen = new JFrame();
                endScreen.setSize(WIDTH, HEIGHT);
                endScreen.setLocationRelativeTo(null);
                JPanel endPanel = new JPanel(new BorderLayout());
                JPanel scorePanel = new JPanel(new GridBagLayout());
                GridBagConstraints c1 = new GridBagConstraints();
                JLabel scoreTitle = new JLabel("SCORE");
                scoreTitle.setHorizontalAlignment(JLabel.CENTER);
                scoreTitle.setFont(new Font(scoreTitle.getFont().getFontName(), Font.BOLD, 35));
                endPanel.add(scoreTitle, BorderLayout.NORTH);
                JLabel endScore = new JLabel(Integer.toString(score[0]));
                endScore.setFont(new Font(endScore.getFont().getFontName(), Font.BOLD, 24));
                scorePanel.add(endScore);
                endPanel.add(scorePanel, BorderLayout.CENTER);
                JButton endButton = new JButton("End");
                JPanel endButtonPanel = new JPanel(new GridBagLayout());
                endButtonPanel.add(endButton);
                endPanel.add(endButtonPanel, BorderLayout.SOUTH);
                endPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
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