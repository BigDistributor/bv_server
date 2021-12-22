package org.mzouink;


import org.mzouink.entities.HistorySmartLink;
import org.mzouink.entities.SmartLink;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/link")
public class SmartLinkApi {
    public static Map<String, SmartLink> currentLinks = new HashMap<>();
    public static Map<String, HistorySmartLink> historyLinks = new HashMap<>();

    private final static long BEFORE_TIME = 1000 * 60 * 60; // An hour

    @GET
    @Path("get")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response get(@QueryParam("id") String id) {
        if (currentLinks.containsKey(id)) {
            SmartLink link = currentLinks.get(id);
            historyLinks.put(id, new HistorySmartLink(link));
            currentLinks.remove(id);
            return Response.ok(link.getJson()).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("create")
    public Response create(String json) {

        String id = SmartLink.generateID().toUpperCase();
        while (currentLinks.containsKey(id)) {
            id = SmartLink.generateID();
        }
        SmartLink link = new SmartLink(id, json);
        currentLinks.put(id, link);
        clean();
        return Response.ok(id).build();
    }

    private void clean() {
        long timeFilter = System.currentTimeMillis() - BEFORE_TIME;
        new Thread(() -> {
            for (SmartLink sm : currentLinks.values()) {
                if (Long.parseLong(sm.getTs()) < timeFilter)
                    currentLinks.remove(sm.getID());
            }
        }).start();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("test")
    public Response getAll() {
        return Response.ok(currentLinks.values()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("history")
    public Response getHistory() {
        return Response.ok(historyLinks.values()).build();
    }
}
