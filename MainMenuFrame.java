package Project3_6680081;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.ArrayList;



public class MainMenuFrame extends JFrame {
    private JTextField nameField;
    private JRadioButton[] skinButtons;
    private JComboBox<String> difficultyBox;
    private JList<String> deathList;
    private JButton startButton;
    private JTextArea groupInfo;
    private JLabel contentpane;

    //retrieve the Main Menu Frame Width and Height from Utilities.java
    private int frameWidth = MyConstants.MM_FRAME_WIDTH;
    private int frameHeight = MyConstants.MM_FRAME_HEIGHT;

    public MainMenuFrame() {
        setTitle("Rage Parkour - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);

        MyImageIcon background = new MyImageIcon(MyConstants.FILE_BG).resize(frameWidth, frameHeight);
        setContentPane(contentpane = new JLabel(background));
        contentpane.setLayout(null);

        setupComponents();

        setVisible(true);
    }

    private void setupComponents() {
        JLabel nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setBounds(60, 50, 200, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.WHITE);
        contentpane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(270, 50, 250, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        contentpane.add(nameField);

        JPanel radioPanel = new JPanel(new GridLayout(1, 5));
        radioPanel.setBounds(60, 120, 600, 60);
        radioPanel.setOpaque(false);
        TitledBorder skinBorder = BorderFactory.createTitledBorder("Select Character Skin");
        skinBorder.setTitleColor(Color.WHITE);
        radioPanel.setBorder(skinBorder);

        ButtonGroup skinGroup = new ButtonGroup();
        skinButtons = new JRadioButton[5];
        for (int i = 0; i < 5; i++) {
            skinButtons[i] = new JRadioButton("Skin " + (i + 1));
            skinButtons[i].setOpaque(false);
            skinButtons[i].setForeground(Color.WHITE);
            skinButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            skinGroup.add(skinButtons[i]);
            radioPanel.add(skinButtons[i]);
        }
        skinButtons[0].setSelected(true);
        contentpane.add(radioPanel);

        JLabel diffLabel = new JLabel("Select Difficulty:");
        diffLabel.setBounds(60, 210, 200, 30);
        diffLabel.setFont(new Font("Arial", Font.BOLD, 18));
        diffLabel.setForeground(Color.WHITE);
        contentpane.add(diffLabel);

        String[] difficulties = {"Easy", "Medium", "Hard", "Impossible", "Nightmare"};
        difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setBounds(270, 210, 200, 30);
        difficultyBox.setFont(new Font("Arial", Font.PLAIN, 16));
        contentpane.add(difficultyBox);

        JLabel deathLabel = new JLabel("Previous Death Messages:");
        deathLabel.setBounds(60, 280, 300, 30);
        deathLabel.setFont(new Font("Arial", Font.BOLD, 18));
        deathLabel.setForeground(Color.WHITE);
        contentpane.add(deathLabel);

        //Do not hard code there, insert the message into the file text
        String[] messages = loadDeathMessages();
        
        deathList = new JList<>(messages);
        deathList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        deathList.setVisibleRowCount(3);
        JScrollPane scrollPane = new JScrollPane(deathList);
        scrollPane.setBounds(60, 320, 400, 80);
        contentpane.add(scrollPane);

        startButton = new JButton("START GAME");
        startButton.setBounds(500, 450, 220, 50);
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(0, 153, 0));
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(new StartButtonListener());
        contentpane.add(startButton);

        groupInfo = new JTextArea();
        groupInfo.setBounds(60, 700, 500, 120);
        groupInfo.setEditable(false);
        groupInfo.setOpaque(false);
        groupInfo.setForeground(Color.LIGHT_GRAY);
        groupInfo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        groupInfo.setText(
            "Group Members:\n" +
            "- Kongphop Kayoonvihcien (ID: 6680081)\n" +
            "- Jonathan Reppert (ID: 6680701)\n" +
            "- Wasupon Wiriyakitanan (ID: 6680646)\n" +
            "- Phattarada Limsuchaiwat (ID: 6680684)\n" +
            "- Kasidit Boonluar (ID: 6680028)"
        );
        contentpane.add(groupInfo);
    }
    
    //can change if don't needed
    private String[] loadDeathMessages() {
        List<String> lines = new ArrayList<>();

        String filePath = MyConstants.PATH + "deadlog.txt";

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            lines.add("No death logs found.");
            System.err.println("Failed to read deadlog.txt: " + e.getMessage());
        }

        return lines.toArray(new String[0]);
    }

    public void setMenuEnabled(boolean enabled) {
        for (Component comp : contentpane.getComponents()) {
            setComponentEnabled(comp, enabled);
        }
    }

    private void setComponentEnabled(Component comp, boolean enabled) {
        comp.setEnabled(enabled);
        if (comp instanceof Container) {
            for (Component child : ((Container) comp).getComponents()) {
                setComponentEnabled(child, enabled);
            }
        }
    }

    private class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String playerName = nameField.getText();
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(MainMenuFrame.this, "Please enter your name!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int selectedSkinIndex = -1;
            for (int i = 0; i < skinButtons.length; i++) {
                if (skinButtons[i].isSelected()) {
                    selectedSkinIndex = i;
                    break;
                }
            }
            String selectedDifficulty = (String) difficultyBox.getSelectedItem();

            setMenuEnabled(false);
            
            //new GameFrame(playerName, selectedSkinIndex, selectedDifficulty, MainMenuFrame.this);
        }
    }
}
