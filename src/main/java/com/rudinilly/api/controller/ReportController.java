package com.rudinilly.api.controller;

import com.rudinilly.api.dto.NewClientReport;
import com.rudinilly.api.dto.OldestProductsReport;
import com.rudinilly.api.dto.RevenueReportDTO;
import com.rudinilly.api.dto.TopProductDTO;
import com.rudinilly.domain.service.ReportService;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import java.time.LocalDate;
import java.util.List;

@Path("reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GET
    @Path("/new-clients")
    @Operation(summary = "Return clients information based in a year")
    public Response newClients(@NotNull  @Parameter(example = "2026") @QueryParam("year") int year) {
        List<NewClientReport> clients =  reportService.findClientsByYear(year);
        return Response.ok(clients).build();
    }

    @GET
    @Operation(summary = "Return oldest products information")
    @Path("/oldest-products")
    public Response oldest() {
       List<OldestProductsReport> products = reportService.oldest();

       return Response.ok(products).build();
    }

    @GET
    @Operation(summary = "Return top 4 revenue products")
    @Path("/top-products")
    public Response topProducts() {
        List<TopProductDTO> products = reportService.top4ProductsByRevenue();
        return Response.ok(products).build();
    }

    @GET
    @Operation(summary = "Return past 12 monthly revenue information base in a date")
    @Path("/revenue")
    public Response revenue(@NotNull @Parameter(example = "2026-01-01") @QueryParam("date") LocalDate date) {
        RevenueReportDTO report = reportService.revenueLast12Months(date);
        return Response.ok(report).build();
    }
}
