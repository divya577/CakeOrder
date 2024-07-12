import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RemoveCartItemServlet")

public class RemoveCartItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemToRemove = request.getParameter("item");
  
        // Retrieve from the session, update session based on the item selected.
        HttpSession session = request.getSession();

        // Retrieve the cart items from the session
        List<String> cart = (List<String>) session.getAttribute("cart");

        // Check if the cart exists and is not empty
        if (cart != null && !cart.isEmpty()) {

            cart.remove(itemToRemove);


            session.setAttribute("cart", cart);


            response.setStatus(HttpServletResponse.SC_OK);
        } else {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
