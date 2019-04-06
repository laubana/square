package project.ppaya.square.vo;

public class GroupCategory {
	int group_category_id;
	String name;
	public GroupCategory() {
		super();
	}
	
	public GroupCategory(int group_category_id) {
		super();
		this.group_category_id = group_category_id;
	}
	
	public GroupCategory(String name) {
		super();
		this.name = name;
	}

	public GroupCategory(int group_category_id, String name) {
		super();
		this.group_category_id = group_category_id;
		this.name = name;
	}

	public int getGroup_category_id() {
		return group_category_id;
	}

	public void setGroup_category_id(int group_category_id) {
		this.group_category_id = group_category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
