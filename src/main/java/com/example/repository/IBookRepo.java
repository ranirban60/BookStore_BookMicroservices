package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.BookModel;

@Repository
public interface IBookRepo extends JpaRepository<BookModel, Long> {

	@Query(value = "SELECT * FROM book WHERE book_name=:bookName", nativeQuery = true)
	BookModel findBookByName(String bookName);

	@Query(value = "select * from book order by price", nativeQuery = true)
	List<BookModel> getSortedListOfBooksInAsc();

	@Query(value = "select * from book order by price desc", nativeQuery = true)
	List<BookModel> getSortedListOfBooksInDesc();
}
