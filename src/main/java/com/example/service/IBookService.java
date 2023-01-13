package com.example.service;

import java.util.List;

import com.example.dto.BookDto;
import com.example.model.BookModel;

public interface IBookService {

	BookModel addBook(BookDto bookDTO);

	List<BookModel> findAll();

	BookModel FindById(Long bookid);

	String deleteById(Long bookid);

	BookModel findBookByName(String bookName);

	BookModel updateBookData(Long id, BookDto bookDTO);

	List<BookModel> sortPriceLowToHigh();

	List<BookModel> sortPriceHighToLow();

	BookModel changeBookQty(Long bookid, int bookQuantity);
}
