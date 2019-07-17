package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SortingTest {

	public static void main(String[] args) {
        sortUsingCollections();
        sortUsingArrays();

        sortUsingComparator();
	}

	static void sortUsingCollections(){
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(4);
        list1.add(1);
        list1.add(3);
        list1.add(2);

        // Collections.sort internally uses mergeSort to sort
        Collections.sort(list1);  // Integer already implements Comparable

        for (int i : list1){
            System.out.println(i);
        }
    }

    static void sortUsingArrays () {
        Book b1 = new Book("AAA", 123);
        Book b2 = new Book("AAA", 124);

        Book[] arr1 = {b1, b2};

        Arrays.sort(arr1);  // Book already implements Comparable

        for (Book b : arr1){
            System.out.println(b);
        }
    }

    static void sortUsingComparator(){
        List<Book> list = new LinkedList<>();
        list.add(new Book("Patterns", 12345));
        list.add(new Book("Patterns", 34567));
        list.add(new Book("Examples", 23456));

        Collections.sort(list, new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book1.isbn - book2.isbn;
            }
        });
        // if the element type does not implements Comparable, or if you want list different way of
        // Comparator instance must be provided

        // alternatively
//        Collections.sort(list, Comparator.comparingInt(book -> book.isbn));

        for (Book b : list){
            System.out.println(b);
        }
    }

    static class Book implements Comparable<Book> {
        String title;
        int    isbn;

        Book(String title, int isbn) {
            this.title = title;
            this.isbn  = isbn;
        }

        @Override
        public String toString(){
            return this.title + " " + this.isbn;
        }

        @Override
        public int compareTo(Book book) {
            if (this.title.equals(book.title)) {
                return this.isbn - book.isbn;
            }

            return this.title.compareTo(book.title);
        }
    }

}




