package subway;

import java.util.*;

public class BFS {
	public int BFS(Vertex start,Vertex end) {
		int shortestDist = 0;	//初始化最短路径为0
		ArrayList<Route> passedV = new ArrayList<Route>();	//用于存储通过BFS遍历过的节点
		Queue<Vertex> route = new LinkedList<>() ;	//用队列来实现BFS
		
		//初始化第一个节点，也就是开始站点
		Route r1 = new Route();
		r1.setNow(start);
		route.offer(start);
		passedV.add(r1);
		start.setPassed(true);
		
		Vertex linshi;	//用于存储变量
		
		do {
			linshi = route.poll();
			if(linshi.getName().equals(end.getName())) {
				break;
			}
			ArrayList<Vertex> nearV = linshi.getNearV();	//用于获取现在出队列的节点的相邻节点
			for(int i=0;i<nearV.size();i++) {
				if(nearV.get(i).isPassed()==true) {			//如果已经遍历过的话，跳过
					continue;
				}
				
				Route a = new Route();				//加入存储结构
				a.setNow(nearV.get(i));
				a.setParent(linshi);
				route.offer(nearV.get(i));
				passedV.add(a);
				nearV.get(i).setPassed(true);
			}
		}while(true);
		
		Stack<Vertex> result = new Stack<Vertex>();		//初始化栈，用于输出结果
		
		Route r = null ;
		
		for(int i=passedV.size()-1;i>=0;i--) {		//找到目的地在存储结构中的具体信息，
			if(passedV.get(i).getNow().getName().equals(end.getName())) {
				r=passedV.get(i);
			}
		}
		
		result.push(end);
		
		for(int i=passedV.size()-1;i>=0;i--) {			//这个for循环，从目的地节点不断寻找父节点，实现最短路径的结果搜索
			if(passedV.get(i).getNow().getName().equals(r.getParent().getName())) {
				result.push(passedV.get(i).getNow());
				r = passedV.get(i);
			}
			if(passedV.get(i).getNow().getName().equals(start.getName())) {
				break;
			}
		}
		
		ArrayList<String> line_belong_beforeV = new ArrayList<>();		//这边开始判断是否换乘
		ArrayList<String> line_belong_nowV = new ArrayList<>();
		String line = null;String line_now = null;
		line_belong_beforeV = result.peek().getLine_belong();
		int same_line_cnt;
		while(!result.isEmpty()) {		//出栈过程
			line_belong_nowV = result.peek().getLine_belong();
			same_line_cnt=0;	//由于比如2号线的环线情况存在，会存在多个相同的线路影响判定，这边定义一个变量进行限制
			for(int i=0;i<line_belong_beforeV.size();i++) {
				for(int j=0;j<line_belong_nowV.size();j++) {
					if(line_belong_beforeV.get(i).equals(line_belong_nowV.get(j))) {
						same_line_cnt++;
						if(same_line_cnt==1) {		//进行多相同线路的判定
							if(line==null) {
								line = line_belong_beforeV.get(i);
							}
							if(!line.isEmpty()&&!line.equals(line_belong_nowV.get(j))) {
								line = line_belong_nowV.get(j);
								System.out.println("换乘"+line);
							}
						}
						if(same_line_cnt>1) {
							continue;
						}
					}
				}
			}
			shortestDist++;			//记最短路径的长度
			if(result.size()==1) {
				System.out.print(result.pop().getName());
			}
			else {
				System.out.print(result.pop().getName()+"--->");
			}
			line_belong_beforeV = line_belong_nowV;
		}
		return shortestDist;
	}
}
