import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import calculator.generalCalculator;

public class loginpage extends JFrame implements ActionListener {

    private JLabel titleLabel, userLabel, passLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private RoundedLoginButton loginButton, resetButton;
    private JPanel mainPanel;

    public loginpage() {
        setTitle("Login Calculator");
        setSize(400, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(32, 32, 32));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        titleLabel = new JLabel("Calculator Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 30, 300, 35);
        mainPanel.add(titleLabel);

        userLabel = new JLabel("Username :");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        userLabel.setForeground(new Color(200, 200, 200));
        userLabel.setBounds(50, 110, 100, 25);
        mainPanel.add(userLabel);

        userTextField = new JTextField();
        userTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        userTextField.setBackground(new Color(50, 50, 50));
        userTextField.setForeground(Color.WHITE);
        userTextField.setCaretColor(Color.WHITE);
        userTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userTextField.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
        userTextField.setBounds(160, 110, 180, 30);
        mainPanel.add(userTextField);

        passLabel = new JLabel("Password :");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        passLabel.setForeground(new Color(200, 200, 200));
        passLabel.setBounds(50, 170, 100, 25);
        mainPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        passwordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(75, 75, 75), 1));
        passwordField.setBounds(160, 170, 180, 30);
        mainPanel.add(passwordField);

        loginButton = new RoundedLoginButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(new Color(46, 139, 87));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(65, 250, 110, 35);
        loginButton.addActionListener(this);
        mainPanel.add(loginButton);

        resetButton = new RoundedLoginButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 15));
        resetButton.setBackground(new Color(217, 83, 79));
        resetButton.setForeground(Color.WHITE);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setBounds(205, 250, 110, 35);
        resetButton.addActionListener(this);
        mainPanel.add(resetButton);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                dispose();
                new generalCalculator();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username and Password", "Wrong Credentials", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        new loginpage();
    }
}

class RoundedLoginButton extends JButton {
    public RoundedLoginButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }

        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2.setFont(getFont());
        g2.drawString(getText(), x, y);
        g2.dispose();
    }
}