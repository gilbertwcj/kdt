package com.gilbert.kdt;

public interface GuiDriver {

	void open(String url);

	void quit();

	void setText(String path, String text);

	void select(String path, int index);

	void select(String path, String text);

	void click(String path);

	String getSelectedLabel(String path);

	String getSelectedValue(String path);

	String getValue(String path);

	String getText(String path);

	boolean isVisible(String path);

	boolean isChecked(String path);

	boolean isEditable(String path);

}
