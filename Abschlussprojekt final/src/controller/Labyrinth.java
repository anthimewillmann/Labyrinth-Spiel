package controller;

import model.World;
import view.ConsoleView;
import view.GraphicView;

import javax.swing.*;
import java.awt.*;

public class Labyrinth {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Initialization code for the game
                int width = 15;
                int height = 15;
                World world = new World(width, height);  // Creates a new World object (presumably representing the game world)

                // Creating and configuring the GraphicView
                Dimension fieldDimensions = new Dimension(40, 40);
                GraphicView gview = new GraphicView(width * fieldDimensions.width, height * fieldDimensions.height, fieldDimensions, world);
                world.registerView(gview);  // Registers GraphicView with the World
                gview.setVisible(true);

                // Creating and registering the ConsoleView
                ConsoleView cview = new ConsoleView();
                world.registerView(cview);  // Registers ConsoleView with the World

                // Creating the main JFrame controller
                Controller controller = new Controller(world);
                controller.setTitle("Minecraft Labyrinth");
                controller.setResizable(false);
                controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                controller.getContentPane().add(gview);

                // Creating a comment panel with instructions
                JPanel commentPanel = new JPanel();
                JLabel commentLabel2 = new JLabel("Press ENTER to restart and ESC to leave");
                commentPanel.add(commentLabel2);

                // Configuring layout for the main JFrame
                controller.setLayout(new BorderLayout());
                controller.add(gview, BorderLayout.CENTER);
                controller.add(commentPanel, BorderLayout.SOUTH);

                // Packing and setting the size of the JFrame
                controller.pack();
                Insets insets = controller.getInsets();
                int commentPanelHeight = commentPanel.getPreferredSize().height;
                int windowX = width * fieldDimensions.width + insets.left + insets.right;
                int windowY = height * fieldDimensions.height + insets.bottom + insets.top + commentPanelHeight;
                Dimension size = new Dimension(windowX, windowY);
                controller.setSize(size);
                controller.setMinimumSize(size);
                controller.setVisible(true);
            }
        });
    }
}