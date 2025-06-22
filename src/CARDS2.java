import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
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
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CARDS2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JLabel fondoLabel;
	private final List<Sobre> sobresComprados = new ArrayList<>();
	private double dinero = 10000;
	private JLabel dineroLabel;
	private MisCartasPanel.CartaGuardada cartaGuardada;

	public CARDS2() {
		setTitle("CARDS 2");
		setIconImage(redimensionarIcono("img/icono_original.png", 64, 64).getImage());
		setSize(568, 538);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
		setLayout(null);

		cargarCartasGuardadas();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				guardarCartasAlSalir();
			}
		});

		fondoLabel = new JLabel(new ImageIcon("img/fondo.gif"));
		fondoLabel.setBounds(0, 0, 568, 500);
		fondoLabel.setLayout(null);
		add(fondoLabel);

		panelPrincipal = new JPanel(null);
		panelPrincipal.setOpaque(false);
		panelPrincipal.setBounds(0, 0, 568, 500);
		fondoLabel.add(panelPrincipal);

		Font orbFont;
		try {
			orbFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/RedHatDisplay-VariableFont_wght.ttf"))
					.deriveFont(80f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(orbFont);
		} catch (Exception e) {
			e.printStackTrace();
			orbFont = new Font("SansSerif", Font.BOLD, 64);
		}

		TituloAnimadoConBorde tituloAnimado = new TituloAnimadoConBorde("CARDS 2");
		tituloAnimado.setFont(orbFont);
		tituloAnimado.setBounds(0, 30, 520, 100);
		panelPrincipal.add(tituloAnimado);

		Color[] coloresBase = { Color.cyan, Color.pink, Color.magenta };
		final int[] index = { 0 };
		final float[] paso = { 0.0f };

		new Timer(30, e -> {
			Color c1 = coloresBase[index[0]];
			Color c2 = coloresBase[(index[0] + 1) % coloresBase.length];
			int r = (int) (c1.getRed() + paso[0] * (c2.getRed() - c1.getRed()));
			int g = (int) (c1.getGreen() + paso[0] * (c2.getGreen() - c1.getGreen()));
			int b = (int) (c1.getBlue() + paso[0] * (c2.getBlue() - c1.getBlue()));

			tituloAnimado.setBordeColor(new Color(r, g, b)); // SOLO EL BORDE CAMBIA

			paso[0] += 0.015f;
			if (paso[0] >= 1.0f) {
				paso[0] = 0.0f;
				index[0] = (index[0] + 1) % coloresBase.length;
			}
		}).start();

		dineroLabel = new JLabel("DINERO: " + String.format("%.2f", dinero) + "€");
		dineroLabel.setForeground(Color.magenta);
		dineroLabel.setFont(new Font("RedHatDisplay-VariableFont_wght.ttf", Font.BOLD, 16));
		dineroLabel.setBounds(10, 460, 400, 30);
		// panelPrincipal.add(dineroLabel);

		Font popThin;
		try {
			popThin = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Nabla-Regular-VariableFont_EDPT,EHLT.ttf"))
					.deriveFont(80f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(popThin);
		} catch (Exception e) {
			e.printStackTrace();
			popThin = new Font("SansSerif", Font.BOLD, 64);
		}

		Font botonFont = popThin.deriveFont(22f);

		// BOTÓN TIENDA
		JButton tiendaBtn = new JButton("TIENDA");
		tiendaBtn.setBounds(50, 200, 450, 50);
		tiendaBtn.setFont(botonFont);
		tiendaBtn.setForeground(Color.GREEN);
		tiendaBtn.setBackground(new Color(0, 0, 0, 100));
		tiendaBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
		tiendaBtn.setFocusPainted(false);
		tiendaBtn.setContentAreaFilled(false);
		tiendaBtn.setOpaque(false);
		tiendaBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tiendaBtn.addActionListener(e -> mostrarTienda());
		tiendaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				tiendaBtn.setForeground(Color.WHITE);
				tiendaBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				tiendaBtn.setForeground(Color.GREEN);
				tiendaBtn.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
			}
		});
		panelPrincipal.add(tiendaBtn);

		// BOTÓN SBC
		JButton sbcBtn = new JButton("SBC");
		sbcBtn.setBounds(50, 260, 140, 50);
		sbcBtn.setFont(botonFont);
		sbcBtn.setForeground(Color.MAGENTA);
		sbcBtn.setBackground(new Color(0, 0, 0, 100));
		sbcBtn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2, true));
		sbcBtn.setFocusPainted(false);
		sbcBtn.setContentAreaFilled(false);
		sbcBtn.setOpaque(false);
		sbcBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sbcBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				sbcBtn.setForeground(Color.WHITE);
				sbcBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				sbcBtn.setForeground(Color.MAGENTA);
				sbcBtn.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2, true));
			}
		});
		sbcBtn.addActionListener(e -> {
		});
		panelPrincipal.add(sbcBtn);

		// BOTÓN REGISTRO
		JButton registroBtn = new JButton("REGISTRO");
		registroBtn.setBounds(210, 260, 140, 50);
		registroBtn.setFont(botonFont);
		registroBtn.setForeground(Color.ORANGE);
		registroBtn.setBackground(new Color(0, 0, 0, 100));
		registroBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, true));
		registroBtn.setFocusPainted(false);
		registroBtn.setContentAreaFilled(false);
		registroBtn.setOpaque(false);
		registroBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registroBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				registroBtn.setForeground(Color.WHITE);
				registroBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				registroBtn.setForeground(Color.ORANGE);
				registroBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, true));
			}
		});
		registroBtn.addActionListener(e -> mostrarRegistro());
		panelPrincipal.add(registroBtn);

		// BOTÓN DRAFT
		JButton draftBtn = new JButton("DRAFT");
		draftBtn.setBounds(370, 260, 130, 50);
		draftBtn.setFont(botonFont);
		draftBtn.setForeground(Color.BLUE);
		draftBtn.setBackground(new Color(0, 0, 0, 100));
		draftBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
		draftBtn.setFocusPainted(false);
		draftBtn.setContentAreaFilled(false);
		draftBtn.setOpaque(false);
		draftBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		draftBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				draftBtn.setForeground(Color.WHITE);
				draftBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				draftBtn.setForeground(Color.BLUE);
				draftBtn.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
			}
		});
		draftBtn.addActionListener(e -> {
		});
		panelPrincipal.add(draftBtn);

		// BOTÓN CASINO
		JButton casinoBtn = new JButton("CASINO");
		casinoBtn.setBounds(50, 320, 450, 50);
		casinoBtn.setFont(botonFont);
		casinoBtn.setForeground(Color.RED);
		casinoBtn.setBackground(new Color(0, 0, 0, 100));
		casinoBtn.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		casinoBtn.setFocusPainted(false);
		casinoBtn.setContentAreaFilled(false);
		casinoBtn.setOpaque(false);
		casinoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		casinoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				casinoBtn.setForeground(Color.WHITE);
				casinoBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				casinoBtn.setForeground(Color.RED);
				casinoBtn.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
			}
		});
		casinoBtn.addActionListener(e -> mostrarCasino());
		panelPrincipal.add(casinoBtn);

		// BOTÓN MIS SOBRES
		JButton sobresBtn = new JButton("MIS SOBRES");
		sobresBtn.setBounds(50, 390, 220, 55);
		sobresBtn.setFont(botonFont);
		sobresBtn.setForeground(Color.CYAN);
		sobresBtn.setBackground(new Color(0, 0, 0, 100));
		sobresBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
		sobresBtn.setFocusPainted(false);
		sobresBtn.setContentAreaFilled(false);
		sobresBtn.setOpaque(false);
		sobresBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sobresBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				sobresBtn.setForeground(Color.WHITE);
				sobresBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				sobresBtn.setForeground(Color.CYAN);
				sobresBtn.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2, true));
			}
		});
		sobresBtn.addActionListener(e -> mostrarMisSobres());
		panelPrincipal.add(sobresBtn);

		// BOTÓN MIS CARTAS
		JButton cartasBtn = new JButton("MIS CARTAS");
		cartasBtn.setBounds(280, 390, 220, 55);
		cartasBtn.setFont(botonFont);
		cartasBtn.setForeground(Color.WHITE);
		cartasBtn.setBackground(new Color(0, 0, 0, 100));
		cartasBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		cartasBtn.setFocusPainted(false);
		cartasBtn.setContentAreaFilled(false);
		cartasBtn.setOpaque(false);
		cartasBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cartasBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				cartasBtn.setForeground(Color.YELLOW);
				cartasBtn.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2, true));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				cartasBtn.setForeground(Color.WHITE);
				cartasBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
			}
		});
		cartasBtn.addActionListener(e -> mostrarMisCartas());
		panelPrincipal.add(cartasBtn);

		add(fondoLabel, BorderLayout.CENTER);
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
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		JPanel tienda = new TiendaPanel(this);
		tienda.setBounds(0, 0, 568, 500);
		fondoLabel.add(tienda);

		fondoLabel.repaint();
		fondoLabel.revalidate();
	}

	public void mostrarMisSobres() {
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		JPanel sobres = new MisSobresPanel(this, sobresComprados);
		sobres.setBounds(0, 0, 568, 500);
		fondoLabel.add(sobres);

		fondoLabel.repaint();
		fondoLabel.revalidate();
	}

	public void mostrarMisCartas() {
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		JPanel cartas = new MisCartasPanel(this, cartaGuardada.getCartasPorCalidad());
		cartas.setBounds(0, 0, 568, 500);
		fondoLabel.add(cartas);

		fondoLabel.repaint();
		fondoLabel.revalidate();
	}

	public void mostrarRegistro() {
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		JPanel registro = new RegistroPanel(this);
		registro.setBounds(0, 0, 568, 500);
		fondoLabel.add(registro);

		fondoLabel.repaint();
		fondoLabel.revalidate();
	}

	public void mostrarCasino() {
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		JPanel casino = new CasinoPanel(this);
		casino.setBounds(0, 0, 568, 500);
		fondoLabel.add(casino);

		fondoLabel.repaint();
		fondoLabel.revalidate();
	}

	public void mostrarVentanaCarta(Carta carta) {
		JDialog ventanaCarta = new JDialog(this, "Carta", true);
		ventanaCarta.setSize(420, 440);
		ventanaCarta.setLocationRelativeTo(this);
		ventanaCarta.setResizable(false);
		ventanaCarta.getContentPane().setBackground(Color.BLACK);
		ventanaCarta.setLayout(new BorderLayout());

		Color color = ColoresCalidad.obtener(carta.getCalidad());

		// ASCII
		JTextArea ascii = new JTextArea(carta.getAscii());
		ascii.setFont(new Font("Courier New", Font.PLAIN, 8));
		ascii.setMargin(new Insets(5, 10, 5, 10));
		ascii.setForeground(color);
		ascii.setBackground(Color.BLACK);
		ascii.setEditable(false);
		ascii.setFocusable(false);
		ascii.setLineWrap(false);
		ascii.setWrapStyleWord(false);

		JPanel centro = new JPanel(new GridBagLayout());
		centro.setBackground(Color.BLACK);
		centro.add(ascii);

		JScrollPane scroll = new JScrollPane(centro);
		scroll.setBorder(null);
		scroll.getViewport().setBackground(Color.BLACK);

		JLabel info = new JLabel(carta.getResumenTitulo(), SwingConstants.CENTER);
		info.setFont(new Font("Monospaced", Font.BOLD, 16));
		info.setForeground(color);
		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLayeredPane capa = new JLayeredPane();
		capa.setPreferredSize(new Dimension(400, 350));
		scroll.setBounds(0, 0, 400, 350);
		capa.add(scroll, JLayeredPane.DEFAULT_LAYER);

		// Panel de brillos personalizado
		class BrilloAnimadoPanel extends JPanel {

			private static final long serialVersionUID = 1L;
			int frame = 0;
			String[] simbolos = { " ", " ", " ", " ", " ", " ", " ", " ", " ", "✦", "✨", " ", " ", " " };
			String[] simbolos2 = { " ", " ", " ", "✦", "✨", " ", " ", " ", " ", " ", " ", " ", " ", " " };

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				String calidad = carta.getCalidad().toUpperCase();

				String s = simbolos[frame % simbolos.length];
				String s2 = simbolos2[frame % simbolos2.length];
				g.setFont(new Font("Dialog", Font.PLAIN, 14));

				switch (calidad) {
				case "COMÚN":
					g.setColor(Color.WHITE);
					g.drawString(s, 120, 57); // superior izquierda
					g.drawString(s2, getWidth() - 170, 200); // superior derecha
					break;
				// Aquí podrías añadir más rarezas con más brillos y colores
				}
			}
		}

		BrilloAnimadoPanel animacionPanel = new BrilloAnimadoPanel();
		animacionPanel.setOpaque(false);
		animacionPanel.setBounds(0, 0, 400, 350);
		capa.add(animacionPanel, JLayeredPane.PALETTE_LAYER);

		// Animación si es COMÚN (puedes ampliar para otras calidades)
		Timer t = null;
		if (carta.getCalidad().equalsIgnoreCase("COMÚN")) {
			t = new Timer(250, e -> {
				animacionPanel.frame++;
				animacionPanel.repaint();
			});
			t.start();

			Timer finalT = t;
			ventanaCarta.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					finalT.stop();
				}

				public void windowClosed(java.awt.event.WindowEvent e) {
					finalT.stop();
				}
			});
		}

		ventanaCarta.add(capa, BorderLayout.CENTER);
		ventanaCarta.add(info, BorderLayout.SOUTH);
		ventanaCarta.setVisible(true);
	}

	public void volverAlMenu() {
		actualizarDineroLabel();
		fondoLabel.removeAll();
		fondoLabel.setLayout(null);

		panelPrincipal.setBounds(0, 0, 568, 500);
		fondoLabel.add(panelPrincipal);

		fondoLabel.repaint();
		fondoLabel.revalidate();
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

	private final Image icono = new ImageIcon("img/icono_original.png").getImage();

	public Image getIcono() {
		return icono;
	}

	class TituloAnimadoConBorde extends JLabel {
		private static final long serialVersionUID = 1L;
		private Color bordeColor = Color.WHITE;

		public TituloAnimadoConBorde(String texto) {
			super(texto, SwingConstants.CENTER);
			setOpaque(false);
		}

		public void setBordeColor(Color c) {
			this.bordeColor = c;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Font font = getFont();
			String text = getText();

			FontMetrics fm = g2.getFontMetrics(font);
			int x = (getWidth() - fm.stringWidth(text)) / 2;
			int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

			// Obtener contorno del texto
			var gv = font.createGlyphVector(g2.getFontRenderContext(), text);
			var shape = gv.getOutline(x, y);

			// Dibujar borde del texto
			g2.setColor(bordeColor);
			g2.setStroke(new java.awt.BasicStroke(2)); // Grosor del contorno
			g2.draw(shape); // solo borde

			// No se rellena el interior → transparencia total

			g2.dispose();
		}
	}

}
