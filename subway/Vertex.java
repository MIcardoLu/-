package subway;

import java.util.ArrayList;

public class Vertex {
	private String name;	//վ����
	private ArrayList<Vertex> nearV = new ArrayList<Vertex>();		//�洢��վ������ڵ�վ��
	private ArrayList<String> line = new ArrayList<String>();		//�洢��վ�������·��
	private boolean passed = false;			//��BFS�㷨�У��ж��Ƿ�������ı�ʶ��
	private Vertex() {
		
	}
	public Vertex(String name,String line) {	//���캯��
		this.name = name;
		this.line.add(line);
	}
	
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Vertex> getNearV() {
		return nearV;
	}
	public void setNearV(ArrayList<Vertex> nearV) {
		this.nearV = nearV;
	}
	public ArrayList<String> getLine_belong() {
		return line;
	}
	public void setLine_belong(ArrayList<String> line_belong) {
		this.line = line_belong;
	}
	public void addLine_belong(String line) {
		this.line.add(line);
	}

	
}

