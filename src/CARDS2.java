import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CARDS2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private final List<Sobre> sobresComprados = new ArrayList<>();
	private double dinero = 10000;
	private JLabel dineroLabel;
	private MisCartasPanel.CartaGuardada cartaGuardada;

	public CARDS2() {
		crearCarpetaCartas();
		setTitle("CARDS 2");
		setSize(568, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
		setLayout(new BorderLayout());

		cargarCartasGuardadas(); // carga al iniciar

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				guardarCartasAlSalir(); // guarda al cerrar
			}
		});

		panelPrincipal = new JPanel(null);
		panelPrincipal.setBackground(Color.BLACK);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.black);
		textPane.setFont(new Font("Monospaced", Font.BOLD, 14));
		textPane.setBounds(10, 10, 780, 150);
		StyledDocument doc = textPane.getStyledDocument();

		appendColoredLine(doc, "/==========================================================\\\n", Color.GREEN);
		appendColoredLine(doc, "|| .' ___  |     / \\     |_   __ \\   |_   _ `. .' ____ \\  ||\n", Color.MAGENTA);
		appendColoredLine(doc, "||/ .'   \\_|    / _ \\      | |__) |    | | `. \\| (___ \\_| ||\n", Color.YELLOW);
		appendColoredLine(doc, "||| |          / ___ \\     |  __ /     | |  | | _.____`.  ||\n", Color.BLUE);
		appendColoredLine(doc, "||\\ `.___.'\\ _/ /   \\ \\_  _| |  \\ \\_  _| |_.' /| \\____) | ||\n", Color.RED);
		appendColoredLine(doc, "|| `.____ .'|____| |____||____| |___||______.'  \\______.' ||\n", Color.CYAN);
		appendColoredLine(doc, "\\======================================================V2.0/\n", Color.WHITE);

		panelPrincipal.add(textPane);

		dineroLabel = new JLabel("DINERO: " + dinero + "€");
		dineroLabel.setForeground(Color.WHITE);
		dineroLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
		dineroLabel.setBounds(10, 160, 300, 30);
		panelPrincipal.add(dineroLabel);

		JButton tiendaBtn = new JButton("TIENDA");
		tiendaBtn.setBounds(200, 200, 150, 40);
		tiendaBtn.addActionListener(e -> mostrarTienda());
		panelPrincipal.add(tiendaBtn);

		JButton sobresBtn = new JButton("MIS SOBRES");
		sobresBtn.setBounds(200, 260, 150, 40);
		sobresBtn.addActionListener(e -> mostrarMisSobres());
		panelPrincipal.add(sobresBtn);

		JButton cartasBtn = new JButton("MIS CARTAS");
		cartasBtn.setBounds(200, 320, 150, 40);
		cartasBtn.addActionListener(e -> mostrarMisCartas());
		panelPrincipal.add(cartasBtn);

		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.setBounds(360, 320, 150, 40); // a la derecha de "MIS CARTAS"
		registroBtn.addActionListener(e -> mostrarRegistro());
		panelPrincipal.add(registroBtn);

		add(panelPrincipal, BorderLayout.CENTER);
	}

	private void cargarCartasGuardadas() {
		cartaGuardada = MisCartasPanel.CartaGuardada.cargarDesdeArchivo();
	}

	private void guardarCartasAlSalir() {
		if (cartaGuardada != null) {
			cartaGuardada.guardarEnArchivo();
		}
	}

	public void mostrarTienda() {
		getContentPane().removeAll();
		getContentPane().add(new TiendaPanel(this));
		revalidate();
		repaint();
	}

	public void mostrarMisSobres() {
		getContentPane().removeAll();
		getContentPane().add(new MisSobresPanel(this, sobresComprados));
		revalidate();
		repaint();
	}

	public void mostrarMisCartas() {
		getContentPane().removeAll();
		getContentPane().add(new MisCartasPanel(this, cartaGuardada.getCartasPorCalidad()));
		revalidate();
		repaint();
	}

	public void mostrarRegistro() {
		getContentPane().removeAll();
		getContentPane().add(new RegistroPanel(this));
		revalidate();
		repaint();
	}

	public void mostrarVentanaCarta(Carta carta) {
		JDialog ventanaCarta = new JDialog(this, "Carta", true);
		ventanaCarta.setSize(400, 420);
		ventanaCarta.setLocationRelativeTo(this);
		ventanaCarta.getContentPane().setBackground(Color.BLACK);
		ventanaCarta.setLayout(new BorderLayout());

		Color color = ColoresCalidad.obtener(carta.getCalidad());

		JTextArea ascii = new JTextArea(carta.getAscii());
		ascii.setFont(new Font("Consolas", Font.PLAIN, 13));
		ascii.setMargin(new Insets(5, 10, 5, 10));
		ascii.setForeground(color);
		ascii.setBackground(Color.BLACK);
		ascii.setEditable(false);
		ascii.setFocusable(false);

		JLabel info = new JLabel(carta.getResumenTitulo(), SwingConstants.CENTER);
		info.setForeground(color);
		info.setFont(new Font("Monospaced", Font.BOLD, 16));
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel centro = new JPanel(new GridBagLayout());
		centro.setBackground(Color.BLACK);
		centro.add(ascii);
		ventanaCarta.add(new JScrollPane(centro), BorderLayout.CENTER);
		ventanaCarta.add(info, BorderLayout.SOUTH);

		ventanaCarta.setVisible(true);
	}

	public void volverAlMenu() {
		getContentPane().removeAll();
		actualizarDineroLabel();
		getContentPane().add(panelPrincipal);
		revalidate();
		repaint();
	}

	public boolean intentarComprarSobre(Sobre sobre) {
		if (dinero >= sobre.getPrecio()) {
			dinero -= sobre.getPrecio();
			sobresComprados.add(sobre);
			actualizarDineroLabel();
			return true;
		}
		return false;
	}

	private void actualizarDineroLabel() {
		if (dineroLabel != null) {
			dineroLabel.setText("DINERO: " + String.format("%.2f", dinero) + "€");
		}
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

	private void crearCarpetaCartas() {
		File carpeta = new File("CartasObtenidas");
		if (!carpeta.exists())
			carpeta.mkdirs();
	}

	public double getDinero() {
		return dinero;
	}

	public MisCartasPanel.CartaGuardada getCartaGuardada() {
		return cartaGuardada;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new CARDS2().setVisible(true));
	}

	public void sumarDinero(double cantidad) {
		this.dinero += cantidad;
		actualizarDineroLabel();
	}
}
