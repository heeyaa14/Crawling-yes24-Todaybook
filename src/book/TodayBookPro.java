package book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import book.DBConnect;
import book.TodayBook;

public class TodayBookPro {
	Scanner sc = new Scanner(System.in);
	ArrayList<TodayBook> bookList = null;
	
	public void crawling() {
		String url = "http://www.yes24.com/24/Category/Display/001005033"; //크롤링할 웹페이지 주소
		Document doc = null;
		try {
			
			doc=Jsoup.connect(url).get();
			Elements bookList = doc.select("ul.clearfix").select(".cCont_goodsSet");
			
			for (int i=1; i<bookList.size(); i++) {
				Element book = bookList.get(i);
				String title = book.select(".goods_name").select("a").text();
				String auth = book.select(".goods_auth").text();
				String pub = book.select(".goods_pub").text();
				String p_date = book.select(".goods_date").text();
				String price = book.select(".goods_price").select("em.yes_b").text();
				String score = book.select(".gd_rating").select("em.yes_b").text();
				String img = book.select(".imgBdr").select("img").attr("src");

				TodayBook book1 = new TodayBook(i, title, auth, pub, p_date, price, score, img);
				DBConnect conn = DBConnect.getInstance();
				conn.insertBook(book1);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}	

	public void show() {
		DBConnect conn = DBConnect.getInstance();
		ArrayList<TodayBook> list = conn.selectAll();
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	public void search() {
		System.out.println("검색할 책 제목 입력");
		String title = sc.next();
		DBConnect conn = DBConnect.getInstance();
		ArrayList<TodayBook> list = conn.selectSearch(title);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	private void updateBook() {
		System.out.println("업데이트 할 책 번호 입력");
		int no = sc.nextInt();
		TodayBook book = DBConnect.getInstance().selectOne(no);
		System.out.println(book);
		System.out.println("제목 입력");
		sc.nextLine();
		book.setTitle(sc.nextLine());
		System.out.println("가격 입력");
		book.setPrice(sc.next());

		DBConnect.getInstance().update(book);
	}
	
	private void deleteBook() {
		System.out.println("삭제할 책 번호 입력");
		int no = sc.nextInt();
		TodayBook book = DBConnect.getInstance().selectOne(no);
		System.out.println(book);
		System.out.println("정말 삭제할까요?");
		String yesNo = sc.next();
		if (yesNo.equals("yes")) {
			DBConnect.getInstance().delete(no);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TodayBookPro pro = new TodayBookPro();
		pro.run();
	}
	
	private void run() {
		System.out.println("책 검색 프로그램");
		while(true) {
			System.out.println("1. 책정보 저장 |2. 전체보기 |3. 검색 |4. 업데이트 |5. 삭제 |6. 종료  ");
			int num = sc.nextInt();
			switch (num) {
				case 1: crawling(); break;
				case 2: show(); break;
				case 3: search(); break;
				case 4: updateBook(); break;
				case 5: deleteBook(); break;
				case 6: break;
				default: System.out.println("입력오류"); 
			}
			if (num ==6) break;
		}
		System.out.println("프로그램 종료");
	}
}
