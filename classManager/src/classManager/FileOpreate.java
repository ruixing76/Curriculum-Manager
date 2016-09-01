/**
 * ��ȡexcel��database��
 * @author khf
 *
 */

package classManager;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jxl.Sheet;
import jxl.Workbook;

/**
 * ����SqlserverDBMS
 * ���ݿ�Ϊproject2
 * @author khf
 *
 */
class DBhepler {
	
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=project2";
      
    Connection con = null;
    ResultSet res = null;
    String user = "pro2";
    String passwd = "admin";

    public void DataBase() {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, passwd);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
            	System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" );  
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.err.println("�޷��������ݿ�" ); 
                e.printStackTrace();
            }
    }

    // ��ѯ
    public ResultSet Search(String sql, String str[]) {
        DataBase();
        try {
        	PreparedStatement pst =con.prepareStatement(sql); //��ʾԤ����� SQL���Ķ���
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i+1, str[i]);
                }
            }
            res = pst.executeQuery();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    // ��ɾ�޸�
    public int AddU(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
}

/**
 * ԭʼ������ݶ���
 * @author khf
 *
 */
class CourseBase {
/////////////////////////���ݶ�����//////////////////////////////
	private int No; //���
	private String courseID;  //�γ�ID
	private String courseName; //�γ�����
	private String major;        //רҵ
	private String teachingClass; //��ѧ��
	private int theoryTime;  //����ʱ��
	private int experimentTime;  //ʵ��ʱ��
	private String teacherID;    //��ʦID
	private String teacherName;  //��ʦ����

	
/////////////////////////����������//////////////////////////////
	public CourseBase(int No,String courseID, String courseName, String major, String teachingClass, int theoryTime,
		int experimentTime, String teacherID, String teacherName) {
		super();
		this.No = No;
		this.courseID = courseID;
		this.courseName = courseName;
		this.major = major;
		this.teachingClass = teachingClass;
		this.theoryTime = theoryTime;
		this.experimentTime = experimentTime;
		this.teacherID = teacherID;
		this.teacherName = teacherName;
	}
	
	public CourseBase() {
		super();
		// TODO Auto-generated constructor stub
		this.No = 0;
		this.courseID = null;
		this.courseName = null;
		this.major = null;
		this.teachingClass = null;
		this.theoryTime = 0;
		this.experimentTime = 0;
		this.teacherID = null;
		this.teacherName = null;
	}

	//��ȡ���������
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}

	//��ȡ�����ÿγ�ID
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	//��ȡ�����ÿγ���
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	//��ȡ������רҵ
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	//��ȡ��������ʦ
	public String getTeachingClass() {
		return teachingClass;
	}
	public void setTeachingClass(String teachingClass) {
		this.teachingClass = teachingClass;
	}
	
	//��ȡ���������ۿ�ʱ
	public int getTheoryTime() {
		return theoryTime;
	}
	public void setTheoryTime(int theoryTime) {
		this.theoryTime = theoryTime;
	}
	
	//��ȡ������ʵ���ʱ
	public int getExperimentTime() {
		return experimentTime;
	}
	public void setExperimentTime(int experimentTime) {
		this.experimentTime = experimentTime;
	}
	
	//��ȡ��������ʦ���
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	
	//��ȡ��������ʦ����
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}

/**
 * Ϊ����excel�����ݿ��ṩ����
 * @author khf
 *
 */
class BaseService {
	/**
     * ��ѯָ��Ŀ¼�е��ӱ�������е�����
     * @param file �ļ�����·��
     * @return
     */
    public static List<CourseBase> getAllByExcel(String file){
        List<CourseBase> list=new ArrayList<CourseBase>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//����rwb.getSheet(0)
            int clos=rs.getColumns();//�õ����е���
            int rows=rs.getRows();//�õ����е���
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //��һ�����������ڶ���������
                    String courseID=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
                    String courseName=rs.getCell(j++, i).getContents();
                    String major=rs.getCell(j++, i).getContents();
                    String teachingClass=rs.getCell(j++, i).getContents();
                    String theoryTime=rs.getCell(j++, i).getContents();
                    String experimentTime=rs.getCell(j++, i).getContents();
                    String teacherID=rs.getCell(j++, i).getContents();
                    String teacherName=rs.getCell(j++, i).getContents();
                    
                    System.out.println(courseID+' '+courseName+' '+major+' '+teachingClass+' '
                    		+theoryTime+' '+experimentTime+' '+teacherID+' '+teacherName);
                    list.add(new 
                    		CourseBase(i,courseID,courseName,major,teachingClass,
                    				Integer.parseInt(theoryTime), 
                    				Integer.parseInt(experimentTime), teacherID, teacherName) 
                    		);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
    }
    
    /**
     * ͨ��No�ж��Ƿ����
     * @param id
     * @return
     */
    public static boolean isExistCourseManaget(int No){
        try {
            DBhepler db=new DBhepler();
            ResultSet rs=db.Search("select * from courseManager where No=?", new String[]{No+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isExistCourse(int No){
        try {
            DBhepler db=new DBhepler();
            ResultSet rs=db.Search("select * from course where No=?", new String[]{No+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}

/**
 * �ѻ������ݱ�Project2.xls�������ݿ���
 * @author khf
 *
 */
public class FileOpreate {
	private String path;
	
	public FileOpreate(String path) {
		this.path = path;
	}

	private void sort_list( List<CourseBase> l) {
		for (int i = 0; i < l.size(); i++) {
			for (int j = 1; j < l.size() - i; j++) {
				if ( l.get(j).getTheoryTime() > l.get(j - 1).getTheoryTime()) {
					CourseBase temp = l.get(j - 1);
					l.set(j - 1, l.get(j));
					l.set(j, temp);
				}
			}
		}
		for (int i = 0; i < l.size(); i++) {
			l.get(i).setNo(i+1);
		}
	}
	
	public boolean excelToDB() {
		//�õ���������е�����
        List<CourseBase> listExcel=BaseService.getAllByExcel(path);     
        DBhepler db=new DBhepler();
        sort_list(listExcel);
        
        for (CourseBase courseBase : listExcel) {
            int no=courseBase.getNo();
            int theory = courseBase.getTheoryTime();
            int experiment = courseBase.getExperimentTime();
            String No = String.valueOf(no);
            String ttime = String.valueOf(theory);
            String etime = String.valueOf(experiment);
            if (!BaseService.isExistCourseManaget(no)) {
                //�����ھ����
                String sql="insert into courseManager (No,courseID,courseName,major,teachingClass,theoryTime,experimentTime,teacherID,teacherName) values(?,?,?,?,?,?,?,?,?)";
                String[] str=new String[]{No+"",courseBase.getCourseID(),courseBase.getCourseName(),courseBase.getMajor(),courseBase.getTeachingClass(),ttime+"",etime+"",courseBase.getTeacherID(),courseBase.getTeacherName()};
                db.AddU(sql, str);
            } else {
                //���ھ͸���
                String sql="update courseManager set courseID=?,courseName=?,major=?,teachingClass=?,theoryTime=?,experimentTime=?,teacherID=?,teacherName=? where No=?";
                String[] str=new String[]{courseBase.getCourseID(),courseBase.getCourseName(),courseBase.getMajor(),courseBase.getTeachingClass(),ttime+"",etime+"",courseBase.getTeacherID(),courseBase.getTeacherName(),No+""};
                db.AddU(sql, str);
            }
        }
        return true;
	}
}
