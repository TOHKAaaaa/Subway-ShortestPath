package FileRead;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import Model.Station;

public class ReadTxt {
	 public List<Station> readText(String Path){
		  List<Station> allroute=new ArrayList<Station>();
		  try {
			  String obtain = " ";//分割符号
		      File file=new File(Path);
		      //判断文件是否存在
		      if(file.isFile() && file.exists()){ 
		    	  InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		    	  BufferedReader bufferedReader = new BufferedReader(reader);
		          String stationline = null;
		          while((stationline = bufferedReader.readLine()) != null){       
		        	  //分割字符串
		        	  Station station=new Station();
		        	  String string="";	
		              String tmp[] = stationline.split(obtain);
		              string=tmp[0];
		              station.setStationNo(string);
		              List<String> allstation=new ArrayList<>();
		              //遍历后加入站点
		              for(String s:tmp) {
		            	  allstation.add(s);
		              }
		              allstation.remove(0);
		              station.setAllStation(allstation);
		              allroute.add(station);
		          }
		         // 关闭文件
		          reader.close();
		          bufferedReader.close();
		      }
		      else
		          System.out.println("找不到指定的文件!");
		    } catch (Exception e) {
		        System.out.println("文件内容出错!");
		        e.printStackTrace();
		     }
//		  System.out.println("读入文件结束！");
		  return allroute;
		 }
}
