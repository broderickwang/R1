package marc.com.r1;

/**
 * Created by Broderick
 * User: Broderick
 * Date: 2017/6/12
 * Time: 10:33
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class CheckBean {
	private String price;
	private Boolean check;
	private int count;

	public CheckBean(String price, Boolean check) {
		this.price = price;
		this.check = check;
	}

	public CheckBean(String price, Boolean check, int count) {
		this.price = price;
		this.check = check;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getCheck() {
		return check;
	}

	public void setCheck(Boolean check) {
		this.check = check;
	}
}
