package testgroup.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.library.model.Book;
import testgroup.library.service.BookService;

import java.util.List;

@Controller
public class BookController {
    private int page;//нумерация страниц
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)//главная страница
    public ModelAndView allBooks(@RequestParam(defaultValue = "1") int page) {
        List<Book> books = bookService.allBooks(page);//получаем список книг
        int booksCount = bookService.booksCount();//получаем количество книг в списке
        int pagesCount = (booksCount + 9)/10;//подсчёт количества страниц
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book");//открываем jsp файл
        modelAndView.addObject("page", page);//передаём номер страницы
        modelAndView.addObject("booksList", books);//передаём списко книг
        modelAndView.addObject("booksCount", booksCount);//передаём количество книг
        modelAndView.addObject("pagesCount", pagesCount);//передаём количество страниц
        this.page = page;//фиксируем дествующую страницу
        return modelAndView;
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)//страница списка всех авторов
    public ModelAndView authors(@RequestParam(defaultValue = "1") int page) {
        Book book = bookService.getById(0);//получаем пустую книгу
        List<Book> authors = bookService.allBooksAuthor(page);//получаем список авторов в алфавитном порядке
        int authorCount = bookService.authorCount();//получаем количество авторов
        int pagesCount = (authorCount + 9) / 10;//количество страниц
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authors");
        modelAndView.addObject("page", page);
        modelAndView.addObject("bookList", authors);
        modelAndView.addObject("authorCount", authorCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("book", book);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)//страница списка всех книг
    public ModelAndView bookPage(@RequestParam(defaultValue = "1") int page) {
        Book book = bookService.getById(0);//получение пустой книги для поиска
        List<Book> books = bookService.allBooksName(page);//список всех книг в алфавитном порядке
        int booksCount = bookService.booksCount();//подсчёт книг
        int pagesCount = (booksCount + 9) / 10;//подсчёт страниц
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sortBook");
        modelAndView.addObject("page", page);
        modelAndView.addObject("books", books);
        modelAndView.addObject("booksCount", booksCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("book", book);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/authorName/{author}", method = RequestMethod.GET)//страница книг данного автора
    public ModelAndView authorPage(@PathVariable("author") String author,//имя автора
                                   @ModelAttribute("message") String message,
                                   @RequestParam(defaultValue = "1") int page) {
        author = author + ".";
        int authorNameCount = bookService.authorNameCount(author);//количество книг данного автора
        int pagesCount = (authorNameCount + 9)/10;//количество страниц
        List<Book> book = bookService.checkAuthor(author, page);//получение списка книг автора
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authorName");
        modelAndView.addObject("message", author);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", book);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("authorNameCount", authorNameCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/searchBooks", method = RequestMethod.POST)//страница поиска по названию
    public ModelAndView searchName(@ModelAttribute("book") Book book,
                                   @ModelAttribute("message") String message,
                                   @RequestParam(defaultValue = "1") int page) {

        String title = book.getTitle();//получение названия для поиска
        int searchCount = bookService.searchCount(title);//подсчёт книг по поиску
        int pagesCount = (searchCount + 9)/10;//подсчёт страниц
        List<Book> books = bookService.checkSearch(title, page);//список книг по поиску
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchBook");
        modelAndView.addObject("message", title);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", books);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("searchCount", searchCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/searchBooks/{message}", method = RequestMethod.GET)//страница поиска для перехода по страницам
    public ModelAndView searchNameForPage(@PathVariable("message") String title,//строка для поиска
                                          @RequestParam(defaultValue = "1") int page) {

        int searchCount = bookService.searchCount(title);//подсчёт книг по поиску
        int pagesCount = (searchCount + 9)/10;//количество страниц
        List<Book> books = bookService.checkSearch(title, page);//список найденных книг
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchBook");
        modelAndView.addObject("message", title);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", books);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("searchCount", searchCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/searchAuthor", method = RequestMethod.POST)//страница результатов поиска по автору
    public ModelAndView searchAuthor(@ModelAttribute("book") Book book,
                                     @ModelAttribute("message") String message,
                                     @RequestParam(defaultValue = "1") int page) {
        String author = book.getAuthor();//получение строки для поиска
        int searchCount = bookService.searchCountAuthor(author);//количество найденных авторов
        int pagesCount = (searchCount + 9)/10;//количество страниц
        List<Book> authors = bookService.checkSearchAuthor(author, page);//список найденных авторов
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchAuthor");
        modelAndView.addObject("message", author);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", authors);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("searchCount", searchCount);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/searchAuthor/{message}", method = RequestMethod.GET)//страница поиска авторов для перехода по страницам
    public ModelAndView searchAuthorForPage(@PathVariable("message") String author,
                                            @RequestParam(defaultValue = "1") int page) {
        Book book = bookService.getById(0);//получение пустой книги
        int searchCount = bookService.searchCountAuthor(author);//подсчёт найденных авторов
        int pagesCount = (searchCount + 9) / 10;//количество страниц
        List<Book> authors = bookService.checkSearchAuthor(author, page);//список авторов
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchAuthor");
        modelAndView.addObject("message", author);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", authors);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("searchCount", searchCount);
        modelAndView.addObject("book", book);
        this.page = page;
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)//переход на страницу добавления новой книги
    public ModelAndView addPage(@ModelAttribute("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");//открываем файл для добавления записи в БД
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)//добавление новой книги
    public ModelAndView addBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView();
        if (bookService.checkTitle(book.getTitle())) {//проверка на уникальное название
            modelAndView.setViewName("redirect:/");//переход на главную страницу
            modelAndView.addObject("page", page);
            bookService.add(book);//добавление книги в БД
        } else {
            modelAndView.addObject("message","Книга \"" + book.getTitle() + "\" уже существует!");
            modelAndView.setViewName("redirect:/add");//вывод сообщения о повторяющейся книге
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)//запрос на редактирование книги
    public ModelAndView editPage(@PathVariable("id") int id,//номер книги в БД
                                 @ModelAttribute("message") String message) {
        Book book = bookService.getById(id);//получение книги для редактирования
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)//страница редактирования книги
    public ModelAndView editBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView();
        if (bookService.checkTitle(book.getTitle()) || bookService.getById(book.getId()).getTitle().equals(book.getTitle())) {//проверка книги на уникальность
            modelAndView.setViewName("redirect:/");//переход на главную
            modelAndView.addObject("page", page);
            bookService.edit(book);//редактирование книги
        } else {
            modelAndView.addObject("message","Книга \"" + book.getTitle() + "\" уже существует");
            modelAndView.setViewName("redirect:/edit/" +  + book.getId());//вывод сообщения об ошибке
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)//удаление книги
    public ModelAndView deleteBook(@PathVariable("id") int id) {//получение номера книги
        ModelAndView modelAndView = new ModelAndView();
        int booksCount = bookService.booksCount();//пересчёт оставшихся книг
        int page = ((booksCount - 1) % 10 == 0 && booksCount > 10 && this.page == (booksCount + 9)/10) ?
                this.page - 1 : this.page;//изменение номера страницы при необходимости
        modelAndView.setViewName("redirect:/?page=" + this.page);//переход на главную с сохранением страницы
        modelAndView.addObject("page", page);
        Book book = bookService.getById(id);//получение книги по номеру
        bookService.delete(book);//удаление книги
        return modelAndView;
    }

    @RequestMapping(value="/deleteSort/{id}", method = RequestMethod.GET)//удаление книги на странице всех книг в алфавитном порядке
    public ModelAndView deleteBookSort(@PathVariable("id") int id) {//номер книги
        ModelAndView modelAndView = new ModelAndView();
        int booksCount = bookService.booksCount();//подсчёт книг
        int page = ((booksCount - 1) % 10 == 0 && booksCount > 10 && this.page == (booksCount + 9)/10) ?
                this.page - 1 : this.page;//изменение страницы
        modelAndView.setViewName("redirect:/books?page=" + this.page);//переход обратно с сохранением страницы
        modelAndView.addObject("page", page);
        Book book = bookService.getById(id);//получение книги по номеру
        bookService.delete(book);//удаление
        return modelAndView;
    }

    @RequestMapping(value="/deleteAuthor/{id}/{author}", method = RequestMethod.GET)//удаление книги на странице авторов
    public ModelAndView deleteBookAuthor(@PathVariable("id") int id,//номер книги
                                         @PathVariable("author") String author,//Имя автора
                                         @ModelAttribute("message") String message,
                                         @RequestParam(defaultValue = "1") int page) {
        author = author + ".";
        Book bookA = bookService.getById(id);//получение книги
        bookService.delete(bookA);//удаление книги
        int authorNameCount = bookService.authorNameCount(author);//подсчёт авторов
        int pagesCount = (authorNameCount + 9)/10;//подсчёт страниц
        List<Book> authors = bookService.checkAuthor(author, page);//список авторов
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authorName");
        modelAndView.addObject("message", author);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", authors);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("authorNameCount", authorNameCount);

        return modelAndView;
    }

    @RequestMapping(value="/deleteSearchBook/{id}/{search}", method = RequestMethod.GET)//удаление книги со страницы поиска по книги
    public ModelAndView deleteBookSearchBook(@PathVariable("id") int id,//номер книги
                                         @PathVariable("search") String title,
                                         @RequestParam(defaultValue = "1") int page) {
        Book bookSearch = bookService.getById(id);//получение книги
        bookService.delete(bookSearch);//удаление
        int searchCount = bookService.searchCount(title);//количество книг по поиску
        int pagesCount = (searchCount + 9)/10;//количество страниц
        List<Book> book = bookService.checkSearch(title, page);//получение оставшихся книг
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("searchBook");
        modelAndView.addObject("message", title);
        modelAndView.addObject("page", page);
        modelAndView.addObject("booksList", book);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("searchCount", searchCount);

        return modelAndView;
    }
}
