import th.ac.npru.se.phongsakorn.product.ProductSingleton;

public class TestDriver {
	public static void main(String args[]) {
		ProductSingleton p1 = ProductSingleton.getInstance();
//		p1.setProduct("P004", "blue switch", 2000);
//		p1.insertProduct();
		System.out.println(p1.getProductName("P004"));
	}
}
