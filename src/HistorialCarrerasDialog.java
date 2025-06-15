import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HistorialCarrerasDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public HistorialCarrerasDialog(JFrame parent, int galgoIndex, int[] posiciones) {
		super(parent, "Historial del Galgo " + (galgoIndex + 1), true);
		setSize(400, 200);
		setLocationRelativeTo(parent);
		getContentPane().setBackground(Color.BLACK);
		setLayout(new BorderLayout());

		JLabel titulo = new JLabel("Últimas 8 posiciones", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 18));
		titulo.setForeground(Color.YELLOW);
		titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		JPanel panelHistorial = new JPanel();
		panelHistorial.setLayout(new GridLayout(1, 8, 10, 10));
		panelHistorial.setBackground(Color.BLACK);
		panelHistorial.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		for (int pos : posiciones) {
			JLabel lbl = new JLabel(String.valueOf(pos), SwingConstants.CENTER);
			lbl.setFont(new Font("SansSerif", Font.BOLD, 20));
			lbl.setOpaque(true);

			// Color según posición
			if (pos <= 3) {
				lbl.setBackground(new Color(0, 200, 0)); // Verde
				lbl.setForeground(Color.BLACK);
			} else if (pos >= 6) {
				lbl.setBackground(Color.RED);
				lbl.setForeground(Color.WHITE);
			} else {
				lbl.setBackground(Color.LIGHT_GRAY);
				lbl.setForeground(Color.BLACK);
			}

			panelHistorial.add(lbl);
		}

		add(panelHistorial, BorderLayout.CENTER);

		JButton cerrar = new JButton("Cerrar");
		cerrar.setFocusPainted(false);
		cerrar.addActionListener(e -> dispose());
		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(cerrar);
		add(sur, BorderLayout.SOUTH);

		setVisible(true);
	}

	// Ejemplo de uso:
	public static void main(String[] args) {
		int[] ejemplo = { 4, 7, 1, 2, 5, 3, 8, 6 };
		new HistorialCarrerasDialog(null, 2, ejemplo);
	}
}
