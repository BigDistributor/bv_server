package org.mzouink.entities;


import org.mzouink.HistorySmartLink;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/link")
public class SmartLinkApi {
    public static Map<String, SmartLink> currentLinks = new HashMap<>();
    public static Map<String, HistorySmartLink> historyLinks = new HashMap<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("get")
    public Response get(String id){
        if(currentLinks.containsKey(id)){
            SmartLink link = currentLinks.get(id);
        historyLinks.put(id,new HistorySmartLink(link));
        currentLinks.remove(id);
        return Response.ok(link.getID()).build();
        }
        else{
            //TODO change by error
            return Response.ok().build();
        }
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("create")
    public Response create(String json){
        String id = SmartLink.generateID();
        while(currentLinks.containsKey(id)){
            id = SmartLink.generateID();
        }
        SmartLink link  = new SmartLink(id,json);
        currentLinks.put(id,link);
        return Response.ok(id).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("test")
    public Response getAll(){
        return Response.ok(currentLinks.values()).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("history")
    public Response getHistory(){
        return Response.ok(historyLinks.values()).build();
    }
}
