package browser;

import org.testng.annotations.Test;

public class LoginTest2 extends BaseClass{
	@Test
	public void openLeetcode() {
		driver.get("https://leetcode.com/");
		String title=driver.getTitle();
		System.out.println("Page Title :"+title);
		driver.quit();
	}
}