package com.marklogic.hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DocumentIdentifier;
import com.marklogic.client.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;

public class KnowledgeBase {
    private DatabaseClient db;

    @Autowired
    public KnowledgeBase(DatabaseClient db) {
        this.db = db;
    }

    public Entry loadEntry(String id) {
        XMLDocumentManager docMgr = this.db.newXMLDocumentManager();
        DocumentIdentifier docId = this.db.newDocId("ASDF.xml");
        DOMHandle handle = new DOMHandle();
        docMgr.read(docId, handle);
        Document document = handle.get();

        return new Entry();
    }

    public List<Entry> findEntries(final String query) {

        /*
         * QueryManager queryMgr = this.db.newQueryManager();
         * StringQueryDefinition queryDef = queryMgr.newStringDefinition(query);
         * SearchHandle results = queryMgr.search(queryDef, new SearchHandle());
         * 
         * List<Entry> list = new ArrayList<Entry>(); for (MatchDocumentSummary
         * docSummary : results.getMatchResults()) { Entry entry = new Entry();
         * entry.setAuthor(docSummary.getUri()); list.add(entry); } return list;
         */
        XMLDocumentManager docMgr = this.db.newXMLDocumentManager();
        DocumentIdentifier docId = this.db.newDocId("ASDF.xml");
        DOMHandle handle = new DOMHandle();
        docMgr.read(docId, handle);
        Document document = handle.get();

        List<Entry> list = new ArrayList<Entry>();
        Entry e = new Entry();
        e.setAuthor(document.getDocumentElement().getLocalName());
        list.add(e);
        return list;

    }

    public void storeEntry(Entry entry) {
        throw new RuntimeException("Not implemented yet");
    }

    public void destroy() {
        this.db.release();
    }

}
