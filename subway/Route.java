package subway;

import java.util.ArrayList;
import java.util.List;

public class Route {
	private Vertex now;
	private Vertex parent;
	public Route() {
		
	}
	public Route(Vertex now,Vertex parent) {
		this.now = now;
		this.parent = parent;
	}
	public Vertex getNow() {
		return now;
	}
	public void setNow(Vertex now) {
		this.now = now;
	}
	public Vertex getParent() {
		return parent;
	}
	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	
}
