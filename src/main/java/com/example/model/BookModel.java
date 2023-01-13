package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.dto.BookDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

@Table(name="Book")
public class BookModel {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "bookId", nullable = false)
	    private Long bookId;

	    @Column(name="book_name")
	    private String bookName;
	    private String authorName;
	    private int bookQuantity;
	    private int price;
	    private String BookImage;
	    private String bookDescription;
	    
	 // parameterized constructor
	    public BookModel(BookDto bookDto) {
	        this.bookName = bookDto.getBookName();
	        this.authorName = bookDto.getAuthorName();
	        this.bookQuantity = bookDto.getBookQuantity();
	        this.price = bookDto.getPrice();
	        this.BookImage=bookDto.getBookImage();
	        this.bookDescription =bookDto.getBookDescription();
	    }
}
