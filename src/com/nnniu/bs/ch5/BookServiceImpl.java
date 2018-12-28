package com.nnniu.bs.ch5;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BookServiceImpl implements BookService {
	
	private BookDao bookDao;
	
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	@Override
	public void save(Book book) {
		try {
			bookDao.save(book);
		} catch (DataAccessException e) {
			System.out.println("myy exception");
			e.printStackTrace();
		}
		
	}
}
