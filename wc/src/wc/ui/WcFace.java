package wc.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import wc.model.CodeFile;
import wc.service.FileProcessService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

public class WcFace extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WcFace frame = new WcFace();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private final JButton exit = new JButton("\u9000\u51FA");
	private final JButton selectFile = new JButton("\u9009\u62E9\u6587\u4EF6");
	private final JButton WordNum = new JButton("\u8BA1\u7B97\u5B57\u7B26\u6570");
	private final JButton LinesNum = new JButton("\u8BA1\u7B97\u884C\u6570");
	
	private final JLabel totalLines = new JLabel("\u603B\u884C\u6570\uFF1A");
	private final JLabel codeLines = new JLabel("\u4EE3\u7801\u884C\uFF1A");
	private final JLabel NullLines = new JLabel("\u7A7A\u884C\uFF1A");
	private final JLabel CodeNum = new JLabel("\u5B57\u7B26\u6570\uFF1A");
	private final JLabel Annotation = new JLabel("\u6CE8\u91CA\u884C\uFF1A");

	/**
	 * Create the frame.
	 */
	public WcFace() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		exit.setBounds(270, 228, 154, 23);
		exit.setBackground(Color.LIGHT_GRAY);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
//		JLabel totalLines = new JLabel("\u603B\u884C\u6570\uFF1A");
		totalLines.setBounds(5, 105, 214, 24);
		totalLines.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.add(exit);
		contentPane.add(totalLines);
		
//		JLabel codeLines = new JLabel("\u4EE3\u7801\u884C\uFF1A");
		codeLines.setFont(new Font("宋体", Font.PLAIN, 20));
		codeLines.setBounds(5, 130, 214, 23);
		contentPane.add(codeLines);
		
//		JLabel NullLines = new JLabel("\u7A7A\u884C\uFF1A");
		NullLines.setFont(new Font("宋体", Font.PLAIN, 20));
		NullLines.setBounds(5, 154, 214, 23);
		contentPane.add(NullLines);
		
		textField = new JTextField();
		textField.setText("\u6587\u4EF6\u5730\u5740");
		textField.setBounds(53, 10, 209, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
//		JButton selectFile = new JButton("\u9009\u62E9\u6587\u4EF6");
		selectFile.setBounds(266, 10, 93, 23);
		contentPane.add(selectFile);
		
//		JLabel label_3 = new JLabel("\u5B57\u7B26\u6570\uFF1A");
		CodeNum.setFont(new Font("宋体", Font.PLAIN, 20));
		CodeNum.setBounds(5, 66, 214, 23);
		contentPane.add(CodeNum);
		
//		JButton WordNum = new JButton("\u8BA1\u7B97\u5B57\u7B26\u6570");
		WordNum.setBounds(270, 204, 154, 23);
		contentPane.add(WordNum);
		
//		JButton LinesNum = new JButton("\u8BA1\u7B97\u884C\u6570");
		LinesNum.setBounds(270, 180, 154, 23);
		contentPane.add(LinesNum);
		Annotation.setFont(new Font("宋体", Font.PLAIN, 20));
		Annotation.setBounds(5, 175, 108, 29);
		
		contentPane.add(Annotation);
		exit.addActionListener(this);
		selectFile.addActionListener(this);
		LinesNum.addActionListener(this);
		WordNum.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String soruceName=e.getActionCommand();
		
		if(soruceName.equals("选择文件")){// 监听事件
			
			FileDialog fd=new FileDialog(new JFrame());//弹出显示窗口
			fd.setVisible(true);
			textField.setText(fd.getDirectory()+fd.getFile());// 路径地址+文件名
			
		}else if(soruceName.equals("退出")){// 监听事件
			System.exit(0);//退出
		
		}else if(soruceName.equals("计算行数")){// 监听事件
			
			FileProcessService service=new FileProcessService();//业务操作类
			String fileName=textField.getText();//得到路径
			
			try{
				CodeFile code=service.getLines(fileName);
				
				totalLines.setText("总行数:" + code.getTotalLines());
				codeLines.setText("代码行："+code.getCodeLines());
				NullLines.setText("空行："+code.getNullLines());
				Annotation.setText("注释行："+code.getAnnotations());
				
				System.out.println("总数行="+code.getTotalLines());
				System.out.println("空行"+code.getNullLines());
				System.out.println("代码行"+code.getCodeLines());
				System.out.println("注释行"+code.getAnnotations());
			}catch(Exception el){
				el.printStackTrace();
			}
			
		}else if(soruceName.equals("计算字符数")){
			FileProcessService service=new FileProcessService();//业务操作类
			String fileLocation=textField.getText();//得到路径
			
			try {
				
				String str = service.StringFilter(FileProcessService.File2String(fileLocation));
				CodeNum.setText("字符数："+str.length());
				
			} catch (PatternSyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	
}
