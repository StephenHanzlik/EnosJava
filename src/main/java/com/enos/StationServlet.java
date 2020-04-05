package com.enos;

import com.enos.model.Station;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "stationservlet",
        urlPatterns = "/station-data"
)
public class StationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stationLocation = req.getParameter("Type");

        StationService stationService = new StationService();
        Station station = Station.valueOf(stationLocation);

        List stationMetadata = stationService.getStationMetadata(station);

        req.setAttribute("stationMetadata", stationMetadata);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }

}
