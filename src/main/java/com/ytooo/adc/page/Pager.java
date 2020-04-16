package com.ytooo.adc.page;

public class Pager {
  private int pageId = 1;

  private int rowCount = 0;

  private int pageSize = 10;

  private int pageCount = 0;

  private int pageOffset = 0;

  private int pageTail = 0;

  private String orderField;

  private boolean orderDirection;

  private boolean pageEnabled;

  private int length = 6;

  private int startIndex = 0;

  private int endIndex = 0;

  private int[] indexs;

  public Pager() {
    this.orderDirection = true;
    this.pageEnabled = true;
  }

  public int getLength() {
    return this.length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int[] getIndexs() {
    int len = getEndIndex() - getStartIndex() + 1;
    this.indexs = new int[len];
    for (int i = 0; i < len; i++)
      this.indexs[i] = getStartIndex() + i;
    return this.indexs;
  }

  public void setIndexs(int[] indexs) {
    this.indexs = indexs;
  }

  public int getStartIndex() {
    this.startIndex = (this.pageId - 1) * this.pageSize + 1;
    return this.startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = (this.pageId - 1) * this.pageSize + 1;
  }

  public int getEndIndex() {
    this.endIndex = this.pageId * this.pageSize;
    return this.endIndex;
  }

  public void setEndIndex(int endIndex) {
    this.endIndex = this.pageId * this.pageSize;
  }

  private void doPage() {
    this.pageCount = this.rowCount / this.pageSize + 1;
    if (this.rowCount % this.pageSize == 0 && this.pageCount > 1)
      this.pageCount--;
    this.pageOffset = (this.pageId - 1) * this.pageSize;
    this.pageTail = this.pageOffset + this.pageSize;
    if (this.pageOffset + this.pageSize > this.rowCount)
      this.pageTail = this.rowCount;
  }

  public String getOrderCondition() {
    String condition = "";
    if (this.orderField != null && this.orderField.length() != 0)
      condition = " order by " + this.orderField + (this.orderDirection ? " " : " desc ");
    return condition;
  }

  public String getMysqlQueryCondition() {
    return "";
  }

  public void setOrderDirection(boolean orderDirection) {
    this.orderDirection = orderDirection;
  }

  public boolean isOrderDirection() {
    return this.orderDirection;
  }

  public void setOrderField(String orderField) {
    this.orderField = orderField;
  }

  public String getOrderField() {
    return this.orderField;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public int getPageCount() {
    return this.pageCount;
  }

  public void setPageId(int pageId) {
    this.pageId = pageId;
  }

  public int getPageId() {
    return this.pageId;
  }

  public void setPageOffset(int pageOffset) {
    this.pageOffset = pageOffset;
  }

  public int getPageOffset() {
    return this.pageOffset;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageTail(int pageTail) {
    this.pageTail = pageTail;
  }

  public int getPageTail() {
    return this.pageTail;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
    doPage();
  }

  public int getRowCount() {
    return this.rowCount;
  }

  public boolean isPageEnabled() {
    return this.pageEnabled;
  }

  public void setPageEnabled(boolean pageEnabled) {
    this.pageEnabled = pageEnabled;
  }
}
