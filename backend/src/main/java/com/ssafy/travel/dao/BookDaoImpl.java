package com.ssafy.travel.dao;

import java.util.List;

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
	public List<Book> geteBookList() {
		return sqlSession.selectList("m_book.getBookList");
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
		return sqlSession.selectOne("m_book.getBookDetail", bookno);
	}


}
