import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class TiendaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Font fuenteBoton = new Font("Monospaced", Font.BOLD, 12);

	private JButton crearBotonEstilizado(String texto, java.awt.event.ActionListener accion) {
		JButton boton = new JButton(texto);
		boton.setBackground(Color.BLACK);
		boton.setForeground(Color.WHITE);
		boton.setFont(fuenteBoton);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		boton.setOpaque(true);
		boton.setContentAreaFilled(false);
		boton.addActionListener(accion);
		return boton;
	}

	public TiendaPanel(CARDS2 mainWindow) {
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);

		JLabel titulo = new JLabel("TIENDA", SwingConstants.CENTER);
		titulo.setFont(new Font("Monospaced", Font.BOLD, 28));
		titulo.setForeground(Color.CYAN);
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		JPanel listaPanel = new JPanel();
		listaPanel.setLayout(new BoxLayout(listaPanel, BoxLayout.Y_AXIS));
		listaPanel.setBackground(Color.BLACK);

		for (Sobre sobre : DatosSobres.obtenerSobres()) {
			JPanel fila = new JPanel(new BorderLayout());
			fila.setMaximumSize(new Dimension(920, 60));
			fila.setBackground(Color.BLACK);
			fila.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

			JLabel nombre = new JLabel(sobre.getNombre());
			nombre.setForeground(Color.WHITE);
			nombre.setFont(new Font("Monospaced", Font.BOLD, 16));
			nombre.setPreferredSize(new Dimension(200, 30));

			JLabel precio = new JLabel(sobre.getPrecio() + "€");
			precio.setForeground(Color.WHITE);
			precio.setFont(new Font("Monospaced", Font.PLAIN, 16));
			precio.setHorizontalAlignment(SwingConstants.RIGHT);
			precio.setPreferredSize(new Dimension(100, 30));

			JButton probBtn = crearBotonEstilizado("PROB.", e -> new VentanaProbabilidades(sobre, mainWindow));
			probBtn.setPreferredSize(new Dimension(80, 30));

			JButton comprarBtn = crearBotonEstilizado("COMPRAR", e -> {
				if (mainWindow.intentarComprarSobre(sobre)) {
					JOptionPane.showMessageDialog(this, "¡Has comprado el " + sobre.getNombre() + "!");
				} else {
					JOptionPane.showMessageDialog(this, "No tienes suficiente dinero para comprar este sobre.");
				}
			});
			comprarBtn.setPreferredSize(new Dimension(100, 30));

			JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			derecha.setBackground(Color.BLACK);
			derecha.add(precio);
			derecha.add(Box.createRigidArea(new Dimension(10, 0))); // ← separación horizontal
			derecha.add(probBtn);
			derecha.add(comprarBtn);

			JPanel centro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			centro.setBackground(Color.BLACK);
			centro.add(derecha);
			fila.add(nombre, BorderLayout.WEST);
			fila.add(centro, BorderLayout.CENTER);
			fila.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(50, 50, 50)));
			listaPanel.add(fila);

		}

		JScrollPane scroll = new JScrollPane(listaPanel);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, BorderLayout.CENTER);

		JButton volverBtn = crearBotonEstilizado("Volver al Menú Principal", e -> mainWindow.volverAlMenu());
		volverBtn.setPreferredSize(new Dimension(200, 40));

		JPanel sur = new JPanel();
		sur.setBackground(Color.BLACK);
		sur.add(volverBtn);
		add(sur, BorderLayout.SOUTH);
	}

	private final Image icono = new ImageIcon("icono_original.png").getImage();

	public Image getIcono() {
		return icono;
	}
}
