package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepositor;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepositor, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepositor = bookRepositor;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Publisher publisher1 = new Publisher("Harper", "Los Angeles");
        publisherRepository.save(publisher1);

        Author eric = new Author("eric", "Evans");

        Book book = new Book("Domain Driven Design", "1234", publisher1);

        eric.getBooks().add(book);
        book.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepositor.save(book);

        Publisher publisher2 = new Publisher("Kevin", "San Francisco");
        publisherRepository.save(publisher2);

        Author rod = new Author("rod", "Johnson");
        Book rodBook = new Book("J2EE Development without EJB", "12441", publisher2);
        rod.getBooks().add(rodBook);

        authorRepository.save(rod);
        bookRepositor.save(rodBook);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.initData();
    }
}
