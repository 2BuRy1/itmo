package data;

public class HtmlDocument extends RequestData {
    private final String html;

    public HtmlDocument(String html) {
        this.html = html;
    }

    public String getHtml() {
        return html;
    }


}
