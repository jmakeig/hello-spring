package com.marklogic.hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DocumentIdentifier;
import com.marklogic.client.QueryManager;
import com.marklogic.client.XMLDocumentManager;
import com.marklogic.client.config.MatchDocumentSummary;
import com.marklogic.client.config.StringQueryDefinition;
import com.marklogic.client.impl.StringQueryDefinitionImpl;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.SearchHandle;

public class KnowledgeBase {
    private DatabaseClient db;

    @Autowired
    public KnowledgeBase(DatabaseClient db) {
        this.db = db;
    }

    public Entry loadEntry(String id) {
        return this.loadEntry(this.db.newDocId(id));
    }

    protected Entry loadEntry(DocumentIdentifier id) {
        Document doc = this.db.newXMLDocumentManager().read(id, new DOMHandle()).get();
        return new Entry(doc.getDocumentElement().getTextContent());
    }

    public List<Entry> findEntries(final String query) {

        QueryManager queryMgr = this.db.newQueryManager();
        // null == default options
        StringQueryDefinition queryDef = queryMgr.newStringDefinition(null);
        queryDef.setCriteria(query);
        SearchHandle results = queryMgr.search(queryDef, new SearchHandle());

        List<Entry> list = new ArrayList<Entry>();
        for (MatchDocumentSummary docSummary : results.getMatchResults()) {
            list.add(this.loadEntry(docSummary));
        }
        return list;
    }

    public void storeEntry(Entry entry) {
        throw new RuntimeException("Not implemented yet");
    }

    public void destroy() {
        this.db.release();
    }

}
