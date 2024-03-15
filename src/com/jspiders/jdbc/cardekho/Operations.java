package com.jspiders.jdbc.cardekho;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Operations {
	
		private static Connection connection;
		private static PreparedStatement preparedStatement;
		private static String query;
		private static ResultSet resultSet;
		private static Statement statement;

		Scanner scanner = new Scanner(System.in);

		public void addCar() throws IOException {

			System.out.println("Enter car id");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter car name");
			String name = scanner.nextLine();
			System.out.println("Enter car brand");
			String brand = scanner.nextLine();
			System.out.println("Enter car colour");
			String colour = scanner.nextLine();
			System.out.println("Enter car model");
			String model = scanner.nextLine();
			System.out.println("Enter car fueltype");
			String fueltype = scanner.nextLine();
			System.out.println("Enter car price");
			int price = scanner.nextInt();
			
			try {
				openConnection();
				query = "INSERT INTO cardekho_db VALUES(?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, name);
				preparedStatement.setString(3, brand);
				preparedStatement.setString(4, colour);
				preparedStatement.setString(5, model);
				preparedStatement.setString(6, fueltype);
				preparedStatement.setInt(7, price);
				int res = preparedStatement.executeUpdate();
				System.out.println(res + " row's affected");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public void searchAllCar() throws IOException {

			try {
				openConnection();
				statement = connection.createStatement();
				query = "SELECT * FROM cardekho_db";
				resultSet = statement.executeQuery(query);
				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getString(3));
					System.out.println(resultSet.getString(4));
					System.out.println(resultSet.getString(5));
					System.out.println(resultSet.getString(6));
					System.out.println(resultSet.getInt(7));

				}
				if (!found) {
					System.out.println("Car not found or invalid input");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}

		public void removeCar() throws IOException {
			System.out.println("Enter Id of the car");
			int id = scanner.nextInt();

			try {
				openConnection();
				query = "DELETE FROM cardekho_db WHERE id=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				int res = preparedStatement.executeUpdate();
				if (res == 1) {
					System.out.println(res + " car info deleted");
				} else {
					System.out.println("Car not found");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		public void editCar() {
			System.out.println("Enter id of the car u want to update");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter car name");
			String name = scanner.nextLine();
			System.out.println("Enter car brand");
			String brand = scanner.nextLine();
			System.out.println("Enter car colour");
			String colour = scanner.nextLine();
			System.out.println("Enter car model");
			String model = scanner.nextLine();
			System.out.println("Enter car fueltype");
			String fueltype = scanner.nextLine();
			System.out.println("Enter car price");
			int price = scanner.nextInt();

			try {
				openConnection();
				query = "UPDATE cardekho_db SET name=?,brand=?,colour=?,model=?,fueltype=?,price=? WHERE id=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(7, id);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, brand);
				preparedStatement.setString(3, colour);
				preparedStatement.setString(4, model);
				preparedStatement.setString(5, fueltype);
				preparedStatement.setInt(6, price);
				int res = preparedStatement.executeUpdate();
				if (res == 1) {
					System.out.println(res + " car info updated ");
				} else {
					System.out.println("Car not found");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public void searchCar() throws IOException, SQLException {
			System.out.println("=====Find Cars====\n" + "1.Search all cars\n" + "2.Search by id\n" + "3.Search by price\n"
					+ "4.Search by brand\n" + "5.Search by Fueltype\n" + "6.Go Back\n");

			System.out.println("Enter your choice");
			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				searchAllCar();
				break;
			case 2:
				searchById();
				break;
			case 3:
				searchByPrice();
				break;
			case 4:
				searchByBrand();
				break;
			case 5:
				searchByFuelType();
				break;
			case 6:
				return;

			default:
				System.out.println("invalid input");
				break;
			}
		}

		public void searchById() throws SQLException {
			System.out.println("Enter car id");
			int id = scanner.nextInt();
			try {
				openConnection();
				query = "SELECT * FROM cardekho_db WHERE id=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, id);
				resultSet = preparedStatement.executeQuery();
				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getString(3));
					System.out.println(resultSet.getString(4));
					System.out.println(resultSet.getString(5));
					System.out.println(resultSet.getString(6));
					System.out.println(resultSet.getInt(7));

				}
				if (!found) {
					System.out.println("Car not found or invalid input");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		public void searchByPrice() throws SQLException {
			System.out.println("Enter min car Price");
			int min = scanner.nextInt();
			System.out.println("Enter max car Price");
			int max = scanner.nextInt();

			try {
				openConnection();
				query = "SELECT * FROM cardekho_db WHERE price BETWEEN ? AND ?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, min);
				preparedStatement.setInt(2, max);
				resultSet = preparedStatement.executeQuery();
				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getString(3));
					System.out.println(resultSet.getString(4));
					System.out.println(resultSet.getString(5));
					System.out.println(resultSet.getString(6));
					System.out.println(resultSet.getInt(7));

				}
				if (!found) {
					System.out.println("Car not found or invalid input");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		public void searchByBrand() throws SQLException {
			// whenever the previous input is int and current input is string we need to add
			// nextline() before sting input
			scanner.nextLine();
			System.out.println("Enter car Brand");
			String brand = scanner.nextLine();
			try {
				openConnection();
				query = "SELECT * FROM cardekho_db WHERE brand =?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, brand);
				resultSet = preparedStatement.executeQuery();

				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getString(3));
					System.out.println(resultSet.getString(4));
					System.out.println(resultSet.getString(5));
					System.out.println(resultSet.getString(6));
					System.out.println(resultSet.getInt(7));

				}
				if (!found) {
					System.out.println("Car not found or invalid input");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		public void searchByFuelType() throws SQLException {
			scanner.nextLine();
			System.out.println("Enter car Fuel type");
			String fuel = scanner.nextLine();
			try {
				openConnection();
				query = "SELECT * FROM cardekho_db WHERE fueltype =?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, fuel);
				resultSet = preparedStatement.executeQuery();

				boolean found = false;
				while (resultSet.next()) {
					found = true;
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
					System.out.println(resultSet.getString(3));
					System.out.println(resultSet.getString(4));
					System.out.println(resultSet.getString(5));
					System.out.println(resultSet.getString(6));
					System.out.println(resultSet.getInt(7));

				}
				if (!found) {
					System.out.println("Car not found or invalid input");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

		private static void openConnection() throws IOException, SQLException {
		
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4?user=root&&password=root");

		}

		private static void closeConnection() throws SQLException {

			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
			if (resultSet != null) {
				connection.close();
			}
			if (statement != null) {
				connection.close();
			}
		}

		


	}

