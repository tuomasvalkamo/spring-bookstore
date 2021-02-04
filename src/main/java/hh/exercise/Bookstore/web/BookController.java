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

	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	@PostMapping("/save")
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") long bookId, Model model) {
		repository.deleteById(bookId);

		return "redirect:../booklist";
	}

	@GetMapping("/editbook/{id}")
	public String editBook(@PathVariable("id") long bookId, Book existingBook, Model model) {
		model.addAttribute("book", existingBook);
		model.addAttribute("id", bookId);
		return "editbook";
	}
}
