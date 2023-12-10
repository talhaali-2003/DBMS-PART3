<%@page import="Model.Bills"%>
<%@page import="DAO.SpecifiedFunctionalitiesDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="Model.Users"%>
<%@ page import="Model.Trees"%>
<%@ page import="Model.ClientStatistics"%>
<%@ page import="DAO.UserDAO"%>
<%@ page import="DAO.QuotesDAO"%>
<%@ page import="DAO.TreesDAO"%>
<%@ page import="DAO.BillsDAO"%>
<%@ page import="Model.Quotes"%>
<%@ page import="Helper.ConnectionProvider"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Specified Functionalities</title>

        <!-- Bootstrap CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet">

        <!-- Bootstrap JS and Popper.js (if needed) -->
        <script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    </head>

    <body>

        <%
            // Declare and initialize variables
            UserDAO userDAO = new UserDAO(ConnectionProvider.getConnection());
            List<Users> bigClients = userDAO.getBigClients();

            List<Users> easyClients = userDAO.getEasyClients();

            QuotesDAO quotesDAO = new QuotesDAO(ConnectionProvider.getConnection());
            List<Quotes> oneTreeQuotes = quotesDAO.getOneTreeQuotes();

            List<ClientStatistics> clientStatistics = userDAO.getClientStatistics();
        %>

        <div class="container mt-5">

            <a href="rootDashboard.jsp"> Back To Dashboard </a>

            <h1 class="mb-4">Specified Functionalities</h1>



            <!-- Example 1: Big Clients -->
            <h2>Big Clients</h2>
            <ul class="list-group">
                <%
                    for (Users user : bigClients) {
                %>
                <li class="list-group-item"><%=user.getFirstname()%> <%=user.getLastname()%></li>
                    <%
                        }
                    %>
            </ul>



            <!-- Example 2: Easy Clients -->
            <h2 class="mt-4">Easy Clients</h2>
            <ul class="list-group">
                <%
                    for (Users user : easyClients) {
                %>
                <li class="list-group-item"><%=user.getFirstname()%> <%=user.getLastname()%></li>
                    <%
                        }
                    %>
            </ul>

            <!-- Example 3: One Tree Quotes -->
            <h2 class="mt-4">One Tree Quotes</h2>
            <ul class="list-group">
                <%
                    for (Quotes quote : oneTreeQuotes) {
                %>
                <li class="list-group-item">Quote ID: <%=quote.getId()%>, Client
                    ID: <%=quote.getClientid()%></li>
                    <%
                        }
                    %>
            </ul>

            <!-- Example 4: Client Statistics -->
            <h2 class="mt-4">Client Statistics</h2>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Client ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Tree Count</th>
                        <th>Total Due</th>
                        <th>Total Paid</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ClientStatistics stats : clientStatistics) {
                    %>
                    <tr>
                        <td><%=stats.getClientId()%></td>
                        <td><%=stats.getFirstName()%></td>
                        <td><%=stats.getLastName()%></td>
                        <td><%=stats.getTreeCount()%></td>
                        <td><%=stats.getTotalDue()%></td>
                        <td><%=stats.getTotalPaid()%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>


            <%
                // Assuming you have a connection object named 'con'
                SpecifiedFunctionalitiesDAO specifiedFunctionalitiesDAO = new SpecifiedFunctionalitiesDAO(ConnectionProvider.getConnection());
                TreesDAO treesDAO = new TreesDAO(ConnectionProvider.getConnection());

                // Example 5: Prospective Clients
                List<Users> prospectiveClients = specifiedFunctionalitiesDAO.getBigClients();
            %>

            <!-- Example 5: Prospective Clients -->
            <h2 class="mt-4">Prospective Clients</h2>
            <ul class="list-group">
                <% for (Users client : prospectiveClients) {%>
                <li class="list-group-item">
                    <%= "Client ID: " + client.getId() + ", Name: " + client.getFirstname() + " " + client.getLastname()%>
                </li>
                <% } %>
            </ul>

            <%
                // Example 6: Highest Tree
                List<Trees> allTrees = treesDAO.getAllTreesForQuote(1); // Assuming quoteid 1 for the example
                Trees highestTree = null;

                // Find the highest tree
                for (Trees tree : allTrees) {
                    if (highestTree == null || tree.getHeight() > highestTree.getHeight()) {
                        highestTree = tree;
                    }
                }
            %>

            <!-- Example 6: Highest Tree -->
            <h2 class="mt-4">Highest Tree</h2>
            <ul class="list-group">
                <li class="list-group-item">
                    <%= "Tree ID: " + highestTree.getId() + ", Height: " + highestTree.getHeight()%>
                </li>
            </ul>



            <%
                // Assuming you have a connection object named 'con'
                BillsDAO billsDAO = new BillsDAO(ConnectionProvider.getConnection());

                // Example 7: Overdue Bills
                List<Bills> overdueBills = billsDAO.getOverdueBills();
            %>


            <!-- Example 7: Overdue Bills -->
            <h2 class="mt-4">Overdue Bills</h2>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Bill ID</th>
                        <th>Order ID</th>
                        <th>Status</th>
                        <th>Schedule End</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Bills bill : overdueBills) {%>
                    <tr>
                        <td><%= bill.getId()%></td>
                        <td><%= bill.getOrderid()%></td>
                        <td><%= bill.getStatus()%></td>
                        <!-- Assuming there is an 'Orders' class with a method 'getScheduleEnd()' -->
                        <td><%= bill.getOrder().getScheduleend()%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>


            <%
                // Example 8: Bad Clients
                List<Users> badClients = specifiedFunctionalitiesDAO.getEasyClients();
            %>

            <!-- Example 8: Bad Clients -->
            <h2 class="mt-4">Bad Clients</h2>
            <ul class="list-group">
                <% for (Users client : badClients) {%>
                <li class="list-group-item">
                    <%= "Client ID: " + client.getId() + ", Name: " + client.getFirstname() + " " + client.getLastname()%>
                </li>
                <% }%>
            </ul>







            <%

                // Example 9: Good Clients
                List<Users> goodClients = specifiedFunctionalitiesDAO.getGoodClients();
            %>

            <!-- Example 9: Good Clients -->
            <h2 class="mt-4">Good Clients</h2>
            <ul class="list-group">
                <%
                    List<Users> goodClientss = specifiedFunctionalitiesDAO.getGoodClients();
                    for (Users client : goodClientss) {
                %>
                <li class="list-group-item">
                    <%= "Client ID: " + client.getId() + ", Name: " + client.getFirstname() + " " + client.getLastname()%>
                </li>
                <% }%>
            </ul>


            <!-- Example 10: Other Functionalities -->
            <h2 class="mt-4">Other Functionalities</h2>

            <!-- Section to display overdue bills -->
            <h3>Overdue Bills</h3>
            <ul class="list-group">
                <%

                    for (Bills bill : overdueBills) {
                %>
                <li class="list-group-item">
                    <%= "Bill ID: " + bill.getId() + ", Order ID: " + bill.getOrderid() + ", Amount: $" + bill.getBalance()%>
                </li>
                <%
                    }
                %>
            </ul>

            <!-- Example 11: Additional Statistics -->
            <h2 class="mt-4">Additional Statistics</h2>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>Total Clients</th>
                        <th>Total Quotes</th>
                        <!-- Add more columns if needed -->
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <%-- Add Java code to retrieve and display total clients --%>
                            <%= specifiedFunctionalitiesDAO.getTotalClients()%>
                        </td>
                        <td>
                            <%-- Add Java code to retrieve and display total quotes --%>
                            <%= specifiedFunctionalitiesDAO.getTotalQuotes()%>
                        </td>
                        <!-- Add more cells if needed -->
                    </tr>
                </tbody>
            </table>



        </div>

    </body>

</html>
