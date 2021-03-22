package hh.exercise.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.exercise.Bookstore.domain.Book;
import hh.exercise.Bookstore.domain.BookRepository;
import hh.exercise.Bookstore.domain.Category;
import hh.exercise.Bookstore.domain.CategoryRepository;
import hh.exercise.Bookstore.domain.User;
import hh.exercise.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catRepository, UserRepository urepository) {
		return (args) -> {
			catRepository.save(new Category("Math"));
			catRepository.save(new Category("IT"));
			catRepository.save(new Category("Cooking"));

			repository.save(new Book("Liike-elämän matematiikka", "Pirjo Saaranen", "2011", "12346", 24.99,
					catRepository.findByName("Math").get(0)));
			repository.save(new Book("Liike-elämän kirja", "Matti Saaranen", "2005", "12345", 29.99,
					catRepository.findByName("IT").get(0)));
			repository.save(new Book("Liike-elämän tieto", "Teppo Saaranen", "2009", "12344", 22.99,
					catRepository.findByName("Cooking").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.deleteAll();
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
