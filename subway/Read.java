package subway;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Read {
	
	public static LinkedHashSet<List<Vertex>> lineSet = new LinkedHashSet<List<Vertex>>();		//存储线路的总集合
	public static ArrayList<String> line_name_output = new ArrayList<>();		//用于存储线路名称的集合
	public Read() throws IOException{
		InputStreamReader reader = new InputStreamReader( new FileInputStream("C:\\Users\\23082\\Downloads\\地铁线路信息.txt"));
		BufferedReader br = new BufferedReader(reader);
		String line_info;
		line_info = br.readLine();
		while(line_info!=null) {
			List<Vertex> line = new ArrayList<Vertex>();
			String infos[] = line_info.split(" ");
			String linename = infos[0];		//代表的是线路名称
			line_name_output.add(infos[0]);		
			for(int i=1;i<infos.length;i++) {
				int flag=0;
				for(List<Vertex> v:lineSet) {
					for(int j=0;j<v.size();j++) {	//处理站点的不同线路
						if(v.get(j).getName().equals(infos[i])){
							ArrayList<String> newline = v.get(j).getLine_belong();
							newline.add(linename);
							v.get(j).setLine_belong(newline);
							line.add(v.get(j));
							flag=1;
							break;
						}
					}
					if(flag==1) {
						break;
					}
				}
				if(i==infos.length-1&&infos[i].equals(infos[1])) {
					line.get(0).getNearV().add(line.get(line.size()-1));
					line.get(line.size()-1).getNearV().add(line.get(0));
					line.add(new Vertex("环线",linename));		//给环线最后输入一个标识符，用来输出线路时进行相应的操作
					flag=1;
				}
				if(flag==0) {
					line.add(new Vertex(infos[i],linename));
				}
			}
			for(int a=0;a<line.size();a++) {	//处理站点的相邻站点
				ArrayList<Vertex> NearV = line.get(a).getNearV();
				if(a==0) {
					NearV.add(line.get(a+1));
					line.get(a).setNearV(NearV);
				}
				else if(a==line.size()-1) {
					NearV.add(line.get(a-1));
					line.get(a).setNearV(NearV);
				}
				else {
					NearV.add(line.get(a+1));
					NearV.add(line.get(a-1));
					line.get(a).setNearV(NearV);
				}
			}
			lineSet.add(line);
			line_info = br.readLine();
		}
		br.close();
	}
		 
}
