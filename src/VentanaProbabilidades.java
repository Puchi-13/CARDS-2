import java.awt.Color;
import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class VentanaProbabilidades extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaProbabilidades(Sobre sobre) {
		setTitle("Probabilidades de " + sobre.getNombre());
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextPane area = new JTextPane();
		area.setEditable(false);
		area.setFont(new Font("Monospaced", Font.PLAIN, 13));
		area.setBackground(Color.BLACK);

		StyledDocument doc = area.getStyledDocument();

		appendColoredLine(doc, sobre.getAscii() + "\n\n", Color.CYAN);

		for (Map.Entry<String, Double> entry : sobre.getProbabilidades().entrySet()) {
			Color color = sobre.getColores().getOrDefault(entry.getKey(), Color.LIGHT_GRAY);
			appendColoredLine(doc, entry.getKey() + ": " + entry.getValue() + "%\n", color);
		}

		add(new JScrollPane(area));
		setVisible(true);
	}

	private void appendColoredLine(StyledDocument doc, String line, Color color) {
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr, color);
		try {
			doc.insertString(doc.getLength(), line, attr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}