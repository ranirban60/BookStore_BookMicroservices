package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDto;
import com.example.dto.ResponseDto;
import com.example.model.BookModel;
import com.example.service.IBookService;

@RestController
@RequestMapping("/Book")
public class BookController {

	 @Autowired
	    IBookService bookService;

	    // add book
	    @PostMapping("/add")
	    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody BookDto bookDTO) {
	        BookModel book = bookService.addBook(bookDTO);
	        ResponseDto responseDTO = new ResponseDto("Add Book  Success", book);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    //  Ability to get all book data by findAll() method
	    @GetMapping("/findAll")
	    public ResponseEntity<ResponseDto> findAllDetail() {
	        List<BookModel> userList = bookService.findAll();
	        ResponseDto responseDTO = new ResponseDto("** All book List ** ", userList);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    //Ability to get book data by id
	    @GetMapping("/get/{bookId}")
	    public ResponseEntity<ResponseDto> FindById(@PathVariable Long bookId) {
	    	BookModel response = bookService.FindById(bookId);
	        ResponseDto responseDto = new ResponseDto("***All Details book list using Id***", response);
	        return new ResponseEntity<>(responseDto, HttpStatus.OK);
	    }
	    // Ability to delete book data for particular id
	    @DeleteMapping("/delete/{bookId}")
	    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long bookId) {
	        bookService.deleteById(bookId);
	        ResponseDto reponseDTO = new ResponseDto("**book Data deleted successfully ** ", "deleted id " + bookId);
	        return new ResponseEntity<ResponseDto>(reponseDTO, HttpStatus.ACCEPTED);
	    }
	    /* create a method name as getBookByName
	     Ability to get book by book name*/
	    @GetMapping("/getBybookName/{bookName}")
	    public ResponseEntity<ResponseDto> getBookByName(@PathVariable String bookName) {
	    	BookModel response = bookService.findBookByName(bookName);
	        ResponseDto responseDTO = new ResponseDto("successfully record founded for given book name: ", response);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    //Ability to update book data for particular id
	    @PutMapping("/update/{id}")
	    public ResponseEntity<ResponseDto> editData(@PathVariable Long bookid, @Valid @RequestBody BookDto bookDTO) {
	    	BookModel bookData = bookService.updateBookData(bookid, bookDTO);
	        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", bookData);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    /*  get book data in ascending order
	      return  data in ascending order
	      */
	    @GetMapping("/sortByPriceAsc")
	    public ResponseEntity<ResponseDto> getBookByPriceAsc() {
	        List<BookModel> bookData = bookService.sortPriceLowToHigh();
	        ResponseDto responseDTO = new ResponseDto("Sorted all books by price low to high ", bookData);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }
	    /* get book data in descending order
	      return - data in descending order
	     */
	    @GetMapping("/sortByPriceDsc")
	    public ResponseEntity<ResponseDto> getBookByPriceDsc() {
	        List<BookModel> bookData = bookService.sortPriceHighToLow();
	        ResponseDto responseDTO = new ResponseDto("Sorted all books by price high to low ", bookData);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }

	    // update  book quantity

	    @PostMapping("/update-qty")
	    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam Long bookid, @RequestParam int bookQuantity) {
	    	BookModel book = bookService.changeBookQty(bookid, bookQuantity);
	        ResponseDto responseDTO = new ResponseDto("Book quantity changed successfully", book);
	        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	    }

}
