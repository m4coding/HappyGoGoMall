package com.m4coding.mallforeground.mbg.model;

import java.util.ArrayList;
import java.util.List;

public class UserAuthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserAuthExample() {
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

        public Criteria andAuthIdIsNull() {
            addCriterion("auth_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthIdIsNotNull() {
            addCriterion("auth_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthIdEqualTo(Integer value) {
            addCriterion("auth_id =", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotEqualTo(Integer value) {
            addCriterion("auth_id <>", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThan(Integer value) {
            addCriterion("auth_id >", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auth_id >=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThan(Integer value) {
            addCriterion("auth_id <", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdLessThanOrEqualTo(Integer value) {
            addCriterion("auth_id <=", value, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdIn(List<Integer> values) {
            addCriterion("auth_id in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotIn(List<Integer> values) {
            addCriterion("auth_id not in", values, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdBetween(Integer value1, Integer value2) {
            addCriterion("auth_id between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andAuthIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auth_id not between", value1, value2, "authId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeIsNull() {
            addCriterion("indentity_type is null");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeIsNotNull() {
            addCriterion("indentity_type is not null");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeEqualTo(String value) {
            addCriterion("indentity_type =", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeNotEqualTo(String value) {
            addCriterion("indentity_type <>", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeGreaterThan(String value) {
            addCriterion("indentity_type >", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeGreaterThanOrEqualTo(String value) {
            addCriterion("indentity_type >=", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeLessThan(String value) {
            addCriterion("indentity_type <", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeLessThanOrEqualTo(String value) {
            addCriterion("indentity_type <=", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeLike(String value) {
            addCriterion("indentity_type like", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeNotLike(String value) {
            addCriterion("indentity_type not like", value, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeIn(List<String> values) {
            addCriterion("indentity_type in", values, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeNotIn(List<String> values) {
            addCriterion("indentity_type not in", values, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeBetween(String value1, String value2) {
            addCriterion("indentity_type between", value1, value2, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityTypeNotBetween(String value1, String value2) {
            addCriterion("indentity_type not between", value1, value2, "indentityType");
            return (Criteria) this;
        }

        public Criteria andIndentityIsNull() {
            addCriterion("indentity is null");
            return (Criteria) this;
        }

        public Criteria andIndentityIsNotNull() {
            addCriterion("indentity is not null");
            return (Criteria) this;
        }

        public Criteria andIndentityEqualTo(String value) {
            addCriterion("indentity =", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityNotEqualTo(String value) {
            addCriterion("indentity <>", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityGreaterThan(String value) {
            addCriterion("indentity >", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityGreaterThanOrEqualTo(String value) {
            addCriterion("indentity >=", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityLessThan(String value) {
            addCriterion("indentity <", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityLessThanOrEqualTo(String value) {
            addCriterion("indentity <=", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityLike(String value) {
            addCriterion("indentity like", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityNotLike(String value) {
            addCriterion("indentity not like", value, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityIn(List<String> values) {
            addCriterion("indentity in", values, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityNotIn(List<String> values) {
            addCriterion("indentity not in", values, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityBetween(String value1, String value2) {
            addCriterion("indentity between", value1, value2, "indentity");
            return (Criteria) this;
        }

        public Criteria andIndentityNotBetween(String value1, String value2) {
            addCriterion("indentity not between", value1, value2, "indentity");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNull() {
            addCriterion("certificate is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNotNull() {
            addCriterion("certificate is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateEqualTo(String value) {
            addCriterion("certificate =", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotEqualTo(String value) {
            addCriterion("certificate <>", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThan(String value) {
            addCriterion("certificate >", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("certificate >=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThan(String value) {
            addCriterion("certificate <", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThanOrEqualTo(String value) {
            addCriterion("certificate <=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLike(String value) {
            addCriterion("certificate like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotLike(String value) {
            addCriterion("certificate not like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateIn(List<String> values) {
            addCriterion("certificate in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotIn(List<String> values) {
            addCriterion("certificate not in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateBetween(String value1, String value2) {
            addCriterion("certificate between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotBetween(String value1, String value2) {
            addCriterion("certificate not between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andIfVerifyIsNull() {
            addCriterion("if_verify is null");
            return (Criteria) this;
        }

        public Criteria andIfVerifyIsNotNull() {
            addCriterion("if_verify is not null");
            return (Criteria) this;
        }

        public Criteria andIfVerifyEqualTo(Boolean value) {
            addCriterion("if_verify =", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyNotEqualTo(Boolean value) {
            addCriterion("if_verify <>", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyGreaterThan(Boolean value) {
            addCriterion("if_verify >", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("if_verify >=", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyLessThan(Boolean value) {
            addCriterion("if_verify <", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyLessThanOrEqualTo(Boolean value) {
            addCriterion("if_verify <=", value, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyIn(List<Boolean> values) {
            addCriterion("if_verify in", values, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyNotIn(List<Boolean> values) {
            addCriterion("if_verify not in", values, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyBetween(Boolean value1, Boolean value2) {
            addCriterion("if_verify between", value1, value2, "ifVerify");
            return (Criteria) this;
        }

        public Criteria andIfVerifyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("if_verify not between", value1, value2, "ifVerify");
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