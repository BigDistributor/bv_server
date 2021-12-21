package org.mzouink;

import org.mzouink.entities.SmartLink;

public class HistorySmartLink extends SmartLink {
    private final long tsUse;
    private HistorySmartLink(String id, String json) {
        super(id, json);
        this.tsUse = System.currentTimeMillis();
    }

    public HistorySmartLink(SmartLink link){
          this(formatID(link.getTs(),link.getID()),link.getJson());
    }

    private static String formatID(String st1, String st2) {
        return st1+"_"+st2;
    }
}
