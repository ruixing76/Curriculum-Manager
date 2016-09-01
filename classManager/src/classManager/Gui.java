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
		    super("良辰排课系统");
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
		    		    
		    jl=new JLabel("欢迎使用良辰排课系统！");
		    jl.setFont(new Font("楷体",Font.BOLD, 30));
		    jp1.add(jl);
		    
		    but1=new JButton("开始");
		    but1.setFont(new Font( "楷体",Font.BOLD, 20));
		    but1.setBounds(300, 25, 100, 50);
		    but1.addActionListener(new Listener1()) ;
		    jp2.add(but1); 
		    
		    but2=new JButton("退出");
		    but2.setBounds(300, 175, 100, 50);
		    but2.setFont(new Font( "楷体",Font.BOLD, 20));
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
     	  super("智能排课系统");
		  setBounds(x, y, width, height);
		  setVisible(true);
		  setResizable(false);
		  setDefaultCloseOperation(EXIT_ON_CLOSE);
		 }
		 void init()
		 {
		  JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP); //容器
		  Container container = this.getLayeredPane();//对象化面板
		  JPanel combop = new JPanel();//欢迎标语
		  JPanel p1 = new JPanel();
		  JPanel pp1=new JPanel();
		  JPanel p2 = new JPanel();     //新建panel
		  JPanel pp2=new JPanel();
		  JPanel p3 = new JPanel();
		  JPanel pp3=new JPanel();
		  
		  p1.setLayout(new BorderLayout());
		  pp1.setLayout(null);						//设置panel布局
		  p2.setLayout(new BorderLayout());
		  pp2.setLayout(null);
		  p3.setLayout(new BorderLayout());
		  pp3.setLayout(null);
		  
		  tab.add(p1,"基本数据维护");
		  tab.add(p2,"自动排课");
		  tab.add(p3,"查看结果并编辑");
		  
		  
		  JLabel jl=new JLabel("良辰排课系统");
		  jl.setFont(new Font("楷体",Font.BOLD,30));
		  combop.add(jl);
		  //tips
		  JLabel jl1=new JLabel("基本数据维护の提示");
		  JLabel jl2=new JLabel("自动排课の提示");
		  JLabel jl3=new JLabel("查看结果并编辑の提示");  
		  p1.add(jl1,BorderLayout.SOUTH);
		  p2.add(jl2,BorderLayout.SOUTH);
		  p3.add(jl3,BorderLayout.SOUTH); 
		  
		  JButton bt1=new JButton("导入");
		  JButton bt2=new JButton("导入排课任务");
		  JButton bt3=new JButton("保存排课任务");
		  JButton bt4=new JButton("自动排课");
		  JButton bt5=new JButton("查看并编辑");
		  bt1.addActionListener(new Listener3());      //导入 监听器
		  bt2.addActionListener(new Listener4());		//设置按钮监听
		  bt3.addActionListener(new Listener5());
		  bt4.addActionListener(new Listener6());
		  bt5.addActionListener(new Listener7());
		  
		  bt1.setBounds(20, 20, 150, 60);
		  bt2.setBounds(190, 20, 150, 60);			//设置按钮布局及大小
		  bt3.setBounds(360, 20, 150, 60);
		  bt4.setBounds(20, 20, 150, 60);
		  bt5.setBounds(20, 20, 150, 60);
		  
		  p1.add(pp1,BorderLayout.CENTER);
		  pp1.add(bt1);                 		 //加入按钮
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
			    chooser.setDialogTitle("选择excel");
			    
			    int result = chooser.showOpenDialog(this);
			    if(result == JFileChooser.APPROVE_OPTION){
			     File file1 = chooser.getCurrentDirectory();
			     String filepath = file1.getPath();
			     String filename=chooser.getSelectedFile().getName();
			    
			     
			     //导入数据
			     FileOpreate dataimport=new FileOpreate(filepath + "\\" + filename);
			     boolean import_success = dataimport.excelToDB();
			     if (import_success){
			    	 JOptionPane.showMessageDialog(myframe1.this, "数据成功导入！");
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
			    chooser.setDialogTitle("选择排课任务");
			    
			    int result = chooser.showOpenDialog(this);
			    if(result == JFileChooser.APPROVE_OPTION){
			     File file1 = chooser.getCurrentDirectory();
			     String filepath = file1.getPath();
			     String filename=chooser.getSelectedFile().getName();
			    
			     
			     //导入数据
			    /* dataimport = new StudentManager(filepath + "\\" + filename);
			     boolean import_success = dataimport.init();
			     if (import_success){
					jt.setText("数据导入成功！");
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
		//自动排课
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
 




