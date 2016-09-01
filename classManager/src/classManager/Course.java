package classManager;

/**
 * 管理课程的信息类+
 * @author Mr.Bubbles
 *
 */
class Course{
	/////////////////////////数据定义区//////////////////////////////
	private String no;
	private String courseID;  //课程ID
	private String teachingClass; //教学班
	private String timeID;    //上课时间
	private String classRoomID;  //教室号
	private String major;        //专业
	
	/////////////////////////方法定义区//////////////////////////////
	//构造函数
	public Course(){

	}
	public Course(String no,String courseID, String teachingClass, String timeID, String classRoomID, String major) {
		super();
		this.no = no;
		this.courseID = courseID;
		this.teachingClass = teachingClass;
		this.timeID = timeID;
		this.classRoomID = classRoomID;
		this.major = major;
	}
	
	
	//设置和显示No
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	//设置和显示课程ID 
	void setCourseID(String courseID){
		this.courseID=courseID;
	}
	String getCourseID(){
		return courseID;
	}
	//设置和显示教学班
	void setTeachingClass(String teachingClass){
		this.teachingClass=teachingClass;
	}
	String getTeachingClass(){
		return teachingClass;
	}
	//设置和显示上课时间
	void setTimeID(String timeID){
		this.timeID=timeID;
	}
	String getTimeID(){
		return timeID;
	}
	//设置和显示教室号
	void setClassRoomID(String classRoomID){
		this.classRoomID=classRoomID;
	}
	String getClassRoomID(){
		return classRoomID;
	}
	//设置和显示专业
	void setMajor(String major){
		this.major=major;
	}
	String getMajor(){
		return major;
	}	
}