
package Model;
import java.util.List;

public class PageResult {

    private int currentPageNum;
    private List<Data> data;
    private boolean nextpage;
    private int pageSize;
    private boolean prepage;
    private int startPosition;
    private int totalItemNum;
    private int totalPageNum;
    public void setCurrentPageNum(int currentPageNum) {
         this.currentPageNum = currentPageNum;
     }
     public int getCurrentPageNum() {
         return currentPageNum;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setNextpage(boolean nextpage) {
         this.nextpage = nextpage;
     }
     public boolean getNextpage() {
         return nextpage;
     }

    public void setPageSize(int pageSize) {
         this.pageSize = pageSize;
     }
     public int getPageSize() {
         return pageSize;
     }

    public void setPrepage(boolean prepage) {
         this.prepage = prepage;
     }
     public boolean getPrepage() {
         return prepage;
     }

    public void setStartPosition(int startPosition) {
         this.startPosition = startPosition;
     }
     public int getStartPosition() {
         return startPosition;
     }

    public void setTotalItemNum(int totalItemNum) {
         this.totalItemNum = totalItemNum;
     }
     public int getTotalItemNum() {
         return totalItemNum;
     }

    public void setTotalPageNum(int totalPageNum) {
         this.totalPageNum = totalPageNum;
     }
     public int getTotalPageNum() {
         return totalPageNum;
     }

}