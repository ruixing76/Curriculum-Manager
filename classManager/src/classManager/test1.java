package classManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;  
	   
class test1 extends JFrame {  
	     //手动排课中，被删除的课程记录
		 ArrayList<Course> deletedCourse =new ArrayList();
		 
	     // 弹出菜单的实现，弹出菜单是一个可弹出并显示一系列选项的小窗口  
	     JPopupMenu popupMenu;   
	     MenuBar mb;  
	     Menu menuMajor,menuClass,menuNull1,menuNull2;
	     
	     //课程排课结果的显示窗口，包含一个JTable
	     JTable table;           
	     String[] columnNames={"","一","二","三","四","五"};
	     String[][] data=    {{"12","123","","","",""},
		                      {"34","","","","",""},
                              {"56","","","","",""},
                              {"78","","","","",""}};
	     
	     JPanel panelMenu;
	     JPanel informationHeadPanel;
	     JPanel panelTip;
	     JScrollPane informationBodyPanel;
	     JTable informationTable;    //已删除的课程表格
	     
	     JPanel panelCondition;
	     JScrollPane scrollPane;  //定义滚动条面板(用以使表格可以滚动)
	     
	     
	     //下拉列表选项
	     JComboBox<String> majorSelect;
	     JComboBox<String> classSelect;
	     JMenuItem deleteCourse;
	     JMenuItem addCourse;
	     
	     String SelectedMajor;
	     String SelectedTeachingClass;
	     
	     int selectedRow;int selectedCol; //选中的课程的坐标
	     String []tableHead={"courseName(课程名)","courseID(课程号)"};
    	 String [][]tableBody={{"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""},
    			 {"",""}};
    	 int deletedCourseNum=0;
	     	     
    	 //构建窗体
	     public test1() {  
	         super("课表"); // 调用父类构造函数  `
	         setSize(835,500);
		 	 setLocation(600,300);
		 	 setVisible(true);         //设置窗口可见
		 	 setResizable(false);       //设置窗口大小可变  
	         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序 
	         setLayout(null);
	         
	         // 实例化弹出菜单   
	         popupMenu = new JPopupMenu();   
	         // 增加菜单项到菜单上  
	         deleteCourse=new JMenuItem("删除课程");
	         addCourse=new JMenuItem("添加或替换课程");
	         popupMenu.add(new JLabel("菜单项"));
	         popupMenu.addSeparator();
	         popupMenu.add(deleteCourse);   
	         popupMenu.addSeparator();
	         popupMenu.add(addCourse);  
	         deleteCourseItem();
	         addCourseItem();
	         
	         //专业和教学班的选择下拉菜单
	         majorSelect=new JComboBox<String>(); 
	         majorSelect.setBorder(BorderFactory.createTitledBorder("选择专业:"));
	         classSelect=new JComboBox<String>();
	         classSelect.setBorder(BorderFactory.createTitledBorder("选择班级："));
	         //已经被选择的专业和班级
	         SelectedMajor=new String();
	         SelectedTeachingClass=new String();   
     
	         addMajorSelect();                            //添加所有专业名称	         
	         majorItem();                                 //点击专业，添加该专业的所有教学班名称  
	         classItem();                                 //点击班级，添加该教学班的所有课程 
	         
	         panelMenu=new JPanel();
	         panelMenu.setLayout(new GridLayout(1, 2));
	         panelMenu.setBounds(0, 0, 300, 50);
	         panelMenu.add(majorSelect);
	         panelMenu.add(classSelect);
	         add(panelMenu);
	         
	         
	         table=new JTable(data,columnNames){
	    		  public boolean iscellEditable(int row,int column)
	    		  {
	    			  return true;
	    		  }
	    	  };
	    	  table.setRowHeight(59);

	  

	         String val=(String) table.getModel().getValueAt(2, 1);//获取某个单元格的值
	    	 //System.out.println(val);
	    	 
	    	 setMenuBar(mb);
	    	 
	    	 scrollPane=new JScrollPane();
	    	 scrollPane.setBounds(2,50, 831, 250);  
	    	 table.setFillsViewportHeight(true);  //表格大到足以填充封闭视图的全部高度
	    	 scrollPane.setViewportView(table);    	
	    	 add(scrollPane);
	    	 
	    	 popMenuEvent();       //弹出菜单的事件处理
	    	 
	    	 informationHeadPanel=new JPanel();
	    	 informationHeadPanel.setLayout(new FlowLayout(1,200,10));
	    	 informationHeadPanel.setBounds(0, 310,400,35);
	    	 informationHeadPanel.setVisible(true);
	    	 informationHeadPanel.setBackground(Color.green);
	    	 JLabel informationHead=new JLabel("*******************该班级为待排的课程*******************");
	    	 informationHeadPanel.add(informationHead);
	    	 add(informationHeadPanel);
	    	 informationBodyPanel=new JScrollPane();
	    	 informationBodyPanel.setBounds(0, 346, 400, 95);
	    	 informationBodyPanel.setVisible(true);
	    	 informationBodyPanel.setBackground(Color.cyan);
	    	 informationBodyPanel.setBorder(null);
	    	 
	    	 informationTable=new JTable(tableBody,tableHead);
	    	 informationTable.setRowHeight(25);
	    	 informationTable.setVisible(true);
	    	 informationTable.setFillsViewportHeight(true);
	    	 informationBodyPanel.setViewportView(informationTable);
	    	 add(informationBodyPanel);
	    	 
	    	 panelTip=new JPanel();
	    	 panelTip.setLayout(new FlowLayout(1,200,10));
	    	 panelTip.setBounds(400, 310,428,130);
	    	 panelTip.setVisible(true);
	    	 panelTip.setBackground(Color.gray);
	    	 JLabel tipHead=new JLabel("********************这里显示提示*********************");
	    	 panelTip.add(tipHead);
	    	 JTextArea jta=new JTextArea();
	    	 jta.setText("啊啊啊啊啊啊啊啊啊啊啊啊啊");
	    	 jta.setFont(new Font("宋体", Font.BOLD, 20));
	    	 jta.setEditable(false);
	    	 panelTip.add(jta);
	    	 add(panelTip);
	    	 
	    	 panelCondition=new JPanel();
	    	 panelCondition.setLayout(new FlowLayout(0,10,1));
	    	 panelCondition.setBounds(0, 440, 835, 60);
	    	 panelCondition.setBackground(Color.lightGray);
	    	 JLabel jl2=new JLabel("当前状态:blah blah blah blah blah blah blah blah blah blah blah blah...");
	    	 panelCondition.add(jl2);
	    	 add(panelCondition);
	     }
	     
	     //绑定专业选择BOX的监听器
	     private void majorItem(){
	     majorSelect.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED){
						classSelect.removeAllItems();				
						CourseManager creatCourse=new CourseManager();
						SelectedMajor = majorSelect.getSelectedItem().toString();
						//SelectedMajor="hello";
						//System.out.println(SelectedMajor);
						
					   	Vector<String> teachingClass=new Vector<String>();
					   	teachingClass=creatCourse.getTeachingClassByMajor(SelectedMajor);
					    for(int i=0;i<teachingClass.size();i++)
				    	{
					   		classSelect.addItem(teachingClass.get(i));
					   	}
					}
					
				}
			});
	     }
	     //绑定班级选择BOX的监听器
	     private void classItem(){
		     classSelect.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED) //??????????????????
			            {
							SelectedTeachingClass=classSelect.getSelectedItem().toString();
						CourseManager creatCourse=new CourseManager();
						Vector<Course> SelectedCourse=new Vector<>();
						SelectedCourse=creatCourse.getCourseByClass(SelectedMajor, SelectedTeachingClass);
						cleanData(); //清空所有信息
						String courseName=new String();
						for(int i=0;i<SelectedCourse.size();i++){
							int whichDay=0;
							int whichPiece=0;
							whichDay=Integer.parseInt(SelectedCourse.get(i).getTimeID())/4+1;
							whichPiece=Integer.parseInt(SelectedCourse.get(i).getTimeID())%4-1;
							courseName=CourseManager.getCourseNameByID(SelectedCourse.get(i).getCourseID());
							data[whichPiece][whichDay]=courseName+"/"+SelectedCourse.get(i).getClassRoomID();								
						}
						DefaultTableModel newData=new DefaultTableModel(data,columnNames);
						table.setModel(newData);
			            }
					}
				});
		     }
	     //
	     private void addMajorSelect()
	     {
	    	 CourseManager creatCourse=new CourseManager();
	    	 Vector<String> majors=new Vector<String>();
	    	 majors=creatCourse.getAllMajor();
	    	 for(int i=0;i<majors.size();i++)
	    	 {
	    		 majorSelect.addItem(majors.get(i));
	    	 }
	    	 //添加默认班级
	    	 Vector<String>teachingClass=new Vector<String>();
	    	 teachingClass=creatCourse.getTeachingClassByMajor(majors.get(0));
	    	 for(int i=0;i<teachingClass.size();i++){
	    		 classSelect.addItem(teachingClass.get(i));
	    	 }
         }		     
	     // 窗口的鼠标事件处理 ，主要用于获得当前鼠标选中单元格的坐标，即行列
	         private void popMenuEvent(){         
	         table.addMouseListener(new MouseAdapter() {   
	             // 点击鼠标  
	             public void mousePressed(MouseEvent event) {   
	                 // 调用triggerEvent方法处理事件  
	                 triggerEvent(event);   
	                 selectedRow =((JTable)event.getSource()).rowAtPoint(event.getPoint()); //获得行位置 
	                 selectedCol=((JTable)event.getSource()).columnAtPoint(event.getPoint()); //获得列位置 
	             }  
	             // 释放鼠标  
	             public void mouseReleased(MouseEvent event) {   
	                 triggerEvent(event);  
	             }  
	   
	             private void triggerEvent(MouseEvent event) { // 处理事件  
	                 // isPopupTrigger():返回此鼠标事件是否为该平台的弹出菜单触发事件。  
	                 if (event.isPopupTrigger())   
	                     // 显示菜单  
	                     popupMenu.show(event.getComponent(), event.getX(),  
	                             event.getY());   
	             }  
	         });  
	     }
	   //清空data表（显示已经排好的课程）中的所有元素
	   private void cleanData(){
		   for(int i=0;i<data.length;i++)
			   for(int j=1;j<data[i].length;j++){
				   data[i][j]="";
			   }
	   }
	   //返回修改后的Table Model
	   private DefaultTableModel setData(String content){
		   DefaultTableModel dtm;
		   data[selectedRow][selectedCol]=content;
		   dtm=new DefaultTableModel(data,columnNames);
		   return dtm;	   
	   }
	   //将选中的单元格的内容删除并且保存到已删除列表中，并且更新课程表
	   private void deleteCell(){
			CourseManager cm=new CourseManager();
			String raw=(String) table.getModel().getValueAt(selectedRow, selectedCol);
			String[] subraw =raw.split("/");
			String courseName=subraw[0];
			String classRoomID=subraw[1];
			int timeID=(selectedCol-1)*4+selectedRow+1;
			
			Course newCourse=new Course();
			newCourse.setTimeID(String.valueOf(timeID));
			newCourse.setClassRoomID(classRoomID);
			newCourse=cm.getCourse(newCourse);
			
			deletedCourse.add(newCourse);            //将此条排课记录保存到本地
            cm.deletCourse(newCourse);               //将此条排课记录从数据库中删除
            //去除课程表中对应的课程
            DefaultTableModel dtm=setData("");
            table.setModel(dtm);
            
            //将删除的课程放到已删除课程表格中显示
            tableBody[deletedCourseNum][0]=courseName;
            tableBody[deletedCourseNum][1]=newCourse.getCourseID();
            DefaultTableModel newData=new DefaultTableModel(tableBody,tableHead);
            informationTable.setModel(newData);
            deletedCourseNum++;
	   }
	   
	   //绑定删除按钮
	   private void deleteCourseItem(){
		   deleteCourse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deleteCell();
			}	   
		   });   
	   }
	   
	   //将已删除的课程添加到选择的单元格中
	   private void addCourseItem(){
		   addCourse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int info_selectedRow=informationTable.getSelectedRow(); //获取选择的行
					if(data[selectedRow][selectedCol]!=""){
						int n=JOptionPane.showConfirmDialog(getParent(), "您添加的时间点有课，确定替换吗？");
						if(n==JOptionPane.YES_OPTION){
							
							deleteCell();
						}
					}
					Course waitTobeAdd=deletedCourse.get(info_selectedRow); //获取待添加的课程			
					String timeID=String.valueOf((selectedCol-1)*4+selectedRow+1);
					waitTobeAdd.setTimeID(timeID);
					waitTobeAdd.setClassRoomID(CourseManager.getClassroomID(timeID));
					//设置并且显示手动排课课程
					String content=CourseManager.getCourseNameByID(waitTobeAdd.getCourseID())+'/'+waitTobeAdd.getClassRoomID();
					table.setModel(setData(content));
					
					//在已删除列表之中删除手动排成功的课程
					deletedCourse.remove(info_selectedRow);
					for(int i=info_selectedRow;i<tableBody.length-1;i++){
						//for(int j=0;j<tableBody[i].length;j++){
							tableBody[i]=tableBody[i+1];
						//}
					}
					DefaultTableModel dtm=new DefaultTableModel(tableBody,tableHead);
					informationTable.setModel(dtm);
					//添加手动排课的结果
					CourseManager.uploadSingleTask(waitTobeAdd);     
					deletedCourseNum--;
				}
		   });
	   }
}

