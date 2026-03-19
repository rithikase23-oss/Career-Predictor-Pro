package com.careerpredictor;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.JSONObject;

@WebServlet("/CareerServlet")
public class CareerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read JSON from front-end
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        JSONObject data = new JSONObject(sb.toString());
        String studentType = data.getString("studentType");
        JSONObject answers = data.getJSONObject("answers");

        JSONObject result = new JSONObject();
        // Career prediction logic
        if (studentType.equalsIgnoreCase("school")) {
            String combined = "";
            for (String key : answers.keySet())
                combined += answers.getString(key).toLowerCase();

            if (combined.contains("biology") || combined.contains("doctor") || combined.contains("medicine"))
                result.put("careers", new String[] { "Doctor" });
            else if (combined.contains("law"))
                result.put("careers", new String[] { "Lawyer" });
            else if (combined.contains("journalism") || combined.contains("writing"))
                result.put("careers", new String[] { "Journalist" });
            else if (combined.contains("pilot"))
                result.put("careers", new String[] { "Pilot" });
            else if (combined.contains("math") || combined.contains("physics"))
                result.put("careers", new String[] { "Engineer" });
            else if (combined.contains("design") || combined.contains("art"))
                result.put("careers", new String[] { "Graphic Designer" });
            else if (combined.contains("business"))
                result.put("careers", new String[] { "Business Analyst" });
            else
                result.put("careers", new String[] { "Software Developer" });

        } else { // college
            String combined = "";
            for (String key : answers.keySet())
                combined += answers.getString(key).toLowerCase();

            if (combined.contains("ai") || combined.contains("machine"))
                result.put("careers", new String[] { "AI Engineer" });
            else if (combined.contains("data"))
                result.put("careers", new String[] { "Data Scientist" });
            else if (combined.contains("security"))
                result.put("careers", new String[] { "Cybersecurity Analyst" });
            else if (combined.contains("management") || combined.contains("manager"))
                result.put("careers", new String[] { "Product Manager" });
            else
                result.put("careers", new String[] { "Software Developer" });
        }

        // Add random readiness score
        int score = 70 + (int) (Math.random() * 31);
        result.put("score", score);

        response.setContentType("application/json");
        response.getWriter().write(result.toString());
    }
}