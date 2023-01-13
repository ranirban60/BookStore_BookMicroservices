package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BookDto;
import com.example.exception.BookException;
import com.example.model.BookModel;
import com.example.repository.IBookRepo;
import com.example.utility.EmailSenderService;

@Service
public class BookService implements IBookService{

	 @Autowired
	    IBookRepo bookRepo;
	    @Autowired
	    EmailSenderService emailSenderService;
	    
	    // create a method name as add book
	    public BookModel addBook(BookDto bookDTO) {
	    	BookModel addData = new BookModel(bookDTO);
	        return bookRepo.save(addData);
	    }
	    //  create a method name as findall()
	    @Override
	    public List<BookModel> findAll() {
	        List<BookModel> bookList = bookRepo.findAll();
	        return bookList;
	    }
	    // create a method name as findbyId
	    @Override
	    public BookModel FindById(Long id) {
	    	BookModel book = bookRepo.findById(id).orElse(null);
	        if (book != null) {
	            return book;

	        }else
	            throw new BookException("book id is not found");
	    }
	    //create a method name as gdeleteByid
	    @Override
	    public String deleteById(Long id) {
	    	BookModel findById = bookRepo.findById(id).orElse(null);

	        if (findById != null) {
	            bookRepo.deleteById(id);
	            return "data is deleted";

	        } else throw new BookException("id is not invalid");

	    }
	    //  create a method name as findbookByname

	    @Override
	    public BookModel findBookByName(String bookName) {
	    	BookModel bookList =  bookRepo.findBookByName(bookName);
	        if (bookList!=null) {
	            bookRepo.findBookByName("bookname");
	            return bookList;

	        }else   throw new BookException(" Book with naame  is  found!");

	    }
	    // create a method name as updateBookData
	    @Override
	    public BookModel updateBookData(Long id, BookDto bookdto) {
	    	BookModel editbook = bookRepo.findById(id).orElse(null);
	        if (editbook != null) {
	            editbook.setBookName(bookdto.getBookName());
	            editbook.setAuthorName(bookdto.getAuthorName());
	            editbook.setBookDescription(bookdto.getBookDescription());
	            editbook.setBookImage(bookdto.getBookImage());
	            editbook.setPrice(bookdto.getPrice());
	            editbook.setBookQuantity(bookdto.getBookQuantity());

	            return bookRepo.save(editbook);
	        } else throw new BookException("Id:"+id+" is not present ");

	    }

	    @Override
	    public List<BookModel> sortPriceLowToHigh() {
	        List<BookModel> getSortedList = bookRepo.getSortedListOfBooksInAsc();
	        return getSortedList;
	    }

	    @Override
	    public List<BookModel> sortPriceHighToLow() {
	        List<BookModel> getSortedListInDesc =bookRepo.getSortedListOfBooksInDesc();
	        return getSortedListInDesc;

	    }

	    @Override
	    public BookModel changeBookQty(Long id, int bookQuantity) {
	    	BookModel book = bookRepo.findById(id).orElse(null);
	        if(book == null){
	            throw new BookException("id is not found");
	        }
	        book.setBookQuantity(bookQuantity);
	        return bookRepo.save(book);
	    }
}
