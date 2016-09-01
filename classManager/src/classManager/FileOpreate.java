/**
 * 读取excel到database里
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
 * 连接SqlserverDBMS
 * 数据库为project2
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
            	System.err.println("装载 JDBC/ODBC 驱动程序失败。" );  
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.err.println("无法连接数据库" ); 
                e.printStackTrace();
            }
    }

    // 查询
    public ResultSet Search(String sql, String str[]) {
        DataBase();
        try {
        	PreparedStatement pst =con.prepareStatement(sql); //表示预编译的 SQL语句的对象
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

    // 增删修改
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
 * 原始表的数据定义
 * @author khf
 *
 */
class CourseBase {
/////////////////////////数据定义区//////////////////////////////
	private int No; //序号
	private String courseID;  //课程ID
	private String courseName; //课程名称
	private String major;        //专业
	private String teachingClass; //教学班
	private int theoryTime;  //理论时间
	private int experimentTime;  //实验时间
	private String teacherID;    //教师ID
	private String teacherName;  //教师姓名

	
/////////////////////////方法定义区//////////////////////////////
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

	//获取和设置序号
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}

	//获取和设置课程ID
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	//获取和设置课程名
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	//获取和设置专业
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	//获取和设置老师
	public String getTeachingClass() {
		return teachingClass;
	}
	public void setTeachingClass(String teachingClass) {
		this.teachingClass = teachingClass;
	}
	
	//获取和设置理论课时
	public int getTheoryTime() {
		return theoryTime;
	}
	public void setTheoryTime(int theoryTime) {
		this.theoryTime = theoryTime;
	}
	
	//获取和设置实验课时
	public int getExperimentTime() {
		return experimentTime;
	}
	public void setExperimentTime(int experimentTime) {
		this.experimentTime = experimentTime;
	}
	
	//获取和设置老师编号
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	
	//获取和设置老师姓名
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}

/**
 * 为操作excel和数据库提供帮助
 * @author khf
 *
 */
class BaseService {
	/**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<CourseBase> getAllByExcel(String file){
        List<CourseBase> list=new ArrayList<CourseBase>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String courseID=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
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
     * 通过No判断是否存在
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
 * 把基本数据表Project2.xls读到数据库里
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
		//得到表格中所有的数据
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
                //不存在就添加
                String sql="insert into courseManager (No,courseID,courseName,major,teachingClass,theoryTime,experimentTime,teacherID,teacherName) values(?,?,?,?,?,?,?,?,?)";
                String[] str=new String[]{No+"",courseBase.getCourseID(),courseBase.getCourseName(),courseBase.getMajor(),courseBase.getTeachingClass(),ttime+"",etime+"",courseBase.getTeacherID(),courseBase.getTeacherName()};
                db.AddU(sql, str);
            } else {
                //存在就更新
                String sql="update courseManager set courseID=?,courseName=?,major=?,teachingClass=?,theoryTime=?,experimentTime=?,teacherID=?,teacherName=? where No=?";
                String[] str=new String[]{courseBase.getCourseID(),courseBase.getCourseName(),courseBase.getMajor(),courseBase.getTeachingClass(),ttime+"",etime+"",courseBase.getTeacherID(),courseBase.getTeacherName(),No+""};
                db.AddU(sql, str);
            }
        }
        return true;
	}
}
