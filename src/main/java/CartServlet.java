import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
//testing
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> cart = (List<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        String item = request.getParameter("item");
        if (item != null && !item.isEmpty()) {
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
}
