package hh.exercise.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	// Booklist page
	@GetMapping({ "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	// Add book page
	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
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
	public String deleteBook(@PathVariable("id") long bookId, Model model) {
		repository.deleteById(bookId);

		return "redirect:../booklist";
	}

	// Edit existing book
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		return "editbook";
	}
}
