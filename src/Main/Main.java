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
		System.out.println("1����ѯ·��");
		System.out.println("2����ѯ���·��");
		System.out.println("��������Ҫ�ķ���");
		Scanner input = new Scanner(System.in);
		int inputnum=input.nextInt();
		while(inputnum!=1||inputnum!=2) {
			System.out.println("1����ѯ·��");
			System.out.println("2����ѯ���·��");
			System.out.println("��������Ҫ�ķ���");
			inputnum = input.nextInt();
		}
		input.close();
	}
}
