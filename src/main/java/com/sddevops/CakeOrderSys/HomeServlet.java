package com.sddevops.CakeOrderSys;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    

    private List<String> promotionalCakes = Arrays.asList(
        "Chocolate Truffle Cake - 20% off",
        "Vanilla Sponge Cake - Buy 1 Get 1 Free",
        "Red Velvet Cake - 15% off"
    );

    //  image paths for promotional cakes
    private List<String> cakeImages = Arrays.asList(
        "image/truffle_chocolate_cake.png",
        "image/vanilla_sponge_cake.png",
        "image/red_velvet_cake.png"
    );

    // customer reviews
    private List<String> customerReviews = Arrays.asList(
        "John12: The Chocolate Truffle Cake was amazing!",
        "Jady Smith: Loved the Red Velvet Cake, highly recommend!",
        "Alice: The Vanilla Sponge Cake was good, but a bit too sweet for my taste. Love it anyways!"
    );

    private void displayHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<style>");
        out.println("body { background-color: #fdf5e6; font-family: Arial, sans-serif; margin: 0; padding: 0; }");
        out.println(".header { background-color: black; padding: 10px; text-align: center; }");
        out.println(".header a { color: white; margin: 0 20px; text-decoration: none; font-weight: bold; }");
        out.println("h2 { color: #333; }");
        out.println(".promotional-cakes { margin: 20px; padding: 20px; border: none; background-color: transparent; }");
        out.println(".cake-item { padding: 10px; margin: 10px; border: 2px solid black; border-radius: 5px; flex: 1 1 calc(50% - 40px); box-sizing: border-box; }");
        out.println(".cake-item img { display: block; margin: 0 auto 10px; border-radius: 3px; width: 50%; height: auto; }");
        out.println(".cake-item img.red-velvet { width: 30%; }");  // Specific class for Red Velvet Cake
        out.println(".cake-item h3 { text-align: center; margin: 0 0 10px; }");
        out.println(".flex-container { display: flex; flex-wrap: wrap; justify-content: space-around; }");
        out.println(".customer-reviews { margin: 20px; padding: 20px; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("ul { list-style-type: none; padding: 0; }");
        out.println("li { margin-bottom: 10px; }");
        out.println("</style>");
        out.println("</head><body>");

        // Header container with black background
        out.println("<div class='header'>");
        out.println("<a href='" + request.getContextPath() + "/HomeServlet/home'>Home</a>");
        out.println("<a href='" + request.getContextPath() + "/shop.jsp'>Shop</a>");
        out.println("<a href='" + request.getContextPath() + "/contactUs.jsp'>Contact Us</a>");
        out.println("</div>"); // Close header container

        // Promotional Cakes section
        out.println("<div class='promotional-cakes'>");
        out.println("<h2>Promotional Cakes</h2>");
        out.println("<div class='flex-container'>");
        for (int i = 0; i < promotionalCakes.size(); i++) {
            String cakeClass = promotionalCakes.get(i).contains("Red Velvet Cake") ? " class='red-velvet'" : "";
            out.println("<div class='cake-item'>");
            out.println("<h3>" + promotionalCakes.get(i) + "</h3>");
            out.println("<img src='" + request.getContextPath() + "/" + cakeImages.get(i) + "' alt='" + promotionalCakes.get(i) + "'" + cakeClass + ">");
            out.println("</div>");
        }
        out.println("</div>");
        out.println("</div>");
        
        //review customer part
        out.println("<div class='customer-reviews'>");
        out.println("<h2>Customer Reviews</h2>");
        out.println("<ul>");
        for (String review : customerReviews) {
            out.println("<li>" + review + "</li>");
        }
        out.println("</ul>");
        out.println("</div>");

        out.println("</body></html>");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Displaying the home page
        displayHome(request, response);
    }
}
