package GameProgram;

import Ai.BaseAi;
import runProgram.playGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Objects;


public class WheelOfFortuneGraphics extends JPanel {

    public int numSpins = 0;
    private int numSlices;  // Change this to the desired number of slices
    private int radius;
    private GameWheel wheel;
    private double rotationAngle = 0;
    private double spinTime;
    private Color sliceColor;
    private playGame currentGame;
    private BaseAi ai;
    JFrame frame = new JFrame("Wheel of Fortune");
    JButton spinButton = new JButton("Spin");
    JButton scrambleButton = new JButton("Scramble");
    JButton sortButton = new JButton("Sort");
    JButton addSliceButton = new JButton("Add Slice");
    JButton removeSliceButton = new JButton("Remove Slice");
    JButton colorSort = new JButton("Color Sort");
    JButton reverseSort = new JButton("Reverse Sort");
    public JTextField textField;
    public JTextField textField2;

    public JTextField inputField = new JTextField(20);



    private ArrayList<JTextArea> responses = new ArrayList<JTextArea>();

//    Image desktopIcon = new ImageIcon(Objects.requireNonNull(WheelOfFortuneGraphics.class.getResource("/paul.JPG"))).getImage();

    boolean spinning = false;
    
    public WheelOfFortuneGraphics(GameWheel wheel, playGame game) {
        
        this(wheel, game, 0, 0, 0, 0);
    }

    public WheelOfFortuneGraphics(GameWheel wheel, playGame game, int startX, int startY, int width, int height) {
        if(width == 0)
        {
            Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
            height = 800;
            width = 1300;
            startX = bounds.width/2 - width/2;
            startY = bounds.height/2 - height/2;
        }

        ai = new BaseAi();

        currentGame = game;
        numSlices = wheel.numSlices();
        this.wheel = wheel;

        frame.add(this);
        frame.setSize(width, height);
        frame.setLocation(startX, startY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setIconImage(desktopIcon);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        textField = new JTextField(40);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.CENTER);
        Font newFont = new Font("Serif", Font.BOLD, 30);
        textField.setFont(newFont);
        textField2 = new JTextField(40);
        textField2.setEditable(false);
        textField2.setHorizontalAlignment(JTextField.CENTER);
        textField2.setFont(newFont);
        panel2.add(textField);
        panel2.add(textField2);
        frame.add(panel2, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.add(spinButton);
        panel.add(scrambleButton);
        panel.add(sortButton);
        panel.add(reverseSort);
        panel.add(colorSort);
        panel.add(addSliceButton);
        panel.add(removeSliceButton);

        frame.add(panel, BorderLayout.SOUTH);

        JPanel centerSplitPanel = new JPanel(new GridLayout(1, 2, -15, 0));


        JPanel chatPanel = new JPanel(new BorderLayout());

        JTextArea chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        chatHistory.setLineWrap(true);
        chatHistory.setWrapStyleWord(true);
        chatHistory.setFont(new Font("Söhne Mono", Font.PLAIN, 16));

        Border historyBorder = chatHistory.getBorder();
        Border historyPadding = BorderFactory.createEmptyBorder(6, 6, 6, 20);
        chatHistory.setBorder(BorderFactory.createCompoundBorder(historyBorder, historyPadding));

        JScrollPane chatScroll = new JScrollPane(chatHistory);
        chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        inputField = new JTextField(40);
        inputField.setFont(new Font("Söhne Mono", Font.PLAIN, 16));
        inputField.setHorizontalAlignment(JTextField.LEFT);

        Border currentBorder = inputField.getBorder();
        Border padding = BorderFactory.createEmptyBorder(6, 3, 6, 10);
        inputField.setBorder(BorderFactory.createCompoundBorder(currentBorder, padding));

        chatPanel.add(chatScroll, BorderLayout.CENTER);
        chatPanel.add(inputField, BorderLayout.SOUTH);


        centerSplitPanel.add(chatPanel);
        centerSplitPanel.add(this);


        frame.add(centerSplitPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        System.out.println(wheel.toString());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                spinWheel();
            }
        });
        // Add action listeners to the buttons
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinWheel();
                System.out.println("Spin button clicked");
            }
        });

        scrambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scramble();
                System.out.println("Scramble button clicked");
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort();
                System.out.println("Sort button clicked");
            }
        });
        addSliceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSlice();
                System.out.println("Addslice button clicked");
            }
        });
        removeSliceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSlice();
                System.out.println("Remove button clicked");
            }
        });
        colorSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorSort();
                System.out.println("Color Sort button clicked");
            }
        });
        reverseSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseSort();
            }
        });
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = inputField.getText().trim();

                if (!prompt.isEmpty()) {
                    inputField.setText("");
                    chatHistory.append("You: " + prompt + "\n");
                    chatHistory.append(ai.messageBack(prompt));

                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        numSlices = wheel.numSlices();
        if(getWidth() <= getHeight())
        {
            radius = (int)(getWidth()/2.1);
        }
        else
        {
            radius = (int)(getHeight()/2.1);
        }

        double textScale = 1 - (numSlices * .005);
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;
        double arcAngle = 360.0 / numSlices;
        double textHeight = radius / 10 ;
        for (int i = 0; i < numSlices; i++) {
            sliceColor = wheel.getSlice(i).getColor();
            
            g2d.setColor(sliceColor); // Set the color for this slice

            //We want the wheel to start at 12 o'clock, but in math it starts at 3 o'clock. So we will subtract the degrees we want from 90 to account for that
            double sliceAngle = ((i * arcAngle) - rotationAngle);
            
            //System.out.println(arcAngle);
            Arc2D.Double arc = new Arc2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2, sliceAngle, arcAngle, Arc2D.PIE);
            g2d.fill(arc);

            //FontMetrics fm = g2d.getFontMetrics();
            
            // Draw text in the center of each slice
            double theta = Math.toRadians(((i * arcAngle) + arcAngle / 2) - rotationAngle);
            double textAngle = Math.toDegrees(Math.atan2(textHeight/ 2.0, radius));
            theta += Math.toRadians(textAngle);
            double textX = (centerX + (Math.cos(theta) * radius * 0.95));
            double textY = (centerY - (Math.sin(theta) * radius * 0.95));
                        
            g2d.setFont(new Font("Sledge Hammer", Font.BOLD, (int) textHeight));

            g2d.setColor(wheel.getSlice(i).getTextColor());

            double textRotateAngle = Math.atan2(textY - centerY, textX - centerX) + Math.PI;
            textRotateAngle += Math.toRadians(textAngle)/2.0;
            //System.out.println("Number: " + String.valueOf(wheel.getSlice(i).getPrizeAmount()) + "; X: " + textX + "; Y: " + textY + "; rotate angle:" + textRotateAngle + "; textHeight:" + textHeight + "; Radius: " + radius);

            AffineTransform originalTransform = g2d.getTransform();
            g2d.rotate(textRotateAngle, textX, textY);
            g2d.drawString(String.valueOf(wheel.getSlice(i).getPrizeAmount()), (int) textX, (int) textY);
            g2d.setTransform(originalTransform);
        }  
        int[] xPoints = {(int)(centerX + radius + (radius * 0.1)), (int)(centerX + radius + (radius * 0.1)), (int)(centerX + radius - (radius * 0.01))};
        int[] yPoints = {(int)(centerY + (radius * 0.1)), (int)(centerY  - (int)(radius * 0.1)), (int) centerY};
        g2d.setColor(Color.BLACK);
        int centerCircle = (int)(radius * 0.4);
        g2d.fillOval((int)(centerX - centerCircle/2), (int)(centerY - centerCircle/2), centerCircle, centerCircle);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.WHITE);
        g2d.drawPolygon(xPoints, yPoints, 3);

    }

    private void spinWheel() {
        if(!spinning){
            wheel.numSlices();
            Slice spinResult = currentGame.beginSpin();
            int index = 0;
            for(int i = 0; i < wheel.slices.size(); i++){
                if(wheel.slices.get(i).equals(spinResult)){
                    index = i;
                }
            }
            
            double randomMin = ((360.0 / numSlices) * 0.9);
            double randomMax = ((360.0 / numSlices) * 0.1);
            double arcRange = Math.random() * (randomMax - randomMin) + randomMin;
            spinTime = ((index * (360.0/numSlices)) + 360 + arcRange - rotationAngle);
            //System.out.println(numSlices + " " + arcRange + " " + spinTime);
            if(spinTime < 360)
            {
                spinTime += 360;
            }
            //System.out.println("Index: " + index + ", Prize Ammount: " + wheel.slices.get(index).getPrizeAmount() + ", currentRotation: " + rotationAngle + ", total spin degrees: " + spinTime + "; Numslices: " + numSlices);
            spinning = true;
            new Timer(1, e -> {
                
                rotationAngle += 4;
                if (rotationAngle >= 360) {
                    rotationAngle %= 360;
                }
                spinTime -= 4;
                repaint();
                if (spinTime <= 0) {
                    ((Timer) e.getSource()).stop();
                    spinning = false;
                    currentGame.endSpin();
                }
            }).start();
            
        }
    }



    private void scramble(){
        wheel.scramble();
        rotationAngle = 0;
        repaint();
    }
    public void sort(){
        wheel.sort();
        rotationAngle = 0;
        repaint();
    }
    private void addSlice(){
        wheel.addSlice();
        rotationAngle = 0;
        repaint();
    }
    private void removeSlice(){
        wheel.removeSlice();
        repaint();
    }
    private void colorSort() {
        wheel.quickSort(1, wheel.slices.size() - 1);
        repaint();
    }
    private void reverseSort() {
        wheel.revereSort();
        repaint();
    }
}
