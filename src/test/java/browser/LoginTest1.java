package browser;

import org.testng.annotations.Test;

public class LoginTest1 extends BaseClass{
	@Test
	public void openGoogle() {
		driver.get("https://www.google.com");
		String title=driver.getTitle();
		System.out.println("Page Title :"+title);
		driver.quit();
	}
}