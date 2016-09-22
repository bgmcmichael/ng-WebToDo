package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 9/20/2016.
 */
@RestController
public class WebTwoPointOhTodoRestController {
    @Autowired
    TodoRepository todos;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/todos.json", method = RequestMethod.GET)
    public ArrayList<Todo> getTodos(HttpSession session){
        ArrayList<Todo> todoList = new ArrayList<Todo>();
        Iterable<Todo> alltodos = todos.findByUser((User)session.getAttribute("user"));
        for (Todo todo : alltodos) {
            todoList.add(todo);
        }
        return todoList;
    }

    @RequestMapping(path = "/toggleTodo.json", method = RequestMethod.GET)
    public List<Todo> toggleTodo(int todoID, HttpSession session, Model model) {
        System.out.println("toggling todo with ID " + todoID);
        Todo todo = todos.findOne(todoID);
        if (todo.done.equalsIgnoreCase("")){
            todo.done = "Done";
        } else {
            todo.done = "";
        }
        todos.save(todo);

        return getTodos(session);
    }

    @RequestMapping(path = "/addTodo.json", method = RequestMethod.POST)
    public List<Todo> addTodo(HttpSession session, @RequestBody Todo todo) throws Exception {
        todo.done = "";
        User user = (User)session.getAttribute("user");

        if (user == null) {
            throw new Exception("Unable to add todo without an active user in the session");
        }
        todo.user = user;

        todos.save(todo);

        return getTodos(session);
    }

    @RequestMapping(path = "/deleteTodo.json", method = RequestMethod.GET)
    public List<Todo> deleteTodo(HttpSession session, int todoID) throws Exception {
        Todo todo = todos.findOne(todoID);
        User user = todo.user;
        todos.delete(todoID);

        return getTodos(session);
    }
}
