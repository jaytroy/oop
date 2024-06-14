package nl.rug.oop.rts.view.components;

import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Console text area class for displaying messages.
 */
public class ConsoleTextArea extends JTextArea {
    /**
     * Console text area constructor.
     *
     * @param rows    rows of the area.
     * @param columns columns of the area.
     */
    public ConsoleTextArea(int rows, int columns) {
        super(rows, columns);
        setEditable(false);
        initializeOutputStream();
    }

    /**
     * Initializes the output stream for capturing System.out and System.err.
     */
    void initializeOutputStream() {
        PrintStream printStream = new PrintStream(new ConsoleOutputStream(this));
        System.setOut(printStream);
        System.setErr(printStream);
    }

    private static class ConsoleOutputStream extends OutputStream {
        private final JTextArea textArea;

        ConsoleOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }

        @Override
        public void write(byte[] b, int off, int len) {
            textArea.append(new String(b, off, len));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
