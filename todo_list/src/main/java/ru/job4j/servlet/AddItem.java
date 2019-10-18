package ru.job4j.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Item;
import ru.job4j.persistence.DbStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

@WebServlet("/add")
public class AddItem extends HttpServlet {
    private DbStorage storage = DbStorage.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String desc = new ObjectMapper().readTree(this.getRequestData(req)).get("desc").asText();
        Item add = this.storage.add(new Item(desc, new Date()));
        resp.getWriter().println(new ObjectMapper().writeValueAsString(add));
    }

    /**
     * Returns data from request reader.
     *
     * @param req
     * @return data
     * @throws IOException
     */
    private String getRequestData(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
}
