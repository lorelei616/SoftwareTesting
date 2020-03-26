package cn.tjuscs.selenium;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Locator {
	
	public static void main(String[] args) throws Exception{
		//firefox的驱动
		String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		WebDriver driver = new FirefoxDriver();
		
		//chrome的驱动，虽然我已经放弃用chrome来搞selenium了
//		String driverPath = System.getProperty("user.dir") + "/src/driverchrome/chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", driverPath);
//		WebDriver driver = new ChromeDriver();
		
		String baseUrl = "http://103.120.226.190/selenium-demo/git-repo";
		driver.get(baseUrl);//打开网页
		//存储用户名和密码的String数组
		String[] inputArray=readTable();//声明数组并调用函数将文件中的内容保存在数组中
		//循环进行验证
		int i=0;
		while(i<40) {//一共是20次验证
			WebElement userName = driver.findElement(By.name("user_number"));
			userName.sendKeys(inputArray[i]);
			WebElement passName = driver.findElement(By.name("password"));
			i++;
			passName.sendKeys(inputArray[i]);
			i++;
			WebElement submitName = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div/form/div[3]/input"));
			submitName.click();
			
			WebElement textShow = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/div/form/div[4]/code"));
				if(textShow.getText().equals("")) {
					System.out.println((i+1)/2+"Success!");
				}else {
					System.out.println((i+1)/2+"Failed!");
				}
		}
		
//		driver.close();
	}

	
    //通过对单元格遍历的形式来获取信息 ，这里要判断单元格的类型才可以取出值
    public static String[] readTable() throws Exception {
    	String[] inputArray = new String[40];
        InputStream ips = new FileInputStream("C:\\Users\\Lorelei\\Desktop\\这学期\\软件测试\\第二次实验\\Selenium+Lab2020.xls");
        HSSFWorkbook wb = new HSSFWorkbook(ips);
        HSSFSheet sheet = wb.getSheetAt(0);
        int i=0;
        for (Iterator ite = sheet.rowIterator(); ite.hasNext(); ) {
            HSSFRow row = (HSSFRow) ite.next();
//            System.out.println();
            for (Iterator itet = row.cellIterator(); itet.hasNext(); ) {
                HSSFCell cell = (HSSFCell) itet.next();
                inputArray[i]=cell.getRichStringCellValue().toString();
//                System.out.println(inputArray[i]);
              
              i++;
            }
            if(i==40)
            	break;
        }
//        System.out.println(i);
        return inputArray;
    }
	
	

}
