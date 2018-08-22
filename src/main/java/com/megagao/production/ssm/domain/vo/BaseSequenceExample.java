package com.megagao.production.ssm.domain.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseSequenceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseSequenceExample() {
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

        public Criteria andSequenceIdIsNull() {
            addCriterion("sequence_id is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIdIsNotNull() {
            addCriterion("sequence_id is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceIdEqualTo(Integer value) {
            addCriterion("sequence_id =", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdNotEqualTo(Integer value) {
            addCriterion("sequence_id <>", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdGreaterThan(Integer value) {
            addCriterion("sequence_id >", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sequence_id >=", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdLessThan(Integer value) {
            addCriterion("sequence_id <", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdLessThanOrEqualTo(Integer value) {
            addCriterion("sequence_id <=", value, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdIn(List<Integer> values) {
            addCriterion("sequence_id in", values, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdNotIn(List<Integer> values) {
            addCriterion("sequence_id not in", values, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdBetween(Integer value1, Integer value2) {
            addCriterion("sequence_id between", value1, value2, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andSequenceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sequence_id not between", value1, value2, "sequenceId");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andSeqNameIsNull() {
            addCriterion("seq_name is null");
            return (Criteria) this;
        }

        public Criteria andSeqNameIsNotNull() {
            addCriterion("seq_name is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNameEqualTo(String value) {
            addCriterion("seq_name =", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameNotEqualTo(String value) {
            addCriterion("seq_name <>", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameGreaterThan(String value) {
            addCriterion("seq_name >", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameGreaterThanOrEqualTo(String value) {
            addCriterion("seq_name >=", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameLessThan(String value) {
            addCriterion("seq_name <", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameLessThanOrEqualTo(String value) {
            addCriterion("seq_name <=", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameLike(String value) {
            addCriterion("seq_name like", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameNotLike(String value) {
            addCriterion("seq_name not like", value, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameIn(List<String> values) {
            addCriterion("seq_name in", values, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameNotIn(List<String> values) {
            addCriterion("seq_name not in", values, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameBetween(String value1, String value2) {
            addCriterion("seq_name between", value1, value2, "seqName");
            return (Criteria) this;
        }

        public Criteria andSeqNameNotBetween(String value1, String value2) {
            addCriterion("seq_name not between", value1, value2, "seqName");
            return (Criteria) this;
        }

        public Criteria andCurrentValIsNull() {
            addCriterion("current_val is null");
            return (Criteria) this;
        }

        public Criteria andCurrentValIsNotNull() {
            addCriterion("current_val is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentValEqualTo(Integer value) {
            addCriterion("current_val =", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValNotEqualTo(Integer value) {
            addCriterion("current_val <>", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValGreaterThan(Integer value) {
            addCriterion("current_val >", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_val >=", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValLessThan(Integer value) {
            addCriterion("current_val <", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValLessThanOrEqualTo(Integer value) {
            addCriterion("current_val <=", value, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValIn(List<Integer> values) {
            addCriterion("current_val in", values, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValNotIn(List<Integer> values) {
            addCriterion("current_val not in", values, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValBetween(Integer value1, Integer value2) {
            addCriterion("current_val between", value1, value2, "currentVal");
            return (Criteria) this;
        }

        public Criteria andCurrentValNotBetween(Integer value1, Integer value2) {
            addCriterion("current_val not between", value1, value2, "currentVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValIsNull() {
            addCriterion("increment_val is null");
            return (Criteria) this;
        }

        public Criteria andIncrementValIsNotNull() {
            addCriterion("increment_val is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementValEqualTo(Integer value) {
            addCriterion("increment_val =", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValNotEqualTo(Integer value) {
            addCriterion("increment_val <>", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValGreaterThan(Integer value) {
            addCriterion("increment_val >", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValGreaterThanOrEqualTo(Integer value) {
            addCriterion("increment_val >=", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValLessThan(Integer value) {
            addCriterion("increment_val <", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValLessThanOrEqualTo(Integer value) {
            addCriterion("increment_val <=", value, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValIn(List<Integer> values) {
            addCriterion("increment_val in", values, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValNotIn(List<Integer> values) {
            addCriterion("increment_val not in", values, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValBetween(Integer value1, Integer value2) {
            addCriterion("increment_val between", value1, value2, "incrementVal");
            return (Criteria) this;
        }

        public Criteria andIncrementValNotBetween(Integer value1, Integer value2) {
            addCriterion("increment_val not between", value1, value2, "incrementVal");
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