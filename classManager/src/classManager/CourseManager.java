/**
 * �Կγ̹����ṩ����
 * @author khf
 */

package classManager;

import java.sql.*;
import java.util.Vector;

public class CourseManager {

	//��¼��ǰ��λ��
	public static int now = 0;
	/**
	 * ��Course������һ����¼
	 * @return һ��Course����
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
	 * �����Ͽ�ʱ��
	 * @param רҵ �༶ �γ�
	 * @return String���飬����˸ÿγ�Ҫ���ŵ�ʱ��
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
	//���ÿ�ܿ�ʱ
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
	//Vector����
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
	//ÿ�����ڿ�
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
	//ÿ�ܶ��ڿ�
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
		//situation1��1 3,1 4,1 5��
		no1 = 1;
		no2 = 9;
		for (int i = 0; i < vt.size(); i++) {
			if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 4) {
				no1++;
			}
		}
		if ( no1 > 4 ) {
			//situation2��2 4,2 5��
			for (int i = 0; i < vt.size(); i++) {
				if ( no1 == Integer.parseInt(vt.get(i)) && no1 <= 8) {
					no1++;
				}
			}
			if ( no1 > 8) {
				//situation3��3 5��
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
	//ÿ��һ�ڿ�
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
	 * ��ÿ��еĽ���
	 * Ĭ�Ͻ���Ϊ˳����
	 * @param ΪҪ��ѯ��ʱ��Ƭ
	 * @return ����һ�����õĽ���
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
	 * �����ݿ������һ��Ԫ��
	 * @param һ��Course����
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public static boolean uploadSingleTask(Course course) {
		DBhepler db = new DBhepler();
		int s = 0;
		int index = Integer.parseInt(course.getNo());
		if (!BaseService.isExistCourse(index) ) {
			//�����ھ����
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
            //���ھ͸���
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
	 * ��ȡ����רҵ
	 * @return �����רҵ��Ϣ��Vector
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
	 * ���major�����н�ѧ��
	 * @param 
	 * @return ����˽�ѧ���Vector
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
	 * ��ȡĳ���༶�����пγ�
	 * @param major
	 * @param teachingclass
	 * @return ��ſγ̵�Vector
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
	 * ��Course����ɾ��һ����¼
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
	 * �ҵ��γ̺Ŷ�Ӧ�Ŀγ���
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
	 * ���ò���c�������time��classroom��Ϣ��ȡһ�������Ŀγ���Ϣ
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
 * ������ű�
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