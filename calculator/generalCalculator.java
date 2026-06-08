package calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class generalCalculator extends JFrame implements ActionListener {

    private RoundedButton generalModeBtn, scientificModeBtn;
    private JTextField display;
    private RoundedButton[] generalButtons;
    private RoundedButton[] scientificButtons;
    private JPanel modePanel, buttonPanel;

    private boolean isCalculationDone = false;

    public generalCalculator() {
        setTitle("Calculator");
        setSize(360, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(32, 32, 32));

        modePanel = new JPanel(new GridLayout(1, 2, 10, 0));
        modePanel.setBackground(new Color(32, 32, 32));
        modePanel.setBorder(new EmptyBorder(10, 10, 0, 10));

        generalModeBtn = new RoundedButton("General");
        scientificModeBtn = new RoundedButton("Scientific");

        generalModeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        scientificModeBtn.setFont(new Font("Arial", Font.BOLD, 14));

        generalModeBtn.setBackground(new Color(46, 139, 87));
        generalModeBtn.setForeground(Color.WHITE);
        scientificModeBtn.setBackground(new Color(64, 64, 64));
        scientificModeBtn.setForeground(Color.WHITE);

        generalModeBtn.addActionListener(this);
        scientificModeBtn.addActionListener(this);

        modePanel.add(generalModeBtn);
        modePanel.add(scientificModeBtn);
        add(modePanel, BorderLayout.NORTH);

        display = new JTextField("");
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(new Color(32, 32, 32));
        display.setForeground(Color.WHITE);
        display.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(display, BorderLayout.CENTER);

        buttonPanel = new JPanel(new GridLayout(6, 4, 8, 8));
        buttonPanel.setBackground(new Color(32, 32, 32));
        buttonPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        String[] genLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };
        generalButtons = new RoundedButton[genLabels.length];

        String[] sciLabels = {"sin", "cos", "tan", "log", "ln", "sqrt", "^", "C"};
        scientificButtons = new RoundedButton[sciLabels.length];

        for (int i = 0; i < sciLabels.length; i++) {
            scientificButtons[i] = new RoundedButton(sciLabels[i]);
            scientificButtons[i].setFont(new Font("Arial", Font.BOLD, 14));

            if (sciLabels[i].equals("C")) {
                scientificButtons[i].setBackground(new Color(217, 83, 79));
            } else {
                scientificButtons[i].setBackground(new Color(75, 75, 75));
            }

            scientificButtons[i].setForeground(Color.WHITE);
            scientificButtons[i].addActionListener(this);
            scientificButtons[i].setVisible(false);
            buttonPanel.add(scientificButtons[i]);
        }

        for (int i = 0; i < genLabels.length; i++) {
            generalButtons[i] = new RoundedButton(genLabels[i]);
            generalButtons[i].setFont(new Font("Arial", Font.BOLD, 18));

            String label = genLabels[i];
            if (label.equals("/") || label.equals("*") || label.equals("-") || label.equals("+") || label.equals("=")) {
                generalButtons[i].setBackground(new Color(240, 173, 78));
                generalButtons[i].setForeground(Color.WHITE);
            } else {
                generalButtons[i].setBackground(new Color(50, 50, 50));
                generalButtons[i].setForeground(Color.WHITE);
            }

            generalButtons[i].addActionListener(this);
            buttonPanel.add(generalButtons[i]);
        }

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Scientific")) {
            setSize(450, 620);
            scientificModeBtn.setBackground(new Color(46, 139, 87));
            generalModeBtn.setBackground(new Color(64, 64, 64));

            for (RoundedButton btn : scientificButtons) {
                btn.setVisible(true);
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
        else if (command.equals("General")) {
            setSize(360, 520);
            generalModeBtn.setBackground(new Color(46, 139, 87));
            scientificModeBtn.setBackground(new Color(64, 64, 64));

            for (RoundedButton btn : scientificButtons) {
                btn.setVisible(false);
            }
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
        else if (command.equals("C")) {
            display.setText("0");
            isCalculationDone = false;
        }
        else if (command.equals("=")) {
            String expression = display.getText();
            try {
                double result = evaluateExpression(expression);
                if (Double.isNaN(result) || Double.isInfinite(result)) {
                    display.setText("Error");
                } else if (result == (long) result) {
                    display.setText(String.valueOf((long) result));
                } else {
                    display.setText(String.valueOf(result));
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
            isCalculationDone = true;
        }
        else {
            String currentText = display.getText();

            if (currentText.equals("0") || currentText.equals("Error") || isCalculationDone) {
                if (command.equals("sin") || command.equals("cos") || command.equals("tan") ||
                        command.equals("log") || command.equals("ln") || command.equals("sqrt")) {
                    display.setText(command + "(");
                } else if (command.equals(".")) {
                    display.setText("0.");
                } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("^")) {
                    if (currentText.equals("Error")) {
                        display.setText("0" + command);
                    } else {
                        display.setText(currentText + command);
                    }
                } else {
                    display.setText(command);
                }
                isCalculationDone = false;
            } else {
                if (command.equals("sin") || command.equals("cos") || command.equals("tan") ||
                        command.equals("log") || command.equals("ln") || command.equals("sqrt")) {
                    display.setText(currentText + command + "(");
                } else {
                    display.setText(currentText + command);
                }
            }
        }
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return parseExpression(expression);
    }

    private double parseExpression(String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseTerm();
                while (true) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while (true) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) {
                        double divisor = parseFactor();
                        if (divisor == 0) return Double.NaN;
                        x /= divisor;
                    }
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parse();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) {
                        if (x < 0) return Double.NaN;
                        x = Math.sqrt(x);
                    }
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) {
                        if (x <= 0) return Double.NaN;
                        x = Math.log10(x);
                    }
                    else if (func.equals("ln")) {
                        if (x <= 0) return Double.NaN;
                        x = Math.log(x);
                    }
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        new generalCalculator();
    }
}

class RoundedButton extends JButton {
    public RoundedButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
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