package ml.ytooo.adc.page;

public class BasePage {
  private Integer page = Integer.valueOf(1);

  private Integer pageSize = Integer.valueOf(20);

  private Integer startIndex;

  private Integer endIndex;

  private String orderBy;

  private String order;

  private String q;

  private String sql_filter;

  private Pager pager = new Pager();

  public Pager getPager() {
    this.pager.setPageId(getPage().intValue());
    this.pager.setPageSize(getPageSize().intValue());
    String orderField = "";
    if (this.orderBy != null && this.orderBy.trim().length() > 0)
      orderField = this.orderBy;
    if (orderField.trim().length() > 0 && this.order != null && this.order.trim().length() > 0)
      orderField = orderField + " " + this.order;
    this.pager.setOrderField(orderField);
    return this.pager;
  }

  public void setPager(Pager pager) {
    this.pager = pager;
  }

  public Integer getPage() {
    return this.page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getOrderBy() {
    return this.orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrder() {
    return this.order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public String getQ() {
    return this.q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public Integer getStartIndex() {
    return this.startIndex;
  }

  public void setStartIndex(Integer startIndex) {
    this.startIndex = Integer.valueOf((this.page.intValue() - 1) * this.pageSize.intValue() + 1);
  }

  public Integer getEndIndex() {
    return this.endIndex;
  }

  public void setEndIndex(Integer endIndex) {
    this.endIndex = Integer.valueOf(this.page.intValue() * this.pageSize.intValue());
  }

  public String getSql_filter() {
    return this.sql_filter;
  }

  public void setSql_filter(String sql_filter) {
    this.sql_filter = sql_filter;
  }
}
