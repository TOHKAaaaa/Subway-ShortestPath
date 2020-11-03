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
			  String obtain = " ";//�ָ����
		      File file=new File(Path);
		      //�ж��ļ��Ƿ����
		      if(file.isFile() && file.exists()){ 
		    	  InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
		    	  BufferedReader bufferedReader = new BufferedReader(reader);
		          String stationline = null;
		          while((stationline = bufferedReader.readLine()) != null){       
		        	  //�ָ��ַ���
		        	  Station station=new Station();
		        	  String string="";	
		              String tmp[] = stationline.split(obtain);
		              string=tmp[0];
		              station.setStationNo(string);
		              List<String> allstation=new ArrayList<>();
		              //���������վ��
		              for(String s:tmp) {
		            	  allstation.add(s);
		              }
		              allstation.remove(0);
		              station.setAllStation(allstation);
		              allroute.add(station);
		          }
		         // �ر��ļ�
		          reader.close();
		          bufferedReader.close();
		      }
		      else
		          System.out.println("�Ҳ���ָ�����ļ�!");
		    } catch (Exception e) {
		        System.out.println("�ļ����ݳ���!");
		        e.printStackTrace();
		     }
//		  System.out.println("�����ļ�������");
		  return allroute;
		 }
}
