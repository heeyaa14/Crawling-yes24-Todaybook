package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnect {
	
	private DBConnect() {}
	
	private static DBConnect instance=new DBConnect();
	public static DBConnect getInstance() {
		return instance;
	}
	
	private Connection getConnect() {
		Connection conn =null;
		String url="jdbc:oracle:thin:@localhost:1522:xe";
		String user="pkh";
		String password="1234";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,user,password);
		}catch(Exception ex) {
			ex.getStackTrace();
		}
		return conn;
	}
	
	public ArrayList<TodayBook> selectAll(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from book";
		
		ArrayList<TodayBook> bookList = new ArrayList<TodayBook>();
		try {
			conn = getConnect();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				TodayBook book=new TodayBook();
				book.setNo(rs.getInt("no"));
				book.setTitle(rs.getString("title"));
				book.setAuth(rs.getString("auth"));
				book.setPub(rs.getString("pub"));
				book.setP_date(rs.getString("p_date"));
				book.setPrice(rs.getString("price"));
				book.setScore(rs.getString("score"));
				book.setImg(rs.getString("img"));

				bookList.add(book);
			}
		}catch(Exception ex){
			ex.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return bookList;
	}
	
	public ArrayList<TodayBook> selectSearch(String title){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from book where title like ?";
		ArrayList<TodayBook> bookList = new ArrayList<TodayBook>();
		try {
			conn = getConnect();
			ps=conn.prepareStatement(sql);
			ps.setString(1,"%" + title + "%");
			rs=ps.executeQuery();
			while(rs.next()) {
				TodayBook book=new TodayBook();
				book.setNo(rs.getInt("no"));
				book.setTitle(rs.getString("title"));
				book.setAuth(rs.getString("auth"));
				book.setPub(rs.getString("pub"));
				book.setP_date(rs.getString("p_date"));
				book.setPrice(rs.getString("price"));
				book.setScore(rs.getString("score"));
				book.setImg(rs.getString("img"));
				bookList.add(book);
			}
		}catch(Exception ex){
			ex.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return bookList;
	}
	
	public TodayBook selectOne(int no){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from book";
		TodayBook book = null;
		try {
			conn = getConnect();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				book=new TodayBook();
				book.setNo(rs.getInt("no"));
				book.setTitle(rs.getString("title"));
				book.setAuth(rs.getString("auth"));
				book.setPub(rs.getString("pub"));
				book.setP_date(rs.getString("p_date"));
				book.setPrice(rs.getString("price"));
				book.setScore(rs.getString("score"));
				book.setImg(rs.getString("img"));
			}
		}catch(Exception ex){
			ex.getStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
		return book;
	}
	
	public void insertBook(TodayBook book) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="insert into book values(?,?,?,?,?,?,?,?)";
		try {
			conn=getConnect();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, book.getNo());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuth());
			ps.setString(4, book.getPub());
			ps.setString(5, book.getP_date());
			ps.setString(6, book.getPrice());
			ps.setString(7, book.getScore());
			ps.setNString(8, book.getImg());
			
			int n=ps.executeUpdate();
			if(n>=1)
				System.out.println("데이터 입력 성공");
			
		}catch(Exception ex) {
			ex.getStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	
	public void update (TodayBook book) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update book set title=?,price=? where no=?";
		
		try {
			conn=getConnect();
			ps=conn.prepareStatement(sql);

			ps.setString(1, book.getTitle());
			ps.setString(2, book.getPrice());
			ps.setInt(3, book.getNo());
			
			int n=ps.executeUpdate();
			
			if(n>0)
				System.out.println(n+"개의 행이 업데이트 되었습니다.");
			else 
				System.out.println("업데이트 실패");
		}catch(Exception ex) {
			ex.getStackTrace();
		}finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
	public void delete (int no) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="delete from book where no=?";
		
		try {
			conn = getConnect();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			int n = ps.executeUpdate();
			if (n>0) 
				System.out.println(n+"개의 데이터가 삭제되었습니다.");
			else 
				System.out.println("삭제 실패");
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}
}
