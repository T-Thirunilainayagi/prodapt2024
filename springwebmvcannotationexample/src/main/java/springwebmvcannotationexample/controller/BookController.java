package springwebmvcannotationexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springwebmvcannotationexample.dto.BookDTO;
import springwebmvcannotationexample.exceptions.BookNotFoundException;
import springwebmvcannotationexample.model.Book;
import springwebmvcannotationexample.service.BookService;
import springwebmvcannotationexample.utility.BookUtility;

@Controller
@RequestMapping("/bookapp")
public class BookController {

	@Autowired
	private BookService service;

	// Load Book Details form
	@RequestMapping(value = "/bookdetailsform", method = RequestMethod.GET)
	public ModelAndView loadBookDetailsForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bookform"); // setting view name or jsp name
		return mv;
	}

	// Create
	@RequestMapping(value = "/createbook", method = RequestMethod.POST)
	public ModelAndView addBook(@ModelAttribute Book book) {
		// converting book model to dto
		BookDTO bookDTO = BookUtility.convertBookToBookDTO(book);
		// Sending data to DB
		BookDTO bokDTO = service.addBook(bookDTO);

		ModelAndView mv = new ModelAndView();
		//// converting book dto to model and setting model in ModelAndView
		mv.addObject("bookModel", BookUtility.convertBookDTOToBook(bokDTO));
		mv.setViewName("bookdetails"); // setting view name or jsp name
		return mv;
	}

	// Retrieve
	// URL -
	// http://localhost:8080/springwebmvcannotationexample/bookapp/getbook?id=1
	/**
	 * @GetMapping("/getbook") public ModelAndView bookById(@RequestParam("id")
	 * Integer bookId) { BookDTO bokDTO = null; String errorMsg = null;
	 * 
	 * try { bokDTO = service.bookById(bookId); } catch (BookNotFoundException e) {
	 * errorMsg = e.getMessage(); }
	 * 
	 * ModelAndView mv = new ModelAndView(); if(bokDTO != null) { //// converting
	 * book dto to model and setting model in ModelAndView mv.addObject("bookModel",
	 * BookUtility.convertBookDTOToBook(bokDTO)); mv.setViewName("bookdetails"); //
	 * setting view name or jsp name }else { mv.addObject("errorMsg", errorMsg);
	 * mv.setViewName("errormsg"); // name of the jsp is errormsg.jsp } return mv; }
	 **/
	//Below exception handled by global exception handler class
	@GetMapping("/getbook")
	public ModelAndView bookById(@RequestParam("id") Integer bookId) throws BookNotFoundException {
		BookDTO bokDTO = service.bookById(bookId);

		ModelAndView mv = new ModelAndView();

		//// converting book dto to model and setting model in ModelAndView
		mv.addObject("bookModel", BookUtility.convertBookDTOToBook(bokDTO));
		mv.setViewName("bookdetails"); // setting view name or jsp name

		return mv;
	}

	// Load book form to pass book id as http post method
	// http://localhost:8080/springwebmvcannotationexample/bookapp/getbookform
	@GetMapping("/getbookform")
	public ModelAndView showBookForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("getbookform"); // name of the JSP page to display
		return mv;
	}

	// This method will be called when getbookform form will be submitted
	@RequestMapping(value = "/getbook", method = RequestMethod.POST)
	public ModelAndView bookByIdUsingForm(@ModelAttribute("bookId") Integer bookId) {
		BookDTO bdto = null;
		Book bok = null;
		String errorMsg = null;
		try {
			bdto = service.bookById(bookId);
			bok = BookUtility.convertBookDTOToBook(bdto);// Utility methods to convert dto Book to model
		} catch (BookNotFoundException e) {
			errorMsg = e.getMessage();
		}

		ModelAndView mv = new ModelAndView();
		if (bdto != null) {
			mv.addObject("book", bok);// setting data model
			mv.setViewName("getbookform"); // name of the jsp is greeting.jsp
		} else {
			mv.addObject("errorMsg", errorMsg);
			mv.setViewName("errormsg"); // name of the jsp is errormsg.jsp
		}
		return mv;
	}

	// Get All Books
	@GetMapping("/books")
	public ModelAndView books() throws BookNotFoundException {
		ModelAndView mv = new ModelAndView();
		List<BookDTO> listOfBooks = service.books();
		mv.addObject("bookList", listOfBooks);// setting data model
		mv.setViewName("books"); // name of the jsp is greeting.jsp
		return mv;
	}
}
