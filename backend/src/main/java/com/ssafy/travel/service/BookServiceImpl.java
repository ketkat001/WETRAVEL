package com.ssafy.travel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.travel.dao.BookDao;
import com.ssafy.travel.dto.Book;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao dao;

	@Override
	public boolean registBook(Book book) {
		return dao.registBook(book) == 1;
	}

	@Override
	public List<Book> getBookListByScore() {
		return dao.getBookListByScore();
	}

	@Override
	public boolean deleteBook(int bookno) {
		return dao.deleteBook(bookno) == 1;
	}

	@Override
	public Book getBookDetail(int bookno) {
		return dao.getBookDetail(bookno);
	}

	@Override
	public boolean modifyBook(Book book) {
		return dao.modifyBook(book) == 1;
	}

	@Override
	public List<Book> getBookList(String province, String city, int month) {
		return dao.getBookList(province, city, month);
	}

	@Override
	public List<Book> getBookListByUser(String nickname) {
		return dao.getBookListByUser(nickname);
	}

	@Override
	public int viewIncrement(int bookno, String viewer) {
		return dao.viewIncrement(bookno, viewer);
	}
}
