package subway;

import java.util.*;

public class BFS {
	public int BFS(Vertex start,Vertex end) {
		int shortestDist = 0;	//��ʼ�����·��Ϊ0
		ArrayList<Route> passedV = new ArrayList<Route>();	//���ڴ洢ͨ��BFS�������Ľڵ�
		Queue<Vertex> route = new LinkedList<>() ;	//�ö�����ʵ��BFS
		
		//��ʼ����һ���ڵ㣬Ҳ���ǿ�ʼվ��
		Route r1 = new Route();
		r1.setNow(start);
		route.offer(start);
		passedV.add(r1);
		start.setPassed(true);
		
		Vertex linshi;	//���ڴ洢����
		
		do {
			linshi = route.poll();
			if(linshi.getName().equals(end.getName())) {
				break;
			}
			ArrayList<Vertex> nearV = linshi.getNearV();	//���ڻ�ȡ���ڳ����еĽڵ�����ڽڵ�
			for(int i=0;i<nearV.size();i++) {
				if(nearV.get(i).isPassed()==true) {			//����Ѿ��������Ļ�������
					continue;
				}
				
				Route a = new Route();				//����洢�ṹ
				a.setNow(nearV.get(i));
				a.setParent(linshi);
				route.offer(nearV.get(i));
				passedV.add(a);
				nearV.get(i).setPassed(true);
			}
		}while(true);
		
		Stack<Vertex> result = new Stack<Vertex>();		//��ʼ��ջ������������
		
		Route r = null ;
		
		for(int i=passedV.size()-1;i>=0;i--) {		//�ҵ�Ŀ�ĵ��ڴ洢�ṹ�еľ�����Ϣ��
			if(passedV.get(i).getNow().getName().equals(end.getName())) {
				r=passedV.get(i);
			}
		}
		
		result.push(end);
		
		for(int i=passedV.size()-1;i>=0;i--) {			//���forѭ������Ŀ�ĵؽڵ㲻��Ѱ�Ҹ��ڵ㣬ʵ�����·���Ľ������
			if(passedV.get(i).getNow().getName().equals(r.getParent().getName())) {
				result.push(passedV.get(i).getNow());
				r = passedV.get(i);
			}
			if(passedV.get(i).getNow().getName().equals(start.getName())) {
				break;
			}
		}
		
		ArrayList<String> line_belong_beforeV = new ArrayList<>();		//��߿�ʼ�ж��Ƿ񻻳�
		ArrayList<String> line_belong_nowV = new ArrayList<>();
		String line = null;String line_now = null;
		line_belong_beforeV = result.peek().getLine_belong();
		int same_line_cnt;
		while(!result.isEmpty()) {		//��ջ����
			line_belong_nowV = result.peek().getLine_belong();
			same_line_cnt=0;	//���ڱ���2���ߵĻ���������ڣ�����ڶ����ͬ����·Ӱ���ж�����߶���һ��������������
			for(int i=0;i<line_belong_beforeV.size();i++) {
				for(int j=0;j<line_belong_nowV.size();j++) {
					if(line_belong_beforeV.get(i).equals(line_belong_nowV.get(j))) {
						same_line_cnt++;
						if(same_line_cnt==1) {		//���ж���ͬ��·���ж�
							if(line==null) {
								line = line_belong_beforeV.get(i);
							}
							if(!line.isEmpty()&&!line.equals(line_belong_nowV.get(j))) {
								line = line_belong_nowV.get(j);
								System.out.println("����"+line);
							}
						}
						if(same_line_cnt>1) {
							continue;
						}
					}
				}
			}
			shortestDist++;			//�����·���ĳ���
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
