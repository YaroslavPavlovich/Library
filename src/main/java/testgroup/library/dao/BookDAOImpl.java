package testgroup.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.library.model.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> allBooks(int page) {//список всех книг
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book order by id desc").setFirstResult(10 * (page - 1)).setMaxResults(10).list();//разбиение на страницы
    }//сортировка по времени добавления книги

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> allBooksAuthor(int page) {//список всех авторов
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select author from Book group by author order by author").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }//группировка по авторам и сортировка по автору

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> allBooksName(int page) {//список всех книг в алфавитном порядке
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Book order by title").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> checkSearch(String searching, int page) {//поиск книг по названию
        Session session = sessionFactory.getCurrentSession();
        searching = "%" + searching +"%";
        Query query;
        String Search = "from Book where title like '" + searching + "'";//поиск в БД
        query = session.createQuery(Search);
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();//разбиение на страницы
    }

    @Override
    public int searchCount(String searching) {//подсчёт найденных книг
        Session session = sessionFactory.getCurrentSession();
        searching = "%" + searching +"%";
        String Search = "from Book where title like '" + searching + "'";
        Query query;
        query = session.createQuery(Search);
        return query.list().size();//возвращаем размер полученного списка
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> checkSearchAuthor(String searching, int page) {//поиск по автору
        Session session = sessionFactory.getCurrentSession();
        searching = "%" + searching +"%";
        Query query;
        String Search = "select author from Book where author like '" + searching + "' group by author";//группировка по автору
        query = session.createQuery(Search);
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();//разбиение на страницы
    }

    @Override
    public int searchCountAuthor(String searching) {//подсчёт найденных авторов
        Session session = sessionFactory.getCurrentSession();
        searching = "%" + searching +"%";
        String Search = "select author from Book where author like '" + searching + "' group by author";
        Query query;
        query = session.createQuery(Search);
        return query.list().size();//возвращаем размер полученного списка
    }

    @Override
    public void add(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);//добавление книги в БД
    }

    @Override
    public void delete(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(book);//удаление книги из БД
    }

    @Override
    public void edit(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);//редактирование книги в БД
    }

    @Override
    public Book getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, id);//получение книги по номеру
    }

    @Override
    public int booksCount() {//количество книг в БД
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Book", Number.class).getSingleResult().intValue();
    }

    @Override
    public int authorCount() {//количество авторов
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(distinct author) from Book", Number.class).getSingleResult().intValue();
    }

    @Override
    public int authorNameCount(String author) {//количество книг данного автора
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Book where author = :author");//запрос книг по автору
        query.setParameter("author", author);
        return query.list().size();
    }

    @Override
    public boolean checkTitle(String title) {//проверка названия книги
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Book where title = :title");//получение книг с данным названием
        query.setParameter("title", title);
        return query.list().isEmpty();//проверка на пустоту списка
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> checkAuthor(String author, int page) {//получение книг данного автора
        Session session = sessionFactory.getCurrentSession();
        Query query;
        query = session.createQuery("from Book where author = :author");
        query.setParameter("author", author);
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }
}
