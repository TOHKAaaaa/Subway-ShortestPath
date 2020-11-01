package Main;

import java.io.IOException;
import java.util.Scanner;

import FileTxt.FileRead;

public class Main {
	public static FileRead fileRead;
	public static void main(String[] args){
		try {
			fileRead = new FileRead("src/data.txt");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("1、查询路线");
		System.out.println("2、查询最短路径");
		System.out.println("请输入需要的服务：");
		Scanner input = new Scanner(System.in);
		int inputnum=input.nextInt();
		while(inputnum!=1||inputnum!=2) {
			System.out.println("1、查询路线");
			System.out.println("2、查询最短路径");
			System.out.println("请输入需要的服务：");
			inputnum = input.nextInt();
		}
		input.close();
	}
}
