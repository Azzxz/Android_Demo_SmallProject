package web.blognews.bean;


/**
 * @ClassName: BlogBean
 * @Description: 封装Blog对象
 * @author iamxiarui@foxmail.com
 * @date 2016年3月23日 上午10:44:08
 * 
 */
public class BlogBean {
	
	public int id;
	
	public String title;

	public String des;

	public String url;
	
	public String icon;


	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



}
