package classManager;

/**
 * ����γ̵���Ϣ��+
 * @author Mr.Bubbles
 *
 */
class Course{
	/////////////////////////���ݶ�����//////////////////////////////
	private String no;
	private String courseID;  //�γ�ID
	private String teachingClass; //��ѧ��
	private String timeID;    //�Ͽ�ʱ��
	private String classRoomID;  //���Һ�
	private String major;        //רҵ
	
	/////////////////////////����������//////////////////////////////
	//���캯��
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
	
	
	//���ú���ʾNo
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	//���ú���ʾ�γ�ID 
	void setCourseID(String courseID){
		this.courseID=courseID;
	}
	String getCourseID(){
		return courseID;
	}
	//���ú���ʾ��ѧ��
	void setTeachingClass(String teachingClass){
		this.teachingClass=teachingClass;
	}
	String getTeachingClass(){
		return teachingClass;
	}
	//���ú���ʾ�Ͽ�ʱ��
	void setTimeID(String timeID){
		this.timeID=timeID;
	}
	String getTimeID(){
		return timeID;
	}
	//���ú���ʾ���Һ�
	void setClassRoomID(String classRoomID){
		this.classRoomID=classRoomID;
	}
	String getClassRoomID(){
		return classRoomID;
	}
	//���ú���ʾרҵ
	void setMajor(String major){
		this.major=major;
	}
	String getMajor(){
		return major;
	}	
}