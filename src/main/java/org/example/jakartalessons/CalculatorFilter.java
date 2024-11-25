package org.example.jakartalessons;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebFilter("/calc")
public class CalculatorFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (isNumber(req.getParameter("num1" )) && isNumber(req.getParameter("num2"))
        && isOperator(req.getParameter("operator"))) {
            req.setAttribute("operator", req.getParameter("operator").toUpperCase());
            chain.doFilter(req, res);
        }else throw new ServletException("Invalid value");
    }

    public boolean isNumber(String str) {
        try {
            double value=Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOperator(String str) {
        String[] operators = {"ADD", "SUBTRACT", "MULTIPLY", "DIVIDE"};
        List<String> listOperators = Arrays.asList(operators);
        return listOperators.contains(str.toUpperCase());
    }
}
