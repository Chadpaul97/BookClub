package com.codingdojo.bookclub.controllers;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;

@Controller
public class HomeController {
    
    @Autowired
    private UserService userServ;
	@Autowired
	private BookService bookServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }  
    
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
    	
    	if(session.getAttribute("user_id") != null) {
    		
    	Long user_id = (Long) session.getAttribute("user_id");
    	
    	model.addAttribute("user", userServ.oneUser(user_id));
    	model.addAttribute("books", bookServ.allBooks());
    	 return "dashboard.jsp";
    } else {
    	
    	return "redirect:/";
    }
}  	
    

    
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
    	
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    }
    
    
    //Login 
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
    	
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
    
    //Logout 
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    @GetMapping("/newBook")
    public String newBook(Model model, HttpSession session) {
    	
    	if(session.getAttribute("user_id") != null) {
    		
    	Long user_id = (Long) session.getAttribute("user_id");
    	
    	model.addAttribute("user", userServ.oneUser(user_id));
    	model.addAttribute("createBook", new Book());
    	
    	 return "newBook.jsp";
    } else {
    	
    	return "redirect:/";
    	} 	
    } 
    
    //Need one to create book
    @PostMapping("createBook")
    public String createBook(@Valid @ModelAttribute("newBook") Book book, BindingResult result, HttpSession session) {
        if(session.getAttribute("user_id") != null) {
        	if(result.hasErrors()) {
        		return "newBook.jsp";
        	}
        	bookServ.create(book);
        	return "redirect:/dashboard";
        } else {
        	return "redirect:/";
        }
    }
    
    //Need one to show book
    @GetMapping("/showBook/{id}")
    public String showBook(@PathVariable("id") Long id,Model model, HttpSession session) {
    	
    	if(session.getAttribute("user_id") != null) {
    		
    	Long user_id = (Long) session.getAttribute("user_id");
    	
    	model.addAttribute("user", userServ.oneUser(user_id));
    	model.addAttribute("book", bookServ.oneBook(id));
    	 return "oneBook.jsp";
    } else {
    	
    	return "redirect:/";
    	} 	
    }
    
    
    //Need one to edit book
    @RequestMapping("/editbook/{id}")
    public String editbook(HttpSession session, @PathVariable("id")Long id, @ModelAttribute("editBook") Book book, Model model ) {
    	if(session.getAttribute("user_id") != null) {	
    	Long user_id = (Long) session.getAttribute("user_id");	
    	model.addAttribute("user", userServ.oneUser(user_id));
    	model.addAttribute("editBook", bookServ.oneBook(id));
    	 return "editBook.jsp";
    	} else {
    	
    		return "redirect:/";
    		} 	
    }
    

    @PutMapping("/editBook/update/{id}")
    public String editBook (@PathVariable("id") Long id,@Valid @ModelAttribute("editBook") Book book,BindingResult result) {
        if(result.hasErrors()) {
            return "editBook.jsp";
        }else {
        	bookServ.updateBook(book);
	        return "redirect:/dashboard";
    } 
    }
    
    
    //Need one to delete book
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session){
        if(session.getAttribute("user_id") != null) {
        	bookServ.deleteBook(id);
        	return "redirect:/dashboard";
        } else {
        	return "redirect:/";
        }
            
         }

}  
    
    
