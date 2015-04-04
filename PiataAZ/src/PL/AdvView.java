package PL;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Entities.Advertisment;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AdvView extends JFrame {

	private JPanel contentPane;
	private Advertisment adv;
	public AdvView(Advertisment adv) {
		this.adv = adv;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 13, 352, 56);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		textArea.setText(adv.getDescription());
		BufferedImage img = adv.getImg();
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(12, 81, 408, 250);
		lblImage.setIcon(new ImageIcon(scale(img,408,250)));
		contentPane.add(lblImage);
		
	}
	
	private BufferedImage scale(BufferedImage src, int w, int h) {
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        // Fill background for scale to fit.
        g2.setBackground(UIManager.getColor("Panel.background"));
        g2.clearRect(0,0,w,h);
        double xScale = (double)w/src.getWidth();
        double yScale = (double)h/src.getHeight();
        // Scaling options:
        // Scale to fit - image just fits in label.
        double scale = Math.min(xScale, yScale);
        // Scale to fill - image just fills label.
        //double scale = Math.max(xScale, yScale);
        int width  = (int)(scale*src.getWidth());
        int height = (int)(scale*src.getHeight());
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(src, x, y, width, height, null);
        g2.dispose();
        return dst;
    }
}
