package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Smartphone se = new Smartphone(1, "iPhone SE", 400, "Apple");
    private Smartphone p20 = new Smartphone(2, "P20-lite", 400, "Huawei");
    private Book lotr = new Book(3, "The Lord of the Rings", 400, "J.R.R.Tolkien");
    private Book hobbit = new Book(4, "The Hobbit, or There and Back Again", 300, "J.R.R.Tolkien");
    private Book got = new Book(5, "The Game of Thrones", 150, "J.R.R.Martin");


    @Test
    public void shouldFindByProducer() {

        repository.save(p20);

        Product[] expected = new Product[]{p20};
        Product[] actual = manager.searchBy("Huawei");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByName(){
        repository.save(se);

        Product[] expected = new Product[]{se};
        Product[] actual = manager.searchBy("iPhone SE");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByAuthor(){
        repository.save(got);

        Product[] expected = new Product[]{got};
        Product[] actual = manager.searchBy("J.R.R.Martin");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralItems(){
        repository.save(lotr);
        repository.save(hobbit);

        Product[] expected = new Product[]{lotr, hobbit};
        Product[] actual = manager.searchBy("J.R.R.Tolkien");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindNothing(){

        Product[] expected = new Product[]{};
        Product[] actual = manager.searchBy("Xiaomi");

        assertArrayEquals(expected, actual);
    }
}