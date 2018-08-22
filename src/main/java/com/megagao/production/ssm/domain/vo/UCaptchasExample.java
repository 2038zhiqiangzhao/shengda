package com.megagao.production.ssm.domain.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UCaptchasExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UCaptchasExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andCaptchasIsNull() {
            addCriterion("captchas is null");
            return (Criteria) this;
        }

        public Criteria andCaptchasIsNotNull() {
            addCriterion("captchas is not null");
            return (Criteria) this;
        }

        public Criteria andCaptchasEqualTo(String value) {
            addCriterion("captchas =", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasNotEqualTo(String value) {
            addCriterion("captchas <>", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasGreaterThan(String value) {
            addCriterion("captchas >", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasGreaterThanOrEqualTo(String value) {
            addCriterion("captchas >=", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasLessThan(String value) {
            addCriterion("captchas <", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasLessThanOrEqualTo(String value) {
            addCriterion("captchas <=", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasLike(String value) {
            addCriterion("captchas like", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasNotLike(String value) {
            addCriterion("captchas not like", value, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasIn(List<String> values) {
            addCriterion("captchas in", values, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasNotIn(List<String> values) {
            addCriterion("captchas not in", values, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasBetween(String value1, String value2) {
            addCriterion("captchas between", value1, value2, "captchas");
            return (Criteria) this;
        }

        public Criteria andCaptchasNotBetween(String value1, String value2) {
            addCriterion("captchas not between", value1, value2, "captchas");
            return (Criteria) this;
        }

        public Criteria andSuccessIsIsNull() {
            addCriterion("success_is is null");
            return (Criteria) this;
        }

        public Criteria andSuccessIsIsNotNull() {
            addCriterion("success_is is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessIsEqualTo(Integer value) {
            addCriterion("success_is =", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsNotEqualTo(Integer value) {
            addCriterion("success_is <>", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsGreaterThan(Integer value) {
            addCriterion("success_is >", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsGreaterThanOrEqualTo(Integer value) {
            addCriterion("success_is >=", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsLessThan(Integer value) {
            addCriterion("success_is <", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsLessThanOrEqualTo(Integer value) {
            addCriterion("success_is <=", value, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsIn(List<Integer> values) {
            addCriterion("success_is in", values, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsNotIn(List<Integer> values) {
            addCriterion("success_is not in", values, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsBetween(Integer value1, Integer value2) {
            addCriterion("success_is between", value1, value2, "successIs");
            return (Criteria) this;
        }

        public Criteria andSuccessIsNotBetween(Integer value1, Integer value2) {
            addCriterion("success_is not between", value1, value2, "successIs");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNull() {
            addCriterion("is_available is null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNotNull() {
            addCriterion("is_available is not null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableEqualTo(Integer value) {
            addCriterion("is_available =", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotEqualTo(Integer value) {
            addCriterion("is_available <>", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThan(Integer value) {
            addCriterion("is_available >", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_available >=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThan(Integer value) {
            addCriterion("is_available <", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("is_available <=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIn(List<Integer> values) {
            addCriterion("is_available in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotIn(List<Integer> values) {
            addCriterion("is_available not in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableBetween(Integer value1, Integer value2) {
            addCriterion("is_available between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_available not between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Integer value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Integer value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Integer value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Integer value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Integer> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Integer> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andVersionNoIsNull() {
            addCriterion("version_no is null");
            return (Criteria) this;
        }

        public Criteria andVersionNoIsNotNull() {
            addCriterion("version_no is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNoEqualTo(Integer value) {
            addCriterion("version_no =", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoNotEqualTo(Integer value) {
            addCriterion("version_no <>", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoGreaterThan(Integer value) {
            addCriterion("version_no >", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_no >=", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoLessThan(Integer value) {
            addCriterion("version_no <", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoLessThanOrEqualTo(Integer value) {
            addCriterion("version_no <=", value, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoIn(List<Integer> values) {
            addCriterion("version_no in", values, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoNotIn(List<Integer> values) {
            addCriterion("version_no not in", values, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoBetween(Integer value1, Integer value2) {
            addCriterion("version_no between", value1, value2, "versionNo");
            return (Criteria) this;
        }

        public Criteria andVersionNoNotBetween(Integer value1, Integer value2) {
            addCriterion("version_no not between", value1, value2, "versionNo");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIsNull() {
            addCriterion("create_userid is null");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIsNotNull() {
            addCriterion("create_userid is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUseridEqualTo(Long value) {
            addCriterion("create_userid =", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotEqualTo(Long value) {
            addCriterion("create_userid <>", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridGreaterThan(Long value) {
            addCriterion("create_userid >", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("create_userid >=", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridLessThan(Long value) {
            addCriterion("create_userid <", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridLessThanOrEqualTo(Long value) {
            addCriterion("create_userid <=", value, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridIn(List<Long> values) {
            addCriterion("create_userid in", values, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotIn(List<Long> values) {
            addCriterion("create_userid not in", values, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridBetween(Long value1, Long value2) {
            addCriterion("create_userid between", value1, value2, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUseridNotBetween(Long value1, Long value2) {
            addCriterion("create_userid not between", value1, value2, "createUserid");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameIsNull() {
            addCriterion("create_username is null");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameIsNotNull() {
            addCriterion("create_username is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameEqualTo(String value) {
            addCriterion("create_username =", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameNotEqualTo(String value) {
            addCriterion("create_username <>", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameGreaterThan(String value) {
            addCriterion("create_username >", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("create_username >=", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameLessThan(String value) {
            addCriterion("create_username <", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameLessThanOrEqualTo(String value) {
            addCriterion("create_username <=", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameLike(String value) {
            addCriterion("create_username like", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameNotLike(String value) {
            addCriterion("create_username not like", value, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameIn(List<String> values) {
            addCriterion("create_username in", values, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameNotIn(List<String> values) {
            addCriterion("create_username not in", values, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameBetween(String value1, String value2) {
            addCriterion("create_username between", value1, value2, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUsernameNotBetween(String value1, String value2) {
            addCriterion("create_username not between", value1, value2, "createUsername");
            return (Criteria) this;
        }

        public Criteria andCreateUseripIsNull() {
            addCriterion("create_userip is null");
            return (Criteria) this;
        }

        public Criteria andCreateUseripIsNotNull() {
            addCriterion("create_userip is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUseripEqualTo(String value) {
            addCriterion("create_userip =", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripNotEqualTo(String value) {
            addCriterion("create_userip <>", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripGreaterThan(String value) {
            addCriterion("create_userip >", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripGreaterThanOrEqualTo(String value) {
            addCriterion("create_userip >=", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripLessThan(String value) {
            addCriterion("create_userip <", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripLessThanOrEqualTo(String value) {
            addCriterion("create_userip <=", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripLike(String value) {
            addCriterion("create_userip like", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripNotLike(String value) {
            addCriterion("create_userip not like", value, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripIn(List<String> values) {
            addCriterion("create_userip in", values, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripNotIn(List<String> values) {
            addCriterion("create_userip not in", values, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripBetween(String value1, String value2) {
            addCriterion("create_userip between", value1, value2, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUseripNotBetween(String value1, String value2) {
            addCriterion("create_userip not between", value1, value2, "createUserip");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacIsNull() {
            addCriterion("create_usermac is null");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacIsNotNull() {
            addCriterion("create_usermac is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacEqualTo(String value) {
            addCriterion("create_usermac =", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacNotEqualTo(String value) {
            addCriterion("create_usermac <>", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacGreaterThan(String value) {
            addCriterion("create_usermac >", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacGreaterThanOrEqualTo(String value) {
            addCriterion("create_usermac >=", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacLessThan(String value) {
            addCriterion("create_usermac <", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacLessThanOrEqualTo(String value) {
            addCriterion("create_usermac <=", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacLike(String value) {
            addCriterion("create_usermac like", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacNotLike(String value) {
            addCriterion("create_usermac not like", value, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacIn(List<String> values) {
            addCriterion("create_usermac in", values, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacNotIn(List<String> values) {
            addCriterion("create_usermac not in", values, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacBetween(String value1, String value2) {
            addCriterion("create_usermac between", value1, value2, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateUsermacNotBetween(String value1, String value2) {
            addCriterion("create_usermac not between", value1, value2, "createUsermac");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbIsNull() {
            addCriterion("create_time_db is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbIsNotNull() {
            addCriterion("create_time_db is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbEqualTo(Date value) {
            addCriterion("create_time_db =", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbNotEqualTo(Date value) {
            addCriterion("create_time_db <>", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbGreaterThan(Date value) {
            addCriterion("create_time_db >", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time_db >=", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbLessThan(Date value) {
            addCriterion("create_time_db <", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbLessThanOrEqualTo(Date value) {
            addCriterion("create_time_db <=", value, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbIn(List<Date> values) {
            addCriterion("create_time_db in", values, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbNotIn(List<Date> values) {
            addCriterion("create_time_db not in", values, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbBetween(Date value1, Date value2) {
            addCriterion("create_time_db between", value1, value2, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andCreateTimeDbNotBetween(Date value1, Date value2) {
            addCriterion("create_time_db not between", value1, value2, "createTimeDb");
            return (Criteria) this;
        }

        public Criteria andServerIpIsNull() {
            addCriterion("server_ip is null");
            return (Criteria) this;
        }

        public Criteria andServerIpIsNotNull() {
            addCriterion("server_ip is not null");
            return (Criteria) this;
        }

        public Criteria andServerIpEqualTo(String value) {
            addCriterion("server_ip =", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotEqualTo(String value) {
            addCriterion("server_ip <>", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThan(String value) {
            addCriterion("server_ip >", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpGreaterThanOrEqualTo(String value) {
            addCriterion("server_ip >=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThan(String value) {
            addCriterion("server_ip <", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLessThanOrEqualTo(String value) {
            addCriterion("server_ip <=", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpLike(String value) {
            addCriterion("server_ip like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotLike(String value) {
            addCriterion("server_ip not like", value, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpIn(List<String> values) {
            addCriterion("server_ip in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotIn(List<String> values) {
            addCriterion("server_ip not in", values, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpBetween(String value1, String value2) {
            addCriterion("server_ip between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andServerIpNotBetween(String value1, String value2) {
            addCriterion("server_ip not between", value1, value2, "serverIp");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridIsNull() {
            addCriterion("update_userid is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridIsNotNull() {
            addCriterion("update_userid is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridEqualTo(Long value) {
            addCriterion("update_userid =", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridNotEqualTo(Long value) {
            addCriterion("update_userid <>", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridGreaterThan(Long value) {
            addCriterion("update_userid >", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("update_userid >=", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridLessThan(Long value) {
            addCriterion("update_userid <", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridLessThanOrEqualTo(Long value) {
            addCriterion("update_userid <=", value, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridIn(List<Long> values) {
            addCriterion("update_userid in", values, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridNotIn(List<Long> values) {
            addCriterion("update_userid not in", values, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridBetween(Long value1, Long value2) {
            addCriterion("update_userid between", value1, value2, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUseridNotBetween(Long value1, Long value2) {
            addCriterion("update_userid not between", value1, value2, "updateUserid");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIsNull() {
            addCriterion("update_username is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIsNotNull() {
            addCriterion("update_username is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameEqualTo(String value) {
            addCriterion("update_username =", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotEqualTo(String value) {
            addCriterion("update_username <>", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameGreaterThan(String value) {
            addCriterion("update_username >", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("update_username >=", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLessThan(String value) {
            addCriterion("update_username <", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLessThanOrEqualTo(String value) {
            addCriterion("update_username <=", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameLike(String value) {
            addCriterion("update_username like", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotLike(String value) {
            addCriterion("update_username not like", value, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameIn(List<String> values) {
            addCriterion("update_username in", values, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotIn(List<String> values) {
            addCriterion("update_username not in", values, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameBetween(String value1, String value2) {
            addCriterion("update_username between", value1, value2, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUsernameNotBetween(String value1, String value2) {
            addCriterion("update_username not between", value1, value2, "updateUsername");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripIsNull() {
            addCriterion("update_userip is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripIsNotNull() {
            addCriterion("update_userip is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripEqualTo(String value) {
            addCriterion("update_userip =", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripNotEqualTo(String value) {
            addCriterion("update_userip <>", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripGreaterThan(String value) {
            addCriterion("update_userip >", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripGreaterThanOrEqualTo(String value) {
            addCriterion("update_userip >=", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripLessThan(String value) {
            addCriterion("update_userip <", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripLessThanOrEqualTo(String value) {
            addCriterion("update_userip <=", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripLike(String value) {
            addCriterion("update_userip like", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripNotLike(String value) {
            addCriterion("update_userip not like", value, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripIn(List<String> values) {
            addCriterion("update_userip in", values, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripNotIn(List<String> values) {
            addCriterion("update_userip not in", values, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripBetween(String value1, String value2) {
            addCriterion("update_userip between", value1, value2, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUseripNotBetween(String value1, String value2) {
            addCriterion("update_userip not between", value1, value2, "updateUserip");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacIsNull() {
            addCriterion("update_usermac is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacIsNotNull() {
            addCriterion("update_usermac is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacEqualTo(String value) {
            addCriterion("update_usermac =", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacNotEqualTo(String value) {
            addCriterion("update_usermac <>", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacGreaterThan(String value) {
            addCriterion("update_usermac >", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacGreaterThanOrEqualTo(String value) {
            addCriterion("update_usermac >=", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacLessThan(String value) {
            addCriterion("update_usermac <", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacLessThanOrEqualTo(String value) {
            addCriterion("update_usermac <=", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacLike(String value) {
            addCriterion("update_usermac like", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacNotLike(String value) {
            addCriterion("update_usermac not like", value, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacIn(List<String> values) {
            addCriterion("update_usermac in", values, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacNotIn(List<String> values) {
            addCriterion("update_usermac not in", values, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacBetween(String value1, String value2) {
            addCriterion("update_usermac between", value1, value2, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateUsermacNotBetween(String value1, String value2) {
            addCriterion("update_usermac not between", value1, value2, "updateUsermac");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbIsNull() {
            addCriterion("update_time_db is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbIsNotNull() {
            addCriterion("update_time_db is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbEqualTo(Date value) {
            addCriterion("update_time_db =", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbNotEqualTo(Date value) {
            addCriterion("update_time_db <>", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbGreaterThan(Date value) {
            addCriterion("update_time_db >", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time_db >=", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbLessThan(Date value) {
            addCriterion("update_time_db <", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbLessThanOrEqualTo(Date value) {
            addCriterion("update_time_db <=", value, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbIn(List<Date> values) {
            addCriterion("update_time_db in", values, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbNotIn(List<Date> values) {
            addCriterion("update_time_db not in", values, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbBetween(Date value1, Date value2) {
            addCriterion("update_time_db between", value1, value2, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeDbNotBetween(Date value1, Date value2) {
            addCriterion("update_time_db not between", value1, value2, "updateTimeDb");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoIsNull() {
            addCriterion("client_versionno is null");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoIsNotNull() {
            addCriterion("client_versionno is not null");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoEqualTo(String value) {
            addCriterion("client_versionno =", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoNotEqualTo(String value) {
            addCriterion("client_versionno <>", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoGreaterThan(String value) {
            addCriterion("client_versionno >", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoGreaterThanOrEqualTo(String value) {
            addCriterion("client_versionno >=", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoLessThan(String value) {
            addCriterion("client_versionno <", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoLessThanOrEqualTo(String value) {
            addCriterion("client_versionno <=", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoLike(String value) {
            addCriterion("client_versionno like", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoNotLike(String value) {
            addCriterion("client_versionno not like", value, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoIn(List<String> values) {
            addCriterion("client_versionno in", values, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoNotIn(List<String> values) {
            addCriterion("client_versionno not in", values, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoBetween(String value1, String value2) {
            addCriterion("client_versionno between", value1, value2, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andClientVersionnoNotBetween(String value1, String value2) {
            addCriterion("client_versionno not between", value1, value2, "clientVersionno");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andSendipIsNull() {
            addCriterion("sendIp is null");
            return (Criteria) this;
        }

        public Criteria andSendipIsNotNull() {
            addCriterion("sendIp is not null");
            return (Criteria) this;
        }

        public Criteria andSendipEqualTo(String value) {
            addCriterion("sendIp =", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipNotEqualTo(String value) {
            addCriterion("sendIp <>", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipGreaterThan(String value) {
            addCriterion("sendIp >", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipGreaterThanOrEqualTo(String value) {
            addCriterion("sendIp >=", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipLessThan(String value) {
            addCriterion("sendIp <", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipLessThanOrEqualTo(String value) {
            addCriterion("sendIp <=", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipLike(String value) {
            addCriterion("sendIp like", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipNotLike(String value) {
            addCriterion("sendIp not like", value, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipIn(List<String> values) {
            addCriterion("sendIp in", values, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipNotIn(List<String> values) {
            addCriterion("sendIp not in", values, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipBetween(String value1, String value2) {
            addCriterion("sendIp between", value1, value2, "sendip");
            return (Criteria) this;
        }

        public Criteria andSendipNotBetween(String value1, String value2) {
            addCriterion("sendIp not between", value1, value2, "sendip");
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