# Proxy

<p align="center">
In this OOP Java project, we implemented a proxy to cache database queries.
</p>
<p align="center">
  <a href="#-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-layout">Layout</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
</p>

## 🚀 Technologies

This project was developed with the following technologies:

- Java
- MySQL
- Java Swing

## 💻 Project

This project consists of three main components:

### PC 1 - Client
- A main application that sends a product ID (primary key) to the proxy.
- Queries the database for the product details using the ID.

### PC 2 - Proxy
- Acts as a middleman between the client and the database.
- Utilizes a cache to store recent queries and their results.
- Has a configurable variable to set the cache validation time.
- Accesses PC 3 (Database) if the data is not in the cache or if the cache is expired.
- Prints the source of the data (whether from the cache or database) and the retrieved data to the console.

### PC 3 - Database
- Hosts the MySQL database.
- Stores the product details.

## 🔖 Layout

The layout consists of a simple Java Swing interface:

1. **Client View:**
    - A text field to input the product ID.
    - A button to send the request.
    - An area to display the result (product description and price).

2. **Console Output:**
    - Displays whether the data was fetched from the proxy cache or directly from the database.
    - Shows the details of the query results.

This setup ensures efficient data retrieval and reduces the load on the database by caching frequent queries in the proxy.

Feel free to explore the project and understand how an OOP Java application can integrate with a proxy and a database for optimized data retrieval.