package com.ejb.ejbdemo;

import com.ejb.ejbdemo.beans.service.UserService;
import com.ejb.ejbdemo.entity.User;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "helloServlet", urlPatterns = {"/users", "/users/delete"})
public class HelloServlet extends HttpServlet {

  @Inject
  private UserService userService;

  public void init() {

  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String path = request.getServletPath();
    System.out.println(path);
    if ("/users".equals(path) && request.getMethod().equals("GET")) {
      listUsers(request, response);
    } else if ("/users/delete".equals(path)) {
      try {
        deleteUserById(request, response);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    User user = new User();
    req.setCharacterEncoding("UTF-8");
    user.setUsername(req.getParameter("username"));
    user.setPassword(req.getParameter("password"));
    user.setFirstName(req.getParameter("first_name"));
    user.setLastName(req.getParameter("last_name"));
    user.setAge(Integer.parseInt(req.getParameter("age")));
    user.setEmail(req.getParameter("email"));
    user.setPhone(req.getParameter("phone"));
    user.setAddress(req.getParameter("address"));
    System.out.println(user);
    try {
      userService.createUser(user);
    } catch (Exception e) {
      req.getSession().setAttribute("error", e.getMessage());
      resp.sendRedirect("users");
      return;
    }

    resp.sendRedirect("users");
  }

  private void listUsers(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // List users code
    List<User> users = userService.findAll();
    // Đưa danh sách users vào request attribute
    request.setAttribute("users", users);

    String error = (String) request.getSession().getAttribute("error");
    if (error != null) {
      request.setAttribute("errorMessage", error);
      request.getSession().removeAttribute("error");
    }

    // Forward đến user.jsp
    request.getRequestDispatcher("/view/user.jsp").forward(request, response);
  }

  private void deleteUserById(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String id = request.getParameter("id");

    userService.deleteUser(id);
    response.sendRedirect("users");
  }

  public void destroy() {
  }
}