package entities;

public class Product {
	
	private String name;
	private double price;
	private int qtd;
	
	public Product() {
		
	}
	
	public Product(String name, double price, int qtd) {
		this.name = name;
		this.price = price;
		this.qtd = qtd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	public double total() {
		return price * qtd;
	}

}
