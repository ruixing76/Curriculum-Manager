package classManager;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.Vector;

import javax.management.openmbean.OpenDataException;
import javax.naming.InitialContext;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;

public class Gui {

	public static void main(String[] args) {
		myframe ui = new myframe();
		 ui.init();
	}

}
class myframe extends JFrame{
			private JPanel jp1;
			private JPanel jp2;
			private JButton but1;
			private JButton but2;
			private JLabel jl;
			private int x=650;
			private int y=250;
			private int width=700;
			private int height=600;

		public myframe(){
		    super("�����ſ�ϵͳ");
		    setBounds(x, y, width, height);
		    setResizable(false);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setVisible(true);
		}
		void init(){
		    Container c=getContentPane(); 				
		    c.setLayout(null);  			
		    
		    jp1=new JPanel();
		    jp1.setBounds(0,0,700,200);
		    jp1.setVisible(true);			
		    jp1.setLayout(new FlowLayout(1,0,50));
		    c.add(jp1);
		    jp2=new JPanel();
		    jp2.setBounds(0,200,700,400);
		    jp2.setVisible(true);
		    jp2.setLayout(null);
		    c.add(jp2);
		    		    
		    jl=new JLabel("��ӭʹ�������ſ�ϵͳ��");
		    jl.setFont(new Font("����",Font.BOLD, 30));
		    jp1.add(jl);
		    
		    but1=new JButton("��ʼ");
		    but1.setFont(new Font( "����",Font.BOLD, 20));
		    but1.setBounds(300, 25, 100, 50);
		    but1.addActionListener(new Listener1()) ;
		    jp2.add(but1); 
		    
		    but2=new JButton("�˳�");
		    but2.setBounds(300, 175, 100, 50);
		    but2.setFont(new Font( "����",Font.BOLD, 20));
		    but2.addActionListener(new Listener2());
		    jp2.add(but2);
		    
		 }
class Listener1 implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		dispose();
		myframe1 fr1=new myframe1();
		fr1.init();
	}
}
class Listener2 implements ActionListener
{
	public void actionPerformed(ActionEvent e) {
		CourseManager creatCourse=new CourseManager();
		creatCourse.cleanTable();
	    dispose();
	}
}
class myframe1 extends JFrame{		 
		
		 
		  public myframe1(){
     	  super("�����ſ�ϵͳ");
		  setBounds(x, y, width, height);
		  setVisible(true);
		  setResizable(false);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		 }
		 void init()
		 {
		  JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP); //����
		  Container container = this.getLayeredPane();//�������
		  JPanel combop = new JPanel();//��ӭ����
		  JPanel p1 = new JPanel();
		  JPanel pp1=new JPanel();
		  JPanel p2 = new JPanel();     //�½�panel
		  JPanel pp2=new JPanel();
		  JPanel p3 = new JPanel();
		  JPanel pp3=new JPanel();
		  
		  p1.setLayout(new BorderLayout());
		  pp1.setLayout(null);						//����panel����
		  p2.setLayout(new BorderLayout());
		  pp2.setLayout(null);
		  p3.setLayout(new BorderLayout());
		  pp3.setLayout(null);
		  
		  tab.add(p1,"��������ά��");
		  tab.add(p2,"�Զ��ſ�");
		  tab.add(p3,"�鿴������༭");
		  
		  
		  JLabel jl=new JLabel("�����ſ�ϵͳ");
		  jl.setFont(new Font("����",Font.BOLD,30));
		  combop.add(jl);
		  //tips
		  JLabel jl1=new JLabel("��������ά������ʾ");
		  JLabel jl2=new JLabel("�Զ��ſΤ���ʾ");
		  JLabel jl3=new JLabel("�鿴������༭����ʾ");  
		  p1.add(jl1,BorderLayout.SOUTH);
		  p2.add(jl2,BorderLayout.SOUTH);
		  p3.add(jl3,BorderLayout.SOUTH); 
		  
		  JButton bt1=new JButton("����");
		  JButton bt2=new JButton("�����ſ�����");
		  JButton bt3=new JButton("�����ſ�����");
		  JButton bt4=new JButton("�Զ��ſ�");
		  JButton bt5=new JButton("�鿴���༭");
		  bt1.addActionListener(new Listener3());      //���� ������
		  bt2.addActionListener(new Listener4());		//���ð�ť����
		  bt3.addActionListener(new Listener5());
		  bt4.addActionListener(new Listener6());
		  bt5.addActionListener(new Listener7());
		  
		  bt1.setBounds(20, 20, 150, 60);
		  bt2.setBounds(190, 20, 150, 60);			//���ð�ť���ּ���С
		  bt3.setBounds(360, 20, 150, 60);
		  bt4.setBounds(20, 20, 150, 60);
		  bt5.setBounds(20, 20, 150, 60);
		  
		  p1.add(pp1,BorderLayout.CENTER);
		  pp1.add(bt1);                 		 //���밴ť
		  pp1.add(bt2);
		  pp1.add(bt3);
		  p2.add(pp2, BorderLayout.CENTER);
		  pp2.add(bt4);
		  p3.add(pp3, BorderLayout.CENTER);
		  pp3.add(bt5);
		 
		  
		  container.setLayout(new BorderLayout());
		  container.add(combop,BorderLayout.NORTH);
		  container.add(tab,BorderLayout.CENTER);
		  
		  
		  tab.setVisible(true);
		  
		 }
		 public void open1(){
				JFileChooser chooser = new JFileChooser();
			    chooser.setMultiSelectionEnabled(false);
			    String  []s = {"xls"};
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls", s);
			    chooser.setFileFilter(filter);
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setDialogTitle("ѡ��excel");
			    
			    int result = chooser.showOpenDialog(this);
			    if(result == JFileChooser.APPROVE_OPTION){
			     File file1 = chooser.getCurrentDirectory();
			     String filepath = file1.getPath();
			     String filename=chooser.getSelectedFile().getName();
			    
			     
			     //��������
			     FileOpreate dataimport=new FileOpreate(filepath + "\\" + filename);
			     boolean import_success = dataimport.excelToDB();
			     if (import_success){
			    	 JOptionPane.showMessageDialog(myframe1.this, "���ݳɹ����룡");
					}
			     }
			    }
		 public void open2(){
				JFileChooser chooser = new JFileChooser();
			    chooser.setMultiSelectionEnabled(false);
			    String  []s = {"xls"};
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls", s);
			    chooser.setFileFilter(filter);
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setDialogTitle("ѡ���ſ�����");
			    
			    int result = chooser.showOpenDialog(this);
			    if(result == JFileChooser.APPROVE_OPTION){
			     File file1 = chooser.getCurrentDirectory();
			     String filepath = file1.getPath();
			     String filename=chooser.getSelectedFile().getName();
			    
			     
			     //��������
			    /* dataimport = new StudentManager(filepath + "\\" + filename);
			     boolean import_success = dataimport.init();
			     if (import_success){
					jt.setText("���ݵ���ɹ���");
					}*/
			     }
			    }
class Listener3 implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		open1();
		}
      }	 
class Listener4 implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		open2();
		}
      }	 
class Listener5 implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		
		
		}
      }	 
class Listener6 implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		//�Զ��ſ�
		CourseManager creatCourse=new CourseManager();
		Course newCourse=new Course();
		String [] timeIDs;
		newCourse=creatCourse.readNext();
		int no=0;
		while(newCourse!=null)
		{
			timeIDs=creatCourse.getTimeID(newCourse.getMajor(),newCourse.getTeachingClass(),newCourse.getCourseID());
			for(int i=0;i<timeIDs.length;i++)
			{
				no++;
				newCourse.setNo(String.valueOf(no));
				newCourse.setTimeID(timeIDs[i]);
				newCourse.setClassRoomID(creatCourse.getClassroomID(newCourse.getTimeID()));
				creatCourse.uploadSingleTask(newCourse);
			}
			newCourse=creatCourse.readNext();
		}
      }	 
}
class Listener7 implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		test1 classTable=new test1();
		
		}
      }	 
	}
}
 




