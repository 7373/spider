
package Model;
import java.util.Date;

/**
 * Auto-generated: 2017-12-19 16:43:34
 */
public class Data {

    private int beian;
    private int book_status;
    private int domain_len;
    private String domain_name;
    private String end_date;
    private int price;
    private String reg_date;
    private String short_name;
    
    
    /**
	 * @return the reg_date
	 */
	public String getReg_date() {
		return reg_date;
	}
	/**
	 * @param reg_date the reg_date to set
	 */
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public void setBeian(int beian) {
         this.beian = beian;
     }
     public int getBeian() {
         return beian;
     }

    public void setBook_status(int book_status) {
         this.book_status = book_status;
     }
     public int getBook_status() {
         return book_status;
     }

    public void setDomain_len(int domain_len) {
         this.domain_len = domain_len;
     }
     public int getDomain_len() {
         return domain_len;
     }

    public void setDomain_name(String domain_name) {
         this.domain_name = domain_name;
     }
     public String getDomain_name() {
         return domain_name;
     }

    public void setEnd_date(String end_date) {
         this.end_date = end_date;
     }
     public String getEnd_date() {
         return end_date;
     }

    public void setPrice(int price) {
         this.price = price;
     }
     public int getPrice() {
         return price;
     }

    public void setShort_name(String short_name) {
         this.short_name = short_name;
     }
     public String getShort_name() {
         return short_name;
     }

}