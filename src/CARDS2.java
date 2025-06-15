import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

		setIconImage(redimensionarIcono("icono_original.png", 64, 64).getImage());

		setSize(568, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
		setLayout(new BorderLayout());

		cargarCartasGuardadas();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				guardarCartasAlSalir();
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

		dineroLabel = new JLabel("DINERO: " + String.format("%.2f", dinero) + "€");
		dineroLabel.setForeground(Color.WHITE);
		dineroLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
		dineroLabel.setBounds(30, 170, 400, 30);
		panelPrincipal.add(dineroLabel);

		Font botonFont = new Font("Monospaced", Font.BOLD, 22);

		JButton tiendaBtn = new JButton("TIENDA");
		tiendaBtn.setBounds(50, 200, 450, 50);
		tiendaBtn.setFont(botonFont);
		tiendaBtn.setBackground(Color.BLACK);
		tiendaBtn.setForeground(Color.GREEN);
		tiendaBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		tiendaBtn.setFocusPainted(false);
		tiendaBtn.addActionListener(e -> mostrarTienda());
		panelPrincipal.add(tiendaBtn);

		JButton sbcBtn = new JButton("SBC");
		sbcBtn.setBounds(50, 260, 140, 50);
		sbcBtn.setFont(botonFont);
		sbcBtn.setBackground(Color.BLACK);
		sbcBtn.setForeground(Color.MAGENTA);
		sbcBtn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
		sbcBtn.setFocusPainted(false);
		sbcBtn.addActionListener(e -> {
		});
		panelPrincipal.add(sbcBtn);

		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.setBounds(210, 260, 140, 50);
		registroBtn.setFont(botonFont);
		registroBtn.setBackground(Color.BLACK);
		registroBtn.setForeground(Color.ORANGE);
		registroBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
		registroBtn.setFocusPainted(false);
		registroBtn.addActionListener(e -> mostrarRegistro());
		panelPrincipal.add(registroBtn);

		JButton draftBtn = new JButton("DRAFT");
		draftBtn.setBounds(370, 260, 130, 50);
		draftBtn.setFont(botonFont);
		draftBtn.setBackground(Color.BLACK);
		draftBtn.setForeground(Color.CYAN);
		draftBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
		draftBtn.setFocusPainted(false);
		draftBtn.addActionListener(e -> {
		});
		panelPrincipal.add(draftBtn);

		JButton casinoBtn = new JButton("CASINO");
		casinoBtn.setBounds(50, 320, 450, 50);
		casinoBtn.setFont(botonFont);
		casinoBtn.setBackground(Color.BLACK);
		casinoBtn.setForeground(Color.RED);
		casinoBtn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		casinoBtn.setFocusPainted(false);
		casinoBtn.addActionListener(e -> mostrarCasino());
		panelPrincipal.add(casinoBtn);

		JButton sobresBtn = new JButton("MIS SOBRES");
		sobresBtn.setBounds(50, 390, 220, 55);
		sobresBtn.setFont(botonFont);
		sobresBtn.setBackground(Color.BLACK);
		sobresBtn.setForeground(Color.CYAN);
		sobresBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
		sobresBtn.setFocusPainted(false);
		sobresBtn.addActionListener(e -> mostrarMisSobres());
		panelPrincipal.add(sobresBtn);

		JButton cartasBtn = new JButton("MIS CARTAS");
		cartasBtn.setBounds(280, 390, 220, 55);
		cartasBtn.setFont(botonFont);
		cartasBtn.setBackground(Color.BLACK);
		cartasBtn.setForeground(Color.WHITE);
		cartasBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
		cartasBtn.setFocusPainted(false);
		cartasBtn.addActionListener(e -> mostrarMisCartas());
		panelPrincipal.add(cartasBtn);

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

	public void mostrarCasino() {
		getContentPane().removeAll();
		getContentPane().add(new CasinoPanel(this));
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

	public ImageIcon redimensionarIcono(String ruta, int ancho, int alto) {
		try {
			BufferedImage original = ImageIO.read(new File(ruta));
			Image redimensionada = original.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			return new ImageIcon(redimensionada);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
