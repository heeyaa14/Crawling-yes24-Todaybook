package book;

public class TodayBook {
	private int no;
	private String title;
	private String auth;
	private String pub;
	private String p_date;
	private String price;
	private String score;
	private String img;
	
	public TodayBook() {}

	public TodayBook(int no, String title, String auth, String pub, String p_date, String price, String score,
			String img) {
		super();
		this.no = no;
		this.title = title;
		this.auth = auth;
		this.pub = pub;
		this.p_date = p_date;
		this.price = price;
		this.score = score;
		this.img = img;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getPub() {
		return pub;
	}

	public void setPub(String pub) {
		this.pub = pub;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "no=" + no + ", title=" + title + ", auth=" + auth + ", pub=" + pub + ", p_date=" + p_date
				+ ", price=" + price + ", score=" + score + ", img=" + img;
	}	
}

