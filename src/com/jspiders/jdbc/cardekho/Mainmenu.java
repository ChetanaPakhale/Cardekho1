package com.jspiders.jdbc.cardekho;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
public class Mainmenu {
	
	public static void main(String[] args) throws IOException, SQLException {
		
		Operations operations = new Operations();
		

		boolean flag = true;
		
		Scanner scanner = new Scanner(System.in);

		while (flag) {
			System.out.println("=====Main Menu====\n" + "1.View All Cars\n" + "2.Search Car\n" + "3.Add Car\n"
					+ "4.Remove Car\n" + "5.Edit Car\n" + "6.Exit\n" );
			System.out.println("Enter your choice");
			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				operations.searchAllCar();
				break;
			case 2:
				operations.searchCar();
				break;
			case 3:
				operations.addCar();
				break;
     		case 4:
				operations.removeCar();
				break;
			case 5:
			operations.editCar();
				break;
			case 6:
				flag = false;
				System.out.println("App closed");
				break;

			default:
				System.out.println("invalid input");
				break;
			}

		}

		scanner.close();
	}
		
}
