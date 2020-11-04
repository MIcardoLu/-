package subway;

import java.util.ArrayList;

public class Vertex {
	private String name;	//站点名
	private ArrayList<Vertex> nearV = new ArrayList<Vertex>();		//存储该站点的相邻的站点
	private ArrayList<String> line = new ArrayList<String>();		//存储该站点的所属路线
	private boolean passed = false;			//在BFS算法中，判定是否遍历过的标识符
	private Vertex() {
		
	}
	public Vertex(String name,String line) {	//构造函数
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

