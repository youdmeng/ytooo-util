package com.ytooo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class QuartzInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuartzInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBatchTypeIsNull() {
            addCriterion("batch_type is null");
            return (Criteria) this;
        }

        public Criteria andBatchTypeIsNotNull() {
            addCriterion("batch_type is not null");
            return (Criteria) this;
        }

        public Criteria andBatchTypeEqualTo(String value) {
            addCriterion("batch_type =", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeNotEqualTo(String value) {
            addCriterion("batch_type <>", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeGreaterThan(String value) {
            addCriterion("batch_type >", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeGreaterThanOrEqualTo(String value) {
            addCriterion("batch_type >=", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeLessThan(String value) {
            addCriterion("batch_type <", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeLessThanOrEqualTo(String value) {
            addCriterion("batch_type <=", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeLike(String value) {
            addCriterion("batch_type like", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeNotLike(String value) {
            addCriterion("batch_type not like", value, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeIn(List<String> values) {
            addCriterion("batch_type in", values, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeNotIn(List<String> values) {
            addCriterion("batch_type not in", values, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeBetween(String value1, String value2) {
            addCriterion("batch_type between", value1, value2, "batchType");
            return (Criteria) this;
        }

        public Criteria andBatchTypeNotBetween(String value1, String value2) {
            addCriterion("batch_type not between", value1, value2, "batchType");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeIsNull() {
            addCriterion("execute_time is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeIsNotNull() {
            addCriterion("execute_time is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeEqualTo(String value) {
            addCriterion("execute_time =", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotEqualTo(String value) {
            addCriterion("execute_time <>", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeGreaterThan(String value) {
            addCriterion("execute_time >", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("execute_time >=", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeLessThan(String value) {
            addCriterion("execute_time <", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeLessThanOrEqualTo(String value) {
            addCriterion("execute_time <=", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeLike(String value) {
            addCriterion("execute_time like", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotLike(String value) {
            addCriterion("execute_time not like", value, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeIn(List<String> values) {
            addCriterion("execute_time in", values, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotIn(List<String> values) {
            addCriterion("execute_time not in", values, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeBetween(String value1, String value2) {
            addCriterion("execute_time between", value1, value2, "executeTime");
            return (Criteria) this;
        }

        public Criteria andExecuteTimeNotBetween(String value1, String value2) {
            addCriterion("execute_time not between", value1, value2, "executeTime");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNull() {
            addCriterion("object_name is null");
            return (Criteria) this;
        }

        public Criteria andObjectNameIsNotNull() {
            addCriterion("object_name is not null");
            return (Criteria) this;
        }

        public Criteria andObjectNameEqualTo(String value) {
            addCriterion("object_name =", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotEqualTo(String value) {
            addCriterion("object_name <>", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThan(String value) {
            addCriterion("object_name >", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("object_name >=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThan(String value) {
            addCriterion("object_name <", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLessThanOrEqualTo(String value) {
            addCriterion("object_name <=", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameLike(String value) {
            addCriterion("object_name like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotLike(String value) {
            addCriterion("object_name not like", value, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameIn(List<String> values) {
            addCriterion("object_name in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotIn(List<String> values) {
            addCriterion("object_name not in", values, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameBetween(String value1, String value2) {
            addCriterion("object_name between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectNameNotBetween(String value1, String value2) {
            addCriterion("object_name not between", value1, value2, "objectName");
            return (Criteria) this;
        }

        public Criteria andObjectMethodIsNull() {
            addCriterion("object_method is null");
            return (Criteria) this;
        }

        public Criteria andObjectMethodIsNotNull() {
            addCriterion("object_method is not null");
            return (Criteria) this;
        }

        public Criteria andObjectMethodEqualTo(String value) {
            addCriterion("object_method =", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodNotEqualTo(String value) {
            addCriterion("object_method <>", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodGreaterThan(String value) {
            addCriterion("object_method >", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodGreaterThanOrEqualTo(String value) {
            addCriterion("object_method >=", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodLessThan(String value) {
            addCriterion("object_method <", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodLessThanOrEqualTo(String value) {
            addCriterion("object_method <=", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodLike(String value) {
            addCriterion("object_method like", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodNotLike(String value) {
            addCriterion("object_method not like", value, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodIn(List<String> values) {
            addCriterion("object_method in", values, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodNotIn(List<String> values) {
            addCriterion("object_method not in", values, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodBetween(String value1, String value2) {
            addCriterion("object_method between", value1, value2, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andObjectMethodNotBetween(String value1, String value2) {
            addCriterion("object_method not between", value1, value2, "objectMethod");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeIsNull() {
            addCriterion("batch_exe_type is null");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeIsNotNull() {
            addCriterion("batch_exe_type is not null");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeEqualTo(String value) {
            addCriterion("batch_exe_type =", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeNotEqualTo(String value) {
            addCriterion("batch_exe_type <>", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeGreaterThan(String value) {
            addCriterion("batch_exe_type >", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("batch_exe_type >=", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeLessThan(String value) {
            addCriterion("batch_exe_type <", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeLessThanOrEqualTo(String value) {
            addCriterion("batch_exe_type <=", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeLike(String value) {
            addCriterion("batch_exe_type like", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeNotLike(String value) {
            addCriterion("batch_exe_type not like", value, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeIn(List<String> values) {
            addCriterion("batch_exe_type in", values, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeNotIn(List<String> values) {
            addCriterion("batch_exe_type not in", values, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeBetween(String value1, String value2) {
            addCriterion("batch_exe_type between", value1, value2, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchExeTypeNotBetween(String value1, String value2) {
            addCriterion("batch_exe_type not between", value1, value2, "batchExeType");
            return (Criteria) this;
        }

        public Criteria andBatchDescIsNull() {
            addCriterion("batch_desc is null");
            return (Criteria) this;
        }

        public Criteria andBatchDescIsNotNull() {
            addCriterion("batch_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDescEqualTo(String value) {
            addCriterion("batch_desc =", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotEqualTo(String value) {
            addCriterion("batch_desc <>", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescGreaterThan(String value) {
            addCriterion("batch_desc >", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescGreaterThanOrEqualTo(String value) {
            addCriterion("batch_desc >=", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLessThan(String value) {
            addCriterion("batch_desc <", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLessThanOrEqualTo(String value) {
            addCriterion("batch_desc <=", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescLike(String value) {
            addCriterion("batch_desc like", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotLike(String value) {
            addCriterion("batch_desc not like", value, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescIn(List<String> values) {
            addCriterion("batch_desc in", values, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotIn(List<String> values) {
            addCriterion("batch_desc not in", values, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescBetween(String value1, String value2) {
            addCriterion("batch_desc between", value1, value2, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andBatchDescNotBetween(String value1, String value2) {
            addCriterion("batch_desc not between", value1, value2, "batchDesc");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIsNull() {
            addCriterion("thread_pool_size is null");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIsNotNull() {
            addCriterion("thread_pool_size is not null");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeEqualTo(String value) {
            addCriterion("thread_pool_size =", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotEqualTo(String value) {
            addCriterion("thread_pool_size <>", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeGreaterThan(String value) {
            addCriterion("thread_pool_size >", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeGreaterThanOrEqualTo(String value) {
            addCriterion("thread_pool_size >=", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeLessThan(String value) {
            addCriterion("thread_pool_size <", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeLessThanOrEqualTo(String value) {
            addCriterion("thread_pool_size <=", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeLike(String value) {
            addCriterion("thread_pool_size like", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotLike(String value) {
            addCriterion("thread_pool_size not like", value, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeIn(List<String> values) {
            addCriterion("thread_pool_size in", values, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotIn(List<String> values) {
            addCriterion("thread_pool_size not in", values, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeBetween(String value1, String value2) {
            addCriterion("thread_pool_size between", value1, value2, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andThreadPoolSizeNotBetween(String value1, String value2) {
            addCriterion("thread_pool_size not between", value1, value2, "threadPoolSize");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityIsNull() {
            addCriterion("queue_capacity is null");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityIsNotNull() {
            addCriterion("queue_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityEqualTo(String value) {
            addCriterion("queue_capacity =", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityNotEqualTo(String value) {
            addCriterion("queue_capacity <>", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityGreaterThan(String value) {
            addCriterion("queue_capacity >", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityGreaterThanOrEqualTo(String value) {
            addCriterion("queue_capacity >=", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityLessThan(String value) {
            addCriterion("queue_capacity <", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityLessThanOrEqualTo(String value) {
            addCriterion("queue_capacity <=", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityLike(String value) {
            addCriterion("queue_capacity like", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityNotLike(String value) {
            addCriterion("queue_capacity not like", value, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityIn(List<String> values) {
            addCriterion("queue_capacity in", values, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityNotIn(List<String> values) {
            addCriterion("queue_capacity not in", values, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityBetween(String value1, String value2) {
            addCriterion("queue_capacity between", value1, value2, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andQueueCapacityNotBetween(String value1, String value2) {
            addCriterion("queue_capacity not between", value1, value2, "queueCapacity");
            return (Criteria) this;
        }

        public Criteria andSectionSizeIsNull() {
            addCriterion("section_size is null");
            return (Criteria) this;
        }

        public Criteria andSectionSizeIsNotNull() {
            addCriterion("section_size is not null");
            return (Criteria) this;
        }

        public Criteria andSectionSizeEqualTo(String value) {
            addCriterion("section_size =", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeNotEqualTo(String value) {
            addCriterion("section_size <>", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeGreaterThan(String value) {
            addCriterion("section_size >", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeGreaterThanOrEqualTo(String value) {
            addCriterion("section_size >=", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeLessThan(String value) {
            addCriterion("section_size <", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeLessThanOrEqualTo(String value) {
            addCriterion("section_size <=", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeLike(String value) {
            addCriterion("section_size like", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeNotLike(String value) {
            addCriterion("section_size not like", value, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeIn(List<String> values) {
            addCriterion("section_size in", values, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeNotIn(List<String> values) {
            addCriterion("section_size not in", values, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeBetween(String value1, String value2) {
            addCriterion("section_size between", value1, value2, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andSectionSizeNotBetween(String value1, String value2) {
            addCriterion("section_size not between", value1, value2, "sectionSize");
            return (Criteria) this;
        }

        public Criteria andIndexStartIsNull() {
            addCriterion("index_start is null");
            return (Criteria) this;
        }

        public Criteria andIndexStartIsNotNull() {
            addCriterion("index_start is not null");
            return (Criteria) this;
        }

        public Criteria andIndexStartEqualTo(String value) {
            addCriterion("index_start =", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartNotEqualTo(String value) {
            addCriterion("index_start <>", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartGreaterThan(String value) {
            addCriterion("index_start >", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartGreaterThanOrEqualTo(String value) {
            addCriterion("index_start >=", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartLessThan(String value) {
            addCriterion("index_start <", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartLessThanOrEqualTo(String value) {
            addCriterion("index_start <=", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartLike(String value) {
            addCriterion("index_start like", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartNotLike(String value) {
            addCriterion("index_start not like", value, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartIn(List<String> values) {
            addCriterion("index_start in", values, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartNotIn(List<String> values) {
            addCriterion("index_start not in", values, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartBetween(String value1, String value2) {
            addCriterion("index_start between", value1, value2, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexStartNotBetween(String value1, String value2) {
            addCriterion("index_start not between", value1, value2, "indexStart");
            return (Criteria) this;
        }

        public Criteria andIndexEndIsNull() {
            addCriterion("index_end is null");
            return (Criteria) this;
        }

        public Criteria andIndexEndIsNotNull() {
            addCriterion("index_end is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEndEqualTo(String value) {
            addCriterion("index_end =", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndNotEqualTo(String value) {
            addCriterion("index_end <>", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndGreaterThan(String value) {
            addCriterion("index_end >", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndGreaterThanOrEqualTo(String value) {
            addCriterion("index_end >=", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndLessThan(String value) {
            addCriterion("index_end <", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndLessThanOrEqualTo(String value) {
            addCriterion("index_end <=", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndLike(String value) {
            addCriterion("index_end like", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndNotLike(String value) {
            addCriterion("index_end not like", value, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndIn(List<String> values) {
            addCriterion("index_end in", values, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndNotIn(List<String> values) {
            addCriterion("index_end not in", values, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndBetween(String value1, String value2) {
            addCriterion("index_end between", value1, value2, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andIndexEndNotBetween(String value1, String value2) {
            addCriterion("index_end not between", value1, value2, "indexEnd");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("created_date is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("created_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterionForJDBCDate("created_date =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("created_date <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterionForJDBCDate("created_date >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("created_date >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterionForJDBCDate("created_date <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("created_date <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterionForJDBCDate("created_date in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("created_date not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("created_date between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("created_date not between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}