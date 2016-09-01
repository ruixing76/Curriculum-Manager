/**
 * 对课程管理提供方法
 * @author khf
 */

package classManager;

import java.sql.*;
import java.util.Vector;

public class CourseManager {

	//记录当前的位置
	public static int now = 0;
	/**
	 * 读Course表中下一条记录
	 * @return 一个Course对象
	 */
	public Course readNext() {
		now++;
		DBhepler db = new DBhepler();
		String sql = "select * from courseManager where No=?";
		String[] str = {String.valueOf(now)};
		ResultSet rs = db.Search(sql, str);
		Course result = null;
		String courseid,teachingclass,timeid,classroomid,major;
		courseid = teachingclass = timeid = classroomid = major = null;
		
		try {
			if (rs.next()) {
				courseid = rs.getString("courseID");
				teachingclass = rs.getString("teachingClass");
				major = rs.getString("major");
				String no = String.valueOf(now);
				result = new Course(no, courseid, teachingclass, timeid, classroomid, major);
			}
			
			
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 计算上课时间
	 * @param 专业 班级 课程
	 * @return String数组，存放了该课程要安排的时间
	 */
	public static String[] getTimeID(String majoy, String teachingclass, String courseID) {
		int theory = getTheoryTime(majoy, teachingclass, courseID);
		String[] result = null;
		
		switch (theory) {
		case 1:	
			result = one(majoy,teachingclass);
			break;
		case 2:
			result = two(majoy,teachingclass);
			break;
		case 3:
			result = three(majoy,teachingclass);
			break;

		default:
			break;
		}
		return result;
	}
	//获得每周课时
	private static int getTheoryTime(String majoy, String teachingclass, String courseID) {
		DBhepler db = new DBhepler();
		String sql = "select theoryTime from courseManager where major = ? and teachingClass = ? and courseID = ?";
		String[] str = {majoy, teachingclass, courseID};
		ResultSet rs = db.Search(sql, str);
		int result = 0;
		try {
			while(rs.next()){
				result = Integer.parseInt(rs.getString("theoryTime"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	//Vector排序
	private static void sortVector( Vector<String> v ) {
		for (int i = 0; i < v.size(); i++) {
			for (int j =  1; j < v.size() - i; j++) {
				if ( v.get(j).compareTo(v.get(j - 1))< 0) {
					String temp = v.get(j - 1);
					v.set(j - 1, v.get(j));
					v.set(j, temp);
				}
			}
		}
	}
	//每周三节课
	private static String[] three(String majoy, String teachingclass) {
		DBhepler db = new DBhepler();
		String sql = "select timeID from course where major=? and teachingClass=?";
		String[] str = {majoy,teachingclass};
		Vector<String> vt = new Vector<String>();
		ResultSet rs = db.Search(sql, str);
		try {
			while (rs.next()) {
				vt.add(rs.getString("timeID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sortVector(vt);
		int no1,no2,no3;
		no1 = 1;
		no2 = 9;
		no3 = 17;
		for (int i = 0; i < vt.size(); i++) {
			if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 4) {
				no1++;
			}
		}
		for (int i = 0; i < vt.size(); i++) {
			if ( no2 == Integer.parseInt(vt.get(i)) && no1 <= 12) {
				no1++;
			}
		}
		for (int i = 0; i < vt.size(); i++) {
			if ( no2 == Integer.parseInt(vt.get(i)) && no1 <= 20) {
				no1++;
			}
		}
		String[] result = {
				String.valueOf(no1),
				String.valueOf(no2),
				String.valueOf(no3),
		};
		return result;
	}
	//每周二节课
	private static String[] two(String majoy, String teachingclass) {
		DBhepler db = new DBhepler();
		String sql = "select timeID from course where major=? and teachingClass=?";
		String[] str = {majoy,teachingclass};
		Vector<String> vt = new Vector<String>();
		ResultSet rs = db.Search(sql, str);
		try {
			while (rs.next()) {
				vt.add(rs.getString("timeID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sortVector(vt);
		int no1,no2;
		//situation1（1 3,1 4,1 5）
		no1 = 1;
		no2 = 9;
		for (int i = 0; i < vt.size(); i++) {
			if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 4) {
				no1++;
			}
		}
		if ( no1 > 4 ) {
			//situation2（2 4,2 5）
			for (int i = 0; i < vt.size(); i++) {
				if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 8) {
					no1++;
				}
			}
			if ( no1 > 8) {
				//situation3（3 5）
				for (int i = 0; i < vt.size(); i++) {
					if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 12) {
						no1++;
					}
				}
				no2 = 17;
				for (int i = 0; i < vt.size(); i++) {
					if ( no2 == Integer.parseInt(vt.get(i)) && no2 <= 20) {
						no2++;
					}
				}
			} else {
				no2 = 13;
				for (int i = 0; i < vt.size(); i++) {
					if ( no2 == Integer.parseInt(vt.get(i)) && no2 <= 20) {
						no2++;
					}
				}
			}
		} else {
			no2 = 9;
			for (int i = 0; i < vt.size(); i++) {
				if ( no2 == Integer.parseInt(vt.get(i)) && no2 <= 20) {
					no2++;
				}
			}
		}
		String[] result = {
				String.valueOf(no1),
				String.valueOf(no2),
		};
		return result;
	}
	//每周一节课
	private static String[] one(String majoy, String teachingclass) {
		DBhepler db = new DBhepler();
		String sql = "select timeID from course where major=? and teachingClass=?";
		String[] str = {majoy,teachingclass};
		Vector<String> vt = new Vector<String>();
		ResultSet rs = db.Search(sql, str);
		try {
			while (rs.next()) {
				vt.add(rs.getString("timeID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		sortVector(vt);
		int no1;
		no1 = 1;
		for (int i = 0; i < vt.size(); i++) {
			if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 20) {
					no1++;
			}
		}
		String[] result = {
				String.valueOf(no1),
		};
		return result;
	}
	
	
	/**
	 * 获得空闲的教室
	 * 默认教室为顺序安排
	 * @param 为要查询的时间片
	 * @return 返回一个可用的教室
	 */
	public static String getClassroomID(String timeid) {
		DBhepler db = new DBhepler();
		String sql = "select classRoom from course where timeID=?";
		String[] str = {timeid};
		ResultSet rs = db.Search(sql, str);
		int room_int = 0;
		try {
			while (rs.next()) {
				room_int++;
			}
			room_int++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String room_string = String.valueOf(room_int);
		return room_string;
	}
	
	/**
	 * 向数据库里加入一个元组
	 * @param 一个Course对象
	 * @return 成功返回true，失败返回false
	 */
	public static boolean uploadSingleTask(Course course) {
		DBhepler db = new DBhepler();
		int s = 0;
		int index = Integer.parseInt(course.getNo());
		if (!BaseService.isExistCourse(index) ) {
			//不存在就添加
			String sql = "insert into course (No,courseID,teachingClass,timeID,classRoom,major) values(?,?,?,?,?,?)";
			String[] str = {
				course.getNo(),
				course.getCourseID(),
				course.getTeachingClass(),
				course.getTimeID(),
				course.getClassRoomID(),
				course.getMajor()
			};
			s = db.AddU(sql, str);
			if (s == 0) {
				return false;
			} else {
				return true;
			}
        }else {
            //存在就更新
            String sql="update course set courseID=?,teachingClass=?,timeID=?,classRoom=?,major=? where No=?";
            String[] str=new String[]{
            		course.getCourseID(),
            		course.getTeachingClass(),
            		course.getTimeID(),
            		course.getClassRoomID(),
            		course.getMajor(),
            		course.getNo()
            };
            s = db.AddU(sql, str);
            if (s == 0) {
				return false;
			} else {
				return true;
			}
        }
	}
	
	/**
	 * 获取所有专业
	 * @return 存放了专业信息的Vector
	 */
	public Vector<String> getAllMajor() {
		DBhepler db = new DBhepler();
		String sql = "select major from course";
		String[] str = null;
		Vector<String> result = new Vector<String>();
		ResultSet rs = db.Search(sql, str);
		
		try {
			while ( rs.next()) {
				String temp = rs.getString("major");
				if ( !result.contains(temp)) {
					result.add(temp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得major的所有教学班
	 * @param 
	 * @return 存放了教学班的Vector
	 */
	public Vector<String> getTeachingClassByMajor( String major) {
		DBhepler db = new DBhepler();
		String sql = "select teachingClass from course where major=?";
		String[] str = {major};
		Vector<String> result = new Vector<String>();
		ResultSet rs = db.Search(sql, str);
		
		try {
			while ( rs.next()) {
				String temp = rs.getString("teachingClass");
				if ( !result.contains(temp)) {
					result.add(temp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取某个班级的所有课程
	 * @param major
	 * @param teachingclass
	 * @return 存放课程的Vector
	 */
	public Vector<Course> getCourseByClass( String major, String teachingclass) {
		DBhepler db = new DBhepler();
		String sql = "select * from course where major=? and teachingClass=?";
		String[] str = {major,teachingclass};
		Vector<Course> result = new Vector<Course>();
		ResultSet rs = db.Search(sql, str);
		String no = null;
		String courseid = null;
		String teachingClass = null;
		String timeid = null;
		String classroom = null;
		String Major = null;
		
		try {
			while ( rs.next()) {
				no = rs.getString("No");
				courseid = rs.getString("courseID");
				teachingClass = rs.getString("teachingClass");
				timeid = rs.getString("timeID");
				classroom = rs.getString("classRoom");
				Major = rs.getString("major");
				Course temp = new Course(no, courseid, teachingClass, timeid, classroom, Major);
				if ( !result.contains(temp)) {
					result.add(temp);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 从Course表中删除一条记录
	 * @param major
	 * @param teachingclass
	 * @param courseID
	 */
	public void deletCourse(Course course) {
		String major=course.getMajor();
		String teachingclass=course.getTeachingClass();
		String courseID=course.getCourseID();
		String timeID = course.getTimeID();
		DBhepler db = new DBhepler();
		String sql = "delete from course where major=? and teachingClass=? and courseID=? and timeID=?";
		String[] str = {major,teachingclass,courseID,timeID};
		db.AddU(sql, str);
	}
	/**
	 * 找到课程号对应的课程名
	 * @author khf
	 * @param courseid
	 * @return
	 */
	public static String getCourseNameByID( String courseid) {
		DBhepler db = new DBhepler();
		String sql = "select courseName from courseManager where courseID=?";
		String[] str = {courseid};
		ResultSet rs = db.Search(sql, str);
		
		try {
			if (rs.next()) {
				return rs.getString("courseName");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 利用参数c里包含的time和classroom信息获取一条完整的课程信息
	 * @param c
	 * @return
	 */
	public Course getCourse( Course c) {
		DBhepler db = new DBhepler();
		String sql = "select * from course where timeID=? and classRoom=?";
		String str[] = {c.getTimeID(), c.getClassRoomID()};
		ResultSet rs = db.Search(sql, str);
		Course result = new Course(null, null, null, c.getTimeID(), c.getClassRoomID(), null);
		
		try {
			while ( rs.next()) {
				result.setNo(rs.getString("No"));
				result.setCourseID(rs.getString("courseID"));
				result.setTeachingClass(rs.getString("teachingClass"));
				result.setMajor(rs.getString("major"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
/**
 * 清空两张表
 * @author khf
 */
public void cleanTable() {
	DBhepler db = new DBhepler();
	String sql = "delete from course";
	String[] str = null;
	db.AddU(sql, str);
	
	sql = "delete from courseManager";
	db.AddU(sql, str);
    }
}