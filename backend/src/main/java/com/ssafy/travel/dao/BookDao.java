package com.ssafy.travel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.travel.dto.Book;

public interface BookDao {
	public int registBook(Book book); 
	public List<Book> getBookListByScore();
	public int deleteBook(int bookno);
	public int modifyBook(Book book);
	public int viewIncrement(int bookno, String viewer);
	public Book getBookDetail(int bookno);
	public List<Book> getBookList(String province, String city, int month);
	public List<Book> getBookListByUser(String nickname);
}
