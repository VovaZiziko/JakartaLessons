package org.example.jakartalessons;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet ("/calc")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double num1 = Double.parseDouble(req.getParameter("num1"));
        Double num2 = Double.parseDouble(req.getParameter("num2"));
        String operator = req.getAttribute("operator").toString();
        Double result = switch (operator) {
            case "ADD" -> num1 + num2;
            case "SUBTRACT" -> num1 - num2;
            case "MULTIPLY" -> num1 * num2;
            case "DIVIDE" -> num1 / num2;
            default -> throw new IllegalArgumentException("Unknown operation type");
        };
        String message = "%.2f %s %.2f EQUALS %.2f".formatted(num1, operator, num2, result);
        resp.getWriter().print(message);
    }
}

