<%@ page import="com.ejb.ejbdemo.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
      :root {
        --primary-color: #008CBA;
        --danger-color: #f44336;
        --success-color: #4CAF50;
        --border-color: #e0e0e0;
        --hover-bg: #f8f9fa;
      }

      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }

      body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f9f9f9;
        color: #333;
        line-height: 1.6;
      }

      .container {
        width: 90%;
        max-width: 1200px;
        margin: 30px auto;
        padding: 20px;
      }

      h1 {
        font-size: 2.2rem;
        margin-bottom: 20px;
        color: #333;
        text-align: center;
      }

      h2 {
        font-size: 1.5rem;
        margin-bottom: 15px;
        color: #444;
      }

      .add-user-form {
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        margin-bottom: 30px;
      }

      .form-row {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        margin-bottom: 15px;
      }

      input[type="text"],
      input[type="email"],
      input[type="password"] {
        flex: 1;
        min-width: 200px;
        padding: 10px 15px;
        border: 1px solid var(--border-color);
        border-radius: 4px;
        font-size: 16px;
        transition: border-color 0.3s;
      }

      input[type="text"]:focus,
      input[type="email"]:focus,
      input[type="password"]:focus {
        border-color: var(--primary-color);
        outline: none;
        box-shadow: 0 0 0 2px rgba(0, 140, 186, 0.2);
      }

      .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        transition: all 0.3s;
      }

      .btn-add {
        background-color: var(--success-color);
        color: white;
      }

      .btn-add:hover {
        background-color: #45a049;
      }

      .table-container {
        overflow-x: auto;
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      }

      .user-table {
        width: 100%;
        border-collapse: collapse;
      }

      .user-table th,
      .user-table td {
        padding: 12px 15px;
        text-align: left;
        border-bottom: 1px solid var(--border-color);
      }

      .user-table th {
        background-color: #f5f5f5;
        font-weight: 600;
        color: #555;
      }

      .user-table tr:hover {
        background-color: var(--hover-bg);
      }

      .actions {
        display: flex;
        gap: 5px;
      }

      .btn-edit {
        background-color: var(--primary-color);
        color: white;
      }

      .btn-edit:hover {
        background-color: #007095;
      }

      .btn-delete {
        background-color: var(--danger-color);
        color: white;
      }

      .btn-delete:hover {
        background-color: #d32f2f;
      }

      @media (max-width: 768px) {
        .form-row {
          flex-direction: column;
          gap: 10px;
        }

        .user-table {
          font-size: 14px;
        }

        .btn {
          padding: 8px 16px;
          font-size: 14px;
        }
      }
    </style>
</head>
<body>
<div class="container">
    <h1>User Management</h1>

    <!-- Add User Form -->
    <div class="add-user-form">
        <h2>Add New User</h2>
        <form action="/EJBDemo-1.0-SNAPSHOT/users" method="post">
            <div class="form-row">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="text" name="first_name" placeholder="First name" required>
                <input type="text" name="last_name" placeholder="Last name" required>
                <label for="age">Age</label>
                <input type="range" id="age" name="age" min="18" max="60" required>
                <input type="email" name="email" placeholder="Email" required>
                <input type="text" name="phone" placeholder="Phone" required>
                <input type="text" name="address" placeholder="Address" required>
            </div>
            <button type="submit" class="btn btn-add">Add User</button>
        </form>
    </div>

    <!-- User List -->
    <div class="table-container">
        <table class="user-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Address</th>
                <th>Age</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (users != null && !users.isEmpty()) { %>
            <% for (User user : users) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getAddress() != null ? user.getAddress() : "null" %></td>
                <td><%= user.getAge() != null ? user.getAge() : "null" %></td>
                <td><%= user.getFirstName() != null ? user.getFirstName() : "null" %></td>
                <td><%= user.getLastName() != null ? user.getLastName() : "null" %></td>
                <td><%= user.getPhone() != null ? user.getPhone() : "" %></td>
                <td class="actions">
                    <button class="btn btn-edit" onclick="editUser('<%= user.getId() %>')">Edit</button>
                    <button class="btn btn-delete" onclick="deleteUser('<%= user.getId() %>')">Delete</button>
                </td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="9" style="text-align: center;">No users found.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<script>
  function editUser(userId) {
    // In a real application, this could open a modal or redirect to an edit page
    console.log('Edit user with ID: ' + userId);
    // For demonstration purposes, using alert
    alert('Edit user with ID: ' + userId);
  }

  function deleteUser(userId) {
    if (confirm('Are you sure you want to delete this user?')) {
      console.log('Deleting user with ID: ' + userId);
      // In a real application, this would send a request to your backend

      // For demonstration purposes, using alert
      alert('Deleting user with ID: ' + userId);
      // Add your delete servlet call here, e.g.:
      window.location.href = '/EJBDemo-1.0-SNAPSHOT/users/delete?id=' + userId;
    }
  }
</script>
</body>
</html>