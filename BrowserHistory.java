public class BrowserHistory {
    static class BhNode {
        String data;
        BhNode next;
        BhNode back;

        public BhNode(String homePage) {
            this.data = homePage;
            this.next = null;
            this.back = null;
        }
    }

    BhNode currPage;

    public BrowserHistory(String homePage) {
        currPage = new BhNode(homePage);
    }

    // Visit a new page
    public void visit(String url) {
        BhNode newNode = new BhNode(url);
        currPage.next = newNode;
        newNode.back = currPage;
        currPage = newNode; // ✅ move to new page
    }

    // Go forward
    public String forward(int steps) {
        while (steps > 0 && currPage.next != null) {
            currPage = currPage.next;
            steps--;
        }
        return currPage.data;
    }

    // Go backward
    public String backward(int steps) {
        while (steps > 0 && currPage.back != null) {
            currPage = currPage.back;
            steps--;
        }
        return currPage.data;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("google.com");
        browser.visit("youtube.com");
        browser.visit("facebook.com");
        browser.visit("leetcode.com");

        System.out.println(browser.backward(1)); // facebook.com
        System.out.println(browser.backward(1)); // youtube.com
        System.out.println(browser.forward(1));  // facebook.com
        browser.visit("github.com");
        System.out.println(browser.forward(2));  // github.com (can't forward more)
    }
}
