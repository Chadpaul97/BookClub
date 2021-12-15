package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;
	

	
	
	//Create one 
	
	public Book create(Book book) {
		return bookRepo.save(book);
	}
	
	
	// Find one 
	public Book oneBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		}else {
			return null;
		}
		
	}
	
	// Find all 
	 public List<Book> allBooks(){
		 return (List<Book>) bookRepo.findAll();
	 }
	
	// Update One 
	
	 public void updateBook(Book book) {
		 bookRepo.save(book);
	 }
	
	//Delete One 
	
	 public void deleteBook(Long id) {
		 bookRepo.deleteById(id);
	 }
	
	
}
