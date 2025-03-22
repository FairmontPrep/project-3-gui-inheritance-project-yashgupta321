import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

class RobotPart {
    protected Image img;
    protected String description = "";

    public RobotPart(String fileName, String description) {
        loadImage(fileName);
        this.description = description;
    }

    private void loadImage(String fileName) {
        try {
            img = ImageIO.read(new File(fileName));
        } catch (Exception e) {
            System.out.println("Image not found: " + fileName);
        }
    }

    public void draw(Graphics g, int width, int height) {
        if (img != null) {
            g.drawImage(img, (width - img.getWidth(null)) / 2, (height - img.getHeight(null)) / 2, null);
        }
    }

    public String getDescription() {
        return description;
    }
}

class Robot {
    private RobotPart head;
    private RobotPart body;
    private RobotPart arms;
    private RobotPart legs;
    private RobotPart hands;
    private String description = "Robot with ";

    private Random rand = new Random();

    public Robot() {
        setParts();
    }

    private void setParts() {
        head = new RobotPart("Layer 8.png", "Head and Body");
        body = new RobotPart("Layer 8.png", "Head and Body");
        arms = new RobotPart("Layer 13.png", "Arms");
        legs = new RobotPart("Layer 12.png", "Legs");
        hands = rand.nextBoolean() ? new RobotPart("Layer 5.png", "Claw Hands") : new RobotPart("Layer 1.png", "Normal Hands");
        description = head.getDescription() + ", " + body.getDescription() + ", " + arms.getDescription() + ", " + legs.getDescription() + ", " + hands.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void draw(Graphics g, int width, int height) {
        head.draw(g, width, height);
        body.draw(g, width, height);
        arms.draw(g, width, height);
        legs.draw(g, width, height);
        hands.draw(g, width, height);
    }
}

public class RobotBuilderGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        Robot robot = new Robot();

        JPanel robotPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                robot.draw(g, getWidth(), getHeight());
            }
        };
        frame.add(robotPanel, BorderLayout.CENTER);

        JLabel descriptionLabel = new JLabel(robot.getDescription(), SwingConstants.CENTER);
        frame.add(descriptionLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
