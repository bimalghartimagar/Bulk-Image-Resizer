package com.imageresize.app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.naming.SizeLimitExceededException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

public class ImageResizeApp extends JFrame  implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4300850650438543827L;
	private JPanel contentPane;
	private JPanel panel_1 = new JPanel();
	private JPanel panel = new JPanel();
	private JTextField textFieldSource;
	private JTextField textFieldDestination;
	private JTextField textFieldPercentage;
	private JTextField textFieldHeight;
	private JTextField textFieldWidth;
	private JRadioButton rdbtnPxpixel = new JRadioButton("px (Pixel)");
	private JRadioButton rdbtnpercentage = new JRadioButton("% (Percentage)");
	private JButton btnSourceBrowse = new JButton("Browse");
	private JButton btnDescBrowse = new JButton("Browse");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static 	JTextArea textAreaProcess = new JTextArea();

	private ImageResize imgResizeObj = new ImageResize();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageResizeApp frame = new ImageResizeApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImageResizeApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 551);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSourceFolderLocation = new JLabel("Source Folder:");
		lblSourceFolderLocation.setBounds(25, 54, 110, 15);
		lblSourceFolderLocation.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		contentPane.add(lblSourceFolderLocation);
		
		textFieldSource = new JTextField();
		textFieldSource.setBounds(171, 52, 234, 19);
		contentPane.add(textFieldSource);
		textFieldSource.setColumns(10);
		
		JLabel lblDestinationFolder = new JLabel("Destination Folder:");
		lblDestinationFolder.setBounds(22, 98, 146, 15);
		lblDestinationFolder.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		contentPane.add(lblDestinationFolder);
		
		textFieldDestination = new JTextField();
		textFieldDestination.setBounds(171, 96, 234, 19);
		contentPane.add(textFieldDestination);
		textFieldDestination.setColumns(10);
		btnSourceBrowse.setBounds(412, 49, 117, 25);
		contentPane.add(btnSourceBrowse);
		btnDescBrowse.setBounds(412, 93, 117, 25);
		contentPane.add(btnDescBrowse);
		
		JLabel lblImageResizer = new JLabel("Bulk Image Resizer");
		lblImageResizer.setBounds(191, 12, 158, 25);
		lblImageResizer.setFont(new Font("Bitstream Charter", Font.BOLD, 18));
		contentPane.add(lblImageResizer);
		
		JLabel lblResizeBy = new JLabel("Resize By:");
		lblResizeBy.setBounds(25, 137, 85, 15);
		lblResizeBy.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		contentPane.add(lblResizeBy);
		
		buttonGroup.add(rdbtnpercentage);
		rdbtnpercentage.setBounds(45, 164, 133, 23);
		rdbtnpercentage.setBackground(new Color(0, 204, 51));
		contentPane.add(rdbtnpercentage);
		panel.setBounds(35, 191, 234, 90);
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(0, 204, 51));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPercentage = new JLabel("Percentage:");
		lblPercentage.setBounds(12, 30, 92, 15);
		panel.add(lblPercentage);
		
		textFieldPercentage = new JTextField();
		textFieldPercentage.setText("");
		textFieldPercentage.setBounds(105, 28, 36, 19);
		panel.add(textFieldPercentage);
		textFieldPercentage.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setBounds(146, 30, 21, 15);
		panel.add(label);
		panel_1.setBounds(281, 191, 234, 90);
		
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(0, 204, 51));
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(12, 30, 57, 15);
		panel_1.add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(12, 62, 57, 15);
		panel_1.add(lblWidth);
		
		textFieldHeight = new JTextField();
		textFieldHeight.setBounds(76, 26, 114, 19);
		panel_1.add(textFieldHeight);
		textFieldHeight.setColumns(10);
		
		textFieldWidth = new JTextField();
		textFieldWidth.setBounds(76, 58, 114, 19);
		panel_1.add(textFieldWidth);
		textFieldWidth.setColumns(10);
		
		JLabel lblPx = new JLabel("px");
		lblPx.setBounds(194, 28, 24, 15);
		panel_1.add(lblPx);
		
		JLabel lblPx_1 = new JLabel("px");
		lblPx_1.setBounds(194, 60, 24, 15);
		panel_1.add(lblPx_1);
		
		JButton btnResize = new JButton("Resize");
		btnResize.setBounds(212, 402, 117, 25);
		contentPane.add(btnResize);
		
		textFieldPercentage.setEditable(false);
		textFieldHeight.setEditable(false);
		textFieldWidth.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 123, 504, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 293, 504, 8);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(25, 390, 504, 2);
		contentPane.add(separator_2);
		
		JLabel lblResizeType = new JLabel("Resize Type:");
		lblResizeType.setBounds(25, 303, 110, 15);
		lblResizeType.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		contentPane.add(lblResizeType);
		
		JRadioButton rdbtnFast = new JRadioButton("Fast");
		buttonGroup_1.add(rdbtnFast);
		rdbtnFast.setBounds(28, 330, 85, 15);
		rdbtnFast.setBackground(new Color(0, 204, 51));
		contentPane.add(rdbtnFast);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 439, 531, 81);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(textAreaProcess);
		
		JRadioButton rdbtnSmooth = new JRadioButton("Smooth");
		buttonGroup_1.add(rdbtnSmooth);
		rdbtnSmooth.setBounds(28, 360, 85, 15);
		rdbtnSmooth.setBackground(new Color(0, 204, 51));
		contentPane.add(rdbtnSmooth);
		
		btnSourceBrowse.addActionListener(this);
		btnDescBrowse.addActionListener(this);
		
		rdbtnpercentage.addActionListener(this);
		
		rdbtnFast.addActionListener(this);
		rdbtnFast.setSelected(true);
		
		buttonGroup.add(rdbtnPxpixel);
		rdbtnPxpixel.setBounds(285, 164, 96, 23);
		contentPane.add(rdbtnPxpixel);
		rdbtnPxpixel.setBackground(new Color(0, 204, 51));
		rdbtnPxpixel.addActionListener(this);
		rdbtnSmooth.addActionListener(this);
		
		btnResize.addActionListener(this);

	}//constructor ending

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedItem = e.getActionCommand();

		if(e.getSource().equals(btnSourceBrowse)){
			JFileChooser sourceFileChooser = new JFileChooser();
			sourceFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = sourceFileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				textFieldSource.setText(sourceFileChooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		if(e.getSource().equals(btnDescBrowse)){
			JFileChooser descFileChooser = new JFileChooser();
			descFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = descFileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				textFieldDestination.setText(descFileChooser.getSelectedFile().getAbsolutePath());
			}
		}
		
		switch (selectedItem) {
		case "% (Percentage)":
			textFieldPercentage.setEditable(true);
			panel.setBackground(new Color(0, 153, 0));
			textFieldHeight.setEditable(false);
			textFieldWidth.setEditable(false);
			panel_1.setBackground(new Color(0, 204, 51));
			break;

		case "px (Pixel)":
			textFieldPercentage.setEditable(false);
			panel.setBackground(new Color(0, 204, 51));
			textFieldHeight.setEditable(true);
			textFieldWidth.setEditable(true);
			panel_1.setBackground(new Color(0, 153, 0));
			break;
			
		case "Resize":
			if(textFieldSource.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Source location cannot be empty","Required Field",JOptionPane.ERROR_MESSAGE);
			}
			
			if(textFieldDestination.getText().trim().isEmpty()){
				JOptionPane.showMessageDialog(this, "Destination location cannot be empty","Required Field",JOptionPane.ERROR_MESSAGE);

			}
			
			if(!buttonGroup.isSelected(null)){
				
				if(rdbtnpercentage.isSelected()){
					if(textFieldPercentage.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(this, "Percentage field cannot be empty","Required Field",JOptionPane.ERROR_MESSAGE);
					}
					else{
						try{
							int percentage = Integer.parseInt(textFieldPercentage.getText().toString());
							int resizeType = 1;
							if(percentage>100){
								throw new SizeLimitExceededException();
							}
							imgResizeObj.resizeImageByPercentage(textFieldSource.getText(), textFieldDestination.getText(), percentage, resizeType);
						}
						catch (NumberFormatException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "Enter Valid Percentage value","Required Field",JOptionPane.ERROR_MESSAGE);					
						
						}
						catch (IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "Folder location invalid","Required Field",JOptionPane.ERROR_MESSAGE);
						}
						catch (SizeLimitExceededException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(this, "Percentage value cannot be greater than 100%","Required Field",JOptionPane.ERROR_MESSAGE);

						}
					}
				}
				
				if(rdbtnPxpixel.isSelected()){
					if(textFieldHeight.getText().trim().isEmpty() || textFieldWidth.getText().trim().isEmpty()){
						JOptionPane.showMessageDialog(this, "Height or Width field cannot be empty","Required Field",JOptionPane.ERROR_MESSAGE);
					}
					else{
						try{
							int height = Integer.parseInt(textFieldHeight.getText().toString());
							int width = Integer.parseInt(textFieldWidth.getText().toString());
							int resizeType = 1;
							imgResizeObj.resizeImageByPixel(textFieldSource.getText(), textFieldDestination.getText(), height, width, resizeType);
						}
						catch (NumberFormatException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "Enter valid Height or Width value","Required Field",JOptionPane.ERROR_MESSAGE);					
						}
						catch (IOException e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "Folder location invalid","Required Field",JOptionPane.ERROR_MESSAGE);
						}
						catch (IllegalArgumentException e2) {
							String error = e.getActionCommand();
							System.out.println(error);
							switch (error) {
							case "height":
								JOptionPane.showMessageDialog(this, "Height cannot be greater than original image","Required Field",JOptionPane.ERROR_MESSAGE);
								break;

							case "width":
								JOptionPane.showMessageDialog(this, "Width cannot be greater than original image","Required Field",JOptionPane.ERROR_MESSAGE);

								break;
							}
						}
					}
				}
			}
					
			
			break;
			
		}//switch ending	
	
	}//actionPerformed ending
	
	
}//class ending	