package hh.exercise.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;
import hh.exercise.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository catRepository;

	// Booklist page
	@GetMapping({ "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}
	
	// Login page
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	// Add book page
	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catRepository.findAll());
		return "addbook";
	}

	// Save new book
	@PostMapping("/save")
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	// Delete existing book
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);

		return "redirect:../booklist";
	}

	// Edit existing book
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", catRepository.findAll());
		return "editbook";
	}

	// RESTful service to get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get book by id
	@GetMapping("/book/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
}
