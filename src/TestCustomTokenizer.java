import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCustomTokenizer extends JFrame {

    // GUI Components
    private JTextArea inputArea;
    private JTextArea resultArea;
    private JButton tokenizeButton;
    private JButton clearButton;

    public TestCustomTokenizer() {
        // Set up the frame
        setTitle("TOKENIZER: Hash");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null); 

        // Panels
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Text Areas for input and result
        inputArea = new JTextArea("Input text here", 10, 20);
        resultArea = new JTextArea("Result:", 10, 20);

        inputArea.setLineWrap(true); 
        inputArea.setWrapStyleWord(true);
        inputArea.setCaretPosition(0);
        inputArea.moveCaretPosition(inputArea.getText().length());
        
        resultArea.setEditable(false);
        resultArea.setLineWrap(true); 
        resultArea.setWrapStyleWord(true);

        // Buttons
        tokenizeButton = new JButton("TOKENIZE");
        clearButton = new JButton("CLEAR");

        
        // Add Action Listeners
        tokenizeButton.addActionListener(new TokenizeAction());
        clearButton.addActionListener(new ClearAction());

        // Add components to panels
        mainPanel.add(new JScrollPane(inputArea));
        mainPanel.add(new JScrollPane(resultArea));
        buttonPanel.add(tokenizeButton);
        buttonPanel.add(clearButton);

        // Add panels to frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Action for Tokenize Button
    private class TokenizeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputArea.getText();
            if (!input.isEmpty()) {
                StringBuilder result = new StringBuilder();
                
                // Phase 1: Tokenize and classify
                String[] tokens = CustomTokenizer.splitByDelimiter(input, '#');
                result.append("Phase 1 Output:\n");
                for (String token : tokens) {
                    String type = CustomTokenizer.classifyToken(token);
                    result.append("Token: \"").append(token).append("\" - Type: ").append(type).append("\n");
                }
                
                // Phase 2: Granular breakdown
                result.append("\nPhase 2 Output (Granular Breakdown):\n");
                for (String token : tokens) {
                    result.append("Token: \"").append(token).append("\" -> ");
                    for (char c : token.toCharArray()) {
                        result.append("'").append(c).append("', ");
                    }
                    result.append("\n");
                }

                // Display the result in the resultArea
                resultArea.setText(result.toString());
            }
        }
    }

    // Action for Clear Button
    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputArea.setText("");
            resultArea.setText("Result:");
        }
    }

    public static void main(String[] args) {
        // Create the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestCustomTokenizer().setVisible(true);
            }
        });
    }
}