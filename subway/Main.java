package subway;

import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Read r = new Read();
		int line_output_cnt=0;
		Scanner input = new Scanner(System.in);
		System.out.println("------地铁线路查询系统------");
		System.out.println();
		System.out.println("1.线路总览\t2.线路查询");
		int choice = input.nextInt();
		if(choice==1) {
			for(List<Vertex> v:r.lineSet) {
				System.out.print(r.line_name_output.get(line_output_cnt)+":");
				for(Vertex x:v) {
					if(x.getName().equals("环线")) {
						System.out.print(v.get(0).getName());
					}
					else {
						System.out.print(x.getName()+" ");
					}
				}
				System.out.println();
				line_output_cnt++;
			}
		}
		if(choice==2) {
			System.out.print("请输入起始站点：");
			String start_name = input.next();
			System.out.print("请输入目的站点：");
			String end_name = input.next();
			
			System.out.println();
			
			
			Vertex start = null;Vertex end = null;
			int flag=0;
			for(List<Vertex> l:r.lineSet) {
				for(Vertex v:l) {
					if(v.getName().equals(start_name)&&flag==0) {
						start = v;
						flag=1;
					}
					if(flag==1&&v.getName().equals(end_name)) {
						end = v;
						flag=2;
					}
					if(flag==2) {
						break;
					}
				}
				if(flag==2) {
					break;
				}
			}
			if(start==null) {
				System.out.println("无此起始站点");
			}
			if(end==null) {
				System.out.println("无此目的站点");
			}
			if(start!=null&&end!=null) {
				System.out.println("建议路线为：");
				System.out.println();
				BFS bfs = new BFS();
				int shortestDist = bfs.BFS(start, end);
				System.out.println();
				System.out.print("共经过站点数："+shortestDist);
			}
		}	
	}
}
