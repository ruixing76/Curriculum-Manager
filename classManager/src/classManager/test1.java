package classManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;  
	   
class test1 extends JFrame {  
	     //�ֶ��ſ��У���ɾ���Ŀγ̼�¼
		 ArrayList<Course> deletedCourse =new ArrayList();
		 
	     // �����˵���ʵ�֣������˵���һ���ɵ�������ʾһϵ��ѡ���С����  
	     JPopupMenu popupMenu;   
	     MenuBar mb;  
	     Menu menuMajor,menuClass,menuNull1,menuNull2;
	     
	     //�γ��ſν������ʾ���ڣ�����һ��JTable
	     JTable table;           
	     String[] columnNames={"","һ","��","��","��","��"};
	     String[][] data=    {{"12","123","","","",""},
		                      {"34","","","","",""},
                              {"56","","","","",""},
                              {"78","","","","",""}};
	     
	     JPanel panelMenu;
	     JPanel informationHeadPanel;
	     JPanel panelTip;
	     JScrollPane informationBodyPanel;
	     JTable informationTable;    //��ɾ���Ŀγ̱��
	     
	     JPanel panelCondition;
	     JScrollPane scrollPane;  //������������(����ʹ�����Թ���)
	     
	     
	     //�����б�ѡ��
	     JComboBox<String> majorSelect;
	     JComboBox<String> classSelect;
	     JMenuItem deleteCourse;
	     JMenuItem addCourse;
	     
	     String SelectedMajor;
	     String SelectedTeachingClass;
	     
	     int selectedRow;int selectedCol; //ѡ�еĿγ̵�����
	     String []tableHead={"courseName(�γ���)","courseID(�γ̺�)"};
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
	     	     
    	 //��������
	     public test1() {  
	         super("�α�"); // ���ø��๹�캯��  `
	         setSize(835,500);
		 	 setLocation(600,300);
		 	 setVisible(true);         //���ô��ڿɼ�
		 	 setResizable(false);       //���ô��ڴ�С�ɱ�  
	         //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رմ���ʱ�˳����� 
	         setLayout(null);
	         
	         // ʵ���������˵�   
	         popupMenu = new JPopupMenu();   
	         // ���Ӳ˵���˵���  
	         deleteCourse=new JMenuItem("ɾ���γ�");
	         addCourse=new JMenuItem("��ӻ��滻�γ�");
	         popupMenu.add(new JLabel("�˵���"));
	         popupMenu.addSeparator();
	         popupMenu.add(deleteCourse);   
	         popupMenu.addSeparator();
	         popupMenu.add(addCourse);  
	         deleteCourseItem();
	         addCourseItem();
	         
	         //רҵ�ͽ�ѧ���ѡ�������˵�
	         majorSelect=new JComboBox<String>(); 
	         majorSelect.setBorder(BorderFactory.createTitledBorder("ѡ��רҵ:"));
	         classSelect=new JComboBox<String>();
	         classSelect.setBorder(BorderFactory.createTitledBorder("ѡ��༶��"));
	         //�Ѿ���ѡ���רҵ�Ͱ༶
	         SelectedMajor=new String();
	         SelectedTeachingClass=new String();   
     
	         addMajorSelect();                            //�������רҵ����	         
	         majorItem();                                 //���רҵ����Ӹ�רҵ�����н�ѧ������  
	         classItem();                                 //����༶����Ӹý�ѧ������пγ� 
	         
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

	  

	         String val=(String) table.getModel().getValueAt(2, 1);//��ȡĳ����Ԫ���ֵ
	    	 //System.out.println(val);
	    	 
	    	 setMenuBar(mb);
	    	 
	    	 scrollPane=new JScrollPane();
	    	 scrollPane.setBounds(2,50, 831, 250);  
	    	 table.setFillsViewportHeight(true);  //���������������ͼ��ȫ���߶�
	    	 scrollPane.setViewportView(table);    	
	    	 add(scrollPane);
	    	 
	    	 popMenuEvent();       //�����˵����¼�����
	    	 
	    	 informationHeadPanel=new JPanel();
	    	 informationHeadPanel.setLayout(new FlowLayout(1,200,10));
	    	 informationHeadPanel.setBounds(0, 310,400,35);
	    	 informationHeadPanel.setVisible(true);
	    	 informationHeadPanel.setBackground(Color.green);
	    	 JLabel informationHead=new JLabel("*******************�ð༶Ϊ���ŵĿγ�*******************");
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
	    	 JLabel tipHead=new JLabel("********************������ʾ��ʾ*********************");
	    	 panelTip.add(tipHead);
	    	 JTextArea jta=new JTextArea();
	    	 jta.setText("��������������������������");
	    	 jta.setFont(new Font("����", Font.BOLD, 20));
	    	 jta.setEditable(false);
	    	 panelTip.add(jta);
	    	 add(panelTip);
	    	 
	    	 panelCondition=new JPanel();
	    	 panelCondition.setLayout(new FlowLayout(0,10,1));
	    	 panelCondition.setBounds(0, 440, 835, 60);
	    	 panelCondition.setBackground(Color.lightGray);
	    	 JLabel jl2=new JLabel("��ǰ״̬:blah blah blah blah blah blah blah blah blah blah blah blah...");
	    	 panelCondition.add(jl2);
	    	 add(panelCondition);
	     }
	     
	     //��רҵѡ��BOX�ļ�����
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
	     //�󶨰༶ѡ��BOX�ļ�����
	     private void classItem(){
		     classSelect.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED) //??????????????????
			            {
							SelectedTeachingClass=classSelect.getSelectedItem().toString();
						CourseManager creatCourse=new CourseManager();
						Vector<Course> SelectedCourse=new Vector<>();
						SelectedCourse=creatCourse.getCourseByClass(SelectedMajor, SelectedTeachingClass);
						cleanData(); //���������Ϣ
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
	    	 //���Ĭ�ϰ༶
	    	 Vector<String>teachingClass=new Vector<String>();
	    	 teachingClass=creatCourse.getTeachingClassByMajor(majors.get(0));
	    	 for(int i=0;i<teachingClass.size();i++){
	    		 classSelect.addItem(teachingClass.get(i));
	    	 }
         }		     
	     // ���ڵ�����¼����� ����Ҫ���ڻ�õ�ǰ���ѡ�е�Ԫ������꣬������
	         private void popMenuEvent(){         
	         table.addMouseListener(new MouseAdapter() {   
	             // ������  
	             public void mousePressed(MouseEvent event) {   
	                 // ����triggerEvent���������¼�  
	                 triggerEvent(event);   
	                 selectedRow =((JTable)event.getSource()).rowAtPoint(event.getPoint()); //�����λ�� 
	                 selectedCol=((JTable)event.getSource()).columnAtPoint(event.getPoint()); //�����λ�� 
	             }  
	             // �ͷ����  
	             public void mouseReleased(MouseEvent event) {   
	                 triggerEvent(event);  
	             }  
	   
	             private void triggerEvent(MouseEvent event) { // �����¼�  
	                 // isPopupTrigger():���ش�����¼��Ƿ�Ϊ��ƽ̨�ĵ����˵������¼���  
	                 if (event.isPopupTrigger())   
	                     // ��ʾ�˵�  
	                     popupMenu.show(event.getComponent(), event.getX(),  
	                             event.getY());   
	             }  
	         });  
	     }
	   //���data����ʾ�Ѿ��źõĿγ̣��е�����Ԫ��
	   private void cleanData(){
		   for(int i=0;i<data.length;i++)
			   for(int j=1;j<data[i].length;j++){
				   data[i][j]="";
			   }
	   }
	   //�����޸ĺ��Table Model
	   private DefaultTableModel setData(String content){
		   DefaultTableModel dtm;
		   data[selectedRow][selectedCol]=content;
		   dtm=new DefaultTableModel(data,columnNames);
		   return dtm;	   
	   }
	   //��ѡ�еĵ�Ԫ�������ɾ�����ұ��浽��ɾ���б��У����Ҹ��¿γ̱�
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
			
			deletedCourse.add(newCourse);            //�������ſμ�¼���浽����
            cm.deletCourse(newCourse);               //�������ſμ�¼�����ݿ���ɾ��
            //ȥ���γ̱��ж�Ӧ�Ŀγ�
            DefaultTableModel dtm=setData("");
            table.setModel(dtm);
            
            //��ɾ���Ŀγ̷ŵ���ɾ���γ̱������ʾ
            tableBody[deletedCourseNum][0]=courseName;
            tableBody[deletedCourseNum][1]=newCourse.getCourseID();
            DefaultTableModel newData=new DefaultTableModel(tableBody,tableHead);
            informationTable.setModel(newData);
            deletedCourseNum++;
	   }
	   
	   //��ɾ����ť
	   private void deleteCourseItem(){
		   deleteCourse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deleteCell();
			}	   
		   });   
	   }
	   
	   //����ɾ���Ŀγ���ӵ�ѡ��ĵ�Ԫ����
	   private void addCourseItem(){
		   addCourse.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					int info_selectedRow=informationTable.getSelectedRow(); //��ȡѡ�����
					if(data[selectedRow][selectedCol]!=""){
						int n=JOptionPane.showConfirmDialog(getParent(), "����ӵ�ʱ����пΣ�ȷ���滻��");
						if(n==JOptionPane.YES_OPTION){
							
							deleteCell();
						}
					}
					Course waitTobeAdd=deletedCourse.get(info_selectedRow); //��ȡ����ӵĿγ�			
					String timeID=String.valueOf((selectedCol-1)*4+selectedRow+1);
					waitTobeAdd.setTimeID(timeID);
					waitTobeAdd.setClassRoomID(CourseManager.getClassroomID(timeID));
					//���ò�����ʾ�ֶ��ſογ�
					String content=CourseManager.getCourseNameByID(waitTobeAdd.getCourseID())+'/'+waitTobeAdd.getClassRoomID();
					table.setModel(setData(content));
					
					//����ɾ���б�֮��ɾ���ֶ��ųɹ��Ŀγ�
					deletedCourse.remove(info_selectedRow);
					for(int i=info_selectedRow;i<tableBody.length-1;i++){
						//for(int j=0;j<tableBody[i].length;j++){
							tableBody[i]=tableBody[i+1];
						//}
					}
					DefaultTableModel dtm=new DefaultTableModel(tableBody,tableHead);
					informationTable.setModel(dtm);
					//����ֶ��ſεĽ��
					CourseManager.uploadSingleTask(waitTobeAdd);     
					deletedCourseNum--;
				}
		   });
	   }
}

