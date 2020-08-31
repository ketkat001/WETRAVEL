package com.ssafy.travel.service;

import java.util.List;

import com.ssafy.travel.dto.Book;

public interface BookService {
	public boolean registBook(Book book); 
	public List<Book> getBookList(String province, String city, int month);
	public List<Book> getBookListByScore();
	public boolean deleteBook(int bookno);
	public int viewIncrement(int bookno, String viewer);
	public Book getBookDetail(int bookno);
	public boolean modifyBook(Book book);
	public List<Book> getBookListByUser(String nickname);
}
