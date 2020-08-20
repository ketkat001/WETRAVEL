package com.ssafy.travel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.travel.dto.Book;

@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public int registBook(Book book) {
		return sqlSession.insert("m_book.registBook",book);
	}

	@Override
	public List<Book> getBookListByScore(String province, String city) {
		Book b = new Book();
		b.setProvince(province);
		b.setCity(city);
		return sqlSession.selectList("m_book.getBookListByScore", b);
	}

	@Override
	public int deleteBook(int bookno) {
		return sqlSession.delete("m_book.deleteBook",bookno);
	}

	@Override
	public int modifyBook(Book book) {
		return sqlSession.update("m_book.modifyBook",book);
	}

	@Override
	public Book getBookDetail(int bookno) {
		Book res = sqlSession.selectOne("m_book.getBookDetail", bookno);
		return res;
	}

	@Override
	public List<Book> getBookList(String province, String city, int month) {
		Book b = new Book();
		b.setProvince(province);
		b.setCity(city);
		b.setStartdate(month+"");
		return sqlSession.selectList("m_book.getBookList", b);
	}
	

	@Override
	public List<Book> getBookListByUser(String nickname) {
		return sqlSession.selectList("m_book.getBookListByUser", nickname);
	}

	@Override
	public int viewIncrement(int bookno, String viewer) {
		Map map = new HashMap<String, Object>();
		map.put("bookno", bookno);
		map.put("viewer", viewer);
		return sqlSession.update("m_book.viewIncrement", map);
	}
}
